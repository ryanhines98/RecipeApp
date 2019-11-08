package com.example.recipeapp;


import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;

@Dao
public interface StepDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(Step... steps);


}
