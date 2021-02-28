package com.example.house.Adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.house.model.EmployeeBean;
import com.example.house.R;
import com.ramotion.foldingcell.FoldingCell;


import org.w3c.dom.Text;

import java.util.List;

public class UserFoldingCellAdapter extends BaseAdapter {
    private Context mContext;
    private List<EmployeeBean> data;

    public UserFoldingCellAdapter(Context mContext, List<EmployeeBean> data) {
        this.mContext = mContext;
        this.data = data;
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int i) {
        return data.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View convertView, ViewGroup viewGroup) {
        FoldingCell view = (FoldingCell) convertView;
        ViewHolder holder = null;
        if (view == null) {
            view = (FoldingCell) LayoutInflater.from(mContext).inflate(R.layout.cell, viewGroup, false);
            holder = new ViewHolder();
            holder.imgHeadPortrait = view.findViewById(R.id.head_portrait);
            holder.UserName = view.findViewById(R.id.user_name);
            holder.NameInfo = view.findViewById(R.id.name_info);
            holder.QQ = view.findViewById(R.id.QQ_info);
            holder.ti = view.findViewById(R.id.title_index);
            holder.tun = view.findViewById(R.id.title_user_name);
            holder.EmployeeRole = view.findViewById(R.id.employee_role);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }
        String url = "http://47.101.144.65:8080/one/dist/picture/" + data.get(i).getXuehao() + "." + "jpg";
        Glide.with(view.getContext()).load(url).into(holder.imgHeadPortrait);
        holder.ti.setText("" + i);
        holder.tun.setText(data.get(i).getUserName());
        holder.UserName.setText(data.get(i).getUserName());
        holder.NameInfo.setText(data.get(i).getNameInfo());
        holder.QQ.setText(data.get(i).getQQ());
        holder.EmployeeRole.setText((data.get(i).getEmployeeRole()));
        return view;
    }

    class ViewHolder {
        ImageView imgHeadPortrait;
        TextView UserName;
        TextView NameInfo;
        TextView EmployeeRole;
        TextView QQ;
        TextView ti;
        TextView tun;
    }
}