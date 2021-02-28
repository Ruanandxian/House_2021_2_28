package com.example.house.normal;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.house.Adapter.UserFoldingCellAdapter;
import com.example.house.Adapter.mydingweiAdapter;
import com.example.house.R;
import com.example.house.model.Dingwei;
import com.example.house.model.EmployeeBean;
import com.example.house.model.Student;
import com.example.house.model.UserBean;
import com.example.house.model.myBean;
import com.example.house.myListView;
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


public class Admin_3Fragment extends Fragment {


    SharedPreferences sharedPreferences = null;
    SharedPreferences.Editor editor = null;
    private List<Dingwei> Dws = null;//用来存放数据的数组
    private ListView myListView;
    private mydingweiAdapter mydingweiAdapter = null;
    private PullToRefreshView mPullToRefreshView = null;
    private List<Dingwei> students = new ArrayList<>();//用来存放数据的数组

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.admin_4, container, false);
        myListView = view.findViewById(R.id.admin_4_lv);
        sharedPreferences = getContext().getSharedPreferences("share", MODE_PRIVATE);
        editor = sharedPreferences.edit();
        mPullToRefreshView = (PullToRefreshView) view.findViewById(R.id.admin_4_pull_to_refresh);
        mPullToRefreshView.setOnRefreshListener(new PullToRefreshView.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mPullToRefreshView.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        finddStudent();
                        initData();
                        mydingweiAdapter = new mydingweiAdapter(Dws, getContext());
                        myListView.setAdapter(mydingweiAdapter);
                        mPullToRefreshView.setRefreshing(false);
                    }
                }, 1500);
            }
        });

        initData();
        mydingweiAdapter = new mydingweiAdapter(Dws, getContext());
        myListView.setAdapter(mydingweiAdapter);
        return view;
    }


    private void initData() {
        String string = sharedPreferences.getString("Student_dw", "fail");
        Gson gson = new Gson();
        students = gson.fromJson(string, new TypeToken<List<Dingwei>>() {
        }.getType());
        Dws = new ArrayList<>();
        for (int i = 0; i < students.size(); i++) {
            if (students.get(i).getDingwei_tag().equals("0")) {
                Dingwei d1 = new Dingwei(students.get(i).getNicheng(), students.get(i).getDingwei_tag(), students.get(i).getClasses(), students.get(i).getQQ());
                Dws.add(d1);
            } else {
            }

        }


    }


    // OKHttp
    private void finddStudent() {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder().url("http://47.101.144.65:8080/House/Dingwei_tag_find").get().build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
            }//发送失败回调函数

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                final String string = response.body().string();
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if (string.trim().equals("fail")) {
                        } else {
                            editor.putString("Student_dw", string);
                            editor.commit();
                        }
                    }
                });
            }
        });
    }
}


