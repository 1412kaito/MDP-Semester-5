package edu.stts.weekseven;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.ArrayList;

public class ListPokemonRVAdapter extends RecyclerView.Adapter<ListPokemonRVAdapter.ListViewHolder> {
    private ArrayList<Pokemon> data;

    public ListPokemonRVAdapter(ArrayList<Pokemon> data) {
        this.data = data;
    }

    private OnItemClickCallback onItemClickCallback;

    public void setOnItemClickCallback(OnItemClickCallback onItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback;
    }

    @NonNull
    @Override
    public ListPokemonRVAdapter.ListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_pokemon,parent, false);
        return new ListViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull final ListPokemonRVAdapter.ListViewHolder holder, int position) {
        Pokemon p = data.get(position);
        Glide.with(holder.itemView.getContext())
        .load(p.getGambar())
        .apply(new RequestOptions().override(80,80))
        .into(holder.imageView);
        holder.textViewNama.setText(p.getNama());
        holder.textViewDetail.setText(p.getDetail());
        holder.textViewNomor.setText(""+p.getDexNo());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onItemClickCallback.onItemClicked(data.get(holder.getAdapterPosition()));
            }
        });
//        holder.textViewNomor.setText((""+p.getDexNo()));
//        holder.imageView.setImageResource(p.getGambar());
//        holder.textViewNama.setText(p.getNama());
//        holder.textViewDetail.setText(p.getDetail());
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class ListViewHolder extends RecyclerView.ViewHolder {
        private TextView textViewNama, textViewDetail, textViewNomor;
        private ImageView imageView;
        public ListViewHolder(@NonNull View v) {
            super(v);
            textViewNama = v.findViewById(R.id.namaPokemon);
            textViewDetail = v.findViewById(R.id.detailPokemon);
            textViewNomor = v.findViewById(R.id.dexNo);
            imageView = v.findViewById(R.id.imgPokemon);
        }
    }

    public interface OnItemClickCallback {
        void onItemClicked(Pokemon p);
    }
}
