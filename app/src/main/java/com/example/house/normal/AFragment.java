package com.example.house.normal;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;

import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.bigkoo.alertview.AlertView;
import com.bigkoo.alertview.OnItemClickListener;
import com.example.house.Adapter.myBaseAdapter;
import com.example.house.Adapter.myadapter;
import com.example.house.City;
import com.example.house.Home;
import com.example.house.MainActivity;
import com.example.house.R;
import com.example.house.map;
import com.example.house.model.UserBean;
import com.example.house.model.myBean;
import com.example.house.myListView;
import com.example.house.weather;
import com.github.johnpersano.supertoasts.library.Style;
import com.github.johnpersano.supertoasts.library.SuperActivityToast;
import com.github.johnpersano.supertoasts.library.utils.PaletteUtils;
import com.google.gson.Gson;


import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Timer;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import static android.content.Context.MODE_PRIVATE;


public class AFragment extends Fragment implements View.OnClickListener {


    View view = null;
    //声明AMapLocationClient类对象
    AMapLocationClient mLocationClient = null;
    //声明AMapLocationClientOption对象
    AMapLocationClientOption mLocationOption = null;
    String adress = null;
    Boolean tag = true;
    SharedPreferences sharedPreferences = null;
    private List<myBean> myBeanList = new ArrayList<>();//用来存放数据的数组
    private com.example.house.myListView myListView;
    myBaseAdapter myBaseAdapter = null;
    LinearLayout L1 = null;
    LinearLayout L2 = null;
    LinearLayout L1_1 = null;
    LinearLayout L2_1 = null;
    LinearLayout L3_1 = null;
    EditText e1 = null;
    EditText e1_1 = null;
    Button yes = null;
    Button no = null;
    Button yes1 = null;
    Button no1 = null;
    UserBean userBean = null;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {


        view = inflater.inflate(R.layout.fragment_a, container, false);


        sharedPreferences = getContext().getSharedPreferences("share", MODE_PRIVATE);
        String User = sharedPreferences.getString("UserBean", "false");
        Gson gson = new Gson();
        userBean = gson.fromJson(User, UserBean.class);


        L1 = view.findViewById(R.id.LL1);
        L2 = view.findViewById(R.id.LL2);
        myListView = view.findViewById(R.id.Lv);
        e1 = view.findViewById(R.id.e1);
        e1_1 = view.findViewById(R.id.e1_1);
        yes = view.findViewById(R.id.yes);
        no = view.findViewById(R.id.no);
        yes1 = view.findViewById(R.id.yes1);
        no1 = view.findViewById(R.id.no1);
        L1_1 = view.findViewById(R.id.LL1_1);
        L2_1 = view.findViewById(R.id.LL2_1);
        L3_1 = view.findViewById(R.id.LL3_1);


        ImageButton k1 = view.findViewById(R.id.kuai_1);
        ImageButton k2 = view.findViewById(R.id.kuai_2);
        ImageButton k3 = view.findViewById(R.id.kuai_3);
        ImageButton k4 = view.findViewById(R.id.kuai_4);
        ImageButton k5 = view.findViewById(R.id.kuai_5);
        ImageButton k6 = view.findViewById(R.id.kuai_6);

        ImageButton c1 = view.findViewById(R.id.chang_1);
        ImageButton c2 = view.findViewById(R.id.chang_2);
        ImageButton c3 = view.findViewById(R.id.chang_3);
        ImageButton c4 = view.findViewById(R.id.chang_4);
        ImageButton c5 = view.findViewById(R.id.chang_5);

        ImageButton q1 = view.findViewById(R.id.qi_1);
        ImageButton q2 = view.findViewById(R.id.qi_2);
        ImageButton q3 = view.findViewById(R.id.qi_3);
        ImageButton q4 = view.findViewById(R.id.qi_4);
        ImageButton q5 = view.findViewById(R.id.qi_5);


        k1.setOnClickListener(this);
        k2.setOnClickListener(this);
        k3.setOnClickListener(this);
        k4.setOnClickListener(this);
        k5.setOnClickListener(this);
        k6.setOnClickListener(this);

        c1.setOnClickListener(this);
        c2.setOnClickListener(this);
        c3.setOnClickListener(this);
        c4.setOnClickListener(this);
        c5.setOnClickListener(this);

        q1.setOnClickListener(this);
        q2.setOnClickListener(this);
        q3.setOnClickListener(this);
        q4.setOnClickListener(this);
        q5.setOnClickListener(this);


        return view;
    }


    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.kuai_1:
                Web("http://jw.xyu.edu.cn/eams/loginExt.action");
                break;
            case R.id.kuai_2:
                Web("https://weixiao.qq.com/apps/public/cet/index.html");
                break;
            case R.id.kuai_3:
                Web("https://www.cnki.net/");
                break;
            case R.id.kuai_4:
                Web("http://www.wanfangdata.com.cn/index.html");
                break;
            case R.id.kuai_5:
                Web("http://qikan.cqvip.com/");
                break;
            case R.id.kuai_6:
                Web("https://720yun.com/t/313je5kkOy3?scene_id=15968595");
                break;
            case R.id.chang_1:
                Dw();
                break;
            case R.id.chang_2:
                jump();
                break;
            case R.id.chang_3:
                jump2();
                break;
            case R.id.chang_4:
                question();
                break;
            case R.id.chang_5:
                daiban();
                break;
            case R.id.qi_1:
                Web("https://ailongmiao.com/");
                break;
            case R.id.qi_2:
                Web("http://music.ifkdy.com/");
                break;
            case R.id.qi_3:
                Web("https://www.hipdf.cn/");
                break;
            case R.id.qi_4:
                Web("https://jinaconvert.com/cn/index.php");
                break;
            case R.id.qi_5:
                Web("https://pmgeek.net/cn/index.html");
                break;
        }
    }


    private void daiban() {
        L1.setVisibility(View.GONE);
        L2.setVisibility(View.GONE);
        L1_1.setVisibility(View.VISIBLE);
        L2_1.setVisibility(View.VISIBLE);
        L3_1.setVisibility(View.VISIBLE);
        if (tag) {
            myBean b1 = new myBean("House", R.mipmap.laji);
            myBean b2 = new myBean("西风吹老洞庭波", R.mipmap.laji);
            myBean b3 = new myBean("一夜湘君白发多", R.mipmap.laji);
            myBean b4 = new myBean("醉后不知天在水", R.mipmap.laji);
            myBean b5 = new myBean("满船清梦压星河", R.mipmap.laji);

            myBeanList.add(b1);
            myBeanList.add(b2);
            myBeanList.add(b3);
            myBeanList.add(b4);
            myBeanList.add(b5);

//        myadapter myadapter=new myadapter(view.getContext(),R.layout.daiban,myBeanList);
//        myListView.setAdapter(myadapter);
            myBaseAdapter = new myBaseAdapter(myBeanList, view.getContext());
            myListView.setAdapter(myBaseAdapter);
            tag = false;
        }


        yes1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myBeanList.add(new myBean(e1_1.getText().toString(), R.mipmap.laji));
                myBaseAdapter.notifyDataSetChanged();
                e1_1.setText("");
            }
        });

        no1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                e1_1.setText("");
                L1_1.setVisibility(View.GONE);
                L2_1.setVisibility(View.GONE);
                L3_1.setVisibility(View.GONE);
            }
        });

    }


    private void question() {
        L1.setVisibility(View.VISIBLE);
        L2.setVisibility(View.VISIBLE);
        L1_1.setVisibility(View.GONE);
        L2_1.setVisibility(View.GONE);
        L3_1.setVisibility(View.GONE);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm E");
        String time = sdf.format(System.currentTimeMillis());
        yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String question = e1.getText().toString() + "------------" + time;
                SendQuestion(question, userBean.getXuehao());
                mess(question);
                e1.setText("");
                L1.setVisibility(View.GONE);
                L2.setVisibility(View.GONE);
            }
        });

        no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                e1.setText("");
                L1.setVisibility(View.GONE);
                L2.setVisibility(View.GONE);

            }
        });


    }

    public void Web(String url) {
        Uri uri = Uri.parse(url);//要跳转的网址
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        startActivity(intent);
    }


    public void Dw() {
        if (ContextCompat.checkSelfPermission(view.getContext(), Manifest.permission.ACCESS_COARSE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            quanxian();
        }
        //声明定位回调监听器
        AMapLocationListener mLocationListener = new AMapLocationListener() {
            @Override
            public void onLocationChanged(AMapLocation amapLocation) {
                if (amapLocation != null) {
                    if (amapLocation.getErrorCode() == 0) {
                        //可在其中解析amapLocation获取相应内容。
                        adress = amapLocation.getAddress();
                        System.out.println("执行了:" + adress);
                        String country = amapLocation.getCountry();
                        ((City) getContext().getApplicationContext()).setCity(country);
                        //获取定位时间
                        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                        Date date = new Date(amapLocation.getTime());
                        String time = df.format(date);
                        mess("定位成功：" + adress + "-----------" + time + "--in" + ((City) getContext().getApplicationContext()).getCity());
                        String Addr = adress + "-----------" + time + "--in" + ((City) getContext().getApplicationContext()).getCity();
                        SendDingwei(Addr, userBean.getXuehao());
                        mLocationClient.stopLocation();
                    } else {
                        //定位失败时，可通过ErrCode（错误码）信息来确定失败的原因，errInfo是错误信息，详见错误码表。
                        Log.e("AmapError", "location Error, ErrCode:"
                                + amapLocation.getErrorCode() + ", errInfo:"
                                + amapLocation.getErrorInfo());
                    }
                }
            }
        };
//初始化定位
        mLocationClient = new AMapLocationClient(getContext());
//设置定位回调监听
        mLocationClient.setLocationListener(mLocationListener);
//初始化AMapLocationClientOption对象
        mLocationOption = new AMapLocationClientOption();
        AMapLocationClientOption option = new AMapLocationClientOption();
        /**
         * 设置定位场景，目前支持三种场景（签到、出行、运动，默认无场景）
         */
        option.setLocationPurpose(AMapLocationClientOption.AMapLocationPurpose.SignIn);
        mLocationClient.setLocationOption(option);
        //设置场景模式后最好调用一次stop，再调用start以保证场景模式生效
//        mLocationClient.stopLocation();
//        mLocationClient.startLocation();
        mLocationOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Hight_Accuracy);
        mLocationOption.setOnceLocation(true);
//        mLocationOption.setOnceLocationLatest(true);
//设置是否返回地址信息（默认返回地址信息）
        mLocationOption.setNeedAddress(true);
        //单位是毫秒，默认30000毫秒，建议超时时间不要低于8000毫秒。
        mLocationOption.setHttpTimeOut(20000);
        //给定位客户端对象设置定位参数
        mLocationClient.setLocationOption(mLocationOption);
//启动定位
        mLocationClient.startLocation();

    }


    private void SendDingwei(String adress, String xuehao) {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder().url("http://47.101.144.65:8080/House/Dingwei?username=" + xuehao + "&dingwei=" + adress).get().build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
            }//发送失败回调函数

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {


            }
        });

    }

    private void SendQuestion(String question, String xuehao) {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder().url("http://47.101.144.65:8080/House/Question_add?username=" + xuehao + "&question=" + question).get().build();
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
        SuperActivityToast.create(view.getContext(), new Style(), Style.TYPE_STANDARD)
                .setButtonText("UNDO")
                .setButtonIconResource(R.drawable.icon)
                .setProgressBarColor(Color.WHITE)
                .setText(mess)
                .setDuration(Style.DURATION_LONG)
                .setFrame(Style.FRAME_LOLLIPOP)
                .setColor(PaletteUtils.getSolidColor(PaletteUtils.MATERIAL_GREEN))
                .setAnimations(Style.ANIMATIONS_POP).show();
    }


    public void jump() {
        Intent intent = new Intent(getActivity(), map.class);
        startActivity(intent);
    }

    public void jump2() {
        Intent intent = new Intent(getActivity(), weather.class);
        startActivity(intent);
    }


    public void quanxian() {
        if (ContextCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_COARSE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            show();
        }

    }


    public void show() {
        new AlertView.Builder().setContext(getContext())
                .setStyle(AlertView.Style.Alert)
                .setTitle("---------权限设置---------")
                .setMessage("需设置定位权限为始终允许")
                .setCancelText("取消")
                .setDestructive("转到设置")
                .setOthers(null)
                .setOnItemClickListener(new OnItemClickListener() {
                    @Override
                    public void onItemClick(Object o, int position) {
                        if (position == 0) {
                            getAppDetailSettingIntent(getContext());
                        }

                    }
                })
                .build()
                .show();
    }

    private void getAppDetailSettingIntent(Context context) {
        Intent intent = new Intent();
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        if (Build.VERSION.SDK_INT >= 9) {
            intent.setAction("android.settings.APPLICATION_DETAILS_SETTINGS");
            intent.setData(Uri.fromParts("package", getContext().getPackageName(), null));
        } else if (Build.VERSION.SDK_INT <= 8) {
            intent.setAction(Intent.ACTION_VIEW);
            intent.setClassName("com.android.settings", "com.android.settings.InstalledAppDetails");
            intent.putExtra("com.android.settings.ApplicationPkgName", getContext().getPackageName());
        }
        startActivity(intent);
    }
}
