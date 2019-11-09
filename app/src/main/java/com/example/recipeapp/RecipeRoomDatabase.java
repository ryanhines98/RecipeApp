package com.example.recipeapp;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import java.util.List;

@Database(entities = {Recipe.class, Ingredient.class, Step.class, RecipeIngredient.class}, version = 1)
public abstract class RecipeRoomDatabase extends RoomDatabase {

    public abstract RecipeDao recipeDao();
    public abstract IngredientDao ingredientDao();
    public abstract StepDao stepDao();
    public abstract RecipeIngredientDao recipeIngredientDao();

    private static volatile RecipeRoomDatabase INSTANCE;

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
            rDao.deleteAll();

            Recipe temp = new Recipe("Chicken Parmesan", null, 30);
            rDao.insert(temp);
            temp = new Recipe("French Toast", null, 20);
            rDao.insert(temp);
            temp = new Recipe("Sunny Side Eggs", null, 10);
            rDao.insert(temp);

            return null;
        }
    }
}
