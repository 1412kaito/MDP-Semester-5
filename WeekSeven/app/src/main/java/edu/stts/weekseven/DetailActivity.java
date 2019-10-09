package edu.stts.weekseven;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import de.hdodenhof.circleimageview.CircleImageView;

public class DetailActivity extends AppCompatActivity {

    Pokemon fromMain;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        try {
            fromMain = getIntent().getParcelableExtra("POKEMON");
            CircleImageView circleImageView = findViewById(R.id.imgPokemon);
            TextView textViewNama, textViewNomor, textViewDetail;
            textViewDetail = findViewById(R.id.detailPokemon);
            textViewNama = findViewById(R.id.namaPokemon);
            textViewNomor = findViewById(R.id.dexNo);
            textViewDetail.setText(fromMain.getDetail());
            textViewNama.setText(fromMain.getNama());
            textViewNomor.setText(fromMain.getDexNo()+". ");
            Glide.with(getApplicationContext()).load(fromMain.getGambar())
                    .fitCenter()
                    .into(circleImageView);
            Button share = findViewById(R.id.btnShare);
            share.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent send = new Intent(Intent.ACTION_SENDTO, Uri.parse("mailto:"));
                    send.putExtra(Intent.EXTRA_SUBJECT, fromMain.getNama());
                    send.putExtra(Intent.EXTRA_TEXT, fromMain.getNama()+"\n"+fromMain.getDetail());
                    startActivity(send);
                }
            });
        } catch (Exception e){
            Toast.makeText(this, "Data not found", Toast.LENGTH_SHORT).show();
        }
        Button back = findViewById(R.id.btnBack);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
