package com.example.hiddebraun.pokewoke;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button pokeButton = (Button) findViewById(R.id.btn_pokemenu);
        pokeButton.setOnClickListener(pokeButtonListener);

    }


    // Create an anonymous implementation of OnClickListener
    private View.OnClickListener pokeButtonListener = new View.OnClickListener() {
        public void onClick(View v) {
            Snackbar mySnackbar = Snackbar.make(v, R.string.buttonClicked, Snackbar.LENGTH_LONG);
            mySnackbar.show();

            Toast toast = Toast.makeText(getApplicationContext(), R.string.buttonClicked, Toast.LENGTH_LONG);
            toast.setGravity(Gravity.CENTER|Gravity.TOP, 0, 200);
            toast.show();

            LayoutInflater inflater = getLayoutInflater();
            View layout = inflater.inflate(R.layout.custom_toast,
                    (ViewGroup) findViewById(R.id.custom_toast_container));

            Toast customToast = new Toast(getApplicationContext());
            customToast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
            customToast.setDuration(Toast.LENGTH_LONG);
            customToast.setView(layout);
            customToast.show();


        }
    };

}
