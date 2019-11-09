package com.example.recipeapp;


import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
public interface StepDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(Step... steps);

    @Query("SELECT * FROM step_table WHERE step_table.recipeId=:recipeId" +
            " ORDER BY step_table.stepNumber ASC")
    List<Step> getSteps(int recipeId);

}
