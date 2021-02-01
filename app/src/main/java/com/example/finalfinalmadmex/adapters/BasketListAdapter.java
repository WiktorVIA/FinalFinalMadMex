package com.example.finalfinalmadmex.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.example.finalfinalmadmex.databinding.BasketItemBinding;
import com.example.finalfinalmadmex.models.BasketFood;
import com.example.finalfinalmadmex.models.FoodModel;

public class BasketListAdapter extends ListAdapter<BasketFood, BasketListAdapter.BasketVH> {

    private BasketInterface basketInterface;
    public BasketListAdapter(BasketInterface basketInterface) {
        super(BasketFood.itemCallback);
        this.basketInterface = basketInterface;
    }

    @NonNull
    @Override
    public BasketVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {


        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        BasketItemBinding basketItemBinding = BasketItemBinding.inflate(layoutInflater, parent, false);
        return new BasketVH(basketItemBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull BasketVH holder, int position) {
holder.basketItemBinding.setBasketFood(getItem(position));
holder.basketItemBinding.executePendingBindings();
    }

    class BasketVH extends RecyclerView.ViewHolder
    {
        BasketItemBinding basketItemBinding;
        public BasketVH(@NonNull BasketItemBinding basketItemBinding) {
            super(basketItemBinding.getRoot());
            this.basketItemBinding = basketItemBinding;

            basketItemBinding.deleteFoodButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    basketInterface.deleteFood(getItem(getAdapterPosition()));
                }
            });

            basketItemBinding.quantitySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    int quantity = position + 1;
                    if (quantity == getItem(getAdapterPosition()).getQuantity())
                    {
                        return;
                    }
                    basketInterface.changeQuantity(getItem(getAdapterPosition()), quantity);
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });
        }
    }

    public interface BasketInterface {
        void deleteFood (BasketFood basketFood);
        void changeQuantity (BasketFood basketFood, int quantity);

    }
}

