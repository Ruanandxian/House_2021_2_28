package com.example.house.normal;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.MediaType.Companion.*;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.RequestBody.Companion.*;
import okhttp3.Response;

import android.provider.MediaStore;
import android.text.TextPaint;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
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
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import com.bigkoo.alertview.AlertView;
import com.bigkoo.alertview.OnItemClickListener;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.example.house.Admin;
import com.example.house.BitmapUtil;
import com.example.house.City;
import com.example.house.Home;
import com.example.house.MainActivity;
import com.example.house.R;
import com.example.house.model.UserBean;
import com.example.house.view.RoundedImageView;
import com.github.johnpersano.supertoasts.library.Style;
import com.github.johnpersano.supertoasts.library.SuperActivityToast;
import com.github.johnpersano.supertoasts.library.utils.PaletteUtils;
import com.google.gson.Gson;

import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

import static android.content.Context.MODE_PRIVATE;
import static com.amap.api.maps.model.BitmapDescriptorFactory.getContext;


public class DFragment extends Fragment implements View.OnClickListener {
    Boolean m_tag_1 = true;
    Boolean m_tag_2 = true;
    Boolean m_tag_4 = true;
    Boolean m_tag_5 = true;
    Boolean tv_tag = false;
    RoundedImageView tx = null;
    TextView wo_1 = null;
    TextView wo_2 = null;
    TextView wo_3 = null;
    TextView wo_4 = null;
    TextView wo_5 = null;
    TextView wo_6 = null;
    TextView wo_7 = null;
    TextView wo_5_tv = null;
    TextView wo_3_2 = null;
    TextView nicheng = null;
    TextView sushe = null;
    LinearLayout wo_1_l = null;
    LinearLayout wo_1_l2 = null;
    LinearLayout wo_4_l = null;
    LinearLayout wo_4_l2 = null;
    LinearLayout wo_5_view = null;
    LinearLayout wo_3_l = null;
    CheckBox wo_5_checkBox = null;
    ImageButton wo_5_headimage = null;
    EditText wo_1_e = null;
    EditText wo_4_e = null;
    Button wo_1_no = null;
    Button wo_1_yes = null;
    Button wo_4_no = null;
    Button wo_4_yes = null;
    ImageView wo_1_q = null;
    ImageView wo_2_q = null;
    ImageView wo_3_q = null;
    ImageView wo_4_q = null;
    ImageView wo_5_q = null;
    ImageView wo_3_1 = null;
    ImageView wo_3_3 = null;
    private AlertView mAlertViewExt;//窗口拓展例子
    private EditText etName;//拓展View内容
    private InputMethodManager imm;
    SharedPreferences sharedPreferences = null;
    boolean isFirstRun = true;
    SharedPreferences.Editor editor = null;
    public static final int RC_CHOOSE_PHOTO = 2;
    UserBean userBean = null;
    private boolean isGetData = false;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_d, container, false);


        tx = view.findViewById(R.id.tou);
        nicheng = view.findViewById(R.id.nicheng);
        sushe = view.findViewById(R.id.sushe);
        wo_1 = view.findViewById(R.id.wo_1);
        wo_2 = view.findViewById(R.id.wo_2);
        wo_3 = view.findViewById(R.id.wo_3);
        wo_4 = view.findViewById(R.id.wo_4);
        wo_5 = view.findViewById(R.id.wo_5);
        wo_6 = view.findViewById(R.id.wo_6);
        wo_7 = view.findViewById(R.id.wo_7);
        wo_3_1 = view.findViewById(R.id.wo_3_1);
        wo_3_2 = view.findViewById(R.id.wo_3_2);
        wo_3_3 = view.findViewById(R.id.wo_3_3);
        wo_1_l = view.findViewById(R.id.wo_1_l);
        wo_1_l2 = view.findViewById(R.id.wo_1_l2);
        wo_4_l = view.findViewById(R.id.wo_4_l);
        wo_4_l2 = view.findViewById(R.id.wo_4_l2);
        wo_5_view = view.findViewById(R.id.wo_5_view);
        wo_5_checkBox = view.findViewById(R.id.wo_5_checkBox);
        wo_5_headimage = view.findViewById(R.id.wo_5_headimage);
        wo_5_tv = view.findViewById(R.id.wo_5_tv);
        wo_3_l = view.findViewById(R.id.wo_3_l);
        wo_1_e = view.findViewById(R.id.wo_1_e);
        wo_4_e = view.findViewById(R.id.wo_4_e);
        wo_1_no = view.findViewById(R.id.wo_1_no);
        wo_1_yes = view.findViewById(R.id.wo_1_yes);
        wo_4_no = view.findViewById(R.id.wo_4_no);
        wo_4_yes = view.findViewById(R.id.wo_4_yes);
        wo_1_q = view.findViewById(R.id.wo_1_q);
        wo_2_q = view.findViewById(R.id.wo_2_q);
        wo_3_q = view.findViewById(R.id.wo_3_q);
        wo_4_q = view.findViewById(R.id.wo_4_q);
        wo_5_q = view.findViewById(R.id.wo_5_q);

        tx.setOnClickListener(this);
        wo_1.setOnClickListener(this);
        wo_2.setOnClickListener(this);
        wo_3.setOnClickListener(this);
        wo_3_3.setOnClickListener(this);
        wo_4.setOnClickListener(this);
        wo_5.setOnClickListener(this);
        wo_6.setOnClickListener(this);
        wo_7.setOnClickListener(this);
        wo_5_headimage.setOnClickListener(this);
        wo_5_checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @SuppressLint("ResourceAsColor")
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    wo_5_checkBox.setTextColor(R.color.bgColor_divier);
                    wo_5_checkBox.setTextSize(8);
                    TextPaint tp = wo_5_checkBox.getPaint();
                    tp.setFakeBoldText(false);
                    tp.setStrikeThruText(true);
                    wo_5_headimage.setVisibility(View.VISIBLE);
                } else {
                    wo_5_checkBox.setTextColor(R.color.black);
                    wo_5_checkBox.setTextSize(12);
                    TextPaint tp1 = wo_5_checkBox.getPaint();
                    tp1.setFakeBoldText(true);
                    tp1.setStrikeThruText(false);
                    wo_5_headimage.setVisibility(View.GONE);
                }
            }
        });


        String name = ((City) getContext().getApplicationContext()).getXuehao();
        String ul = "http://47.101.144.65:8080/one/dist/picture/" + name + "." + "jpg";
        try {
            URL url = new URL(ul);
            // 返回一个 URLConnection 对象，它表示到 URL 所引用的远程对象的连接。
            URLConnection uc = url.openConnection();
            // 打开的连接读取的输入流。
            InputStream in = uc.getInputStream();
            Glide.with(this).load(ul).into(tx);
        } catch (Exception e) {
            Glide.with(this).load(R.mipmap.xiaobiaoqing).into(tx);
            System.out.println("没有");
        }


        sharedPreferences = getContext().getSharedPreferences("share", MODE_PRIVATE);
        editor = sharedPreferences.edit();
        String User = sharedPreferences.getString("UserBean", "false");
        Gson gson = new Gson();
        userBean = gson.fromJson(User, UserBean.class);
        nicheng.setText(userBean.getNicheng());
        sushe.setText(userBean.getRoom());
        load();

        return view;
    }

    @Override
    public Animation onCreateAnimation(int transit, boolean enter, int nextAnim) {
//           进入当前Fragment
        if (enter && !isGetData) {
            isGetData = true;
            //   这里可以做网络请求或者需要的数据刷新操作
            //   initData();
            dingwei_tag();
            question_tag();
        } else {
            isGetData = false;
        }
        return super.onCreateAnimation(transit, enter, nextAnim);
    }


    @Override
    public void onPause() {
        super.onPause();
        isGetData = false;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tou:
                touxiang();
                break;
            case R.id.wo_1:
                nicheng();
                break;
            case R.id.wo_2:
                zhanghao();
                break;
            case R.id.wo_3:
                dingwei();
            case R.id.wo_3_3:
                dingwei_tag();
                break;
            case R.id.wo_4:
                mima();
                break;
            case R.id.wo_5:
                question();
                break;
            case R.id.wo_5_headimage:
                question_delete();
                break;
            case R.id.wo_6:
                guanli();
                break;
            case R.id.wo_7:
                QQ();
                break;
        }
    }

    private void QQ() {
        imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
        ViewGroup extView = (ViewGroup) LayoutInflater.from(getActivity()).inflate(R.layout.alertext_form2, null);
        etName = (EditText) extView.findViewById(R.id.et_Name);
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
        mAlertViewExt = new AlertView("QQ", "您现在的QQ是：" + userBean.getQQ(), "取消", null, new String[]{"完成"}, getContext(), AlertView.Style.Alert, new OnItemClickListener() {
            @Override
            public void onItemClick(Object o, int position) {
                if (position == 0) {
//                    mess(etName.getText().toString());
                    mAlertViewExt.dismissImmediately();
                    sendByOKHttp_QQ(etName.getText().toString());

                }

            }
        });
        mAlertViewExt.addExtView(extView);
        mAlertViewExt.show();
    }

    private void guanli() {
        if (userBean.getAdmin().equals("1")) {
            Intent intent = new Intent(getActivity(), Admin.class);
            startActivity(intent);
        } else {
            mess("没有管理权限");
        }


    }

    private void question_delete() {
        wo_5_q.setPivotX(wo_5_q.getWidth() / 2);
        wo_5_q.setPivotY(wo_5_q.getHeight() / 2);
        wo_5_q.setRotation(360);
        wo_5_view.setVisibility(View.GONE);
        wo_5_tv.setVisibility(View.GONE);
        wo_5_checkBox.setChecked(true);
        SendQuestion("暂无", userBean.getXuehao());
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

    private void question() {
        question_tag();
        if (m_tag_5) {
            wo_5_q.setPivotX(wo_5_q.getWidth() / 2);
            wo_5_q.setPivotY(wo_5_q.getHeight() / 2);
            wo_5_q.setRotation(90);
            m_tag_5 = !m_tag_5;
            if (tv_tag) {
                wo_5_view.setVisibility(View.GONE);
                wo_5_tv.setVisibility(View.VISIBLE);
            } else {
                wo_5_view.setVisibility(View.VISIBLE);
                wo_5_tv.setVisibility(View.GONE);
            }
        } else {
            wo_5_q.setPivotX(wo_5_q.getWidth() / 2);
            wo_5_q.setPivotY(wo_5_q.getHeight() / 2);
            wo_5_q.setRotation(360);
            m_tag_5 = !m_tag_5;
            wo_5_view.setVisibility(View.GONE);
            wo_5_tv.setVisibility(View.GONE);
        }

    }

    private void mima() {
        if (m_tag_4) {
            wo_4_l.setVisibility(View.VISIBLE);
            wo_4_l2.setVisibility(View.VISIBLE);
            wo_4_q.setPivotX(wo_4_q.getWidth() / 2);
            wo_4_q.setPivotY(wo_4_q.getHeight() / 2);
            wo_4_q.setRotation(90);
            m_tag_4 = !m_tag_4;
        } else {
            wo_4_l.setVisibility(View.GONE);
            wo_4_l2.setVisibility(View.GONE);
            wo_4_q.setPivotX(wo_4_q.getWidth() / 2);
            wo_4_q.setPivotY(wo_4_q.getHeight() / 2);
            wo_4_q.setRotation(360);
            m_tag_4 = !m_tag_4;
        }

        wo_4_yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (wo_4_e.getText().toString().equals("")) {
                    wo_4_l.setVisibility(View.GONE);
                    wo_4_l2.setVisibility(View.GONE);
                    wo_4_q.setPivotX(wo_4_q.getWidth() / 2);
                    wo_4_q.setPivotY(wo_4_q.getHeight() / 2);
                    wo_4_q.setRotation(360);
                } else {
                    wo_4_l.setVisibility(View.GONE);
                    wo_4_l2.setVisibility(View.GONE);
                    wo_4_q.setPivotX(wo_4_q.getWidth() / 2);
                    wo_4_q.setPivotY(wo_4_q.getHeight() / 2);
                    wo_4_q.setRotation(360);
                    sendByOKHttp_mima(wo_4_e.getText().toString());
                }
                wo_4_e.setText("");
                mess("密码修改成功！！！下次进入将重新登录");
                sharedPreferences = getContext().getSharedPreferences("share", MODE_PRIVATE);
                editor = sharedPreferences.edit();
                editor.putBoolean("isFirstRun", true);
                editor.commit();
            }
        });

        wo_4_no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                wo_4_e.setText("");
                wo_4_l.setVisibility(View.GONE);
                wo_4_l2.setVisibility(View.GONE);
                wo_4_q.setImageResource(R.mipmap.qianjin);
                wo_4_q.setPivotX(wo_4_q.getWidth() / 2);
                wo_4_q.setPivotY(wo_4_q.getHeight() / 2);
                wo_4_q.setRotation(360);
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
                .setAnimations(Style.ANIMATIONS_POP).show();
    }

    private void dingwei() {
        if (m_tag_2) {
            wo_3_l.setVisibility(View.VISIBLE);
            wo_3_q.setPivotX(wo_1_q.getWidth() / 2);
            wo_3_q.setPivotY(wo_1_q.getHeight() / 2);
            wo_3_q.setRotation(90);
            m_tag_2 = !m_tag_2;
        } else {
            wo_3_l.setVisibility(View.GONE);
            wo_3_q.setPivotX(wo_1_q.getWidth() / 2);
            wo_3_q.setPivotY(wo_1_q.getHeight() / 2);
            wo_3_q.setRotation(360);
            m_tag_2 = !m_tag_2;
        }


    }

    private void dingwei_tag() {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder().url("http://47.101.144.65:8080/House/Dingwei_tag?xuehao=" + userBean.getXuehao()).get().build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
            }//发送失败回调函数

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                String num = response.body().string();
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if (num.trim().equals("未定位")) {
                            wo_3_1.setImageResource(R.drawable.dingwei_false);
                            wo_3_2.setText("未定位");
                        } else {
                            wo_3_1.setImageResource(R.drawable.dingwei_true);
                            wo_3_2.setText(num);
                        }
                    }
                });
            }
        });
    }

    private void question_tag() {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder().url("http://47.101.144.65:8080/House/Question_find?username=" + userBean.getXuehao()).get().build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
            }//发送失败回调函数

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                String num = response.body().string();
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if (num.trim().equals("暂无")) {
                            tv_tag = true;
                        } else {
                            tv_tag = false;
                            wo_5_checkBox.setText(num.trim());
                        }
                    }
                });
            }
        });

    }


    private void zhanghao() {
        sharedPreferences = getContext().getSharedPreferences("share", MODE_PRIVATE);
        editor = sharedPreferences.edit();
        editor.putBoolean("isFirstRun", true);
        editor.commit();
        Intent intent = new Intent(getContext(), MainActivity.class);
        startActivity(intent);
//        SharedPreferences pref = getContext().getSharedPreferences("*", Context.MODE_PRIVATE);
//        if (pref != null) {
//            SharedPreferences.Editor editor = pref.edit();
//            editor.clear();
//            editor.commit();
//
//        }

        editor.putBoolean("isFirstRun", isFirstRun);
        editor.commit();
        getActivity().finish();

    }

    private void nicheng() {
        if (m_tag_1) {
            wo_1_l.setVisibility(View.VISIBLE);
            wo_1_l2.setVisibility(View.VISIBLE);
            wo_1_q.setPivotX(wo_1_q.getWidth() / 2);
            wo_1_q.setPivotY(wo_1_q.getHeight() / 2);
            wo_1_q.setRotation(90);
            m_tag_1 = !m_tag_1;
        } else {
            wo_1_l.setVisibility(View.GONE);
            wo_1_l2.setVisibility(View.GONE);
            wo_1_q.setPivotX(wo_1_q.getWidth() / 2);
            wo_1_q.setPivotY(wo_1_q.getHeight() / 2);
            wo_1_q.setRotation(360);
            m_tag_1 = !m_tag_1;
        }

        wo_1_yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (wo_1_e.getText().toString().equals("")) {
                    wo_1_l.setVisibility(View.GONE);
                    wo_1_l2.setVisibility(View.GONE);
                    wo_1_q.setPivotX(wo_1_q.getWidth() / 2);
                    wo_1_q.setPivotY(wo_1_q.getHeight() / 2);
                    wo_1_q.setRotation(360);
                } else {
                    nicheng.setText(wo_1_e.getText().toString());
                    wo_1_l.setVisibility(View.GONE);
                    wo_1_l2.setVisibility(View.GONE);
                    wo_1_q.setPivotX(wo_1_q.getWidth() / 2);
                    wo_1_q.setPivotY(wo_1_q.getHeight() / 2);
                    wo_1_q.setRotation(360);
                    sendByOKHttp(wo_1_e.getText().toString());
                }
                wo_1_e.setText("");
            }
        });

        wo_1_no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                wo_1_e.setText("");
                wo_1_l.setVisibility(View.GONE);
                wo_1_l2.setVisibility(View.GONE);
                wo_1_q.setImageResource(R.mipmap.qianjin);
                wo_1_q.setPivotX(wo_1_q.getWidth() / 2);
                wo_1_q.setPivotY(wo_1_q.getHeight() / 2);
                wo_1_q.setRotation(360);
            }
        });


    }

    private void touxiang() {
        if (ContextCompat.checkSelfPermission(getContext(),
                Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 101);
        } else {
            Intent intentToPickPic = new Intent(Intent.ACTION_PICK, null);
            intentToPickPic.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*");
            startActivityForResult(intentToPickPic, RC_CHOOSE_PHOTO);
        }
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case RC_CHOOSE_PHOTO:
                Uri uri = data.getData();
                com.example.house.FileUtil fileUtil = new com.example.house.FileUtil();
                String filePath = fileUtil.getFilePathByUri(getContext(), uri);
                if (!TextUtils.isEmpty(filePath)) {
                    RequestOptions requestOptions1 = new RequestOptions().skipMemoryCache(true).diskCacheStrategy(DiskCacheStrategy.NONE);
                    //将照片显示在 ivImage上
                    String name = ((City) getContext().getApplicationContext()).getXuehao();
                    File file = new File(BitmapUtil.compressImage(filePath));
                    uploadImage(file, name);
                    Glide.with(this).load(uri).apply(requestOptions1).into(tx);
                }
                save(filePath);
                break;
        }
    }

    private void uploadImage(File file, String n) {
        OkHttpClient okHttpClient;
//        RequestBody image = RequestBody.create( MediaType.parse( "image/*" ), file );
        String fn = file.getName();
        String type = fn.substring(fn.lastIndexOf(".") + 1);
        String nFileName = n + "." + type;
        System.out.println(nFileName);
        MediaType mediaType = MediaType.Companion.parse("image/*");
        RequestBody fileBody = RequestBody.Companion.create(file, mediaType);
        RequestBody requestBody = new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("file", nFileName, fileBody)
//                .addFormDataPart("pattern",type)
                .build();
        okhttp3.Request request = new okhttp3.Request.Builder()
                .url("http://47.101.144.65:8080/House/LoadImage")
                .post(requestBody)
                .build();
        okhttp3.OkHttpClient.Builder httpBuilder = new OkHttpClient.Builder();
        okHttpClient = httpBuilder.build();
        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {

            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {

            }
        });


    }

    private void save(String imagePath) {
        SharedPreferences.Editor editor = getActivity().getSharedPreferences("data", MODE_PRIVATE).edit();//获得SHaredPreferences.Editor对象
        editor.putBoolean("imageChange", true);//添加一个名为imageChange的boolean值，数值为true
        editor.putString("imagePath", imagePath);//保存imagePath图片路径
        editor.apply();//提交
    }

    private void load() {
        SharedPreferences preferences = getActivity().getSharedPreferences("data", MODE_PRIVATE);//获得SharedPreferences的对象
        //括号里的判断是去找imageChange这个对应的数值，若是找不到，则是返回false，找到了的话就是我们上面定义的true，就会执行其中的语句
        if (preferences.getBoolean("imageChange", false)) {
            String imagePath = preferences.getString("imagePath", "");//取出保存的imagePath，若是找不到，则是返回一个空
            RequestOptions requestOptions1 = new RequestOptions().skipMemoryCache(true).diskCacheStrategy(DiskCacheStrategy.NONE);
            //将照片显示在 ivImage上
            Glide.with(this).load(imagePath).apply(requestOptions1).into(tx);
        }
    }

//    private void save_wo(String n,String nn,String nc) {
//        SharedPreferences.Editor editor = getActivity().getSharedPreferences("data", MODE_PRIVATE).edit();//获得SHaredPreferences.Editor对象
//        editor.putBoolean(n, true);
//        editor.putString(nn, nc);
//        editor.apply();//提交
//
//    }


    private void sendByOKHttp_QQ(String QQ) {
        OkHttpClient client = new OkHttpClient();
//        System.out.println(userBean.getXuehao());
//        System.out.println(nicheng);
        Request request = new Request.Builder().url("http://47.101.144.65:8080/House/QQ?xuehao=" + userBean.getXuehao() + "&QQ=" + QQ).get().build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
            }//发送失败回调函数

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {

            }
        });
        userBean.setQQ(QQ);
        Gson gson = new Gson();
        String NC = gson.toJson(userBean);
        editor.putString("UserBean", NC);
        editor.commit();
    }


    private void sendByOKHttp(String nicheng) {
        OkHttpClient client = new OkHttpClient();
//        System.out.println(userBean.getXuehao());
//        System.out.println(nicheng);
        Request request = new Request.Builder().url("http://47.101.144.65:8080/House/Nicheng?xuehao=" + userBean.getXuehao() + "&nicheng=" + nicheng).get().build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
            }//发送失败回调函数

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {

            }
        });
        userBean.setNicheng(nicheng);
        Gson gson = new Gson();
        String NC = gson.toJson(userBean);
        editor.putString("UserBean", NC);
        editor.commit();
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


//    private void load_nc() {
//        SharedPreferences preferences = getActivity().getSharedPreferences("data", MODE_PRIVATE);//获得SharedPreferences的对象
//        //括号里的判断是去找imageChange这个对应的数值，若是找不到，则是返回false，找到了的话就是我们上面定义的true，就会执行其中的语句
//        if (preferences.getBoolean("nc", false)) {
//            String nc = preferences.getString("nicheng", "");//取出保存的imagePath，若是找不到，则是返回一个空
//            nicheng.setText(nc);
//        }
//    }

}
