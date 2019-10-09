package edu.stts.tugaspraktikum03;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class DetailActivity extends AppCompatActivity implements View.OnClickListener{
    public static int DETAIL = 6607;
    Movies m; int jam, jumlah;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        m = getIntent().getParcelableExtra("FILM YANG INI");
        jam = getIntent().getIntExtra("JAM", 0);

        TextView tvFilm = findViewById(R.id.tvFilm);
        tvFilm.setText(m.nama);

        TextView tvBioskop = findViewById(R.id.tvBioskop);
        tvBioskop.setText(m.tempat);

        TextView tvJam = findViewById(R.id.tvJam);
        tvJam.setText(m.jam[jam]);

        Button buttonBuy, buttonCancel;
        buttonBuy = findViewById(R.id.btnBuy);
        buttonCancel = findViewById(R.id.btnCancel);
        buttonBuy.setOnClickListener(this);
        buttonCancel.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        EditText editTextJumlah = findViewById(R.id.edtJumlah);
        this.jumlah = Integer.valueOf(editTextJumlah.getText().toString());
        switch (v.getId()){
            case R.id.btnBuy:
                if (MainActivity.getUang() >= (this.jumlah*25000)){
                    Intent i = new Intent();
                    i.putExtra("TICKET", new Ticket(this.m, this.jam, this.jumlah));
                    setResult(RESULT_OK, i);
                    finish();
                } else {
                    Toast.makeText(this, "Uang tidak cukup. Top Up dulu", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.btnCancel:
                setResult(RESULT_CANCELED);
                finish();
                break;
        }
    }
}
