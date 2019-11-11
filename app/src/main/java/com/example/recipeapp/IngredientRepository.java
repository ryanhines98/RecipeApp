package com.example.recipeapp;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.List;

public class IngredientRepository {

    private RecipeIngredientDao riDao;
    private StepDao sDao;

    private LiveData<List<String>> recipeIngredients;
    private LiveData<List<Step>> steps;

    IngredientRepository(Application application) {
        RecipeRoomDatabase db = RecipeRoomDatabase.getDatabase(application);

        riDao = db.recipeIngredientDao();
        sDao = db.stepDao();

    }

    LiveData<List<String>> getIngredientsForRecipe(int recipeId) {
        recipeIngredients = riDao.getIngredientsForRecipe(recipeId);
        return recipeIngredients;
    }

    LiveData<List<Step>> getStepsForRecipe(int recipeId) {
        steps = sDao.getSteps(recipeId);
        return steps;
    }
}
