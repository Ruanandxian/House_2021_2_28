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

import com.example.house.R;
import com.example.house.model.myBean;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class myBaseAdapter extends BaseAdapter {
    private List<myBean> myBeans;
    private Context mContext;

    public myBaseAdapter(List<myBean> myBeans, Context mContext) {
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
        myBean myBean = (myBean) getItem(position);
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View view = inflater.inflate(R.layout.daiban, null);
        CheckBox checkBox = view.findViewById(R.id.checkBox);
        ImageButton headimage = view.findViewById(R.id.headimage);
        headimage.setBackgroundResource(myBean.getImageID());
        checkBox.setText(myBean.getText());


        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @SuppressLint("ResourceAsColor")
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    checkBox.setTextColor(R.color.bgColor_divier);
                    checkBox.setTextSize(8);
                    TextPaint tp = checkBox.getPaint();
                    tp.setFakeBoldText(false);
                    tp.setStrikeThruText(true);
                    headimage.setVisibility(View.VISIBLE);

                } else {
                    checkBox.setTextColor(R.color.black);
                    checkBox.setTextSize(12);
                    TextPaint tp1 = checkBox.getPaint();
                    tp1.setFakeBoldText(true);
                    tp1.setStrikeThruText(false);
                    headimage.setVisibility(View.GONE);
                }

            }
        });

        headimage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myBeans.remove(position);
                myBaseAdapter.this.notifyDataSetChanged();


            }
        });

        return view;
    }
}
