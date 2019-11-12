package com.example.recipeapp;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.List;

public class RecipeDetailRepository {

    private RecipeIngredientDao riDao;
    private StepDao sDao;
    private IngredientNameAmountDao inaDao;

    private LiveData<List<String>> recipeIngredients;
    private LiveData<List<Step>> steps;
    private LiveData<List<String>> ingredientAmounts;
    private LiveData<List<IngredientNameAmountDao.IngredientNameAmount>> ingredientDetails;

    RecipeDetailRepository(Application application) {
        RecipeRoomDatabase db = RecipeRoomDatabase.getDatabase(application);

        riDao = db.recipeIngredientDao();
        sDao = db.stepDao();
        inaDao = db.ingredientNameAmountDao();
    }

    LiveData<List<String>> getIngredientsForRecipe(int recipeId) {
        recipeIngredients = riDao.getIngredientsForRecipe(recipeId);
        return recipeIngredients;
    }

    LiveData<List<Step>> getStepsForRecipe(int recipeId) {
        steps = sDao.getSteps(recipeId);
        return steps;
    }

    LiveData<List<String>> getIngredientAmountsForRecipe(int recipeId) {
        ingredientAmounts = riDao.getIngredientAmountsForRecipe(recipeId);
        return ingredientAmounts;
    }

    LiveData<List<IngredientNameAmountDao.IngredientNameAmount>> getIngredientDetails(int recipeId) {
        ingredientDetails = inaDao.getIngredientDetails(recipeId);
        return ingredientDetails;
    }
}
