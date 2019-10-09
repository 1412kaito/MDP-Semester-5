package edu.stts.tugaspraktikumsenin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import edu.stts.tugaspraktikumsenin.History.KIND;

public class ViewHistoryActivity extends AppCompatActivity {
    User u;
    HistoryAdapter historyAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_history);
        u= LoginActivity.currentUser;
        ListView lv = findViewById(R.id.lvHistory);
        historyAdapter = new HistoryAdapter(this, u.getHistories());
        lv.setAdapter(historyAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.optionmenu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.optHome: finish();
            case R.id.optLogout: setResult(HomeActivity.LOGOUT); finish(); break;
            case R.id.optHistory:
                Toast.makeText(this, "Sudah di History", Toast.LENGTH_SHORT).show();;
        }
        return super.onOptionsItemSelected(item);
    }
    
}

class HistoryAdapter extends BaseAdapter{
    ArrayList<History> histories;
    Context context;

    public HistoryAdapter(Context context, ArrayList<History> histories) {
        this.context = context;
        this.histories = histories;
    }

    @Override
    public int getCount() {
        return histories.size();
    }

    @Override
    public Object getItem(int position) {
        return histories.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null){
            convertView = LayoutInflater.from(context).inflate(R.layout.item_history, parent,
                    false);
        }
        HistoryHolder historyHolder = new HistoryHolder(convertView);
        historyHolder.bind((History) getItem(position));
        return convertView;
    }

    private class HistoryHolder {
        TextView keterangan, jenis, nominal;
        public HistoryHolder(View convertView) {
            keterangan = convertView.findViewById(R.id.tvHistoryKeterangan);
            jenis = convertView.findViewById(R.id.tvHistoryJenis);
            nominal = convertView.findViewById(R.id.tvHistoryNominal);
        }

        void bind(History h){
            keterangan.setText(h.getTujuan().isEmpty()?h.getKind().name():h.getTujuan());
            jenis.setText(h.getKind().name());
            if (h.getKind().equals(KIND.CASHBACK) || h.getKind().equals(KIND.INBOUND_TRANSFER)
                    || h.getKind().equals(KIND.TOPUP)){
                nominal.setTextColor(Color.GREEN);
                nominal.setText("+");
            } else {
                nominal.setTextColor(Color.RED);
                nominal.setText("-");
            }
            nominal.setText(nominal.getText()+"Rp. "+h.getAmount());
        }
    }
}