package com.example.dicodingsubmission;

import android.graphics.drawable.Drawable;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class PokemonAdapter extends RecyclerView.Adapter<PokemonAdapter.PokemonViewHolder>{
    @NonNull

    private ArrayList<Pokemon> arrayPokemon;
    private OnItemClickCallback onItemClickCallback;



    public PokemonAdapter(@NonNull ArrayList<Pokemon> arrayPokemon) {
        this.arrayPokemon = arrayPokemon;
    }

    @Override
    public String toString() {
        return super.toString();
    }

    public void setOnItemClickCallback(OnItemClickCallback onItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback;
    }

    @NonNull
    @Override
    public PokemonViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.item_row, parent, false);
        return new PokemonViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull final PokemonViewHolder holder, final int position) {
        Pokemon p = arrayPokemon.get(position);
        holder.imgPhoto.setImageResource(p.getGambar());
        holder.tvName.setText(p.getNama());
        holder.tvDetail.setText(p.getDetail());
        String temp = "#"+p.getDexNo()+"  ";
        holder.tvNumber.setText(temp);

        holder.itemView.setOnClickListener(
                new View.OnClickListener(){
                    @Override
                    public void onClick(View v) {
//                        Toast.makeText(v.getContext(), v.getId()+"", Toast.LENGTH_SHORT).show();
                        onItemClickCallback.onItemClicked(arrayPokemon.get(holder.getAdapterPosition()));
                    }
                }
        );
    }

    @Override
    public int getItemCount() {
        return arrayPokemon.size();
    }

    //inner class
    class PokemonViewHolder extends RecyclerView.ViewHolder {
        ImageView imgPhoto;
        TextView tvName, tvDetail, tvNumber;
        PokemonViewHolder(View itemView) {
            super(itemView);
            imgPhoto = itemView.findViewById(R.id.img_item_photo);
            tvName = itemView.findViewById(R.id.tv_item_name);
            tvDetail = itemView.findViewById(R.id.tv_item_detail);
            tvNumber = itemView.findViewById(R.id.tv_item_no);
        }
    }

    //inner interface
    //entah kenapa nda dibuat eksternal saja?
    //CARI TAHU
    interface OnItemClickCallback {
        void onItemClicked(Pokemon data);
    }
}
