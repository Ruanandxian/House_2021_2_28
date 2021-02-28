package com.example.house.normal;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextPaint;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.bigkoo.alertview.AlertView;
import com.bigkoo.alertview.OnDismissListener;
import com.bigkoo.alertview.OnItemClickListener;
import com.bumptech.glide.Glide;
import com.cjj.sva.JJSearchView;
import com.cjj.sva.anim.controller.JJCircleToSimpleLineController;
import com.example.house.City;
import com.example.house.R;
import com.example.house.model.UserBean;
import com.example.house.model.myBean;
import com.example.house.view.RoundedImageView;
import com.github.johnpersano.supertoasts.library.Style;
import com.github.johnpersano.supertoasts.library.SuperActivityToast;
import com.github.johnpersano.supertoasts.library.utils.PaletteUtils;
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


public class Admin_2Fragment extends Fragment implements View.OnClickListener {
    EditText admin_1t = null;
    ImageView admin_1i = null;
    JJSearchView mJJSearchView = null;
    boolean tag = false;
    boolean tag_1 = true;

    TextView admin_xuehao = null;
    TextView admin_name = null;
    TextView admin_nicheng = null;
    TextView admin_cla = null;
    TextView admin_sushe = null;
    TextView admin_mima = null;
    TextView admin_QQ = null;
    TextView admin_sta = null;

    TextView admin_1 = null;
    TextView admin_2 = null;
    TextView admin_3 = null;
    TextView admin_4 = null;


    UserBean userBean = null;
    RoundedImageView admin_tou = null;
    LinearLayout admin_lout1 = null;
    LinearLayout admin_lout2 = null;

    SharedPreferences sharedPreferences = null;
    SharedPreferences.Editor editor = null;
    private boolean isGetData = false;
    private List<myBean> myBeanList = new ArrayList<>();//用来存放数据的数组
    private com.example.house.myListView myListView;
    com.example.house.Adapter.myBaseAdapter myBaseAdapter = null;
    LinearLayout c_l2 = null;

    private AlertView mAlertView;//避免创建重复View，先创建View，然后需要的时候show出来，推荐这个做法
    private AlertView mAlertViewExt;//窗口拓展例子
    private EditText etName;//拓展View内容
    private InputMethodManager imm;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.admin_3, container, false);
        mJJSearchView = (JJSearchView) view.findViewById(R.id.jjsv);
        admin_1 = view.findViewById(R.id.admin_1);
        admin_2 = view.findViewById(R.id.admin_2);
        admin_3 = view.findViewById(R.id.admin_3);
        admin_4 = view.findViewById(R.id.admin_4);
        admin_1t = view.findViewById(R.id.admin_1t);
        admin_1i = view.findViewById(R.id.admin_1i);
        admin_xuehao = view.findViewById(R.id.admin_xuehao);
        admin_name = view.findViewById(R.id.admin_name);
        admin_nicheng = view.findViewById(R.id.admin_nicheng);
        admin_cla = view.findViewById(R.id.admin_cla);
        admin_sushe = view.findViewById(R.id.admin_sushe);
        admin_mima = view.findViewById(R.id.admin_mima);
        admin_sta = view.findViewById(R.id.admin_sta);
        admin_tou = view.findViewById(R.id.admin_tou);
        admin_lout1 = view.findViewById(R.id.admin_lout1);
        admin_lout2 = view.findViewById(R.id.admin_lout2);


        admin_1.setOnClickListener(this);
        admin_2.setOnClickListener(this);
        admin_3.setOnClickListener(this);
        admin_4.setOnClickListener(this);


        imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);

        ViewGroup extView = (ViewGroup) LayoutInflater.from(getActivity()).inflate(R.layout.alertext_form, null);
        etName = (EditText) extView.findViewById(R.id.etName);
        etName.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean focus) {
                //输入框出来则往上移动
                boolean isOpen = imm.isActive();
                mAlertViewExt.setMarginBottom(isOpen && focus ? 120 : 0);
                System.out.println(isOpen);
            }
        });
        //拓展窗口
        mAlertViewExt = new AlertView("宿舍", "请输入用户的新宿舍！", "取消", null, new String[]{"完成"}, getContext(), AlertView.Style.Alert, new OnItemClickListener() {
            @Override
            public void onItemClick(Object o, int position) {
                if (position == 1) {
                    mAlertViewExt.dismissImmediately();
                    tag_1 = false;
                    sendByOKHttp_sushe(etName.getText().toString());
                    admin_sushe.setText(etName.getText().toString());
                }

            }
        });
        mAlertViewExt.addExtView(extView);

        mJJSearchView.setController(new JJCircleToSimpleLineController());
        mJJSearchView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mJJSearchView.startAnim();
                admin_1t.setVisibility(View.VISIBLE);
                admin_1t.requestFocus();
                admin_1i.setVisibility(View.VISIBLE);

            }
        });


        admin_1t.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if ((event != null && KeyEvent.KEYCODE_ENTER == event.getKeyCode() && KeyEvent.ACTION_DOWN == event.getAction())) {
//                    mess(admin_1t.getText().toString());
                    if (admin_1t.getText().toString() != "") {
                        finduser(admin_1t.getText().toString());
                        init();
                        mJJSearchView.resetAnim();
                        admin_1t.setVisibility(View.GONE);
                        admin_1i.setVisibility(View.GONE);
                        admin_1t.setText("");
                    }
                    return true;
                }
                return false;
            }
        });


        admin_1i.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                mess(admin_1t.getText().toString());
                if (admin_1t.getText().toString() != "") {
                    finduser(admin_1t.getText().toString());
                    init();
                    mJJSearchView.resetAnim();
                    admin_1t.setVisibility(View.GONE);
                    admin_1i.setVisibility(View.GONE);
                    admin_1t.setText("");

                }

            }
        });
        return view;
    }


    public void init() {
        if (tag) {
            String ul = "http://47.101.144.65:8080/one/dist/picture/" + userBean.getXuehao() + "." + "jpg";
            Glide.with(this).load(ul).into(admin_tou);
            admin_xuehao.setText(userBean.getXuehao());
            admin_name.setText(userBean.getName());
            admin_nicheng.setText(userBean.getNicheng());
            admin_cla.setText(userBean.getCla());
            if (tag_1) {
                admin_sushe.setText(userBean.getRoom());
            } else {
                admin_sushe.setText(etName.getText().toString());
            }

            admin_mima.setText(userBean.getPassword());
            if (userBean.getAdmin().equals("0")) {
                admin_sta.setText("普通用户");
            }
            if (userBean.getAdmin().equals("1")) {
                admin_sta.setText("管理员");
                admin_2.setText("取消管理员");
            }


        } else {

        }
    }

    public void finduser(String name) {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder().url("http://47.101.144.65:8080/House/Student_find?username=" + name).get().build();
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
                        if (string.trim().equals("fail！！！")) {
                            tag = false;
                            mess("没有查找到相应内容");
                        } else {
                            admin_lout1.setVisibility(View.VISIBLE);
                            admin_lout2.setVisibility(View.VISIBLE);
                            Gson gson = new Gson();
                            userBean = gson.fromJson(string, UserBean.class);
                            tag = true;
                            init();
                        }
                    }
                });
            }
        });
    }


    public void mess(String mess) {
        //                提示消息
        SuperActivityToast.create(getActivity(), new Style(), Style.TYPE_STANDARD)
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
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.admin_1:
                admin_1();
                break;
            case R.id.admin_2:
                admin_2();
                break;
            case R.id.admin_3:
                admin_3();
                break;
            case R.id.admin_4:
                admin_4();
                break;

        }

    }

    private void admin_4() {
        show4();
    }

    private void admin_3() {
        show3();
    }

    private void admin_2() {
        if (admin_2.getText().toString().equals("设为管理员")) {
            show2("1");
        }
        if (admin_2.getText().toString().equals("取消管理员")) {
            show2_2("0");
        }

    }

    private void admin_1() {
        show1();

    }

    public void show3() {
        mAlertViewExt.show();
    }

    public void show4() {
        new AlertView.Builder().setContext(getContext())
                .setStyle(AlertView.Style.Alert)
                .setTitle("---------删除用户---------")
                .setMessage("该用户将被永久删除!!!")
                .setCancelText("取消")
                .setDestructive("确认")
                .setOthers(null)
                .setOnItemClickListener(new OnItemClickListener() {
                    @Override
                    public void onItemClick(Object o, int position) {
                        if (position == 0) {
                            sendByOKHttp_user();
                            admin_lout1.setVisibility(View.GONE);
                            admin_lout2.setVisibility(View.GONE);
                        }

                    }
                })
                .build()
                .show();
    }


    public void show2_2(String admin) {
        new AlertView.Builder().setContext(getContext())
                .setStyle(AlertView.Style.Alert)
                .setTitle("---------取消管理员---------")
                .setMessage("该用户将被设为普通用户")
                .setCancelText("取消")
                .setDestructive("确认")
                .setOthers(null)
                .setOnItemClickListener(new OnItemClickListener() {
                    @Override
                    public void onItemClick(Object o, int position) {
                        if (position == 0) {
                            sendByOKHttp_admin(admin);
                            admin_sta.setText("普通用户");
                            userBean.setAdmin("0");
                            admin_2.setText("设为管理员");
                        }

                    }
                })
                .build()
                .show();
    }

    public void show2(String admin) {
        new AlertView.Builder().setContext(getContext())
                .setStyle(AlertView.Style.Alert)
                .setTitle("---------设为管理员---------")
                .setMessage("该用户将被设为管理员")
                .setCancelText("取消")
                .setDestructive("确认")
                .setOthers(null)
                .setOnItemClickListener(new OnItemClickListener() {
                    @Override
                    public void onItemClick(Object o, int position) {
                        if (position == 0) {
                            sendByOKHttp_admin(admin);
                            admin_sta.setText("管理员");
                            userBean.setAdmin("1");
                            admin_2.setText("取消管理员");
                        }

                    }
                })
                .build()
                .show();
    }

    public void show1() {
        new AlertView.Builder().setContext(getContext())
                .setStyle(AlertView.Style.Alert)
                .setTitle("---------密码重置---------")
                .setMessage("密码将被重置为000000")
                .setCancelText("取消")
                .setDestructive("确认")
                .setOthers(null)
                .setOnItemClickListener(new OnItemClickListener() {
                    @Override
                    public void onItemClick(Object o, int position) {
                        if (position == 0) {
                            sendByOKHttp_mima("000000");
                            admin_mima.setText("000000");
                        }

                    }
                })
                .build()
                .show();
    }


    private void sendByOKHttp_user() {
        OkHttpClient client = new OkHttpClient();
//        System.out.println(userBean.getXuehao());
//        System.out.println(nicheng);
        Request request = new Request.Builder().url("http://47.101.144.65:8080/House/Delete_user?xuehao=" + userBean.getXuehao()).get().build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
            }//发送失败回调函数

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {

            }
        });
    }


    private void sendByOKHttp_sushe(String sushe) {
        OkHttpClient client = new OkHttpClient();
//        System.out.println(userBean.getXuehao());
//        System.out.println(nicheng);
        Request request = new Request.Builder().url("http://47.101.144.65:8080/House/Sushe_set?xuehao=" + userBean.getXuehao() + "&sushe=" + sushe).get().build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
            }//发送失败回调函数

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {

            }
        });
    }

    private void sendByOKHttp_admin(String admin) {
        OkHttpClient client = new OkHttpClient();
//        System.out.println(userBean.getXuehao());
//        System.out.println(nicheng);
        Request request = new Request.Builder().url("http://47.101.144.65:8080/House/Admin_set?xuehao=" + userBean.getXuehao() + "&admin=" + admin).get().build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
            }//发送失败回调函数

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {

            }
        });
    }


    private void sendByOKHttp_mima(String mima) {
        OkHttpClient client = new OkHttpClient();
//        System.out.println(userBean.getXuehao());
//        System.out.println(nicheng);
        Request request = new Request.Builder().url("http://47.101.144.65:8080/House/Mima?xuehao=" + userBean.getXuehao() + "&mima=" + mima).get().build();
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
