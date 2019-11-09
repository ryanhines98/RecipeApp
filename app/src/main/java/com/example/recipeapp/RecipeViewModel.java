package com.example.recipeapp;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class RecipeViewModel extends AndroidViewModel {

    private RecipeRepository repository;

    private LiveData<List<Recipe>> recipes;

    public RecipeViewModel(Application application) {
        super(application);
        repository = new RecipeRepository(application);
        recipes = repository.getRecipes();
    }

    LiveData<List<Recipe>> getRecipes() { return this.recipes; }

}
