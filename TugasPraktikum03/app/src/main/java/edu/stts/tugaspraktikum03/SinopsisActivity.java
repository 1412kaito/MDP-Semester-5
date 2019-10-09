package edu.stts.tugaspraktikum03;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class SinopsisActivity extends AppCompatActivity implements View.OnClickListener {
    public static int SINOPSIS = 6609;
    Parcelable[] m;
    Movies[] mov;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sinopsis);
        m = getIntent().getParcelableArrayExtra("MOVIES");
        if (m != null){
            mov = Movies.CREATOR.newArray(m.length);
            for (int i=0; i<m.length; ++i){
                mov[i] = (Movies)m[i];
            }
            TextView tvTitle = findViewById(R.id.title);
            tvTitle.setText(mov[0].nama);
            TextView tvSinopsis = findViewById(R.id.sinopsis);
            tvSinopsis.setText(mov[0].sinopsis);
        }
        Button buy = findViewById(R.id.btnBuy);
        Button back = findViewById(R.id.btnBack);
        buy.setOnClickListener(this);
        back.setOnClickListener(this);
    }
    @Override
    public void onClick(View v) {
        Intent i;
        switch (v.getId()){
            case R.id.btnBuy:
                i = new Intent(SinopsisActivity.this, BuyActivity.class);
                i.putExtra("MOVIES", m);
                startActivityForResult(i,SINOPSIS);
                break;
            case R.id.btnBack:
                finish();
                break;
        }
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode==SINOPSIS && resultCode==BuyActivity.BELI){
            Intent i = new Intent();
            Ticket t = data.getParcelableExtra("TICKET");
            Toast.makeText(this, t.m.nama, Toast.LENGTH_SHORT).show();
            i.putExtra("TICKET", t);
            setResult(SINOPSIS, i);
            finish();
        } else {
            setResult(RESULT_CANCELED);
            finish();
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}
