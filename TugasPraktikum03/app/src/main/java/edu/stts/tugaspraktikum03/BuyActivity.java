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

public class BuyActivity extends AppCompatActivity implements View.OnClickListener{
    Parcelable[] m, b;
    public static final int BELI = 6606;
    boolean isMovie, isBioskop;
    Movies[] mov, bio;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buy);
        isMovie = false; isBioskop = false;
        m = getIntent().getParcelableArrayExtra("MOVIES");
        b = getIntent().getParcelableArrayExtra("BIOSKOP");
        if (m != null){
            mov = Movies.CREATOR.newArray(m.length);
            for (int i=0; i<m.length; ++i){
                mov[i] = (Movies)m[i];
            }
            isMovie = true;
            bio = mov;
        }
        if (b != null){
            bio = Movies.CREATOR.newArray(b.length);
            for (int i=0; i<b.length; ++i){
                bio[i] = (Movies)b[i];
            }
            isBioskop = true;
            mov = bio;
        }
        Button back = findViewById(R.id.btnBack);
        back.setOnClickListener(this);

        if (isMovie){
            TextView textViewKnown = findViewById(R.id.known);
            textViewKnown.setText(mov[0].nama);
            TextView tv1, tv2, tv3;
            tv1 = findViewById(R.id.tv1);
            tv2 = findViewById(R.id.tv2);
            tv3 = findViewById(R.id.tv3);
            tv1.setText(mov[0].tempat);
            tv2.setText(mov[1].tempat);
            tv3.setText(mov[2].tempat);
            Button btn1, btn2;
            btn1 = findViewById(R.id.btn11);
            btn1.setText(mov[0].jam[0]);
            btn2 = findViewById(R.id.btn12);
            btn2.setText(mov[0].jam[1]);
            btn1.setOnClickListener(this);
            btn2.setOnClickListener(this);
            btn1 = findViewById(R.id.btn21);
            btn1.setText(mov[1].jam[0]);
            btn2 = findViewById(R.id.btn22);
            btn2.setText(mov[1].jam[1]);
            btn1.setOnClickListener(this);
            btn2.setOnClickListener(this);
            btn1 = findViewById(R.id.btn31);
            btn1.setText(mov[2].jam[0]);
            btn2 = findViewById(R.id.btn32);
            btn2.setText(mov[2].jam[1]);
            btn1.setOnClickListener(this);
            btn2.setOnClickListener(this);
        }
        else if (isBioskop){
            TextView textViewKnown = findViewById(R.id.known);
            textViewKnown.setText(bio[0].tempat);
            TextView tv1, tv2, tv3;
            tv1 = findViewById(R.id.tv1);
            tv2 = findViewById(R.id.tv2);
            tv3 = findViewById(R.id.tv3);
            tv1.setText(bio[0].nama);
            tv2.setText(bio[1].nama);
            tv3.setText(bio[2].nama);
            Button btn1, btn2;
            btn1 = findViewById(R.id.btn11);
            btn1.setText(bio[0].jam[0]);
            btn2 = findViewById(R.id.btn12);
            btn2.setText(bio[0].jam[1]);
            btn1.setOnClickListener(this);
            btn2.setOnClickListener(this);
            btn1 = findViewById(R.id.btn21);
            btn1.setText(bio[1].jam[0]);
            btn2 = findViewById(R.id.btn22);
            btn2.setText(bio[1].jam[1]);
            btn1.setOnClickListener(this);
            btn2.setOnClickListener(this);
            btn1 = findViewById(R.id.btn31);
            btn1.setText(bio[2].jam[0]);
            btn2 = findViewById(R.id.btn32);
            btn2.setText(bio[2].jam[1]);
            btn1.setOnClickListener(this);
            btn2.setOnClickListener(this);
        }
    }

    @Override
    public void onClick(View v) {
        Intent beli;
        beli = new Intent(BuyActivity.this, DetailActivity.class);
        switch (v.getId()){
            case R.id.btnBack: finish();
                break;
            case R.id.btn11:
                beli.putExtra("FILM YANG INI", mov[0] );
                beli.putExtra("JAM", 0);
                startActivityForResult(beli, this.BELI);
                break;
            case R.id.btn12:
                beli.putExtra("FILM YANG INI", mov[0]);
                beli.putExtra("JAM", 1);
                startActivityForResult(beli, this.BELI);
                break;
            case R.id.btn21:
                beli.putExtra("FILM YANG INI", mov[1]);
                beli.putExtra("JAM", 0);
                startActivityForResult(beli, this.BELI);
                break;
            case R.id.btn22:
                beli.putExtra("FILM YANG INI", mov[1]);
                beli.putExtra("JAM", 1);
                startActivityForResult(beli, this.BELI);
                break;
            case R.id.btn31:
                beli.putExtra("FILM YANG INI", mov[2] );
                beli.putExtra("JAM", 0);
                startActivityForResult(beli, this.BELI);
                break;
            case R.id.btn32:
                beli.putExtra("FILM YANG INI", mov[2]);
                beli.putExtra("JAM", 1);
                startActivityForResult(beli, this.BELI);
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (resultCode == DetailActivity.RESULT_OK && requestCode==this.BELI){
            Intent i = new Intent();
            Ticket t = data.getParcelableExtra("TICKET");
            i.putExtra("TICKET", t);
            setResult(this.BELI, i);
            finish();
        } else if (resultCode==RESULT_CANCELED){
            setResult(RESULT_CANCELED);
            finish();
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}
