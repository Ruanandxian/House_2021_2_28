package com.example.house.normal;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.house.Adapter.UserFoldingCellAdapter;
import com.example.house.model.EmployeeBean;
import com.example.house.R;
import com.example.house.model.Student;
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


public class C_2Fragment extends Fragment {

    //    @Nullable
//    @Override
//    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
//        View view = inflater.inflate(R.layout.fragment_b, container,false);
//        return view;
//    }
    private FoldingCell fc;
    private Button btnToggle;
    private Button btnToggleInstant;
    private ListView userFoldingCellList;
    private UserFoldingCellAdapter adapter;
    private List<EmployeeBean> data;
    SharedPreferences sharedPreferences = null;
    SharedPreferences.Editor editor = null;
    private PullToRefreshView mPullToRefreshView;
    private boolean isGetData = false;
    private List<Student> students = new ArrayList<>();//用来存放数据的数组

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_c_2, container, false);
//        FoldingCell fc = (FoldingCell) view.findViewById(R.id.folding_cell);
//        // attach click listener to folding cell
//        fc.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                fc.toggle(false);
//            }
//        });
        sharedPreferences = getContext().getSharedPreferences("share", MODE_PRIVATE);
        editor = sharedPreferences.edit();
        userFoldingCellList = view.findViewById(R.id.user_list_view);


        mPullToRefreshView = (PullToRefreshView) view.findViewById(R.id.pull_to_refresh);
        mPullToRefreshView.setOnRefreshListener(new PullToRefreshView.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mPullToRefreshView.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        findStudent();
                        initData();
                        adapter = new UserFoldingCellAdapter(getActivity(), data);
                        userFoldingCellList.setAdapter(adapter);
                        initEvent();


                        mPullToRefreshView.setRefreshing(false);
                    }
                }, 1500);
            }
        });


        initData();
        adapter = new UserFoldingCellAdapter(getActivity(), data);
        userFoldingCellList.setAdapter(adapter);
        initEvent();


        return view;
    }

    private void initEvent() {
        userFoldingCellList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                ((FoldingCell) view).toggle(false);
            }
        });
    }


    private void initData() {
        String string = sharedPreferences.getString("Students", "fail");
        Gson gson = new Gson();
        students = gson.fromJson(string, new TypeToken<List<Student>>() {
        }.getType());
        EmployeeBean bean;
        data = new ArrayList<>();
        System.out.println(students.size());
        for (int i = 0; i < students.size(); i++) {
            bean = new EmployeeBean(students.get(i).getXuehao(), students.get(i).getNicheng(), students.get(i).getRoom(), students.get(i).getQQ(), students.get(i).getSquestion());
            data.add(bean);
        }


//        bean = new EmployeeBean(R.drawable.touxiang,"山河","3-132","急需考试用品");
//        data.add(bean);
//        bean = new EmployeeBean(R.drawable.touxiang,"山河","3-132","急需考试用品");
//        data.add(bean);
//        bean = new EmployeeBean(R.drawable.touxiang,"山河","3-132","急需考试用品");
//        data.add(bean);
    }


    // OKHttp
    private void findStudent() {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder().url("http://47.101.144.65:8080/House/Question_s_find").get().build();
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
                            editor.putString("Students", string);
                            editor.commit();
                        }
                    }
                });
            }
        });
    }
}
