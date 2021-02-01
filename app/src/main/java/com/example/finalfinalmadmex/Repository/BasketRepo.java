package com.example.finalfinalmadmex.Repository;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.finalfinalmadmex.models.BasketFood;
import com.example.finalfinalmadmex.models.FoodModel;

import java.util.ArrayList;
import java.util.List;

public class BasketRepo {

    private MutableLiveData<List<BasketFood>> mutableBasket = new MutableLiveData<>();
    private MutableLiveData<Integer> mutableTotalPrice = new MutableLiveData<>();

    public LiveData<List<BasketFood>> getBasket()
    {
        if (mutableBasket.getValue()==null)
        {
            initCart();
        }
        return mutableBasket;
    }

    public void initCart()
    {
        mutableBasket.setValue(new ArrayList<BasketFood>());
        caluclateBasketTotal();
    }

    public boolean addFoodToBasket(FoodModel food)
    {
        if (mutableBasket.getValue() == null)
        {
            initCart();
        }
        List <BasketFood> basketFoodList= new ArrayList<>(mutableBasket.getValue());
        for(BasketFood basketFood: basketFoodList)
        {
            if(basketFood.getFood().getName().equals(food.getName()))
            {
                if (basketFood.getQuantity()==10)
                {
                    return false;
                }
                else
                {
                    int index = basketFoodList.indexOf(basketFood);
                    basketFood.setQuantity(basketFood.getQuantity()+1);
                    basketFoodList.set(index, basketFood);

                    mutableBasket.setValue(basketFoodList);
                    caluclateBasketTotal();

                    return true;
                }
            }
        }
        BasketFood basketFood = new BasketFood(food, 1);
        basketFoodList.add(basketFood);
        mutableBasket.setValue(basketFoodList);
        caluclateBasketTotal();
        return true;

    }

    public void removeFoodFromBasket(BasketFood basketFood) {
        if (mutableBasket.getValue() == null) {
            return;
        }
        List<BasketFood> basketFoodList = new ArrayList<>(mutableBasket.getValue());
        basketFoodList.remove(basketFood);
        mutableBasket.setValue(basketFoodList);
        caluclateBasketTotal();
    }

    public void changeQuantity (BasketFood basketFood, int quantity)
    {
        if (mutableBasket.getValue() == null) return;

        List <BasketFood> basketFoodList = new ArrayList<>(mutableBasket.getValue());

        BasketFood updatedFood = new BasketFood(basketFood.getFood(),quantity);
        basketFoodList.set(basketFoodList.indexOf(basketFood), updatedFood);

        mutableBasket.setValue(basketFoodList);
        caluclateBasketTotal();
    }

    private void caluclateBasketTotal()
    {
        if(mutableBasket.getValue() == null) return;
        int total = 0;
        List<BasketFood> basketFoodList = mutableBasket.getValue();
        for (BasketFood basketFood: basketFoodList)
        {
            total += basketFood.getFood().getPrice() * basketFood.getQuantity();
        }
        mutableTotalPrice.setValue(total);
    }

    public LiveData<Integer> getTotalPrice()
    {
        if(mutableTotalPrice.getValue()== null)
        {
            mutableTotalPrice.setValue(0);
        }
        return mutableTotalPrice;

    }

}
