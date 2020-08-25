package com.nojun.wefopla;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    ListView mealList;
    Button createWeekPlanButton;
    Button goToRecipeListButton;
    ArrayList<String> weeklyRecipeNames;
    ArrayList<Recipe> allRecipes;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        loadWeekRecipeNames();
        loadAllRecipes();

        mealList = (ListView)findViewById(R.id.meallist);
        allRecipes = new ArrayList<>();

        Ingredient ingredient1 = new Ingredient("Chicken", 200, "g");
        Ingredient ingredient2 = new Ingredient("Rice", 300, "g");
        ArrayList<Ingredient> ingredients = new ArrayList<>();
        ingredients.add(ingredient1);
        ingredients.add(ingredient2);
        allRecipes.add(new Recipe("Spaghetti Bolo", ingredients));
        Ingredient ingredient3 = new Ingredient("Spaghetti", 250, "g");
        Ingredient ingredient4 = new Ingredient("Bolo", 150, "g");
        ArrayList<Ingredient> ingredients2 = new ArrayList<>();
        ingredients.add(ingredient3);
        ingredients.add(ingredient4);
        allRecipes.add(new Recipe("Pfannkuchen", ingredients2));
        Ingredient ingredient5 = new Ingredient("Milch", 500, "ml");
        Ingredient ingredient6 = new Ingredient("Mehl", 300, "g");
        ArrayList<Ingredient> ingredients3 = new ArrayList<>();
        ingredients.add(ingredient5);
        ingredients.add(ingredient6);
        allRecipes.add(new Recipe("Chicken Rice", ingredients3));

        ArrayAdapter arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, weeklyRecipeNames);

        mealList.setAdapter(arrayAdapter);

        mealList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(MainActivity.this, weeklyRecipeNames.get(i), Toast.LENGTH_SHORT).show();
            }
        });
        createWeekPlanButton = (Button)findViewById(R.id.createWeekPlanButton);
        createWeekPlanButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                weeklyRecipeNames.clear();
                for (int i = 0; i < 7; i++) {
                    int index = new Random().nextInt(allRecipes.size());
                    weeklyRecipeNames.add(allRecipes.get(index).getName());
                }
                ArrayAdapter arrayAdapter = new ArrayAdapter(MainActivity.this, android.R.layout.simple_list_item_1, weeklyRecipeNames);

                mealList.setAdapter(arrayAdapter);
                saveWeekRecipeNames();
                saveAllRecipes();
            }
        });
        goToRecipeListButton = (Button)findViewById(R.id.goToRecipeListButton);
        goToRecipeListButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, NewRecipeActivity.class));
            }
        });
    }

    private void saveAllRecipes() {
        SharedPreferences sharedPreferences = getSharedPreferences("shared preferences", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();
        String json = gson.toJson(allRecipes);
        editor.putString("all recipes", json);
        editor.apply();
        System.out.println(json);
    }

    private void loadAllRecipes() {
        SharedPreferences sharedPreferences = getSharedPreferences("shared preferences", MODE_PRIVATE);
        Gson gson = new Gson();
        String json = sharedPreferences.getString("all recipes", null);
        Type type = new TypeToken<ArrayList<Recipe>>() {}.getType();
        System.out.println(json);
        allRecipes = gson.fromJson(json, type);
        if (allRecipes == null) {
            allRecipes = new ArrayList<>();
        }
    }

    private void saveWeekRecipeNames() {
        SharedPreferences sharedPreferences = getSharedPreferences("shared preferences", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();
        String json = gson.toJson(weeklyRecipeNames);
        editor.putString("week recipe names", json);
        editor.apply();
    }

    private void loadWeekRecipeNames() {
        SharedPreferences sharedPreferences = getSharedPreferences("shared preferences", MODE_PRIVATE);
        Gson gson = new Gson();
        String json = sharedPreferences.getString("week recipe names", null);
        System.out.println(json);
        Type type = new TypeToken<ArrayList<String>>() {}.getType();
        weeklyRecipeNames = gson.fromJson(json, type);
        if (weeklyRecipeNames == null) {
            weeklyRecipeNames = new ArrayList<>();
        }
    }

    private void createShoppingList() {

    }

}
