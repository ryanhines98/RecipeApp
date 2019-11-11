package com.example.recipeapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class RecipeListAdapter extends RecyclerView.Adapter<RecipeListAdapter.RecipeViewHolder> {

    class RecipeViewHolder extends RecyclerView.ViewHolder {
        private final TextView mRecipeNameView;
        private final TextView mCookTimeView;
        private final ImageView mImageView;

        public RecipeViewHolder(View itemView) {
            super(itemView);
            mRecipeNameView = itemView.findViewById(R.id.textView1);
            mCookTimeView = itemView.findViewById(R.id.textView2);
            mImageView = itemView.findViewById(R.id.recycler_image_view);
        }

        public void bind(final Recipe item, final OnItemClickListener listener) {
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onItemClick(item);
                }
            });
        }
    }

    public interface OnItemClickListener {
        void onItemClick(Recipe item);
    }

    private final LayoutInflater inflater;
    private List<Recipe> recipes;
    private final OnItemClickListener listener;

    RecipeListAdapter(Context context, OnItemClickListener listener) {
        inflater = LayoutInflater.from(context);
        this.listener = listener;
    }

    @Override
    public RecipeViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = inflater.inflate(R.layout.recyclerview_item, parent, false);
        return new RecipeViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(RecipeViewHolder holder, int position) {
        if(recipes != null) {
            Recipe current = recipes.get(position);
            holder.mRecipeNameView.setText(current.getRecipeName());
            holder.mCookTimeView.setText(Integer.toString(current.getCookTime()));
            holder.bind(current, listener);
            Picasso.get()
                    .load(current.getImageUrl())
                    .fit()
                    .centerCrop()
                    .into(holder.mImageView);
        } else {
            holder.mRecipeNameView.setText("No Recipe");
            holder.mCookTimeView.setText("0");
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
