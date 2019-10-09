package edu.stts.tugaspraktikumsenin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class TopUpActivity extends AppCompatActivity implements View.OnClickListener {
    User user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_top_up);
        findViewById(R.id.btnTopUp).setOnClickListener(this);
        findViewById(R.id.btnCancel).setOnClickListener(this);
        try {
            user = RegisterActivity.users.get(getIntent().getIntExtra("USER", -1));
        } catch (Exception e){
            Toast.makeText(this, "Data not found", Toast.LENGTH_SHORT).show();
            findViewById(R.id.btnTopUp).setEnabled(false);
            findViewById(R.id.btnCancel).setOnClickListener(this);
        }
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
                setResult(HomeActivity.TO_HOME);
                finish(); break;
            case R.id.optHistory:
                setResult(HomeActivity.TO_HISTORY);
                finish(); break;
            case R.id.optLogout:
                setResult(HomeActivity.LOGOUT);
                finish(); break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnTopUp:
                EditText editText = findViewById(R.id.edtValue);
                if (!editText.getText().toString().isEmpty()){
                    long l = Long.valueOf(editText.getText().toString());
                    if (l>=10000L){
                        user.setUang(user.getUang()+l);
                        user.getHistories().add(History.TopUp(l));
                        finish();
                    } else {
                        Toast.makeText(this, "Nominal Top Up terkecil adalah 10.000", Toast.LENGTH_SHORT).show();
                    }
                }
                break;
            case R.id.btnCancel: finish(); break;
        }
    }
}
