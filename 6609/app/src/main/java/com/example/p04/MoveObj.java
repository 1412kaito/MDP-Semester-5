package com.example.p04;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class MoveObj extends AppCompatActivity {
    public static final String EXTRA_MAHASISWA = "extra_mahasiswa";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_move_obj);
        TextView tv = findViewById(R.id.tvObject);
        Mahasiswa temp = getIntent().getParcelableExtra(EXTRA_MAHASISWA);
        tv.setText(temp.toString());

    }
}
