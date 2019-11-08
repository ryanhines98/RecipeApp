package com.example.recipeapp;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

@Database(entities = {Recipe.class, Ingredient.class, Step.class, RecipeIngredient.class}, version = 1)
public class RecipeRoomDatabase extends RoomDatabase {

    public abstract RecipeDao recipeDao();
    public abstract IngredientDao ingredientDao();
    public abstract StepDao stepDao();
    public abstract RecipeIngredientDao recipeIngredientDao();

    public static volatile RecipeRoomDatabase INSTANCE;

    static RecipeRoomDatabase getDatabase(final Context context) {
        if(INSTANCE == null) {
            synchronized (RecipeRoomDatabase.class) {
                if(INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            RecipeRoomDatabase.class,"word_database").addCallback(sRoomDatabaseCallback).build();
                }
            }
        }
        return INSTANCE;
    }

    private static RoomDatabase.Callback sRoomDatabaseCallback = new RoomDatabase.Callback(){
        @Override
        public void onOpen (@NonNull SupportSQLiteDatabase db){
            super.onOpen(db);
            new PopulateDbAsync(INSTANCE).execute();
        }
    };

    private static class PopulateDbAsync extends AsyncTask<Void, Void, Void> {

        private final RecipeDao rDao;
        private final IngredientDao iDao;
        private final StepDao sDao;
        private final RecipeIngredientDao riDao;

        PopulateDbAsync(RecipeRoomDatabase db) {
            rDao = db.recipeDao();
            iDao = db.ingredientDao();
            sDao = db.stepDao();
            riDao = db.recipeIngredientDao();
        }

        @Override
        protected Void doInBackground(final Void... params) {

            //Initial population of data we want into database;
            return null;
        }
    }
}
