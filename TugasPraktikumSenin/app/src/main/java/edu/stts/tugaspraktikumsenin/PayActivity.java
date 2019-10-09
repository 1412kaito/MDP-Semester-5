package edu.stts.tugaspraktikumsenin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class PayActivity extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemClickListener {
    User user; ListView lvMerchant;
    ArrayAdapter<String> adapterMerchant;
    ArrayList<String> merchants;
    RadioButton rbCash, rbPoint;
    EditText edtJumlah, edtMerchant;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pay);
        try {
            user = RegisterActivity.users.get(getIntent().getIntExtra("USER", -1));
            merchants = RegisterActivity.merchants;
            TextView textView = findViewById(R.id.tvCash);
            textView.setText("Cash: "+user.getUang());
            textView = findViewById(R.id.tvPoints);
            textView.setText("Point: "+user.getPoint());
            textView = findViewById(R.id.tvNama);
            textView.setText("Nama: "+user.getNama());
        } catch (Exception e){
            Toast.makeText(this, "Data not found", Toast.LENGTH_SHORT).show();
            user = new User("coba", "1234", "password");
            merchants = new ArrayList<>();
        }
        lvMerchant = findViewById(R.id.lvMerchant);
        adapterMerchant = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1, merchants);
        lvMerchant.setAdapter(adapterMerchant);
        adapterMerchant.notifyDataSetChanged();
        lvMerchant.setOnItemClickListener(this);
        edtMerchant = findViewById(R.id.edtMerchant);
        rbCash = findViewById(R.id.radioCash); rbCash.setChecked(true);
        rbPoint = findViewById(R.id.radioPoint);
        edtJumlah = findViewById(R.id.edtJumlah);
        findViewById(R.id.btnPay).setOnClickListener(this);
        findViewById(R.id.btnCancel).setOnClickListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.optionmenu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.optHome: setResult(HomeActivity.TO_HOME);
                finish(); break;
            case R.id.optHistory: setResult(HomeActivity.TO_HISTORY);
                finish(); break;
            case R.id.optLogout: setResult(HomeActivity.LOGOUT);
                finish(); break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnPay: if(rbCash.isChecked()){
                if (!edtMerchant.getText().toString().isEmpty() && !edtJumlah.getText().toString().isEmpty()){
                    long amount = Long.valueOf(edtJumlah.getText().toString());
                    if (user.getUang() < amount ){
                        Toast.makeText(this, "Saldo tidak cukup", Toast.LENGTH_SHORT).show();
                    } else {
                        if (!merchants.contains(edtMerchant.getText().toString())){
                            merchants.add(edtMerchant.getText().toString());
                            adapterMerchant.notifyDataSetChanged();
                        }
                        user.setUang(user.getUang() - amount);
                        user.getHistories().add(History.PayMerchant(edtMerchant.getText().toString(), amount));
                        user.setPoint(user.getPoint() + (amount/2));
                        user.getHistories().add(History.Point(edtMerchant.getText().toString(),
                                amount/2));
                        finish();
                    }
                }
            } else {
                if (!edtMerchant.getText().toString().isEmpty() && !edtJumlah.getText().toString().isEmpty()){
                    long amount = Long.valueOf(edtJumlah.getText().toString());
                    if (user.getPoint() < amount){
                        Toast.makeText(this, "Point tidak cukup", Toast.LENGTH_SHORT).show();
                    } else {
                        if (!merchants.contains(edtMerchant.getText().toString())){
                            merchants.add(edtMerchant.getText().toString());
                            adapterMerchant.notifyDataSetChanged();
                        }
                        user.setPoint(user.getPoint() - amount);
                        user.getHistories().add(History.PayMerchant(edtMerchant.getText().toString(), amount));
                        finish();
                    }
                }
            }
            break;
            case R.id.btnCancel: finish(); break;
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        edtMerchant.setText(adapterMerchant.getItem(position));
    }
}
