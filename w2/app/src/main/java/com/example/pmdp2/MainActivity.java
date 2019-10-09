package com.example.pmdp2;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    public static final int NEWGAME = 999;
    ArrayList<player> arrPlayer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        arrPlayer=new ArrayList<>();
        Button btnNewGame, btnHighScore, btnInvite;
        btnNewGame= findViewById(R.id.btnNEWGAME);
        btnHighScore= findViewById(R.id.btnHIGHSCORE);
        btnInvite = findViewById(R.id.btnINVITE);
        btnNewGame.setOnClickListener(this);
        btnHighScore.setOnClickListener(this);
        btnInvite.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent i;
        switch (v.getId()){
            case R.id.btnHIGHSCORE:
                i = new Intent(MainActivity.this, ScoreActivity.class);
                i.putParcelableArrayListExtra("array_player",arrPlayer);
                startActivity(i);
                break;
            case R.id.btnNEWGAME:
                i = new Intent(MainActivity.this, GameActivity.class);
                startActivityForResult(i, MainActivity.NEWGAME);
                break;
            case R.id.btnINVITE:
                i = new Intent(MainActivity.this, InviteActivity.class);
                startActivity(i);
                break;

        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode==this.NEWGAME && resultCode == GameActivity.RETURNSCORE){
            player p = data.getParcelableExtra("player");
            arrPlayer.add(p);
        }
    }
}
