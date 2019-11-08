package com.example.recipeapp;

import androidx.room.Entity;
import androidx.room.ForeignKey;

@Entity(tableName = "recipe_ingredient_table",
        primaryKeys = {"recipeId","ingredientId"},
        foreignKeys = {@ForeignKey(entity = Recipe.class,
                                    parentColumns = "id",
                                    childColumns = "recipeId"),
                        @ForeignKey(entity = Ingredient.class,
                                    parentColumns = "id",
                                    childColumns = "ingredientId")})
public class RecipeIngredient {
    private int recipeId;
    private int ingredientId;
}
