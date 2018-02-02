package com.example.jim.dyapp.homepage.search;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.ImageView;

import com.example.jim.dyapp.R;
import com.example.jim.dyapp.appnet.bean.CategoryBean;
import com.example.jim.dyapp.appnet.bean.XbannerBean;
import com.example.jim.dyapp.homepage.search.adapter.FxAdapter;
import com.example.jim.dyapp.homepage.search.presenter.FxPresenter;
import com.example.jim.dyapp.homepage.search.view.FxViewAPI;
import com.jcodecraeer.xrecyclerview.ProgressStyle;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SearchActivity extends AppCompatActivity implements FxViewAPI {
    @BindView(R.id.mXRecyclerView)
    XRecyclerView mXRecyclerView;
    @BindView(R.id.img_hui)
    ImageView mImgHui;
    private List<XbannerBean.BannerBean> ban = new ArrayList<>();
    private List<CategoryBean.CategoryListBean> list = new ArrayList<>();
    private int i = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        ButterKnife.bind(this);

        setListData(i, 20);

        //-----------------
        mXRecyclerView.setPullRefreshEnabled(true);
        mXRecyclerView.setLoadingMoreEnabled(true);

        mXRecyclerView.setRefreshProgressStyle(ProgressStyle.BallSpinFadeLoader);
        mXRecyclerView.setLoadingMoreProgressStyle(ProgressStyle.BallSpinFadeLoader);
        mXRecyclerView.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                list.clear();
                setListData(i, 20);
                mXRecyclerView.refreshComplete();
            }

            @Override
            public void onLoadMore() {
                i++;
                setListData(i, 5);
                mXRecyclerView.loadMoreComplete();
            }
        });
    }

    private void setListData(int cursor, int count) {
        FxPresenter fxPresenter = new FxPresenter(this, this);
        fxPresenter.getBanner("http://api.amemv.com/", "http://api.amemv.com/aweme/v1/find/?retry_type=no_retry&iid=23028350734&device_id=42386607829&aid=1128");
        fxPresenter.getList("http://api.amemv.com/", cursor, count);

    }

    @Override
    public void onBannerSuccess(XbannerBean xbannerBean) {
        ban.clear();
        List<XbannerBean.BannerBean> banner = xbannerBean.getBanner();
        ban.addAll(banner);
    }

    @Override
    public void onBannerFailed(String err) {

    }

    @Override
    public void onListSuccess(CategoryBean categoryBean) {
        List<CategoryBean.CategoryListBean> category_list = categoryBean.getCategory_list();
        list.addAll(category_list);
        mXRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        mXRecyclerView.setAdapter(new FxAdapter(this, ban, list));

    }

    @Override
    public void onListFailed(String err) {

    }

    @OnClick(R.id.img_hui)
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.img_hui:
                finish();
                break;
        }
    }
}
