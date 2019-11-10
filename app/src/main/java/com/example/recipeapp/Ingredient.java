package com.example.recipeapp;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "ingredient_table")
public class Ingredient {

    @PrimaryKey(autoGenerate = true)
    private int id;
    private String ingredientName;

    public Ingredient(int id, String ingredientName) {
        this.id = id;
        this.ingredientName = ingredientName;
    }

    public int getId() { return this.id; }
    public String getIngredientName() { return this.ingredientName; }

    public void setId(int id) { this.id = id; }
    public void setIngredientName(String name) { this.ingredientName = name; }

}
