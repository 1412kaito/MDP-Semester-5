package edu.stts.tugaspraktikum03;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class TopUpActivity extends AppCompatActivity implements View.OnClickListener {
    Long uang;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_top_up);
        uang = getIntent().getLongExtra("UANG", -1);
        if (uang == -1){
            Toast.makeText(this, "Passing data gagal", Toast.LENGTH_SHORT).show();
        }
        Button b = findViewById(R.id.btnTopup);
        b.setOnClickListener(this);

        b = findViewById(R.id.btnCancel);
        b.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnCancel:
                finish();
            break;
            case R.id.btnTopup:
                EditText tv = findViewById(R.id.edtTopup);
                long tambahan = Long.valueOf(tv.getText().toString());
                if (tambahan >= 50000 && tambahan % 50000 == 0){
                    Intent selesai = new Intent();
                    uang += tambahan;
                    selesai.putExtra("UANG", this.uang);
                    setResult(RESULT_OK, selesai);
                    finish();
                } else {
                    Toast.makeText(this, "Top Up hanya bisa kelipatan 50000", Toast.LENGTH_SHORT).show();
                }
            break;
        }
    }
}
