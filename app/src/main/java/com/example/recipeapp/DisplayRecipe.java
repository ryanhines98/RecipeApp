package com.example.recipeapp;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class DisplayRecipe extends AppCompatActivity {

    public static final String RECIPE_KEY = "recipe";
    private Recipe mRecipe;
    private IngredientViewModel mIngredientViewModel;
    private DisplayListAdapter adapter;
    private ListView list;

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


        //Load Ingredient Names into ListView

        list = findViewById(R.id.listView1);
        mIngredientViewModel = new ViewModelProvider(this).get(IngredientViewModel.class);

        mIngredientViewModel.getIngredients(mRecipe.getId()).observe(this, new Observer<List<String>>() {
            @Override
            public void onChanged(@Nullable final List<String> items) {
                if(items != null) {
                    adapter = new DisplayListAdapter(getApplicationContext(), items);
                    list.setAdapter(adapter);
                }
                adapter.notifyDataSetChanged();
            }
        });

    }
}
