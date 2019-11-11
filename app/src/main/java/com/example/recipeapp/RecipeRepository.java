package com.example.recipeapp;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.List;

public class RecipeRepository {

    private RecipeDao rDao;
    private IngredientDao iDao;
    private RecipeIngredientDao riDao;
    private StepDao sDao;

    private LiveData<List<Recipe>> recipes;

    RecipeRepository(Application application) {
        RecipeRoomDatabase db = RecipeRoomDatabase.getDatabase(application);

        rDao = db.recipeDao();
        iDao = db.ingredientDao();
        riDao = db.recipeIngredientDao();
        sDao = db.stepDao();

        recipes = rDao.getAlphabetizedRecipes();
    }

    LiveData<List<Recipe>> getRecipes() { return this.recipes; }
}
