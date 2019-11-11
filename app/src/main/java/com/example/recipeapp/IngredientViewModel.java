package com.example.recipeapp;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class IngredientViewModel extends AndroidViewModel {
    private IngredientRepository repository;

    private LiveData<List<String>> ingredients;

    public IngredientViewModel(Application application) {
        super(application);
        repository = new IngredientRepository(application);
    }

    LiveData<List<String>> getIngredients(int recipeId) {
        ingredients = repository.getIngredientsForRecipe(recipeId);
        return this.ingredients;
    }
}
