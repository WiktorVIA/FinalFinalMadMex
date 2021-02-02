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
        foodModelList.add(new FoodModel("Chicken Burrito", "I'm not Mexican, so I won't even attempt to describe this delicious dish, because I will just undervalue the greatness of it.", "Real", 125, "https://firebasestorage.googleapis.com/v0/b/madmex-e8b0b.appspot.com/o/image1280663296?alt=media&token=000b1292-1d49-4454-9835-7194db26c0fb"));
        foodModelList.add(new FoodModel("Chicken Fajitas", "I'm not Mexican, so I won't even attempt to describe this delicious dish, because I will just undervalue the greatness of it.", "Real", 100, "https://firebasestorage.googleapis.com/v0/b/madmex-e8b0b.appspot.com/o/chicken_fajitas.jpeg?alt=media&token=cb0ba54a-dbf9-4596-be97-1f2199e3905c"));
        foodModelList.add(new FoodModel("Beef Tacos", "I'm not Mexican, so I won't even attempt to describe this delicious dish, because I will just undervalue the greatness of it.", "Real", 100, "https://firebasestorage.googleapis.com/v0/b/madmex-e8b0b.appspot.com/o/beef_tacos.jpg?alt=media&token=80c27db0-aa3e-4801-bf11-28f1a76e9f5c"));
        foodModelList.add(new FoodModel("Beef Chili", "I'm not Mexican, so I won't even attempt to describe this delicious dish, because I will just undervalue the greatness of it.", "Real", 100, "https://firebasestorage.googleapis.com/v0/b/madmex-e8b0b.appspot.com/o/beef_chili.jpg?alt=media&token=aa9557e6-671b-47ca-ad01-c6dc7514d850"));
        foodModelList.add(new FoodModel("Chicken Tacos", "I'm not Mexican, so I won't even attempt to describe this delicious dish, because I will just undervalue the greatness of it.", "Real", 100, "https://firebasestorage.googleapis.com/v0/b/madmex-e8b0b.appspot.com/o/chicken_tacos.jpg?alt=media&token=7e46043a-8dab-4cda-9f75-89cb93b8a97c"));
        foodModelList.add(new FoodModel("Chili Con Carne", "I'm not Mexican, so I won't even attempt to describe this delicious dish, because I will just undervalue the greatness of it.", "Real", 125, "https://firebasestorage.googleapis.com/v0/b/madmex-e8b0b.appspot.com/o/chili_con_carne.jpg?alt=media&token=72ce2a19-aac5-4feb-bd29-a7d9fe987895"));
        foodModelList.add(new FoodModel("Fajitas", "I'm not Mexican, so I won't even attempt to describe this delicious dish, because I will just undervalue the greatness of it.", "Vegan", 100, "https://firebasestorage.googleapis.com/v0/b/madmex-e8b0b.appspot.com/o/vegan_fajitas.jpg?alt=media&token=f78239eb-4dad-4e44-a4bb-6f08def5d19b"));
        foodModelList.add(new FoodModel("Tacos", "I'm not Mexican, so I won't even attempt to describe this delicious dish, because I will just undervalue the greatness of it.", "Vegan", 100, "https://firebasestorage.googleapis.com/v0/b/madmex-e8b0b.appspot.com/o/vegan_tacos.jpg?alt=media&token=cc5847f5-0405-467a-958f-4695f2d548d6"));
        foodModelList.add(new FoodModel("Nachos", "I'm not Mexican, so I won't even attempt to describe this delicious dish, because I will just undervalue the greatness of it.", "Vegan", 75, "https://firebasestorage.googleapis.com/v0/b/madmex-e8b0b.appspot.com/o/vegan_nachos.jpg?alt=media&token=a12f2cc4-9abe-434e-b8ee-e54b0e872e98"));
        foodModelList.add(new FoodModel("Cajeta Crepes", "I'm not Mexican, so I won't even attempt to describe this delicious dish, because I will just undervalue the greatness of it.", "Dessert", 100, "https://firebasestorage.googleapis.com/v0/b/madmex-e8b0b.appspot.com/o/cajeta_crepes.jpg?alt=media&token=2da42478-7b81-4a13-b696-c6f124d1edee"));
        foodModelList.add(new FoodModel("Churros", "I'm not Mexican, so I won't even attempt to describe this delicious dish, because I will just undervalue the greatness of it.", "Dessert", 75, "https://firebasestorage.googleapis.com/v0/b/madmex-e8b0b.appspot.com/o/churros.jpg?alt=media&token=0b6d89c1-3fb2-479e-a4ff-b9bb3fe46565"));
        foodModelList.add(new FoodModel("Chocolate Taco", "I'm not Mexican, so I won't even attempt to describe this delicious dish, because I will just undervalue the greatness of it.", "Dessert", 75, "https://firebasestorage.googleapis.com/v0/b/madmex-e8b0b.appspot.com/o/chocolate_taco.jpg?alt=media&token=e7583527-a0fb-443a-b7ae-dbd5404d623c"));


        mutableFoodList.setValue(foodModelList);
    }
}
