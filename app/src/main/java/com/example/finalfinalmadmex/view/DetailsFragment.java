package com.example.finalfinalmadmex.view;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.finalfinalmadmex.databinding.FragmentDetailsBinding;
import com.example.finalfinalmadmex.viewmodels.FoodListViewModel;
import com.example.finalfinalmadmex.databinding.FragmentFoodListBinding;


public class DetailsFragment extends Fragment {

FragmentDetailsBinding fragmentDetailsBinding;
FoodListViewModel foodListViewModel;

    public DetailsFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        fragmentDetailsBinding = FragmentDetailsBinding.inflate(inflater, container, false);
        return fragmentDetailsBinding.getRoot();


    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        foodListViewModel = new ViewModelProvider(requireActivity()).get(FoodListViewModel.class);
        fragmentDetailsBinding.setFoodListViewModel(foodListViewModel);
    }

}