package com.example.recipeapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

public class DisplayRecipe extends AppCompatActivity {

    public static final String RECIPE_KEY = "recipe";
    private Recipe mRecipe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        mRecipe = intent.getParcelableExtra(RECIPE_KEY);

        setContentView(R.layout.activity_display_recipe);
    }
}
