package edu.stts.tugaspraktikumsenin;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class HomeActivity extends AppCompatActivity implements View.OnClickListener {
    public static int TO_HOME = 101, LOGOUT=102, TO_HISTORY=103;
    User currentUser;
    ArrayList<User> users;
    ArrayList<String> merchants;
    TextView textViewName, textViewHP, textViewPassword;
    TextView textViewCash,  textViewPoint;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        users = RegisterActivity.users;
        currentUser = LoginActivity.currentUser;
        merchants = RegisterActivity.merchants;
        updatefield();

        findViewById(R.id.btnToPay).setOnClickListener(this);
        findViewById(R.id.btnToTopUp).setOnClickListener(this);
        findViewById(R.id.btnToTransfer).setOnClickListener(this);
    }

    private void updatefield() {
        if(textViewName==null)
            textViewName = findViewById(R.id.tvNama);
        if(textViewHP==null)
            textViewHP = findViewById(R.id.tvHP);
        if(textViewPassword==null)
            textViewPassword = findViewById(R.id.tvPassword);
        if(textViewCash==null)
            textViewCash = findViewById(R.id.tvCash);
        if(textViewPoint==null)
            textViewPoint = findViewById(R.id.tvPoints);

        textViewName.setText(currentUser.getNama());
        textViewHP.setText(currentUser.getHp());
        textViewPassword.setText(currentUser.getPassword());
        textViewCash.setText(String.valueOf(currentUser.getUang()));
        textViewPoint.setText(String.valueOf(currentUser.getPoint()));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.optionmenu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.optHome:
                Toast.makeText(this, "Sudah di home", Toast.LENGTH_SHORT).show(); break;
            case R.id.optLogout: finish(); break;
            case R.id.optHistory: startActivityForResult(new Intent(HomeActivity.this,
                    ViewHistoryActivity.class), 1); break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnToTopUp: Intent top_up = new Intent(HomeActivity.this, TopUpActivity.class);
                top_up.putExtra("USER", users.indexOf(currentUser)); startActivityForResult(top_up, 1);
                break;
            case R.id.btnToPay: Intent to_pay = new Intent(HomeActivity.this, PayActivity.class);
                to_pay.putExtra("USER", users.indexOf(currentUser)); startActivityForResult(to_pay, 1);
                break;
            case R.id.btnToTransfer: Intent to_transfer = new Intent(HomeActivity.this,
                    TransferActivity.class);
                to_transfer.putExtra("USER", users.indexOf(currentUser));
                startActivityForResult(to_transfer, 1);
                break;

        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        updatefield();
        if (resultCode == LOGOUT){
            finish();
        }
        else if (resultCode == TO_HISTORY){
            startActivityForResult(new Intent(HomeActivity.this, ViewHistoryActivity.class), 1);
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}
