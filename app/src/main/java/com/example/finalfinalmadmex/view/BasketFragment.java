package com.example.finalfinalmadmex.view;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.DividerItemDecoration;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.finalfinalmadmex.R;
import com.example.finalfinalmadmex.adapters.BasketListAdapter;
import com.example.finalfinalmadmex.databinding.FragmentBasketBinding;
import com.example.finalfinalmadmex.models.BasketFood;
import com.example.finalfinalmadmex.viewmodels.FoodListViewModel;

import java.util.List;

public class BasketFragment extends Fragment implements BasketListAdapter.BasketInterface{

    private static final String TAG = "BasketFragment";
    FoodListViewModel foodListViewModel;
    FragmentBasketBinding fragmentBasketBinding;
    NavController navController;


    public BasketFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
       fragmentBasketBinding = FragmentBasketBinding.inflate(inflater, container, false);
       return fragmentBasketBinding.getRoot();


    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        navController = Navigation.findNavController(view);

        final BasketListAdapter basketListAdapter = new BasketListAdapter(this);
        fragmentBasketBinding.cartRecyclerView.setAdapter(basketListAdapter);
        foodListViewModel = new ViewModelProvider(requireActivity()).get(FoodListViewModel.class);
        foodListViewModel.getBasket().observe(getViewLifecycleOwner(), new Observer<List<BasketFood>>() {
            @Override
            public void onChanged(List<BasketFood> basketFoods) {
                basketListAdapter.submitList(basketFoods);
                fragmentBasketBinding.placeOrderButton.setEnabled(basketFoods.size()>0);

            }
        });

        foodListViewModel.getTotalPrice().observe(getViewLifecycleOwner(), new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {
fragmentBasketBinding.orderTotalTextView.setText("Total: DKK " + integer.toString());
            }
        });

        fragmentBasketBinding.placeOrderButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navController.navigate(R.id.action_basketFragment_to_summaryFragment);
                foodListViewModel.resetBasket();
            }
        });

    }

    @Override
    public void deleteFood(BasketFood basketFood) {
        foodListViewModel.removeFoodFromBasket(basketFood);

    }

    @Override
    public void changeQuantity(BasketFood basketFood, int quantity) {
        foodListViewModel.changeQuantity(basketFood, quantity);
    }
}