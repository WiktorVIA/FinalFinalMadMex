package com.example.finalfinalmadmex.models;

import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.databinding.BindingAdapter;
import androidx.recyclerview.widget.DiffUtil;

public class BasketFood {

    private FoodModel food;
    private int quantity;

    public BasketFood(FoodModel food, int quantity) {
        this.food = food;
        this.quantity = quantity;
    }

    public FoodModel getFood() {
        return food;
    }

    public void setFood(FoodModel food) {
        this.food = food;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "CartItem{" +
                "product=" + food +
                ", quantity=" + quantity +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BasketFood basketFood = (BasketFood) o;
        return getQuantity() == basketFood.getQuantity() &&
                getFood().equals(basketFood.getFood());

    }

    @BindingAdapter("android:setVal")
    public static void getSelectedSpinnerValue(Spinner spinner, int quantity) {
        spinner.setSelection(quantity - 1, true);
    }

    public static DiffUtil.ItemCallback<BasketFood> itemCallback = new DiffUtil.ItemCallback<BasketFood>() {
        @Override
        public boolean areItemsTheSame(@NonNull BasketFood oldItem, @NonNull BasketFood newItem) {

            return oldItem.getQuantity() == newItem.getQuantity();
        }

        @Override
        public boolean areContentsTheSame(@NonNull BasketFood oldItem, @NonNull BasketFood newItem) {
            return oldItem.equals(newItem);
        }
    };

}

