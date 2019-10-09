package com.example.pmdp2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class InviteActivity extends AppCompatActivity {
    EditText phoneNumber;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_invite);
        phoneNumber = findViewById(R.id.phonenumber);
        Button btnKirim = findViewById(R.id.btnSEND);
        btnKirim.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                Intent i = new Intent(Intent.ACTION_SENDTO, Uri.parse("smsto:"+phoneNumber.getText().toString()));
                i.putExtra("sms_body", "Yuk main game seru ini!");
                startActivity(i);
            }
        });
    }
}
