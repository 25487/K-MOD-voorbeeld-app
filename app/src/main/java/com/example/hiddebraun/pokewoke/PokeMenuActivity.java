package com.example.hiddebraun.pokewoke;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

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
        String url = BuildConfig.POKE_JSON_URL;


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

        loadJsonData(url);
    }

    private void loadJsonData(String url) {
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading Poke Bowl...");
        progressDialog.setIcon(getDrawable(R.drawable.ic_launcher_foreground));
        progressDialog.show();

        // Instantiate the RequestQueue.
        RequestQueue queue = Volley.newRequestQueue(this);

        Response.Listener<JSONObject> responseListener = new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {

                    Log.d("JSON", response.toString());

                    String baseUrl = response.getString("base_url");
                    JSONArray ingredients = response.getJSONArray("ingredients");
                    for (int i = 0; i < ingredients.length(); i++) {
                        JSONObject ingredientObj = ingredients.getJSONObject(i);
                        Ingredient ingredient = new Ingredient();

                        ingredient.setId(ingredientObj.getInt("id"));
                        ingredient.setName(ingredientObj.getString("name"));
                        ingredient.setPrice(ingredientObj.getDouble("price"));;
                        String fullUrl = baseUrl + ingredientObj.getString("image");
                        ingredient.setImageUrl(fullUrl);

                        ingredientList.add(ingredient);

                    }
                    ingredientAdapter.notifyDataSetChanged();
                    progressDialog.dismiss();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        };

        Response.ErrorListener errorListener = new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast toast = new Toast(getApplicationContext());
                toast.setText(error.getMessage());
                toast.setGravity(Gravity.CENTER, 0, 0);
                toast.setDuration(2000);
                toast.show();
            }
        };

        // Request a string response from the provided URL.
        JsonObjectRequest jsonRequest = new JsonObjectRequest(Request.Method.GET, url, null, responseListener, errorListener);

        // Add the request to the RequestQueue.
        queue.add(jsonRequest);

    }


}
