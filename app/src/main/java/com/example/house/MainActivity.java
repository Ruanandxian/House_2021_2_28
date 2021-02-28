package com.example.house;

import android.Manifest;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;


import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.amap.api.maps.AMap;
import com.amap.api.maps.MapView;

import com.example.house.Adapter.myadapter;
import com.example.house.model.Student;
import com.example.house.model.UserBean;
import com.example.house.model.myBean;
import com.github.johnpersano.supertoasts.library.Style;
import com.github.johnpersano.supertoasts.library.SuperActivityToast;
import com.github.johnpersano.supertoasts.library.utils.PaletteUtils;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.hjq.bar.TitleBar;


import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import org.jetbrains.annotations.NotNull;
import org.zackratos.ultimatebar.UltimateBar;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import static com.amap.api.maps.model.BitmapDescriptorFactory.getContext;

public class MainActivity extends AppCompatActivity {


    EditText et1, et2 = null;
    String Name, Password = null;
    ImageView add_commit = null;
    SharedPreferences sharedPreferences = null;
    boolean isFirstRun = false;
    SharedPreferences.Editor editor = null;
    private TitleBar mTitleBar;
    MapView mMapView = null;
    Button tv1 = null;

    private List<myBean> myBeanList = new ArrayList<>();//用来存放数据的数组

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);

//        ListView listView=findViewById(R.id.Lv);
//        myBean b1=new myBean("aa",R.mipmap.laji);
//        myBean b2=new myBean("aa",R.mipmap.xiaobiaoqing);
//        myBean b3=new myBean("aa",R.mipmap.laji);
//        myBean b4=new myBean("aa",R.mipmap.laji);
//        myBeanList.add(b1);
//        myBeanList.add(b2);
//        myBeanList.add(b3);
//        myBeanList.add(b4);
//        myadapter myadapter=new myadapter(MainActivity.this,R.layout.daiban,myBeanList);
//        listView.setAdapter(myadapter);


//        tv1=findViewById(R.id.tv1);


//        new FinestWebView.Builder(MainActivity.this).show("http://www.baidu.com");
//
//


//        mTitleBar = findViewById(R.id.tb_main_bar);
//        mTitleBar.setOnTitleBarListener(new OnTitleBarListener() {
//
//            @Override
//            public void onLeftClick(View v) {
//                mess("左");
//            }
//
//            @Override
//            public void onTitleClick(View v) {
//                mess("中");
//            }
//
//            @Override
//            public void onRightClick(View v) {
//                mess("右");
//            }
//        });


        sharedPreferences = this.getSharedPreferences("share", MODE_PRIVATE);
        isFirstRun = sharedPreferences.getBoolean("isFirstRun", true);
        editor = sharedPreferences.edit();
        if (isFirstRun) {
//            Toast.makeText(MainActivity.this, "第一次运行", Toast.LENGTH_SHORT).show();
            setContentView(R.layout.login);
            init();
        } else {
//            Toast.makeText(MainActivity.this, "不是第一次运行", Toast.LENGTH_SHORT).show();
            jump();
        }

    }


    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        //在activity执行onSaveInstanceState时执行mMapView.onSaveInstanceState (outState)，保存地图当前的状态
        mMapView.onSaveInstanceState(outState);
    }


    public void init() {
        UltimateBar ultimateBar = new UltimateBar(this);
        ultimateBar.setImmersionBar();
        et1 = (EditText) findViewById(R.id.et1);
        et2 = (EditText) findViewById(R.id.et2);
        Name = et1.getText().toString();
        Password = et2.getText().toString();
        add_commit = (ImageView) findViewById(R.id.add_commit);


        et2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            //et2改变后执行
            @Override
            public void afterTextChanged(Editable editable) {
                add_commit.setVisibility(View.VISIBLE);
            }
        });

        add_commit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Name = et1.getText().toString();
                Password = et2.getText().toString();
                findStudent();
                findaStudent();
                finddStudent();
                sendByOKHttp(Name, Password);

            }
        });
    }

    // OKHttp
    private void sendByOKHttp(String name, String password) {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder().url("http://47.101.144.65:8080/House/Login2?username=" + name + "&password=" + password).get().build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
            }//发送失败回调函数

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                final String string = response.body().string();


                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if (string.trim().equals("用户名或密码错误！！！")) {
                            mess(string);
                        } else {
                            editor.putString("UserBean", string);
                            Gson gson = new Gson();
                            UserBean userBean = gson.fromJson(string, UserBean.class);
                            editor.putString("xuehao", name);
                            editor.commit();
                            ((City) getApplication()).setXuehao(name);
                            jump();
                        }
                    }
                });
            }
        });
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
                runOnUiThread(new Runnable() {
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


    // OKHttp
    private void findaStudent() {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder().url("http://47.101.144.65:8080/House/Question_a_find").get().build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
            }//发送失败回调函数

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                final String string = response.body().string();
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if (string.trim().equals("fail")) {
                        } else {
                            editor.putString("Student", string);
                            editor.commit();
                        }
                    }
                });
            }
        });
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
                runOnUiThread(new Runnable() {
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

    public void jump() {
        //                跳转页面
        editor.putBoolean("isFirstRun", false);
        String name = sharedPreferences.getString("xuehao", String.valueOf(false));
        ((City) getApplication()).setXuehao(name);
        editor.commit();
        Intent intent = new Intent(MainActivity.this, Home.class);
        startActivity(intent);
        MainActivity.this.finish();
    }


    public void mess(String mess) {
        //                提示消息
        SuperActivityToast.create(MainActivity.this, new Style(), Style.TYPE_STANDARD)
                .setButtonText("UNDO")
                .setButtonIconResource(R.drawable.icon)
                .setProgressBarColor(Color.WHITE)
                .setText(mess)
                .setDuration(Style.DURATION_LONG)
                .setFrame(Style.FRAME_LOLLIPOP)
                .setColor(PaletteUtils.getSolidColor(PaletteUtils.MATERIAL_GREEN))
                .setAnimations(Style.ANIMATIONS_POP).show();
    }


    @Override
    public void startActivity(Intent intent) {
        super.startActivity(intent);
        // activity切换动画
        // 参数一：新Activity进入的动画(最右侧进来)
        // 参数二：旧Activity退出的动画(最左侧出去)
        overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


}