package com.example.recipeapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class DisplayRecipe extends AppCompatActivity {

    public static final String RECIPE_KEY = "recipe";
    private Recipe mRecipe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_recipe);

        Intent intent = getIntent();
        mRecipe = intent.getParcelableExtra(RECIPE_KEY);

        String url = mRecipe.getImageUrl();

        TextView mRecipeNameHead = findViewById(R.id.recipe_name_heading);
        mRecipeNameHead.setText(mRecipe.getRecipeName());

        ImageView mImageView = findViewById(R.id.display_recipe_image);
        Picasso.get()
                .load(url)
                .into(mImageView);
    }
}
