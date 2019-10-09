package edu.stts.tugaspraktikumsenin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

public class RegisterActivity extends AppCompatActivity {
    public static ArrayList<User> users;
    public static ArrayList<String> merchants;
    EditText name, phone, password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        if (users == null){
            users = new ArrayList<>();
        }
        if (merchants == null){
            merchants = new ArrayList<>();
        }
        name = findViewById(R.id.edtNama);
        phone = findViewById(R.id.edtHP);
        password = findViewById(R.id.edtPassword);

        Button register, login;
        login = findViewById(R.id.btnToLogin);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent toLogin = new Intent(RegisterActivity.this, LoginActivity.class);
                startActivity(toLogin);
            }
        });

        register = findViewById(R.id.btnRegis);
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (name.getText().toString().isEmpty() || phone.getText().toString().isEmpty() || 
                        password.getText().toString().isEmpty()){
                    Toast.makeText(RegisterActivity.this, "Harap isi semua kolom yang ada", Toast.LENGTH_SHORT).show();
                } else {
                    users.add(new User(name.getText().toString(), phone.getText().toString(),
                            password.getText().toString()));
                    Toast.makeText(RegisterActivity.this, users.get(users.size()-1).toString(),
                            Toast.LENGTH_SHORT).show();
                    name.getText().clear();
                    phone.getText().clear();
                    password.getText().clear();
                }
            }
        });
    }
}
