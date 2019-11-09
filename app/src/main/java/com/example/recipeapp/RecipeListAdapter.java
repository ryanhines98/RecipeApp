package com.example.recipeapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class RecipeListAdapter extends RecyclerView.Adapter<RecipeListAdapter.RecipeViewHolder> {

    class RecipeViewHolder extends RecyclerView.ViewHolder {
        private final TextView recipeItemView;

        public RecipeViewHolder(View itemView) {
            super(itemView);
            recipeItemView = itemView.findViewById(R.id.textView);
        }
    }

    private final LayoutInflater inflater;
    private List<Recipe> recipes;

    RecipeListAdapter(Context context) { inflater = LayoutInflater.from(context); }

    @Override
    public RecipeViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = inflater.inflate(R.layout.recyclerview_item, parent, false);
        return new RecipeViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(RecipeViewHolder holder, int position) {
        if(recipes != null) {
            Recipe current = recipes.get(position);
            holder.recipeItemView.setText(current.getRecipeName());
        } else {
            holder.recipeItemView.setText("No Recipe");
        }
    }

    void setRecipes(List<Recipe> r) {
        recipes = r;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        if(recipes != null)
            return recipes.size();
        else return 0;
    }

}
