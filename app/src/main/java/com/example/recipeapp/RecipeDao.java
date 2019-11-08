package com.example.recipeapp;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
public interface RecipeDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(Recipe... recipes);
    
    @Query("DELETE FROM recipe_table")
    void deleteAll();

    @Query("SELECT * FROM recipe_table ORDER BY recipeName ASC")
    LiveData<List<Recipe>> getAlphabetizedRecipes();

}
