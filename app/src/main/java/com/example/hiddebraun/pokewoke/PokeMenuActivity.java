package com.example.hiddebraun.pokewoke;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.util.ArrayList;
import java.util.List;

public class PokeMenuActivity extends AppCompatActivity {

    private RecyclerView ingredientListView;

    private LinearLayoutManager linearLayoutManager;
    private DividerItemDecoration dividerItemDecoration;
    private List<Ingredient> ingredientList;
    private RecyclerView.Adapter ingredientAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pokemenu);

        String filename = "pokebowl.json";


        // Haal de list view (RecyclerView) op
        ingredientListView = findViewById(R.id.ingredient_list);

        // Maak een lege lijst om alle ingredient in op te slaan
        ingredientList = new ArrayList<>();

        //Maak de adapter die de data aan de listview koppelt / geeft
        ingredientAdapter = new IngredientAdapter(getApplicationContext(), ingredientList);

        // Maak een layoutmanager aan
        linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        dividerItemDecoration = new DividerItemDecoration(ingredientListView.getContext(), linearLayoutManager.getOrientation());

        ingredientListView.setHasFixedSize(true);
        ingredientListView.setLayoutManager(linearLayoutManager);
        ingredientListView.addItemDecoration(dividerItemDecoration);
        ingredientListView.setAdapter(ingredientAdapter);

        try {
            loadJsonData(filename);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    /**
     * @param filename JSON filename in src/main/assets to load
     * @throws JSONException
     */
    private void loadJsonData(String filename) throws JSONException {

        String jsonString = Utils.getJsonFromAssets(getApplicationContext(), filename);
        JSONObject   object = (JSONObject) new JSONTokener(jsonString).nextValue();
        String baseUrl = object.getString("base_url");
        JSONArray ingredients = object.getJSONArray("ingredients");

        for (int i = 0; i < ingredients.length(); i++) {
            JSONObject ingredientObj = ingredients.getJSONObject(i);
            Ingredient ingredient = new Ingredient();

            ingredient.setId(ingredientObj.getInt("id"));
            ingredient.setName(ingredientObj.getString("name"));
            ingredient.setPrice(ingredientObj.getDouble("price"));
            String fullUrl = baseUrl + ingredientObj.getString("image");
            ingredient.setImageUrl(fullUrl);

            ingredientList.add(ingredient);

        }
        ingredientAdapter.notifyDataSetChanged();

    }


}
