package com.example.hiddebraun.pokewoke;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button pokeButton = findViewById(R.id.btn_pokemenu);
        pokeButton.setOnClickListener(pokeButtonListener);

    }


    // Create an anonymous implementation of OnClickListener
    private View.OnClickListener pokeButtonListener = new View.OnClickListener() {

        public void onClick(View v) {
            Intent pokeBowlIntent = new Intent(v.getContext(), PokeMenuActivity.class);
            startActivity(pokeBowlIntent);
        }
    };

}
