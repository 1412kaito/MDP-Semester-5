package com.example.dicodingsubmission;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.DataSetObserver;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class HomePage extends AppCompatActivity {
    ArrayList<Pokemon> arr;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        arr = new ArrayList<>();
        setContentView(R.layout.activity_home_page);
        Button btnAbout = findViewById(R.id.btnToAbout);
        btnAbout.setOnClickListener(
                new View.OnClickListener(){
                    @Override
                    public void onClick(View view) {
                        Intent moveIntent = new Intent(HomePage.this, MainActivity.class);
                        startActivity(moveIntent);
                    }
                }
        );

        if (getSupportActionBar() != null){
            getSupportActionBar().setTitle("Home Page");
        }

        arr.addAll(DataMon.isi());
        RecyclerView rvPokemon = findViewById(R.id.rvPokemon);
        rvPokemon.setHasFixedSize(true);
        rvPokemon.setLayoutManager(new LinearLayoutManager(this));
        PokemonAdapter listPokemon = new PokemonAdapter(arr);
        rvPokemon.setAdapter(listPokemon);
        listPokemon.setOnItemClickCallback(new PokemonAdapter.OnItemClickCallback() {
            @Override
            public void onItemClicked(Pokemon data) {
                showSelectedHero(data);
            }
        });

    }

    private void showSelectedHero(Pokemon p) {
        Toast.makeText(this, "Kamu memilih " + p.getNama(), Toast.LENGTH_SHORT).show();
        Intent moveWithDataIntent = new Intent(HomePage.this, Details.class);
        moveWithDataIntent.putExtra(Details.DETAILS, p.getDetail());
        moveWithDataIntent.putExtra(Details.IMAGE_RESOURCE_ID, p.getGambar());
        moveWithDataIntent.putExtra(Details.DEX_NUMBER, p.getDexNo());
        moveWithDataIntent.putExtra(Details.NAMA_POKEMON, p.getNama());
        startActivity(moveWithDataIntent);
    }
}
