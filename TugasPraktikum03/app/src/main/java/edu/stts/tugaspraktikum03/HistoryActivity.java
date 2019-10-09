package edu.stts.tugaspraktikum03;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

public class HistoryActivity extends AppCompatActivity implements View.OnClickListener {
    ArrayList<Ticket> tickets;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);
        tickets = getIntent().getParcelableArrayListExtra("HISTORY");
        TextView tvHistory = findViewById(R.id.tvHistory);
        tvHistory.setText("");
        String str = "";
        for (Ticket ticket:tickets) {
            tvHistory.setText(tvHistory.getText()+"\n\n"+ticket.toString());
        }
        Button back = findViewById(R.id.btnBack);
        back.setOnClickListener(this);
    }
    @Override
    public void onClick(View v) {
        finish();
    }
}
