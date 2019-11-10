package com.example.recipeapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.view.menu.MenuView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class DisplayRecipe extends AppCompatActivity {

    public static final String RECIPE_KEY = "recipe";
    private Recipe mRecipe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_recipe);

        Intent intent = getIntent();
        mRecipe = intent.getParcelableExtra(RECIPE_KEY);

        TextView mRecipeNameHead = (TextView) findViewById(R.id.recipeNameHeading);
        mRecipeNameHead.setText(mRecipe.getRecipeName());
    }
}
