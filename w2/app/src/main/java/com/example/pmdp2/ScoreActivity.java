
package com.example.pmdp2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;

public class ScoreActivity extends AppCompatActivity {

    ArrayList<player> temp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score);

        temp = getIntent().getParcelableArrayListExtra("array_player");
        Collections.sort(temp, Collections.<player>reverseOrder());
        TextView tvList = findViewById(R.id.tvList);
        tvList.setText("");
        for(player p:temp){
            tvList.setText(tvList.getText()+p.toString()+"\n");
        }
    }
}
