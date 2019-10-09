package com.example.p04;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class MoveResult extends AppCompatActivity {
    RadioGroup rgMajor;
    public static final String EXTRA_NAMA_MAJOR = "extra_nama_major";
    public static final int RESULT_CODE = 6609;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_move_result);

        Button btnPilih = findViewById(R.id.btnSubmit);
        rgMajor = findViewById(R.id.rgMajor);

        btnPilih.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                RadioButton t = findViewById(rgMajor.getCheckedRadioButtonId());
//                Toast.makeText(MoveResult.this, t.getText(), Toast.LENGTH_SHORT).show();
                Intent temp;
                temp = new Intent();
                temp.putExtra(EXTRA_NAMA_MAJOR, t.getText().toString());
                setResult(RESULT_CODE, temp);
                finish();
            }
        });
    }
}
