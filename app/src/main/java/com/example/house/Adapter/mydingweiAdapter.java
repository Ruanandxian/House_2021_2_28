package com.example.house.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.text.TextPaint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.house.R;
import com.example.house.model.Dingwei;
import com.example.house.model.myBean;

import java.util.List;

public class mydingweiAdapter extends BaseAdapter {
    private List<Dingwei> myBeans;
    private Context mContext;

    public mydingweiAdapter(List<Dingwei> myBeans, Context mContext) {
        super();
        this.myBeans = myBeans;
        this.mContext = mContext;
    }

    @Override
    public int getCount() {
        return myBeans.size();
    }

    @Override
    public Object getItem(int position) {
        return myBeans.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Dingwei myBean = (Dingwei) getItem(position);
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View view = inflater.inflate(R.layout.admin_cell_title, null);
        TextView t1 = view.findViewById(R.id.admin_title_1);
        TextView t2 = view.findViewById(R.id.admin_title_2);
        TextView t3 = view.findViewById(R.id.admin_title_3);
        t1.setText(myBean.getNicheng());
        t2.setText(myBean.getClasses());
        t3.setText(myBean.getQQ());
        return view;
    }
}
