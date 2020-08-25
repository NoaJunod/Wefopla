package com.nojun.wefopla;

import java.util.ArrayList;

public class MealList {
    private ArrayList<Recipe> recipes;

    public MealList(ArrayList<Recipe> recipes) {
        this.recipes = recipes;
    }

    public ArrayList<Recipe> getRecipes() {
        return recipes;
    }

    public void setRecipes(ArrayList<Recipe> recipes) {
        this.recipes = recipes;
    }

}
