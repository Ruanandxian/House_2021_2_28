package com.example.house.normal;

import android.annotation.SuppressLint;
import android.content.SharedPreferences;
import android.os.Bundle;

import android.text.Editable;
import android.text.TextPaint;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.house.Adapter.myBaseAdapter;
import com.example.house.R;
import com.example.house.model.UserBean;
import com.example.house.model.myBean;
import com.google.gson.Gson;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import static android.content.Context.MODE_PRIVATE;


public class C_1Fragment extends Fragment {

    //    @Nullable
//    @Override
//    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
//        View view = inflater.inflate(R.layout.fragment_b, container,false);
//        return view;
//    }
    SharedPreferences sharedPreferences = null;
    SharedPreferences.Editor editor = null;
    UserBean userBean = null;
    private boolean isGetData = false;
    private List<myBean> myBeanList = new ArrayList<>();//用来存放数据的数组
    private com.example.house.myListView myListView;
    com.example.house.Adapter.myBaseAdapter myBaseAdapter = null;
    LinearLayout c_l2 = null;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_c_1, container, false);
//        myListView=view.findViewById(R.id.c_Lv);
//        myBean b1=new myBean("House",R.mipmap.laji);
//        myBean b2=new myBean("去年今日此门中",R.mipmap.laji);
//        myBean b3=new myBean("人面桃花相映红",R.mipmap.laji);
//        myBean b4=new myBean("人面不知何处去",R.mipmap.laji);
//        myBean b5=new myBean("桃花依旧笑春风",R.mipmap.laji);
//        myBeanList.add(b1);
//        myBeanList.add(b2);
//        myBeanList.add(b3);
//        myBeanList.add(b4);
//        myBeanList.add(b5);
//        myBaseAdapter=new myBaseAdapter(myBeanList,view.getContext());
//        myListView.setAdapter(myBaseAdapter);


        sharedPreferences = getContext().getSharedPreferences("share", MODE_PRIVATE);
        editor = sharedPreferences.edit();
        String User = sharedPreferences.getString("UserBean", "false");
        Gson gson = new Gson();
        userBean = gson.fromJson(User, UserBean.class);
        EditText ct = view.findViewById(R.id.c_e);
        Button c_yes = view.findViewById(R.id.c_yes);
        Button c_no = view.findViewById(R.id.c_no);
        LinearLayout Cl = view.findViewById(R.id.wo_c_view);
        CheckBox cx = view.findViewById(R.id.wo_c_checkBox);
        ImageButton ib = view.findViewById(R.id.wo_c_headimage);
        c_l2 = view.findViewById(R.id.c_l2);
        String squestion = sharedPreferences.getString("squestion", "fail");
        if (squestion.equals("fail")) {
            cx.setText("添加需要求助的内容0.0");
        } else {
            cx.setText(squestion);
        }

        ct.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            //et2改变后执行
            @Override
            public void afterTextChanged(Editable editable) {
                c_l2.setVisibility(View.VISIBLE);
            }
        });

        cx.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @SuppressLint("ResourceAsColor")
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    cx.setTextColor(R.color.bgColor_divier);
                    cx.setTextSize(8);
                    TextPaint tp = cx.getPaint();
                    tp.setFakeBoldText(false);
                    tp.setStrikeThruText(true);
                    ib.setVisibility(View.VISIBLE);
                } else {
                    cx.setTextColor(R.color.black);
                    cx.setTextSize(12);
                    TextPaint tp1 = cx.getPaint();
                    tp1.setFakeBoldText(true);
                    tp1.setStrikeThruText(false);
                    ib.setVisibility(View.GONE);
                }
            }
        });

        ib.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DeleteQuestion(userBean.getXuehao());
                editor.putString("squestion", "fail");
                editor.commit();
                cx.setText("添加需要求助的内容0.0");
                Cl.setVisibility(View.GONE);
                cx.setChecked(false);
            }
        });

        c_yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                myBeanList.add(new myBean(ct.getText().toString(),R.mipmap.laji));
//                myBaseAdapter.notifyDataSetChanged();
                SendQuestion(ct.getText().toString(), userBean.getXuehao());
                cx.setText(ct.getText().toString());
                editor.putString("squestion", ct.getText().toString());
                editor.commit();
                ct.setText("");
                Cl.setVisibility(View.VISIBLE);
                c_l2.setVisibility(View.GONE);


            }
        });

        c_no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ct.setText("");
                c_l2.setVisibility(View.GONE);
            }
        });
        return view;
    }


    private void SendQuestion(String question, String xuehao) {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder().url("http://47.101.144.65:8080/House/Question_s_add?username=" + xuehao + "&question=" + question).get().build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
            }//发送失败回调函数

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
            }
        });

    }

    private void DeleteQuestion(String xuehao) {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder().url("http://47.101.144.65:8080/House/Question_s_delete?username=" + xuehao).get().build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
            }//发送失败回调函数

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
            }
        });
    }

}
