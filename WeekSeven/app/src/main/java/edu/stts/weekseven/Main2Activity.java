package edu.stts.weekseven;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.content.Intent;
import android.content.res.TypedArray;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import java.util.ArrayList;

public class Main2Activity extends AppCompatActivity {
    ArrayList<Pokemon> arrPokemon;
    String[] dataNama, dataDetail;
    int[]  dataNomorUrut; TypedArray dataGambar;
    int mode;
    private final String STATE_LIST = "state_list";
    private final String STATE_MODE = "state_mode";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        arrPokemon = new ArrayList<>();
        if (savedInstanceState==null){
            preparedata(); fillList();
            ListPokemonRVAdapter p = new ListPokemonRVAdapter(arrPokemon);
            RecyclerView rvPokedex = findViewById(R.id.rvPokemon);
            rvPokedex.setHasFixedSize(true);
            showRecyclerList(p, rvPokedex);
            mode = R.id.optList;
        } else {
            arrPokemon = savedInstanceState.getParcelableArrayList(STATE_LIST);
            mode = savedInstanceState.getInt(STATE_MODE);
            RecyclerView rvPokedex = findViewById(R.id.rvPokemon);
            rvPokedex.setHasFixedSize(true);
            setMode(rvPokedex);
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(STATE_MODE, mode);
        outState.putParcelableArrayList(STATE_LIST, arrPokemon);
    }

    private void fillList() {
        arrPokemon = new ArrayList<>(dataNama.length);
        for (int i=0; i<dataNomorUrut.length; ++i){
            Pokemon p = new Pokemon(dataDetail[i],
                    dataNama[i], dataNomorUrut[i], dataGambar.getResourceId(i, -1));
            arrPokemon.add(p);
        }
    }

    void preparedata(){
        dataNama = getResources().getStringArray(R.array.data_nama);
        dataDetail = getResources().getStringArray(R.array.data_details);
        dataGambar = getResources().obtainTypedArray(R.array.data_photo);
        dataNomorUrut = getResources().getIntArray(R.array.data_dex);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.optmenu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        RecyclerView rv = findViewById(R.id.rvPokemon);
        mode = item.getItemId();
        setMode(rv);
        return super.onOptionsItemSelected(item);
    }

    private void setMode(RecyclerView rv) {
        switch (mode){
            case R.id.optList:
                showRecyclerList(new ListPokemonRVAdapter(arrPokemon), rv);
                break;
            case R.id.optGrid:
                showGridList(new GridPokemonAdapter(arrPokemon), rv);
                break;
            case R.id.optCard:
                showCardList(new CardViewPokemonAdapter(arrPokemon), rv);
                break;
        }
        setJudul();

    }

    private void setJudul(){
        switch (mode){
            case R.id.optList: setTitle("Mode List"); break;
            case R.id.optGrid: setTitle("Mode Grid"); break;
            case R.id.optCard: setTitle("Mode CardView"); break;
        }
    }

    private void showGridList(GridPokemonAdapter gridPokemonAdapter, RecyclerView rv) {
        rv.setLayoutManager(new GridLayoutManager(this, 2));
        rv.setAdapter(gridPokemonAdapter);
        gridPokemonAdapter.setCallback(new GridPokemonAdapter.OnItemClickCallback() {
            @Override
            public void onItemClicked(Pokemon p) {
                showDetail(p);
            }
        });
    }

    private void showCardList(CardViewPokemonAdapter cardViewPokemonAdapter, RecyclerView rv) {
        rv.setLayoutManager(new LinearLayoutManager(this));
        rv.setAdapter(cardViewPokemonAdapter);
        cardViewPokemonAdapter.setOnItemClickCallback(new CardViewPokemonAdapter.OnItemClickCallback() {
            @Override
            public void onItemClicked(Pokemon p) {
                showDetail(p);
            }
        });

        cardViewPokemonAdapter.setOnShareButtonCallback(new CardViewPokemonAdapter.OnShareButtonCallback() {
            @Override
            public void onButtonClicked(Pokemon p) {
                Intent send = new Intent(Intent.ACTION_SENDTO, Uri.parse("mailto:"));
                send.putExtra(Intent.EXTRA_SUBJECT, p.getNama());
                send.putExtra(Intent.EXTRA_TEXT, p.getNama()+"\n"+p.getDetail());
                startActivity(send);
            }
        });
    }

    private void showRecyclerList(ListPokemonRVAdapter pokemonRVAdapter, RecyclerView rvPokedex) {
        rvPokedex.setLayoutManager(new LinearLayoutManager(this));
        rvPokedex.setAdapter(pokemonRVAdapter);
        pokemonRVAdapter.setOnItemClickCallback(new ListPokemonRVAdapter.OnItemClickCallback() {
            @Override
            public void onItemClicked(Pokemon p) {
                showDetail(p);
            }
        });
    }

    private void showDetail(Pokemon p) {
        Intent intent = new Intent(Main2Activity.this, DetailActivity.class);
        intent.putExtra("POKEMON", p);
        startActivity(intent);
    }


}
