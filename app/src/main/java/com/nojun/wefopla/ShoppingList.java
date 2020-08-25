package com.nojun.wefopla;

import java.util.ArrayList;

public class ShoppingList {
    private ArrayList<Ingredient> ingredients;

    public ShoppingList(ArrayList<Ingredient> ingredients) {
        this.ingredients = ingredients;
    }

    public ArrayList<Ingredient> getIngredients() {
        return ingredients;
    }

    public void setIngredients(ArrayList<Ingredient> ingredients) {
        this.ingredients = ingredients;
    }

}
