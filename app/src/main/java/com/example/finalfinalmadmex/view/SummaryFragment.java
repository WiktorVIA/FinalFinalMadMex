package com.example.finalfinalmadmex.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.example.finalfinalmadmex.R;
import com.example.finalfinalmadmex.databinding.FragmentSummaryBinding;
import com.example.finalfinalmadmex.viewmodels.FoodListViewModel;


public class SummaryFragment extends Fragment {

    NavController navController;
    FragmentSummaryBinding fragmentSummaryBinding;
    FoodListViewModel foodListViewModel;

    public SummaryFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        fragmentSummaryBinding = FragmentSummaryBinding.inflate(inflater, container, false);
        return fragmentSummaryBinding.getRoot();

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        navController = Navigation.findNavController(view);
        foodListViewModel = new ViewModelProvider(requireActivity()).get(FoodListViewModel.class);

        fragmentSummaryBinding.continueShoppingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navController.navigate(R.id.action_summaryFragment_to_foodListFragment);
                foodListViewModel.resetBasket();
            }
        });
    }
}