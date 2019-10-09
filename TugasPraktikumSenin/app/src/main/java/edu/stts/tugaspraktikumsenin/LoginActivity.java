package edu.stts.tugaspraktikumsenin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

public class LoginActivity extends AppCompatActivity {
    ArrayList<User> users;
    EditText phone, password;
    public static User currentUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        this.users = RegisterActivity.users;
        phone = findViewById(R.id.edtHP);
        password = findViewById(R.id.edtPassword);

        Button login, register;
        login = findViewById(R.id.btnLogin);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (phone.getText().toString().isEmpty() || password.getText().toString().isEmpty()){
                } else {
                    LoginActivity.currentUser = null;
                    for (User u : users){
                        if (u.getHp().equalsIgnoreCase(phone.getText().toString()) &&
                                password.getText().toString().equalsIgnoreCase(u.getPassword())){
                            currentUser = u; break;
                        }
                    }
                    if (currentUser == null){
                        Toast.makeText(LoginActivity.this, "Data tidak ada / salah", Toast.LENGTH_SHORT).show();
                        phone.getText().clear(); password.getText().clear();
                    } else {
                        phone.getText().clear(); password.getText().clear();
                        Intent home = new Intent(LoginActivity.this, HomeActivity.class);
                        startActivity(home);
                    }
                }
            }
        });
        register = findViewById(R.id.btnToRegis);
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

}
