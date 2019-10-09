package edu.stts.tugaspraktikum03;

import androidx.annotation.*;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Locale;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    public static final int MAIN = 1001;
    public static Movies[] boo, hobs, it;
    ArrayList<Ticket> tickets;
    private static Long uang;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tickets = new ArrayList<>();
        boo = new Movies[3];
        boo[0] = new Movies("BOO", "GALAXY XXI", new String[]{"13:00", "15:00"}, "Ini sinopsi BOO");
        boo[1] = new Movies("BOO", "PAKUWON MALL XXI", new String[]{"13:00", "15:00"}, "Ini sinopsi BOO");
        boo[2] = new Movies("BOO", "TUNJUNGAN 5 XXI", new String[]{"12:00", "17:00"}, "Ini sinopsi BOO");
        hobs = new Movies[3];
        hobs[0] = new Movies("HOBBS AND SHAW", "GALAXY XXI", new String[]{"14:00", "16:00"}, "Ini sinopsi HOBBS AND SHAW");
        hobs[1] = new Movies("HOBBS AND SHAW", "PAKUWON MALL XXI", new String[]{"12:00", "14:00"}, "Ini sinopsi HOBBS AND SHAW");
        hobs[2] = new Movies("HOBBS AND SHAW", "TUNJUNGAN 5 XXI", new String[]{"14:30", "19:30"},"Ini sinopsi HOBBS AND SHAW");
        it = new Movies[3];
        it[0] = new Movies("IT CHAPTER TWO", "GALAXY XXI", new String[]{"13:30", "17:30"},"Ini sinopsi IT CHAPTER TWO");
        it[1] = new Movies("IT CHAPTER TWO", "PAKUWON MALL XXI", new String[]{"16:00", "19:00"},"Ini sinopsi IT CHAPTER TWO");
        it[2] = new Movies("IT CHAPTER TWO", "TUNJUNGAN 5 XXI", new String[]{"16:00", "21:30"},"Ini sinopsi IT CHAPTER TWO");
        uang = (long)100000;
        setUang();
        ImageButton btn1 = findViewById(R.id.imgBoo);
        btn1.setOnClickListener(this);
        btn1 = findViewById(R.id.imgHS);
        btn1.setOnClickListener(this);
        btn1 = findViewById(R.id.imgIT);
        btn1.setOnClickListener(this);
        Button btnBioskop = findViewById(R.id.btn1);
        btnBioskop.setOnClickListener(this);
        btnBioskop = findViewById(R.id.btn2);
        btnBioskop.setOnClickListener(this);
        btnBioskop = findViewById(R.id.btn3);
        btnBioskop.setOnClickListener(this);
    }
    private void setUang() {
        TextView tvUang = findViewById(R.id.tvUang);
        DecimalFormat f = (DecimalFormat) NumberFormat.getInstance(Locale.US);
        tvUang.setText("Rp. "+f.format(uang));
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.option_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.omTopup:
                Intent topup = new Intent(MainActivity.this, TopUpActivity.class);
                topup.putExtra("UANG", uang);
                startActivityForResult(topup, 420);
                break;
            case R.id.omMytickets:
                Intent history = new Intent(MainActivity.this, HistoryActivity.class);
                history.putParcelableArrayListExtra("HISTORY", tickets);
                startActivity(history);
                break;
        }
        return super.onOptionsItemSelected(item);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode==420 && resultCode==RESULT_OK){
            uang = data.getLongExtra("UANG", uang);
            setUang();
        } else if (resultCode != RESULT_CANCELED) {
            try {
                Ticket t = data.getParcelableExtra("TICKET");
                this.uang -= t.jumlah*25000;
                tickets.add(t);
                setUang();
            } catch (Exception e){
                Toast.makeText(this, "Back to Home", Toast.LENGTH_SHORT).show();
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.imgBoo: movieBoo(v); break;
            case R.id.imgHS: movieHobbs(v); break;
            case R.id.imgIT: movieIT(v); break;
            case R.id.btn1:
                Intent galaxy = new Intent(MainActivity.this, BuyActivity.class);
                Movies[] Bioskop = new Movies[3];
                Bioskop[0] = boo[0];
                Bioskop[1] = hobs[0];
                Bioskop[2] = it[0];
                galaxy.putExtra("BIOSKOP", Bioskop);
                startActivityForResult(galaxy, MAIN);
                break;
            case R.id.btn2:
                Intent pakuwon = new Intent(MainActivity.this, BuyActivity.class);
                Movies[] Biosko = new Movies[3];
                Biosko[0] = boo[1];
                Biosko[1] = hobs[1];
                Biosko[2] = it[1];
                pakuwon.putExtra("BIOSKOP", Biosko);
                startActivityForResult(pakuwon, MAIN);
                break;
            case R.id.btn3:
                Intent tunjungan = new Intent(MainActivity.this, BuyActivity.class);
                Movies[] Biosk = new Movies[3];
                Biosk[0] = boo[2];
                Biosk[1] = hobs[2];
                Biosk[2] = it[2];
                tunjungan.putExtra("BIOSKOP", Biosk);
                startActivityForResult(tunjungan, MAIN);
                break;
        }
    }
    private void movieIT(View v) {
        PopupMenu p= new PopupMenu(getApplicationContext(), v);
        getMenuInflater().inflate(R.menu.popup, p.getMenu());
        p.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            Movies[] m = MainActivity.it;
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                Intent i;
                switch (item.getItemId()){
                    case R.id.popupviewSinopsis:
                        i = new Intent(MainActivity.this, SinopsisActivity.class);
                        i.putExtra("MOVIES", m);
                        startActivityForResult(i,MAIN);
                        break;
                    case R.id.popupbuyTickets:
                        i = new Intent(MainActivity.this, BuyActivity.class);
                        i.putExtra("MOVIES", m);
                        startActivityForResult(i,MAIN);
                        break;
                }
                return false;
            }
        });
        p.show();
    }

    private void movieHobbs(View v) {
        PopupMenu p= new PopupMenu(getApplicationContext(), v);
        getMenuInflater().inflate(R.menu.popup, p.getMenu());
        p.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            Movies[] m = MainActivity.hobs;
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                Intent i;
                switch (item.getItemId()){
                    case R.id.popupviewSinopsis:
                        i = new Intent(MainActivity.this, SinopsisActivity.class);
                        i.putExtra("MOVIES", m);
                        startActivityForResult(i,MAIN);
                        break;
                    case R.id.popupbuyTickets:
                        i = new Intent(MainActivity.this, BuyActivity.class);
                        i.putExtra("MOVIES", m);
                        startActivityForResult(i,MAIN);
                        break;
                }
                return false;
            }
        });
        p.show();
    }

    private void movieBoo(View v) {
        PopupMenu p= new PopupMenu(getApplicationContext(), v);
        getMenuInflater().inflate(R.menu.popup, p.getMenu());
        p.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            Parcelable[] m = MainActivity.boo;
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                Intent i;
                switch (item.getItemId()){
                    case R.id.popupviewSinopsis:
                        i = new Intent(MainActivity.this, SinopsisActivity.class);
                        i.putExtra("MOVIES", m);
                        startActivityForResult(i,MAIN);
                        break;
                    case R.id.popupbuyTickets:
                        i = new Intent(MainActivity.this, BuyActivity.class);
                        i.putExtra("MOVIES", m);
                        startActivityForResult(i,MAIN);
                        break;
                }
                return false;
            }
        });
        p.show();
    }

    public static long getUang() {
        return uang;
    }
}
