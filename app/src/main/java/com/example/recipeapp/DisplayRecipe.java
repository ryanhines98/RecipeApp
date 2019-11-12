package com.example.recipeapp;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class DisplayRecipe extends AppCompatActivity {

    public static final String RECIPE_KEY = "recipe";
    private Recipe mRecipe;

    private DisplayListAdapter ingredientAdapter;
    private DisplayListAdapter stepAdapter;

    private IngredientViewModel mIngredientViewModel;
    private NonScrollListView ingredientListView;

    private StepViewModel mStepViewModel;
    private NonScrollListView stepListView;

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
                .fit()
                .centerInside()
                .into(mImageView);

        //Load Ingredient Names into ListView
        ingredientListView = findViewById(R.id.listView1);
        mIngredientViewModel = new ViewModelProvider(this).get(IngredientViewModel.class);

        mIngredientViewModel.getIngredientDetails(mRecipe.getId()).observe(this, new Observer<List<IngredientNameAmountDao.IngredientNameAmount>>() {
            @Override
            public void onChanged(@Nullable final List<IngredientNameAmountDao.IngredientNameAmount> items) {
                if(items != null) {
                    List<String> ingredients = new ArrayList<>();

                    for(int i = 0; i < items.size(); i++) {
                        ingredients.add(items.get(i).ingredientName + " (" + items.get(i).ingredientAmount + ")");
                    }

                    ingredientAdapter = new DisplayListAdapter(getApplicationContext(), ingredients);
                    ingredientListView.setAdapter(ingredientAdapter);
                }
                ingredientAdapter.notifyDataSetChanged();
            }
        });

        //Load Steps into ListView
        stepListView = findViewById(R.id.listView2);
        mStepViewModel = new ViewModelProvider(this).get(StepViewModel.class);

        mStepViewModel.getSteps(mRecipe.getId()).observe(this, new Observer<List<Step>>() {
            @Override
            public void onChanged(@Nullable final List<Step> items) {
                if(items != null) {
                    List<String> steps = new ArrayList<>();

                    for(int i = 0; i < items.size(); i++) {
                        steps.add( Integer.toString(items.get(i).getStepNumber()) + ") " +
                                    items.get(i).getStepText());
                    }

                    stepAdapter = new DisplayListAdapter(getApplicationContext(), steps);
                    stepListView.setAdapter(stepAdapter);
                }
                stepAdapter.notifyDataSetChanged();
            }
        });
    }
}
