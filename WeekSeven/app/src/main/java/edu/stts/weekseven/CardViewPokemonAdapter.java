package edu.stts.weekseven;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class CardViewPokemonAdapter extends RecyclerView.Adapter<CardViewPokemonAdapter.CVViewHolder> {
    ArrayList<Pokemon> data;

    private OnItemClickCallback onItemClickCallback;
    private OnShareButtonCallback onShareButtonCallback;

    public CardViewPokemonAdapter(ArrayList<Pokemon> arrPokemon) {
        super();
        data = arrPokemon;
    }

    @NonNull
    @Override
    public CVViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_pokemon, parent, false);
        return new CVViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final CVViewHolder holder, int position) {
        final Pokemon p = data.get(position);
        Glide.with(holder.itemView.getContext())
                .load(p.getGambar()).fitCenter().into(holder.img);
        holder.nama.setText(p.getDexNo()+" - "+p.getNama());
        holder.desc.setText(p.getDetail());
        Button fav = holder.itemView.findViewById(R.id.btn_set_favorite);
        fav.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Toast.makeText(view.getContext(), "Favorite "+data.get(holder.getAdapterPosition()).getNama(), Toast.LENGTH_SHORT).show();
            }
        });

        Button share = holder.itemView.findViewById(R.id.btn_set_share);
        share.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                onShareButtonCallback.onButtonClicked(p);
            }
        });

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemClickCallback.onItemClicked(p);
            }
        });

    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public void setOnShareButtonCallback(OnShareButtonCallback onShareButtonCallback) {
        this.onShareButtonCallback = onShareButtonCallback;
    }

    public class CVViewHolder extends RecyclerView.ViewHolder {
        private ImageView img;
        private TextView nama, desc;
        public CVViewHolder(@NonNull View itemView) {
            super(itemView);
            this.img = itemView.findViewById(R.id.img_photo);
            this.nama = itemView.findViewById(R.id.tv_name);
            this.desc = itemView.findViewById(R.id.tv_desc);
        }
    }

    public void setOnItemClickCallback(OnItemClickCallback onItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback;
    }

    public interface OnItemClickCallback {
        void onItemClicked(Pokemon p);
    }

    public interface OnShareButtonCallback {
        void onButtonClicked(Pokemon p);
    }
}
