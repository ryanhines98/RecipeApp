package com.example.recipeapp;

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
    List<Recipe> getRecipesForIngredient(final int ingredientId);

    @Query("SELECT * FROM ingredient_table INNER JOIN recipe_ingredient_table" +
            " ON ingredient_table.id=recipe_ingredient_table.ingredientId" +
            " WHERE recipe_ingredient_table.recipeId=:recipeId")
    List<Ingredient> getIngredientsForRecipe(final int recipeId);

}
