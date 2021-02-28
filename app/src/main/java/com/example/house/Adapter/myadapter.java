package com.example.house.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.text.TextPaint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.house.R;
import com.example.house.model.myBean;

import java.util.List;
import java.util.Objects;

public class myadapter extends ArrayAdapter {
    private final int ImageId;
    private String cheaktext;

    public myadapter(Context context, int headImage, List<myBean> obj) {
        super(context, headImage, obj);
        ImageId = headImage;

    }


    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        myBean myBean = (myBean) getItem(position);
        View view = LayoutInflater.from(getContext()).inflate(ImageId, parent, false);//这个是实例化一个我们自己写的界面Item
        LinearLayout linearLayout = view.findViewById(R.id.ll_view);
        CheckBox checkBox = view.findViewById(R.id.checkBox);
        ImageButton headimage = view.findViewById(R.id.headimage);
        headimage.setBackgroundResource(myBean.getImageID());
        checkBox.setText(myBean.getText());
        TextPaint tp = checkBox.getPaint();

        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @SuppressLint("ResourceAsColor")
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    checkBox.setTextColor(R.color.bgColor_divier);
                    checkBox.setTextSize(8);
                    tp.setFakeBoldText(false);
                    tp.setStrikeThruText(true);
                    headimage.setVisibility(View.VISIBLE);
                } else {
                    checkBox.setTextColor(R.color.black);
                    checkBox.setTextSize(12);
                    tp.setFakeBoldText(true);
                    tp.setStrikeThruText(false);
                    headimage.setVisibility(View.GONE);
                }
            }
        });

        headimage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                data.remove(position);
//                adapater.notifyDataSetChanged();

//                notifyDataSetChanged();

            }
        });


        return view;
    }
}
