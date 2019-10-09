package edu.stts.tugaspraktikumsenin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

public class TransferActivity extends AppCompatActivity implements View.OnClickListener{
    ArrayList<User> userArrayList;
    ArrayAdapter<User> userArrayAdapter;
    User currentUser; Spinner sp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transfer);
        userArrayList = RegisterActivity.users;
        ArrayList<User> untukSpinner = new ArrayList<>(userArrayList);
        untukSpinner.remove(LoginActivity.currentUser);
        int index = getIntent().getIntExtra("USER", -1);
        if (index > -1){
            currentUser = RegisterActivity.users.get(index);
        } else {
            Toast.makeText(this, "Data not found", Toast.LENGTH_SHORT).show();
        }
        sp = findViewById(R.id.spinnerUser);
        userArrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item,
                untukSpinner);
        sp.setAdapter(userArrayAdapter);
        findViewById(R.id.btnCancel).setOnClickListener(this);
        findViewById(R.id.btnTransfer).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnCancel: finish(); break;
            case R.id.btnTransfer:
                User u = userArrayAdapter.getItem(sp.getSelectedItemPosition());
                int index = userArrayList.indexOf(u);
                u = userArrayList.get(index);
                EditText edtJumlah = findViewById(R.id.edtJumlah);
                if (!edtJumlah.getText().toString().isEmpty()){
                    long nominal = Long.valueOf(edtJumlah.getText().toString());
                    if (nominal <= currentUser.getUang()){
                        currentUser.setUang(currentUser.getUang() - nominal);
                        currentUser.getHistories().add(History.OutboundTransfer(u.getNama(),
                                nominal));

                        u.setUang(u.getUang()+nominal);
                        u.getHistories().add(History.InboundTransfer(currentUser.getNama(),
                                nominal));
                        finish();
                    } else {
                        Toast.makeText(this, "Uang tidak mencukupi", Toast.LENGTH_SHORT).show();
                    }
                }
                break;
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
                finish();
            case R.id.optLogout:
                setResult(HomeActivity.LOGOUT);
                finish(); break;
        }
        return super.onOptionsItemSelected(item);
    }
}
