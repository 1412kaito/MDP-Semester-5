package com.example.dicodingsubmission;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class Details extends AppCompatActivity {
    public static final String IMAGE_RESOURCE_ID = "IMAGE RESOURCE ID";
    public static final String DEX_NUMBER = "NORUT POKEDEX";
    public static final String NAMA_POKEMON = "NAMA POKEMON";
    public static final String DETAILS = "DETAIL POKEMON";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getSupportActionBar() != null){
            getSupportActionBar().setTitle("Details "+getIntent().getStringExtra(NAMA_POKEMON));
        }
        setContentView(R.layout.activity_details);
        Button back = findViewById(R.id.btnBack);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        ImageView ivPokemon = findViewById(R.id.gbrPokemon);
        ivPokemon.setImageResource(getIntent().getIntExtra(IMAGE_RESOURCE_ID, -1));

        TextView nmPokemon = findViewById(R.id.namaPokemon);
        nmPokemon.setText(getIntent().getStringExtra(NAMA_POKEMON));

        TextView dtPokemon = findViewById(R.id.details);
        dtPokemon.setText(getIntent().getStringExtra(DETAILS));

    }
}
