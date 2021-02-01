package com.example.finalfinalmadmex.models;

import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.databinding.BindingAdapter;
import androidx.recyclerview.widget.DiffUtil;

import com.bumptech.glide.Glide;

public class FoodModel {

    String name;
    String description;
    String type;
    Integer price;
    String photoURL;

    FoodModel()
    {

    }

    public FoodModel(String name, String description, String type, Integer price, String photoURL) {
        this.name = name;
        this.description = description;
        this.type = type;
        this.price = price;
        this.photoURL = photoURL;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getPrice() { return price; }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public String getPhotoURL() { return photoURL; }

    public void setPhotoURL(String photoURL) { this.photoURL = photoURL; }

    @Override
    public String toString() {
        return "FoodModel{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", type='" + type + '\'' +
                ", price='" + price + '\'' +
                ", photoURL='" + photoURL + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FoodModel foodModel = (FoodModel) o;
        return Integer.compare(foodModel.getPrice(),getPrice()) == 0 &&
                getName().equals(foodModel.getName()) &&
                getDescription().equals(foodModel.getDescription()) &&
                getType().equals(foodModel.getType()) &&
                getPhotoURL().equals(foodModel.getPhotoURL());
    }


    public static DiffUtil.ItemCallback<FoodModel>itemCallback = new DiffUtil.ItemCallback<FoodModel>() {
        @Override
        public boolean areItemsTheSame(@NonNull FoodModel oldItem, @NonNull FoodModel newItem) {
            return oldItem.getName().equals(newItem.getName());
        }

        @Override
        public boolean areContentsTheSame(@NonNull FoodModel oldItem, @NonNull FoodModel newItem) {
            return oldItem.equals(newItem);
        }
    };

    @BindingAdapter("android:foodPic")
    public static void loadImage(ImageView imageView, String photoURL)
    {
        Glide.with(imageView)
                .load(photoURL)
                .fitCenter()
                .into(imageView);
    }

}

