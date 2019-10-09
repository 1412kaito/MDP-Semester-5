package edu.stts.weekseven;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class PokemonAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<Pokemon> pokemons;

    public PokemonAdapter(Context context) {
        this.context = context;
        this.pokemons = new ArrayList<>();
    }

    public ArrayList<Pokemon> getPokemons() {
        return pokemons;
    }

    @Override
    public int getCount() {
        return pokemons.size();
    }

    @Override
    public Object getItem(int position) {
        return pokemons.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null){
            convertView = LayoutInflater.from(context).inflate(R.layout.item_pokemon, parent,
                    false);
        }
        ViewHolder vh = new ViewHolder(convertView);
        vh.bind((Pokemon)getItem(position));
        return convertView;
    }

    private class ViewHolder {
        private TextView textViewNama, textViewDetail, textViewNomor;
        private ImageView imageView;
        ViewHolder(View v){
            textViewNama = v.findViewById(R.id.namaPokemon);
            textViewDetail = v.findViewById(R.id.detailPokemon);
            textViewNomor = v.findViewById(R.id.dexNo);
            imageView = v.findViewById(R.id.imgPokemon);
        }

        void bind(Pokemon p){
            textViewNomor.setText((""+p.getDexNo()));
//            imageView.setImageResource(p.getGambar());
            textViewNama.setText(p.getNama());
            textViewDetail.setText(p.getDetail());
        }
    }
}
