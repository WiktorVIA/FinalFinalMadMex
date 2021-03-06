package com.example.finalfinalmadmex.view;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.finalfinalmadmex.R;
import com.example.finalfinalmadmex.adapters.FoodListAdapter;
import com.example.finalfinalmadmex.models.FoodModel;
import com.example.finalfinalmadmex.viewmodels.FoodListViewModel;
import com.example.finalfinalmadmex.databinding.FragmentFoodListBinding;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.List;

public class FoodListFragment extends Fragment implements FoodListAdapter.FoodInterface {

 private static final String TAG = "FoodListFragment";
 public FragmentFoodListBinding fragmentFoodListBinding;
 private FoodListAdapter foodListAdapter;
 private FoodListViewModel foodListViewModel;
 private NavController navController;

// ImageView profilePic;
 TextView username;
 FirebaseAuth mAuth;

 FloatingActionButton buttonViewBasket;



 public FoodListFragment() {
 }



 @Override
 public View onCreateView(LayoutInflater inflater, ViewGroup container,
                          Bundle savedInstanceState) {



  // Inflate the layout for this fragment
  fragmentFoodListBinding = FragmentFoodListBinding.inflate(inflater, container, false);
  return fragmentFoodListBinding.getRoot();

 }

 @Override
 public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
  super.onViewCreated(view, savedInstanceState);

  buttonViewBasket = (FloatingActionButton) getView().findViewById(R.id.button_view_basket);
  buttonViewBasket.setOnClickListener(new View.OnClickListener() {
   @Override
   public void onClick(View v) {
    navController.navigate(R.id.action_foodListFragment_to_basketFragment);
   }
  });
 // profilePic = (ImageView) getView().findViewById(R.id.profilePic);
  username = (TextView) getView().findViewById(R.id.tvUsername);

  mAuth = FirebaseAuth.getInstance();
  FirebaseUser mUser = mAuth.getCurrentUser();

  if(mUser !=null)
  {
   String name = mUser.getDisplayName();
   Log.d(TAG, "Name is " + name);
//  String photo = mUser.getPhotoUrl().toString();

  // Glide.with(this).load(photo).into(profilePic);
   username.setText(name);
  }

  foodListAdapter = new FoodListAdapter(this);
  fragmentFoodListBinding.foodRecyclerView.setAdapter(foodListAdapter);


  foodListViewModel = new ViewModelProvider(requireActivity()).get(FoodListViewModel.class);
  foodListViewModel.getFoods().observe(getViewLifecycleOwner(), new Observer<List<FoodModel>>() {
   @Override
   public void onChanged(List<FoodModel> foodModels) {
    foodListAdapter.submitList(foodModels);
   }
  });
  navController = Navigation.findNavController(view);

 }

 @Override
 public void addItem(FoodModel food) {

  boolean isAdded = foodListViewModel.addFoodToBasket(food);
  if (isAdded) {
   Snackbar.make(requireView(), food.getName() + " added to basket.", Snackbar.LENGTH_LONG).
           setAction("Checkout", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
             navController.navigate(R.id.action_foodListFragment_to_basketFragment);
            }
           }).show();
  } else {
   Snackbar.make(requireView(), "Please consider eating less.", Snackbar.LENGTH_LONG).show();
  }
 }


 @Override
 public void onItemClick(FoodModel food) {
     Log.d(TAG, "onItemClick: " + food.toString());
     foodListViewModel.setFood(food);
     navController.navigate(R.id.action_foodListFragment_to_detailsFragment);
 }


}