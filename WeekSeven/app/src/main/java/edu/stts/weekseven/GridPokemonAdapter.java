package edu.stts.weekseven;

import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerView.Adapter;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.ArrayList;

public class GridPokemonAdapter extends Adapter<GridPokemonAdapter.GridViewHolder> {
    public interface OnItemClickCallback{
        void onItemClicked(Pokemon p);
    };
    private OnItemClickCallback callback;

    public void setCallback(OnItemClickCallback callback) {
        this.callback = callback;
    }

    ArrayList<Pokemon> data;

    public GridPokemonAdapter(ArrayList<Pokemon> data) {
        this.data = data;
    }

    @NonNull
    @Override
    public GridPokemonAdapter.GridViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.grid_pokemon, parent, false);
        return  new GridViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull GridPokemonAdapter.GridViewHolder holder, int position) {
        final Pokemon p = data.get(position);
        Glide.with(holder.itemView.getContext())
                .load(p.getGambar())
                .fitCenter()
                .into(holder.imageView);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callback.onItemClicked(p);
            }
        });
    }


    @Override
    public int getItemCount() {
        return data.size();
    }

    public class GridViewHolder extends RecyclerView.ViewHolder {
        private ImageView imageView;

        public GridViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imgPokemon);

        }
    }
}
