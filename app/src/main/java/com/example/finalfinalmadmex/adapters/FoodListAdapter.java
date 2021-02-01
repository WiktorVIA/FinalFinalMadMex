package com.example.finalfinalmadmex.adapters;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.example.finalfinalmadmex.models.FoodModel;
import com.example.finalfinalmadmex.databinding.FoodItemBinding;

public class FoodListAdapter extends ListAdapter<FoodModel, FoodListAdapter.FoodViewHolder> {

    FoodInterface foodInterface;
    public FoodListAdapter(FoodInterface foodInterface)
    {
        super(FoodModel.itemCallback);
        this.foodInterface = foodInterface;
    }



    @NonNull
    @Override
    public FoodViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        FoodItemBinding foodItemBinding = FoodItemBinding.inflate(layoutInflater, parent, false);
        foodItemBinding.setFoodInterface(foodInterface);
        return new FoodViewHolder(foodItemBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull FoodViewHolder holder, int position) {

        FoodModel food = getItem(position);
        holder.foodItemBinding.setFood(food);
    }

    class FoodViewHolder extends RecyclerView.ViewHolder {

        FoodItemBinding foodItemBinding;

        public FoodViewHolder(FoodItemBinding binding) {
            super(binding.getRoot());
            this.foodItemBinding = binding;

        }
    }

    public interface FoodInterface
    {
        void addItem (FoodModel food);
        void onItemClick (FoodModel food);
    }
}

