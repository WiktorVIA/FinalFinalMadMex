package com.example.finalfinalmadmex.view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.example.finalfinalmadmex.R;
import com.example.finalfinalmadmex.models.BasketFood;
import com.example.finalfinalmadmex.viewmodels.FoodListViewModel;
import com.facebook.login.LoginManager;
import com.google.firebase.auth.FirebaseAuth;

import java.util.List;



public class MainActivity extends AppCompatActivity {


    private static final String TAG = "MainActivity";
    NavController navController;
    FoodListViewModel foodListViewModel;
    FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        firebaseAuth = FirebaseAuth.getInstance();


        navController = Navigation.findNavController(this, R.id.nav_host_fragment);
         NavigationUI.setupActionBarWithNavController(this, navController);

        foodListViewModel = new ViewModelProvider(this).get(FoodListViewModel.class);
        foodListViewModel.getBasket().observe(this, new Observer<List<BasketFood>>() {
            @Override
            public void onChanged(List<BasketFood> basketFoods) {
                Log.d(TAG, "onChanged: " + basketFoods.size());
            }
        });
    }

    @Override
    public boolean onSupportNavigateUp() {
        navController.navigateUp();
        return super.onSupportNavigateUp();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;

    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        int id = item.getItemId();



        if(id==R.id.settingsActivity){

            Intent intent = new Intent(MainActivity.this, SettingsActivity.class);
            startActivity(intent);
            return true;
        } else
        if(id==R.id.logout){
            firebaseAuth.signOut();
            LoginManager.getInstance().logOut();
            finish();
            //Intent intent = new Intent(MainActivity.this, LoginActivity.class);
            //startActivity(intent);
        }else
        if(id==R.id.info){
            Intent intent = new Intent(MainActivity.this, InfoActivity.class);
            startActivity(intent);
        }else
        if(id==R.id.contact){

            Intent intent = new Intent(MainActivity.this, ContactActivity.class);
            startActivity(intent);
        }

        return NavigationUI.onNavDestinationSelected(item, navController) || super.onOptionsItemSelected(item);
    }

}

