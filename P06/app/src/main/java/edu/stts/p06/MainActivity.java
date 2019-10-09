package edu.stts.p06;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    MyEditText edtNrp; Button btnSearch;
    Spinner spMajor; ListView lvMhs;
    String[] isiSpiner = {"All", "Computational Intelligence", "Software Engineering", "Internet" +
            " Technology"};
    String[] dataNrp, dataNama, dataMajor;
    ArrayList<mahasiswa> dataMahasiswa;
    ArrayAdapter<mahasiswa> mahasiswaArrayAdapter;
    MhsAdapter custom;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        edtNrp = findViewById(R.id.edtSearch);
        btnSearch = findViewById(R.id.btnSearch);
        spMajor = findViewById(R.id.spMajor);
        lvMhs = findViewById(R.id.lvmahasiswa);
        ArrayAdapter<String> stringArrayAdapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_dropdown_item);
        stringArrayAdapter.addAll(isiSpiner);
        spMajor.setAdapter(stringArrayAdapter);

        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean temp = edtNrp.hasOnClickListeners();
                Toast.makeText(MainActivity.this, temp+"", Toast.LENGTH_SHORT).show();
            }
        });

        prepMahasiswa();
        lvMhs.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                mahasiswa m = (mahasiswa)parent.getItemAtPosition(position);
                Toast.makeText(MainActivity.this, m.toString() ,
                        Toast.LENGTH_SHORT).show();
            }
        });
        spMajor.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String dipilih = (String)parent.getItemAtPosition(position);
                if (dipilih.equalsIgnoreCase("All")){
                    prepMahasiswa();
                } else {
                    filterByMajor(dipilih);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) { }
        });


    }

    private void filterByMajor(String dipilih) {
        dataMahasiswa = new ArrayList<>();
        dataNrp = getResources().getStringArray(R.array.data_nrp);
        dataNama = getResources().getStringArray(R.array.data_nama);
        dataMajor = getResources().getStringArray(R.array.data_major);

        for (int i=0; i<dataNama.length; ++i){
            if (dipilih.equalsIgnoreCase(dataMajor[i])){
                mahasiswa t = new mahasiswa();
                t.setNama(dataNama[i]);
                t.setNrp(Integer.valueOf(dataNrp[i]));
                t.setMajor(dataMajor[i]);
                dataMahasiswa.add(t);
            }
        }
        mahasiswaArrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,
                dataMahasiswa);
        mahasiswaArrayAdapter.notifyDataSetChanged();
        lvMhs.setAdapter(mahasiswaArrayAdapter);
    }

    void filterByNRP(String NRP){
        dataMahasiswa = new ArrayList<>();
        dataNrp = getResources().getStringArray(R.array.data_nrp);
        dataNama = getResources().getStringArray(R.array.data_nama);
        dataMajor = getResources().getStringArray(R.array.data_major);

        for (int i=0; i<dataNama.length; ++i){
            if (NRP.equalsIgnoreCase(dataNrp[i])){
                mahasiswa t = new mahasiswa();
                t.setNama(dataNama[i]);
                t.setNrp(Integer.valueOf(dataNrp[i]));
                t.setMajor(dataMajor[i]);
                dataMahasiswa.add(t);
            }
        }
        mahasiswaArrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,
                dataMahasiswa);
        mahasiswaArrayAdapter.notifyDataSetChanged();
        lvMhs.setAdapter(mahasiswaArrayAdapter);
    }

    private void prepMahasiswa() {
        dataMahasiswa = new ArrayList<>();
        dataNrp = getResources().getStringArray(R.array.data_nrp);
        dataNama = getResources().getStringArray(R.array.data_nama);
        dataMajor = getResources().getStringArray(R.array.data_major);

        for (int i=0; i<dataNama.length; ++i){
            mahasiswa t = new mahasiswa();
            t.setNama(dataNama[i]);
            t.setNrp(Integer.valueOf(dataNrp[i]));
            t.setMajor(dataMajor[i]);
            dataMahasiswa.add(t);
        }
        mahasiswaArrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,
                dataMahasiswa);
        mahasiswaArrayAdapter.notifyDataSetChanged();
        lvMhs.setAdapter(mahasiswaArrayAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.menu_simple:
                mahasiswaArrayAdapter = new ArrayAdapter<mahasiswa>(this, android.R.layout.simple_list_item_1);
                lvMhs.setAdapter(mahasiswaArrayAdapter);
                break;
            case R.id.menu_custom:
                custom = new MhsAdapter(this);
                custom.arraymhs = dataMahasiswa;
                lvMhs.setAdapter(custom);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

}
