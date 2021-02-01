package com.example.finalfinalmadmex;

import com.example.finalfinalmadmex.Repository.BasketRepo;
import com.example.finalfinalmadmex.viewmodels.FoodListViewModel;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

@RunWith(JUnit4.class)
public class ExampleUnitTest {

    FoodListViewModel viewModel;
    BasketRepo repo;

    @Before
    public void calculateBasketTotal()
    {


        viewModel.getTotalPrice();
    }


    @Test
    public void getTotalPrice()
    {
     when(repo.getTotalPrice()).thenReturn();

    }
}