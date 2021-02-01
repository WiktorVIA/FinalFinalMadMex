package com.example.finalfinalmadmex.Repository;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.finalfinalmadmex.models.FoodModel;

import java.util.ArrayList;
import java.util.List;

public class FoodListRepo {

    private MutableLiveData<List<FoodModel>> mutableFoodList;

    public LiveData<List<FoodModel>> getFoods()
    {
        if(mutableFoodList == null)
        {
            mutableFoodList = new MutableLiveData<>();
            loadFoods();
        }
        return mutableFoodList;
    }

    private void loadFoods()
    {
        List<FoodModel> foodModelList = new ArrayList<>();

        foodModelList.add(new FoodModel("Beef Burrito", "Delicious Burrito", "Real", 125, "https://firebasestorage.googleapis.com/v0/b/madmex-e8b0b.appspot.com/o/image1280663296?alt=media&token=000b1292-1d49-4454-9835-7194db26c0fb"));
        foodModelList.add(new FoodModel("Beef Burrito", "Delicious Burrito", "Real", 125, "https://firebasestorage.googleapis.com/v0/b/madmex-e8b0b.appspot.com/o/image1280663296?alt=media&token=000b1292-1d49-4454-9835-7194db26c0fb"));
        foodModelList.add(new FoodModel("Beef Burrito", "Delicious Burrito", "Real", 125, "https://firebasestorage.googleapis.com/v0/b/madmex-e8b0b.appspot.com/o/image1280663296?alt=media&token=000b1292-1d49-4454-9835-7194db26c0fb"));
        foodModelList.add(new FoodModel("Beef Burrito", "Delicious Burrito", "Real", 125, "https://firebasestorage.googleapis.com/v0/b/madmex-e8b0b.appspot.com/o/image1280663296?alt=media&token=000b1292-1d49-4454-9835-7194db26c0fb"));
        foodModelList.add(new FoodModel("Beef Burrito", "Delicious Burrito", "Real", 125, "https://firebasestorage.googleapis.com/v0/b/madmex-e8b0b.appspot.com/o/image1280663296?alt=media&token=000b1292-1d49-4454-9835-7194db26c0fb"));
        foodModelList.add(new FoodModel("Beef Burrito", "Delicious Burrito", "Real", 125, "https://firebasestorage.googleapis.com/v0/b/madmex-e8b0b.appspot.com/o/image1280663296?alt=media&token=000b1292-1d49-4454-9835-7194db26c0fb"));


        mutableFoodList.setValue(foodModelList);
    }
}
