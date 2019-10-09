package edu.stts.p06;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class MhsAdapter extends BaseAdapter {
    public Context context;
    public ArrayList<mahasiswa> arraymhs;

    public MhsAdapter(Context context) {
        this.context = context;
        this.arraymhs = new ArrayList<>();
    }

    @Override
    public int getCount() {
        return arraymhs.size();
    }

    @Override
    public Object getItem(int position) {
        return arraymhs.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView==null) convertView =
                LayoutInflater.from(context).inflate(R.layout.item_mahasiswa, parent, false);

        ViewHolder vh = new ViewHolder (convertView);
        mahasiswa m = (mahasiswa) getItem(position);
        vh.bind(m);
        return convertView;
    }

    private class ViewHolder {
        TextView nrp, nama, major;
        public ViewHolder(View v) {
            nrp = v.findViewById(R.id.tvNrp);
            major = v.findViewById(R.id.tvMajor);
            nama = v.findViewById(R.id.tvNama);
        }

        public void bind(mahasiswa m){
            nrp.setText(m.getNrp()+"");
            major.setText(m.getMajor());
            nama.setText(m.getNama());
        }
    }
}
