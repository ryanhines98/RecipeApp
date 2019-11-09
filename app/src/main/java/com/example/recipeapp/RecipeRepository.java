package com.example.recipeapp;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.List;

public class RecipeRepository {

    private RecipeDao rDao;
    private LiveData<List<Recipe>> recipes;

    RecipeRepository(Application application) {
        RecipeRoomDatabase db = RecipeRoomDatabase.getDatabase(application);
        rDao = db.recipeDao();
        recipes = rDao.getAlphabetizedRecipes();
    }

    LiveData<List<Recipe>> getRecipes() { return this.recipes; }

}
