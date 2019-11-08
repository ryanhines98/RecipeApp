package com.example.recipeapp;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
public interface IngredientDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(Ingredient... ingredients);

    @Query("DELETE FROM ingredient_table")
    void deleteAll();

    @Query("SELECT * FROM ingredient_table ORDER BY ingredientName ASC")
    List<Ingredient> getAlphabetizedIngredients();
}
