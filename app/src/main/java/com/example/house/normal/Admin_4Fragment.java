package com.example.house.normal;

import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.bigkoo.alertview.AlertView;
import com.bigkoo.alertview.OnItemClickListener;
import com.example.house.Adapter.UserFoldingCellAdapter;
import com.example.house.R;
import com.example.house.model.EmployeeBean;
import com.example.house.model.Student;
import com.example.house.model.UserBean;
import com.example.house.model.myBean;
import com.github.johnpersano.supertoasts.library.Style;
import com.github.johnpersano.supertoasts.library.SuperActivityToast;
import com.github.johnpersano.supertoasts.library.utils.PaletteUtils;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.ramotion.foldingcell.FoldingCell;
import com.yalantis.phoenix.PullToRefreshView;

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


public class Admin_4Fragment extends Fragment {

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
    private ListView userFoldingCellList;
    private UserFoldingCellAdapter adapter;
    private List<EmployeeBean> data;
    private PullToRefreshView mPullToRefreshView;
    private List<Student> students = new ArrayList<>();//用来存放数据的数组

    EditText a5_1 = null;
    EditText a5_2 = null;
    EditText a5_4 = null;
    EditText a5_5 = null;
    Button b1 = null;
    Button b2 = null;
    CheckBox a5_cb1 = null;
    CheckBox a5_cb2 = null;

    private String xuehao = null;
    private String name = null;
    private String room = null;
    private String cla = null;
    private String sex = null;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.admin_5, container, false);
        a5_1 = view.findViewById(R.id.a5_1);
        a5_2 = view.findViewById(R.id.a5_2);
        a5_4 = view.findViewById(R.id.a5_4);
        a5_5 = view.findViewById(R.id.a5_5);
        b1 = view.findViewById(R.id.a5_b1);
        b2 = view.findViewById(R.id.a5_b2);
        a5_cb1 = view.findViewById(R.id.a5_cb1);
        a5_cb2 = view.findViewById(R.id.a5_cb2);

        a5_cb1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    sex = "男";
                    a5_cb2.setChecked(false);
                }
            }
        });
        a5_cb2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    sex = "女";
                    a5_cb1.setChecked(false);
                }
            }
        });


        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                xuehao = a5_1.getText().toString();
                name = a5_2.getText().toString();
                room = a5_4.getText().toString();
                cla = a5_5.getText().toString();
                System.out.println(xuehao + name + room + cla);
                if (xuehao.equals("") && name.equals("") && room.equals("") && cla.equals("") && sex.equals("")) {
                    mess("内容不能为空，请检查相关填写");
                } else {
                    sendByOKHttp_user(xuehao, name, sex, room, cla);
                    mess("成功");
                    a5_1.setText("");
                    a5_2.setText("");
                    a5_4.setText("");
                    a5_5.setText("");
                    xuehao = "";
                    name = "";
                    room = "";
                    cla = "";
                    sex = "";
                    a5_cb1.setChecked(false);
                    a5_cb2.setChecked(false);

                }
            }
        });

        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                a5_1.setText("");
                a5_2.setText("");
                a5_4.setText("");
                a5_5.setText("");
                a5_cb1.setChecked(false);
                a5_cb2.setChecked(false);
            }
        });


        return view;
    }


    private void sendByOKHttp_user(String xuehao, String name, String sex, String room, String cla) {
        OkHttpClient client = new OkHttpClient();
//        System.out.println(userBean.getXuehao());
//        System.out.println(nicheng);
        Request request = new Request.Builder().url("http://47.101.144.65:8080/House/Insert_user?xuehao=" + xuehao + "&name=" + name + "&sex=" + sex + "&room=" + room + "&cla=" + cla).get().build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
            }//发送失败回调函数

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {

            }
        });
    }


    public void mess(String mess) {
        //                提示消息
        SuperActivityToast.create(getContext(), new Style(), Style.TYPE_STANDARD)
                .setButtonText("UNDO")
                .setButtonIconResource(R.drawable.icon)
                .setProgressBarColor(Color.WHITE)
                .setText(mess)
                .setDuration(Style.DURATION_LONG)
                .setFrame(Style.FRAME_LOLLIPOP)
                .setColor(PaletteUtils.getSolidColor(PaletteUtils.MATERIAL_GREEN))
                .setAnimations(Style.ANIMATIONS_POP)
                .show();
    }
}


