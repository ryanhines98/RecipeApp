package com.example.recipeapp;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import java.util.List;

@Database(entities = {Recipe.class, Ingredient.class, Step.class, RecipeIngredient.class}, version = 3)
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
                            RecipeRoomDatabase.class,"word_database").addCallback(sRoomDatabaseCallback).fallbackToDestructiveMigration().build();
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
            //rDao.deleteAll();

            rDao.insert(new Recipe(1,"Country House Bed and Breakfast Casserole",
                    "https://images.media-allrecipes.com/userphotos/600x600/885833.jpg", 10));
            iDao.insert(new Ingredient(1,"Butter"));
            iDao.insert(new Ingredient(2,"Croutons"));
            riDao.insert(new RecipeIngredient(1,1,"1/4 cup"),
                    new RecipeIngredient(1,2,"1/2 cup"));
            sDao.insert(
                    new Step(1,1,"Melt butter in an 8x8 inch glass baking dish or small casserole dish. Add croutons and toss to coat. Sprinkle cheese on top of croutons. In a large bowl, beat together eggs, milk and dry mustard. Pour egg mixture over croutons and cheese. Sprinkle on cubed ham. Cover with plastic wrap, and refrigerate overnight."),
                    new Step(1,2,"Preheat oven to 375 degrees F (190 degrees C). Let casserole stand at room temperature while oven heats."),
                    new Step(1,3,"Bake in preheated oven for 40 minutes, until eggs are set. Let sit for 5 minutes before cutting. Can also be frozen and microwaved later.")
            );

            rDao.insert(new Recipe(2,"Spicy grilled cheese sandwich",
                    "https://images.media-allrecipes.com/userphotos/600x600/5081856.jpg", 5));

            rDao.insert(new Recipe(3,"Parmesan Crusted Tilapia Fillets",
                    "https://images.media-allrecipes.com/userphotos/600x600/1122696.jpg", 20));

            rDao.insert(new Recipe(4,"Cheesecake with Cranberry Glaze and Sugared Cranberries",
                    "https://images.media-allrecipes.com/userphotos/600x600/6179381.jpg",80));

            return null;
        }
    }
}
