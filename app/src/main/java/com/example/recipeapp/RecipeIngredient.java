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
    private int ingredientAmount;

    public RecipeIngredient(int recipeId, int ingredientId, int ingredientAmount) {
        this.recipeId = recipeId;
        this.ingredientId = ingredientId;
        this.ingredientAmount = ingredientAmount;
    }

    public int getRecipeId() { return this.recipeId; }
    public int getIngredientId() { return this.ingredientId; }
    public int getIngredientAmount() { return this.ingredientAmount; }

    public void setRecipeId(int id) { this.recipeId = id; }
    public void setIngredientId(int id) { this.ingredientId = id; }
    public void setIngredientAmount(int amount) { this.ingredientAmount = amount; }

}
