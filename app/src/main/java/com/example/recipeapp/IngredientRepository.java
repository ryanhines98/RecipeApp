package com.example.recipeapp;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.List;

public class IngredientRepository {

    private IngredientDao iDao;
    private RecipeIngredientDao riDao;
    private LiveData<List<String>> ingredients;
    private LiveData<List<String>> recipeIngredients;

    IngredientRepository(Application application) {
        RecipeRoomDatabase db = RecipeRoomDatabase.getDatabase(application);

        iDao = db.ingredientDao();
        riDao = db.recipeIngredientDao();

        ingredients = iDao.getAlphabetizedIngredients();

    }

    LiveData<List<String>> getIngredients() { return this.ingredients; }

    LiveData<List<String>> getIngredientsForRecipe(int recipeId) {
        recipeIngredients = riDao.getIngredientsForRecipe(recipeId);
        return recipeIngredients;
    }
}
