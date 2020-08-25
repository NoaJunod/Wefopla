package com.nojun.wefopla;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class RecipeListActivity extends AppCompatActivity {

    ListView recipeList;
    ArrayList<Recipe> allRecipes;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_list);

        loadAllRecipes();

        recipeList = (ListView)findViewById(R.id.recipelist);

        final ArrayList<String> recipeNames = new ArrayList<>();
        for(int i = 0; i < allRecipes.size(); i++) {
            recipeNames.add(allRecipes.get(i).getName());
        }

        ArrayAdapter arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, recipeNames);

        recipeList.setAdapter(arrayAdapter);

        recipeList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(RecipeListActivity.this, recipeNames.get(i), Toast.LENGTH_SHORT).show();
            }
        });
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
}
