package com.example.recipeapp;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "recipe_table")
public class Recipe {

    @PrimaryKey(autoGenerate = true)
    private int id;
    private String imageUrl;
    private String recipeName;
    private int cookTime;

    public Recipe(String name, String image, int time) {
        this.recipeName = name;
        this.imageUrl = image;
        this.cookTime = time;
    }

    public int getId() { return this.id; }
    public String getImageUrl() { return this.imageUrl; }
    public String getRecipeName() { return this.recipeName; }
    public int getCookTime() { return cookTime; }

}
