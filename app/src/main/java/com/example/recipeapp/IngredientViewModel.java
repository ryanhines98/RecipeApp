package com.example.recipeapp;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class IngredientViewModel extends AndroidViewModel {
    private RecipeDetailRepository repository;

    private LiveData<List<String>> ingredients;
    private LiveData<List<IngredientNameAmountDao.IngredientNameAmount>> ingredientDetails;

    public IngredientViewModel(Application application) {
        super(application);
        repository = new RecipeDetailRepository(application);
    }

    LiveData<List<String>> getIngredients(int recipeId) {
        ingredients = repository.getIngredientsForRecipe(recipeId);
        return this.ingredients;
    }

    LiveData<List<IngredientNameAmountDao.IngredientNameAmount>> getIngredientDetails(int recipeId) {
        ingredientDetails = repository.getIngredientDetails(recipeId);
        return ingredientDetails;
    }
}
