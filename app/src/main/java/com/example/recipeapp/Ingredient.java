package com.example.recipeapp;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "ingredient_table")
public class Ingredient {

    @PrimaryKey(autoGenerate = true)
    private int id;
    private String ingredientName;

    public Ingredient(String name) { this.ingredientName = name; }

    public int getId() { return this.id; }
    public String getIngredientName() { return this.ingredientName; }
}
