package com.example.recipeapp;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class StepViewModel extends AndroidViewModel {
    private RecipeDetailRepository repository;

    private LiveData<List<Step>> steps;

    public StepViewModel(Application application) {
        super(application);
        repository = new RecipeDetailRepository(application);
    }

    LiveData<List<Step>> getSteps(int recipeId) {
        steps = repository.getStepsForRecipe(recipeId);
        return this.steps;
    }
}
