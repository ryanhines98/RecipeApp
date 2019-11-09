package com.example.recipeapp;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "recipe_table")
public class Recipe implements Parcelable {

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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.id);
        dest.writeString(this.imageUrl);
        dest.writeString(this.recipeName);
        dest.writeInt(this.cookTime);
    }

    protected Recipe(Parcel in) {
        this.id = in.readInt();
        this.imageUrl = in.readString();
        this.recipeName = in.readString();
        this.cookTime = in.readInt();
    }

    public static final Parcelable.Creator<Recipe> CREATOR = new Parcelable.Creator<Recipe>() {
        @Override
        public Recipe createFromParcel(Parcel source) {
            return new Recipe(source);
        }

        @Override
        public Recipe[] newArray(int size) {
            return new Recipe[size];
        }
    };
}
