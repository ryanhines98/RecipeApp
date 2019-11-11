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
            iDao.insert(new Ingredient(1,"Butter"),
                    new Ingredient(2,"Croutons"),
                    new Ingredient(3, "Shredded Cheddar Cheese"),
                    new Ingredient(4, "Eggs"),
                    new Ingredient(5, "Milk"),
                    new Ingredient(6, "Dry Mustard"),
                    new Ingredient(7, "Ham, cubed"));

            riDao.insert(new RecipeIngredient(1,1,"1/4 cup"),
                    new RecipeIngredient(1,2,"1/2 cup"),
                    new RecipeIngredient(1, 3, "1/2 cup"),
                    new RecipeIngredient(1, 4, "4"),
                    new RecipeIngredient(1, 5, "1/2 cup"),
                    new RecipeIngredient(1, 6, "1 teaspoon"),
                    new RecipeIngredient(1, 7, "1 cup"));
            sDao.insert(
                    new Step(1,1,"Melt butter in an 8x8 inch glass baking dish or small casserole dish. " +
                            "Add croutons and toss to coat. " + "Sprinkle cheese on top of croutons. In a large bowl, beat together eggs, " +
                            "milk and dry mustard." + " Pour egg mixture over croutons and cheese." + " Sprinkle on cubed ham. Cover with " +
                            "plastic wrap, and refrigerate overnight."),
                    new Step(1,2,"Preheat oven to 375 degrees F (190 degrees C). Let casserole stand at room temperature while oven heats."),
                    new Step(1,3,"Bake in preheated oven for 40 minutes, until eggs are set. Let sit for 5 minutes " +
                            "before cutting. Can also be frozen and microwaved later.")
            );

            //2nd recipe

            rDao.insert(new Recipe(2,"Spicy grilled cheese sandwich",
                    "https://images.media-allrecipes.com/userphotos/600x600/5081856.jpg", 5));

            iDao.insert(new Ingredient(8, "Butter"),
                    new Ingredient(9, "White Bread"),
                    new Ingredient(10, "American Cheese"),
                    new Ingredient(11, "Roma (Plum) Tomato"),
                    new Ingredient(12, "Onion"),
                    new Ingredient(13, "Jalapeno Pepper"));

            riDao.insert(new RecipeIngredient(2,8,"2 tablespoons"),
                    new RecipeIngredient(2,9,"4 slices"),
                    new RecipeIngredient(2, 10, "2 slices"),
                    new RecipeIngredient(2, 11, "1"),
                    new RecipeIngredient(2, 12, "1/4 onion"),
                    new RecipeIngredient(2, 13, "1"));

            sDao.insert(
                    new Step(2, 1,"Heat a large skillet over low heat."),
                    new Step(2, 2, "Spread butter or margarine onto one side of two slices of bread."),
                    new Step(2, 3, "Place both pieces buttered side down in the skillet."),
                    new Step(2, 4, "Lay a slice of cheese on each one, and top with slices of tomato, onion and jalapeno."),
                    new Step(2, 5,"Butter one side of the remaining slices of bread, and place on top buttered side up."),
                    new Step(2, 6, "When the bottom of the sandwiches are toasted, flip and fry until brown on the other side.")
            );

            //3rd recipe

            rDao.insert(new Recipe(3,"Parmesan Crusted Tilapia Fillets",
                    "https://images.media-allrecipes.com/userphotos/600x600/1122696.jpg", 20));

            iDao.insert(new Ingredient(14, "Grated Parmesan Cheese"),
                    new Ingredient(15, "Paprika"),
                    new Ingredient(16, "Fresh Parsley"),
                    new Ingredient(17, "Salt"),
                    new Ingredient(18, "Black Pepper"),
                    new Ingredient(19, "Tilapia Fillets"),
                    new Ingredient(20, "Olive Oil"));

            riDao.insert(new RecipeIngredient(3,14,"3/4 cup"),
                    new RecipeIngredient(3,15,"2 teaspoons"),
                    new RecipeIngredient(3, 16, "1 tablespoon"),
                    new RecipeIngredient(3, 17, "to taste"),
                    new RecipeIngredient(3, 18, "to taste"),
                    new RecipeIngredient(3, 19, "4"),
                    new RecipeIngredient(3, 20, "8==D")
            );

            //4th recipe

            rDao.insert(new Recipe(4,"Mrs. Sigg's Snickerdoodles",
                    "https://www.modernhoney.com/wp-content/uploads/2018/12/The-Best-Snickerdoodle-Cookie-Recipe-9jpg.jpg",80));

            iDao.insert(new Ingredient(21, "Butter, softened"),
                    new Ingredient(22, "Shortening"),
                    new Ingredient(23, "White Sugar"),
                    new Ingredient(24, "Eggs"),
                    new Ingredient(25, "Vanilla Extract"),
                    new Ingredient(26, "All-Purpose Flour"),
                    new Ingredient(27, "Cream of Tartar"),
                    new Ingredient(28, "Baking Soda"),
                    new Ingredient(29, "Salt"),
                    new Ingredient(30, "White Sugar"),
                    new Ingredient(31, "Ground Cinnamon"));

            riDao.insert(new RecipeIngredient(4,21,"1/2 cup"),
                    new RecipeIngredient(4,22,"1/2 cup"),
                    new RecipeIngredient(4, 23, "1 1/2 cups"),
                    new RecipeIngredient(4, 24, "2"),
                    new RecipeIngredient(4, 25, "2 teaspoons"),
                    new RecipeIngredient(4, 26, "2 3/4 cups"),
                    new RecipeIngredient(4, 27, "2 teaspoons"),
                    new RecipeIngredient(4, 28, "1 teaspoon"),
                    new RecipeIngredient(4, 29, "1/4 teaspoon"),
                    new RecipeIngredient(4, 30, "2 tablespoons"),
                    new RecipeIngredient(4, 31, "2 teaspoons")

            );

            return null;
        }
    }
}
