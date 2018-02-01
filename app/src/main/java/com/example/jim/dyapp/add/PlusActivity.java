package com.example.jim.dyapp.add;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.jim.dyapp.MainActivity;
import com.example.jim.dyapp.R;
import com.example.jim.dyapp.add.adapter.MyAdapter;
import com.example.jim.dyapp.add.bean.JsonBean;
import com.example.jim.dyapp.add.presenter.NewsPresenter;
import com.example.jim.dyapp.add.view.NewsView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
/**
 * Created by jim on 2018/1/25.
 * 加号
 */

public class PlusActivity extends AppCompatActivity {


    @BindView(R.id.tv_scsp)
    TextView mTvScsp;
    @BindView(R.id.tv_xzyy)
    TextView mTvXzyy;
    @BindView(R.id.m_ll)
    LinearLayout mMLl;
    @BindView(R.id.edit_sou)
    EditText mEditSou;
    @BindView(R.id.edit_ll)
    LinearLayout mEditLl;
    @BindView(R.id.rv)
    RecyclerView mRv;
    @BindView(R.id.tv_cancel)
    TextView mTvCancel;
    private NewsPresenter presenter;
    private List<JsonBean.McListBean> list = new ArrayList<>();
    private MyAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plus);
        ButterKnife.bind(this);
        StaggeredGridLayoutManager managera = new StaggeredGridLayoutManager(2, LinearLayoutManager.HORIZONTAL);
        mRv.setLayoutManager(managera);
        getData();

    }
    public void getData(){
        presenter = new NewsPresenter();
        presenter.getNews();
        presenter.attachView(new NewsView() {
            @Override
            public void success(List<JsonBean.McListBean> data) {
                list.clear();
                list.addAll(data);
                Log.i("zzz", "success: " + list.toString());
                if (adapter ==null){
                    adapter = new MyAdapter(list, PlusActivity.this);
                    mRv.setAdapter(adapter);
                }else {
                    adapter.notifyDataSetChanged();
                }

            }


            @Override
            public void failed(String e) {
                Log.i("ttt",e.toString());
            }
        });
    }

    @OnClick({R.id.tv_scsp, R.id.tv_xzyy, R.id.m_ll, R.id.edit_ll, R.id.rv, R.id.tv_cancel})
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.tv_scsp:
                break;
            case R.id.tv_xzyy:
                break;
            case R.id.m_ll:
                break;
            case R.id.edit_ll:
                break;
            case R.id.tv_cancel:
                Intent intent = new Intent(PlusActivity.this, MainActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.in_from_top, R.anim.out_to_bottom);
                finish();
                break;
        }
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (presenter!=null){
            presenter.detachView();
        }

    }
}

