package edu.stts.weekseven;

import androidx.appcompat.app.AppCompatActivity;

import android.content.res.TypedArray;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
/*
* MainActivity menggunakan List/Custom List View
*/
public class MainActivity extends AppCompatActivity {
    ArrayList<Pokemon> pokedex;
    String[] dataNama, dataDetail;
    int[]  dataNomorUrut;
    TypedArray dataGambar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        preparedata();
        fillList();
        ListView lvPokedex = findViewById(R.id.lvPokemon);
//        ArrayAdapter<Pokemon> adapterNamaPokemon = new ArrayAdapter<>(this,
//                android.R.layout.simple_list_item_1,
//                android.R.id.text1, pokedex);
//        lvPokedex.setAdapter(adapterNamaPokemon);
        PokemonAdapter pokemonAdapter = new PokemonAdapter(this);
        lvPokedex.setAdapter(pokemonAdapter);
        for (Pokemon p:pokedex) {
            pokemonAdapter.getPokemons().add(p);
        }
        pokemonAdapter.notifyDataSetChanged();
        lvPokedex.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(MainActivity.this, pokedex.get(position).getNama(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void fillList() {
        pokedex = new ArrayList<>(dataNama.length);
        for (int i=0; i<dataNomorUrut.length; ++i){
            Pokemon p = new Pokemon(dataDetail[i],
                    dataNama[i], dataNomorUrut[i], dataGambar.getResourceId(i, -1));
            pokedex.add(p);

        }
    }

    void preparedata(){
        dataNama = getResources().getStringArray(R.array.data_nama);
        dataDetail = getResources().getStringArray(R.array.data_details);
        dataGambar = getResources().obtainTypedArray(R.array.data_photo);
        dataNomorUrut = getResources().getIntArray(R.array.data_dex);
    }
}
