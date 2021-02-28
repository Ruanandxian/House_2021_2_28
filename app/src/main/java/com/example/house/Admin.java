package com.example.house;


import android.graphics.Color;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.arlib.floatingsearchview.FloatingSearchView;
import com.cjj.sva.JJSearchView;
import com.cjj.sva.anim.controller.JJChangeArrowController;
import com.cjj.sva.anim.controller.JJCircleToLineAlphaController;
import com.cjj.sva.anim.controller.JJCircleToSimpleLineController;
import com.example.house.normal.Admin_1Fragment;
import com.example.house.normal.Admin_2Fragment;
import com.example.house.normal.Admin_3Fragment;
import com.example.house.normal.Admin_4Fragment;
import com.example.house.normal.C_1Fragment;
import com.example.house.normal.C_2Fragment;
import com.github.johnpersano.supertoasts.library.Style;
import com.github.johnpersano.supertoasts.library.SuperActivityToast;
import com.github.johnpersano.supertoasts.library.utils.PaletteUtils;
import com.ogaclejapan.smarttablayout.SmartTabLayout;
import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItemAdapter;
import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItems;


public class Admin extends AppCompatActivity {
    Button test;
    Button test2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.admin_1);

        FragmentPagerItemAdapter adapter = new FragmentPagerItemAdapter(
                this.getSupportFragmentManager(), FragmentPagerItems.with(this)
                .add(R.string.admin_3, Admin_3Fragment.class)
                .add(R.string.admin_1, Admin_1Fragment.class)
                .add(R.string.admin_2, Admin_2Fragment.class)
                .add(R.string.admin_4, Admin_4Fragment.class)
                .create());
        ViewPager viewPager = (ViewPager) findViewById(R.id.admin_viewpager);
        viewPager.setAdapter(adapter);
        SmartTabLayout viewPagerTab = findViewById(R.id.admin_viewpagertab);
        viewPagerTab.setViewPager(viewPager);
    }


}