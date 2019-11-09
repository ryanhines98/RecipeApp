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

    public Recipe(String recipeName, String imageUrl, int cookTime) {
        this.recipeName = recipeName;
        this.imageUrl = imageUrl;
        this.cookTime = cookTime;
    }

    public int getId() { return this.id; }
    public String getImageUrl() { return this.imageUrl; }
    public String getRecipeName() { return this.recipeName; }
    public int getCookTime() { return cookTime; }

    public void setId(int id) { this.id = id; }
    public void setImageUrl(String url) { this.imageUrl = url; }
    public void setRecipeName(String name) { this.recipeName = name; }
    public void setCookTime(int time) { this.cookTime = time; }

}
