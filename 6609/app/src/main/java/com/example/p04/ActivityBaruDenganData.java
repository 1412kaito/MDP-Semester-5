package com.example.p04;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

public class ActivityBaruDenganData extends AppCompatActivity {
    public static final String EXTRA_NRP = "extra_nrp";
    public static final String EXTRA_NAMA = "extra_nama";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_baru_dengan_data);

        String data;
        Long nrp = getIntent().getLongExtra(EXTRA_NRP, 0);
        String nama = getIntent().getStringExtra(EXTRA_NAMA);
        data = nama + "\n" + nrp.toString();
//        Toast.makeText(this, data, Toast.LENGTH_SHORT).show();

        TextView tv = findViewById(R.id.tvData);
        tv.setText(data);

    }
}
