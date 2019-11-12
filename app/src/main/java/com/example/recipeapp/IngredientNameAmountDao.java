package com.example.recipeapp;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Query;

import java.util.List;

@Dao
public interface IngredientNameAmountDao {

    @Query("SELECT ingredientName, ingredientAmount FROM ingredient_table INNER JOIN recipe_ingredient_table" +
            " ON ingredient_table.id=recipe_ingredient_table.ingredientId" +
            " WHERE recipe_ingredient_table.recipeId=:recipeId")
    LiveData<List<IngredientNameAmount>> getIngredientDetails(int recipeId);

    static class IngredientNameAmount {
        public String ingredientName;
        public String ingredientAmount;
    }
}
