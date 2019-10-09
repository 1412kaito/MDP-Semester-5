package com.example.pmdp2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Random;

public class GameActivity extends AppCompatActivity {
    private TextView tvSkor;
    public static final int RETURNSCORE = 420;
    private Random ran;
    private int counter;
    private ArrayList<Button> btns;
    private int skor;
    private LinearLayout induk;

    void init(ViewGroup induk){
        for(int i=0; i<induk.getChildCount(); ++i){
            View v = induk.getChildAt(i);
            if (v instanceof Button){
                setColor(v);
                btns.add((Button)v);
            } else if (v instanceof ViewGroup){
                init((ViewGroup) v);
            }
        }
    }

    private void setColor(View v) {
        Integer arrC[] = {Color.YELLOW, Color.RED, Color.BLUE, Color.GREEN};
        ArrayList<Integer> arr = new ArrayList<>();
        for (Integer i: arrC) arr.add(i);
        if (v.getBackground() instanceof ColorDrawable)
            arr.remove(((Integer)((ColorDrawable)(v.getBackground())).getColor()));
        int t = ran.nextInt(arr.size());
        v.setBackgroundColor(arr.get(t));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        tvSkor = findViewById(R.id.tvSkor);
        ran = new Random(Calendar.getInstance().getTimeInMillis());
        induk = findViewById(R.id.induk);
        counter = 0; skor = 0;
        tvSkor.setText("Score: "+skor);
        btns = new ArrayList<>();
        init(induk);
        int i=0;
        for(Button b:btns){
            b.setTag(i+"");
            ++i;
            b.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    EditText edtNama = findViewById(R.id.edtNama);
                    Button btn = (Button) v;
                    String tag = btn.getTag().toString();
                    ArrayList<Button> adjacents = new ArrayList<>();
                    Button atasKiri, atas, atasKanan, kiri, kanan, bawahKiri, bawah, bawahKanan;
                    if(edtNama.getText().length()<1){
                        edtNama.requestFocus();
                        Toast.makeText(GameActivity.this, "Isi kolom nama dulu", Toast.LENGTH_SHORT).show();
                    }
                    else{
                        int t = Integer.valueOf(tag);
                        if (t-10-1>=0 && t%10!=0){
                            atasKiri = induk.findViewWithTag(String.valueOf(t-11));
                            adjacents.add(atasKiri);
                        }
                        if (t-10>=0){
                            atas = induk.findViewWithTag(String.valueOf(t-10));
                            adjacents.add(atas);
                        }
                        if (t-10+1>=0 && t%10!=9){
                            atasKanan = induk.findViewWithTag(String.valueOf(t-9));
                            adjacents.add(atasKanan);
                        }
                        if(t-1>=0 && t%10!=0){
                            kiri = induk.findViewWithTag(String.valueOf(t-1));
                            adjacents.add(kiri);
                        }
                        if (t+1<100 && t%10!=9){
                            kanan = induk.findViewWithTag(String.valueOf(t+1));
                            adjacents.add(kanan);
                        }
                        if (t+1+10<100 && t%10!=9){
                            bawahKanan = induk.findViewWithTag(String.valueOf(t+11));
                            adjacents.add(bawahKanan);
                        }
                        if (t+10<100){
                            bawah = induk.findViewWithTag(String.valueOf(t+10));
                            adjacents.add(bawah);
                        }
                        if (t+9<100 && t%10!=0){
                            bawahKiri = induk.findViewWithTag(String.valueOf(t+9));
                            adjacents.add(bawahKiri);
                        }
                        ColorDrawable now = (ColorDrawable)v.getBackground();
                        int tempSkor = 0;
                        try {
                            Thread.sleep(100);
                            for (Button sebelah:adjacents) {
                                ColorDrawable next = (ColorDrawable) sebelah.getBackground();
                                if (next.getColor() == now.getColor()){
                                    setColor(sebelah); tempSkor++;
                                }
                            }
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        tempSkor++;
                        setColor(v);
                        Toast.makeText(GameActivity.this, tempSkor+"", Toast.LENGTH_SHORT).show();
                        ++counter;
                        skor+=tempSkor;
                        tvSkor.setText("Score: "+skor);
                        if (counter>=10) {
                            Toast.makeText(GameActivity.this, "Game Over", Toast.LENGTH_SHORT).show();
                            Intent i = new Intent();
                            i.putExtra("player", new player(edtNama.getText().toString(), skor));
                            setResult(RETURNSCORE, i);
                            finish();
                        }
                    }
                }
            });
        }
    }
}
