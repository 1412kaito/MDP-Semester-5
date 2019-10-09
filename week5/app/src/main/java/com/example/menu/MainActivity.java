package com.example.menu;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.PopupMenu;
import android.widget.PopupMenu.OnMenuItemClickListener;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements OnMenuItemClickListener, View.OnClickListener {

    ArrayList<mahasiswa> dataMahasiswa;
    EditText nrp, nama, hasil;
    TextView major;
    String chosen_major;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dataMahasiswa = new ArrayList<>();
        nrp = findViewById(R.id.edtNRP);
        nama = findViewById(R.id.edtNama);
        hasil = findViewById(R.id.edtHasil);
        major = findViewById(R.id.edtMajor);
        registerForContextMenu(major);
        chosen_major = "";
        Button pp = findViewById(R.id.btnPopup);
        pp.setOnClickListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.option_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        getMenuInflater().inflate(R.menu.context_menu, menu);
        super.onCreateContextMenu(menu, v, menuInfo);
        menu.setHeaderTitle("Choose one");
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        chosen_major = item.getTitle().toString();
        major.setText(chosen_major);
        return super.onContextItemSelected(item);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.search:
                Toast.makeText(this, "Search", Toast.LENGTH_SHORT).show();
                break;
            case R.id.viewData:
                resetTampilan();
                String t = "";
                for (mahasiswa m:dataMahasiswa) {
                    t += m.toString() + "\n";
                }
                hasil.setText(t);
                break;
            case R.id.sendData:
                Intent i = new Intent(Intent.ACTION_SENDTO, Uri.parse("sms:081216282788"));
                i.putExtra("sms_body", dataMahasiswa.toString());
                startActivity(i);
                Toast.makeText(this, "Send", Toast.LENGTH_SHORT).show();
                break;
            case R.id.saveData:
                simpanData();
                resetTampilan();
                break;
            case R.id.clearData:
                resetTampilan();
                dataMahasiswa = new ArrayList<>();
                break;

        }
        return super.onOptionsItemSelected(item);
    }

    void resetTampilan(){
        nama.setText("");
        nrp.setText("");
        major.setText("");
        hasil.setText("");
        nrp.requestFocus();
    }

    void simpanData(){
        if (nrp.getText().length()>0){
            if (nama.getText().length()>0){
                if (major.getText().length()>0){
                    dataMahasiswa.add(new mahasiswa( Long.parseLong(nrp.getText().toString()),
                            nama.getText().toString(), major.getText().toString()));
                    String t = "";
                    for (mahasiswa m:dataMahasiswa) {
                        t += m.toString() + "\n";
                    }
                    hasil.setText(t);
                }else{
                    major.requestFocus();
                }
            } else{
                nama.requestFocus();
            }
        } else {
            nrp.requestFocus();
        }
    }

    @Override
    public boolean onMenuItemClick(MenuItem item) {
        switch (item.getTitle().toString()){
            case "Save":
                simpanData();
                Toast.makeText(this, "save", Toast.LENGTH_SHORT).show();
                break;
            case "Reset":
                resetTampilan();
                break;
            case "Edit Last Data":
                Toast.makeText(this, "edtlasdata", Toast.LENGTH_SHORT).show();
                break;
        }
        return false;
    }

    @Override
    public void onClick(View v) {
        PopupMenu p = new PopupMenu(getApplicationContext(), v);
        getMenuInflater().inflate(R.menu.popup_menu, p.getMenu());
        p.setOnMenuItemClickListener(this);
        p.show();
    }
}
