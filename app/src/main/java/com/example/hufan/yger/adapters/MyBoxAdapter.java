package com.example.hufan.yger.adapters;

import java.util.ArrayList;
import java.util.zip.Inflater;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.hufan.yger.R;
import com.example.hufan.yger.model.Boxs;

public class MyBoxAdapter extends BaseAdapter {
    ArrayList<Boxs> data;
    LayoutInflater inflater;
    public MyBoxAdapter(ArrayList<Boxs> data, Context context) {
        // TODO Auto-generated constructor stub
        this.data = data;
        this.inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        // TODO Auto-generated method stub
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return position;
    }

    public void notifyDataSetChanged(ArrayList<Boxs> data) {
        // TODO Auto-generated method stub
        this.data = data;
        notifyDataSetChanged();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub
        viewHolder holder = null;
        Boxs boxs = data.get(position);
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.boxs, null);
            holder = new viewHolder();
            holder.tv1 = convertView.findViewById(R.id.textView1);
            holder.tv2 = convertView.findViewById(R.id.textView2);
            holder.tv3 = convertView.findViewById(R.id.textView3);
            convertView.setTag(holder);
        } else {
            holder = (viewHolder) convertView.getTag();
        }
        holder.tv1.setText("姓名:" + boxs.getName());
        holder.tv2.setText("总数:" + boxs.getZs());
        holder.tv3.setText("已扫:" + boxs.getYs());
        return convertView;
    }

    class viewHolder {
        TextView tv1, tv2, tv3;
    }
}
