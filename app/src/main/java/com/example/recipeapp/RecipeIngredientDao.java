package com.example.recipeapp;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
public interface RecipeIngredientDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(RecipeIngredient... recipeIngredients);

    @Query("SELECT * FROM recipe_table INNER JOIN recipe_ingredient_table" +
            " ON recipe_table.id=recipe_ingredient_table.recipeId" +
            " WHERE recipe_ingredient_table.ingredientId=:ingredientId")
    LiveData<List<Recipe>> getRecipesForIngredient(final int ingredientId);

    @Query("SELECT ingredientName FROM ingredient_table INNER JOIN recipe_ingredient_table" +
            " ON ingredient_table.id=recipe_ingredient_table.ingredientId" +
            " WHERE recipe_ingredient_table.recipeId=:recipeId")
    LiveData<List<String>> getIngredientsForRecipe(final int recipeId);

    @Query("SELECT ingredientAmount FROM ingredient_table INNER JOIN recipe_ingredient_table" +
            " ON ingredient_table.id=recipe_ingredient_table.ingredientId" +
            " WHERE recipe_ingredient_table.recipeId=:recipeId")
    LiveData<List<String>> getIngredientAmountsForRecipe(final int recipeId);
}
