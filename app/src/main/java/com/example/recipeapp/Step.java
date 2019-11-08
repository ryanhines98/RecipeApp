package com.example.recipeapp;

import androidx.room.Entity;
import androidx.room.ForeignKey;

@Entity(tableName = "step_table",
        primaryKeys = {"recipeId","stepNumber"},
        foreignKeys = @ForeignKey(entity = Recipe.class,
                                  parentColumns = "id",
                                  childColumns = "recipeId"))
public class Step {

    private int recipeId;
    private int stepNumber;
    private String stepText;

    public Step(int recipeId, int stepNumber, String stepText) {
        this.recipeId = recipeId;
        this.stepNumber = stepNumber;
        this.stepText = stepText;
    }

    public int getRecipeId() { return this.recipeId; }
    public int getStepNumber() { return this.stepNumber; }
    public String getStepText() { return this.stepText; }

}
