package com.example.wisatajogja.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.wisatajogja.R;
import com.example.wisatajogja.adapter.MainAdapter;
import com.example.wisatajogja.decoration.LayoutMarginDecoration;
import com.example.wisatajogja.model.ModelMain;
import com.example.wisatajogja.utilities.Tools;

import com.example.wisatajogja.decoration.BannerSlider;
import com.example.wisatajogja.fragment.FragmentSlider;
import com.example.wisatajogja.decoration.SliderIndicator;
import com.example.wisatajogja.adapter.SliderPagerAdapter;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class MainActivity extends AppCompatActivity implements MainAdapter.onSelectData {

    RecyclerView rvMainMenu;
    LayoutMarginDecoration gridMargin;
    ModelMain mdlMainMenu;
    List<ModelMain> lsMainMenu = new ArrayList<>();
    TextView tvToday;
    String hariIni;

    // slider
    private SliderPagerAdapter mAdapter;
    private SliderIndicator mIndicator;

    private BannerSlider bannerSlider;
    private LinearLayout mLinearLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bannerSlider = findViewById(R.id.sliderView);
        mLinearLayout = findViewById(R.id.pagesContainer);
        setupSlider();

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            getWindow().getDecorView().setSystemUiVisibility
                    (View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                            | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        }

        if (Build.VERSION.SDK_INT >= 21) {
            setWindowFlag(this, WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS, false);
            getWindow().setStatusBarColor(Color.TRANSPARENT);
        }

        tvToday = findViewById(R.id.tvDate);
        rvMainMenu = findViewById(R.id.rvMainMenu);
        GridLayoutManager mLayoutManager = new GridLayoutManager(this, 2,
                RecyclerView.VERTICAL, false);
        rvMainMenu.setLayoutManager(mLayoutManager);
        gridMargin = new LayoutMarginDecoration(2, Tools.dp2px(this, 4));
        rvMainMenu.addItemDecoration(gridMargin);
        rvMainMenu.setHasFixedSize(true);

        //get Time Now
        Date dateNow = Calendar.getInstance().getTime();
        hariIni = (String) DateFormat.format("EEEE", dateNow);
        getToday();
        setMenu();
    }

    private void getToday() {
        Date date = Calendar.getInstance().getTime();
        String tanggal = (String) DateFormat.format("d MMMM yyyy", date);
        String formatFix = hariIni + ", " + tanggal;
        tvToday.setText(formatFix);
    }

    private void setMenu() {
        mdlMainMenu = new ModelMain("Wisata", R.drawable.ic_destination);
        lsMainMenu.add(mdlMainMenu);
        mdlMainMenu = new ModelMain("Kuliner", R.drawable.ic_kuliner);
        lsMainMenu.add(mdlMainMenu);
        mdlMainMenu = new ModelMain("Penginapan", R.drawable.ic_hotel);
        lsMainMenu.add(mdlMainMenu);
        mdlMainMenu = new ModelMain("Tempat Ibadah", R.drawable.ic_pray_place);
        lsMainMenu.add(mdlMainMenu);

        MainAdapter myAdapter = new MainAdapter(lsMainMenu, this);
        rvMainMenu.setAdapter(myAdapter);

    }

    @Override
    public void onSelected(ModelMain mdlMain) {
        switch (mdlMain.getTxtName()) {
            case "Penginapan":
                startActivityForResult(new Intent(MainActivity.this, HotelActivity.class), 1);
                break;
            case "Kuliner":
                startActivityForResult(new Intent(MainActivity.this, KulinerActivity.class), 1);
                break;
            case "Tempat Ibadah":
                startActivityForResult(new Intent(MainActivity.this, PrayPlaceActivity.class), 1);
                break;
            case "Wisata":
                startActivityForResult(new Intent(MainActivity.this, WisataActivity.class), 1);
                break;
        }
    }

    //set Transparent Status bar
    public static void setWindowFlag(Activity activity, final int bits, boolean on) {

        Window win = activity.getWindow();
        WindowManager.LayoutParams winParams = win.getAttributes();
        if (on) {
            winParams.flags |= bits;
        } else {
            winParams.flags &= ~bits;
        }
        win.setAttributes(winParams);
    }

    private void setupSlider() {
        bannerSlider.setDurationScroll(800);
        List<Fragment> fragments = new ArrayList<>();

        String url = "https://raw.githubusercontent.com/ikbaladi/wisatajogja/main/img/";
        //link image
        fragments.add(FragmentSlider.newInstance(url + "1.jpg"));
        fragments.add(FragmentSlider.newInstance(url + "2.jpg"));
        fragments.add(FragmentSlider.newInstance(url + "3.jpg"));
        fragments.add(FragmentSlider.newInstance(url + "4.jpg"));

        mAdapter = new SliderPagerAdapter(getSupportFragmentManager(), fragments);
        bannerSlider.setAdapter(mAdapter);
        mIndicator = new SliderIndicator(this, mLinearLayout, bannerSlider, R.drawable.indicator_circle);
        mIndicator.setPageCount(fragments.size());
        mIndicator.show();
    }
}
