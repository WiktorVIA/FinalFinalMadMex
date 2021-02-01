package com.example.finalfinalmadmex.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.finalfinalmadmex.Repository.BasketRepo;
import com.example.finalfinalmadmex.models.BasketFood;
import com.example.finalfinalmadmex.models.FoodModel;
import com.example.finalfinalmadmex.Repository.FoodListRepo;

import java.util.List;

public class FoodListViewModel extends ViewModel {

    FoodListRepo foodlistRepo = new FoodListRepo();
    BasketRepo basketRepo = new BasketRepo();


    MutableLiveData<FoodModel> mutableFoodModel = new MutableLiveData<>();

    public LiveData<List<FoodModel>> getFoods() {
        return foodlistRepo.getFoods();
    }

    public void setFood(FoodModel food) {
        mutableFoodModel.setValue(food);
    }

    public LiveData<FoodModel> getFoodModel() {
        return mutableFoodModel;
    }

    public LiveData<List<BasketFood>> getBasket() {
        return basketRepo.getBasket();
    }

    public boolean addFoodToBasket(FoodModel food) {
        return basketRepo.addFoodToBasket(food);

    }

    public void removeFoodFromBasket(BasketFood basketFood) {
        basketRepo.removeFoodFromBasket(basketFood);
    }

    public void changeQuantity(BasketFood basketFood, int quantity) {
        basketRepo.changeQuantity(basketFood, quantity);
    }

    public LiveData<Integer> getTotalPrice() {
        return basketRepo.getTotalPrice();

    }

    public void resetBasket()
    {
        basketRepo.initCart();
    }

}

