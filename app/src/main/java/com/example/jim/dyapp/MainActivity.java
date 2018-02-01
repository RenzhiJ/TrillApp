package com.example.jim.dyapp;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.jim.dyapp.add.PlusActivity;
import com.example.jim.dyapp.attention.AttentionFragment;
import com.example.jim.dyapp.homepage.HomePageFragment;
import com.example.jim.dyapp.message.MessageFragment;
import com.example.jim.dyapp.mine.MineFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.frame_Layouts)
    FrameLayout frameLayouts;
    @BindView(R.id.home_page_tv)
    TextView homePageTv;
    @BindView(R.id.flash_img)
    ImageView flashImg;
    @BindView(R.id.home_page_img)
    ImageView homePageImg;
    @BindView(R.id.home_page)
    LinearLayout homePage;
    @BindView(R.id.attention_img)
    ImageView attentionImg;
    @BindView(R.id.attention)
    LinearLayout attention;
    @BindView(R.id.music)
    LinearLayout music;
    @BindView(R.id.message_img)
    ImageView messageImg;
    @BindView(R.id.message)
    LinearLayout message;
    @BindView(R.id.mine_img)
    ImageView mineImg;
    @BindView(R.id.mine)
    LinearLayout mine;
    @BindView(R.id.music_img)
    ImageView musicImg;
    @BindView(R.id.bottomBar)
    LinearLayout bottomBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            //透明状态栏
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            //透明导航栏
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        }
        //设置Fragment
        setFragment(new HomePageFragment());



    }

    @OnClick({R.id.home_page, R.id.attention, R.id.music, R.id.message, R.id.mine})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.home_page:
                //文字隐藏
                homePageTv.setVisibility(View.GONE);
                //刷新图标显示
                flashImg.setVisibility(View.VISIBLE);
                //下划线显示
                homePageImg.setVisibility(View.VISIBLE);
                //其他的隐藏
                attentionImg.setVisibility(View.GONE);
                messageImg.setVisibility(View.GONE);
                mineImg.setVisibility(View.GONE);
                //去除背景色
                bottomBar.setBackgroundResource(0);
                //设置Fragment
                setFragment(new HomePageFragment());
                break;
            case R.id.attention:
                //文字显示
                homePageTv.setVisibility(View.VISIBLE);
                //刷新图标隐藏
                flashImg.setVisibility(View.GONE);
                //下划线显示
                attentionImg.setVisibility(View.VISIBLE);
                //其他的隐藏
                homePageImg.setVisibility(View.GONE);
                messageImg.setVisibility(View.GONE);
                mineImg.setVisibility(View.GONE);
                bottomBar.setBackgroundColor(Color.BLACK);
                //设置Fragment
                setFragment(new AttentionFragment());
                break;
            case R.id.music:
                //文字显示
                homePageTv.setVisibility(View.VISIBLE);
                //刷新图标隐藏
                flashImg.setVisibility(View.GONE);
                //下划线显示
                attentionImg.setVisibility(View.GONE);
                //其他的隐藏
                homePageImg.setVisibility(View.GONE);
                music.setVisibility(View.VISIBLE);
                messageImg.setVisibility(View.GONE);
                mineImg.setVisibility(View.GONE);
                bottomBar.setBackgroundColor(Color.BLACK);
                Intent intent = new Intent(this, PlusActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.in_from_bottom, R.anim.out_to_top);
                finish();
                break;
            case R.id.message:
                //文字显示
                homePageTv.setVisibility(View.VISIBLE);
                //刷新图标隐藏
                flashImg.setVisibility(View.GONE);
                //下划线显示
                attentionImg.setVisibility(View.GONE);
                //其他的隐藏
                homePageImg.setVisibility(View.GONE);
                messageImg.setVisibility(View.VISIBLE);
                mineImg.setVisibility(View.GONE);
                bottomBar.setBackgroundColor(Color.BLACK);
                //设置Fragment
                setFragment(new MessageFragment());
                break;
            case R.id.mine:
                //文字显示
                homePageTv.setVisibility(View.VISIBLE);
                //刷新图标隐藏
                flashImg.setVisibility(View.GONE);
                //下划线显示
                mineImg.setVisibility(View.VISIBLE);
                //其他的隐藏
                homePageImg.setVisibility(View.GONE);
                messageImg.setVisibility(View.GONE);
                attentionImg.setVisibility(View.GONE);
                bottomBar.setBackgroundColor(Color.BLACK);
                //设置Fragment
                setFragment(new MineFragment());
                break;
        }
    }


    /**
     * 切换Fragment
     */
    private void setFragment(Fragment f) {
        getSupportFragmentManager().beginTransaction().replace(R.id.frame_Layouts, f).commit();
    }

}