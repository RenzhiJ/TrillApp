package com.example.jim.dyapp.homepage.nearby;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.jim.dyapp.R;
import com.example.jim.dyapp.appnet.bean.CategoryBean;
import com.example.jim.dyapp.appnet.bean.CommentBean;
import com.example.jim.dyapp.homepage.play.presenter.SyPresenter;
import com.example.jim.dyapp.homepage.play.view.SyViewAPI;
import com.jcodecraeer.xrecyclerview.ProgressStyle;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by jim on 2018/1/26.
 * 附近
 */

public class NearbyFragment extends Fragment implements SyViewAPI {
    @BindView(R.id.myxRecyclerView)
    XRecyclerView myxRecyclerView;
    Unbinder unbinder;
    private int cursor = 1;
    private int count = 20;
    private MyNearbyAdapter myNearbyAdapter;
    private List<CategoryBean.CategoryListBean> listBeans = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable final ViewGroup container, @Nullable Bundle savedInstanceState) {
        View inflate = inflater.inflate(R.layout.fragment_nearby, null);
        unbinder = ButterKnife.bind(this, inflate);
        getData(cursor, count);
//设置可上拉
        myxRecyclerView.setPullRefreshEnabled(true);
        myxRecyclerView.setLoadingMoreEnabled(true);
        //设置上拉下拉样式
        myxRecyclerView.setRefreshProgressStyle(ProgressStyle.SemiCircleSpin);
        myxRecyclerView.setLoadingMoreProgressStyle(ProgressStyle.BallBeat);

        myxRecyclerView.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                cursor = 1;
                listBeans.clear();
                getData(cursor, count);
                myxRecyclerView.refreshComplete();
            }

            @Override
            public void onLoadMore() {
                cursor++;
                getData(cursor, count);
                myxRecyclerView.loadMoreComplete();
            }
        });
        return inflate;
    }

    private void getData(int cursor, int count) {
        SyPresenter syPresenter = new SyPresenter(this, getContext());
        syPresenter.getList("http://api.amemv.com/", cursor, count);
    }


    @Override
    public void onListSuccess(CategoryBean categoryBean) {

        listBeans.addAll(categoryBean.getCategory_list());

        // 线性布局管理器   VERTICAL默认样式/竖向显示       第三个参数是数据是否到过来显示
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 2, GridLayoutManager.VERTICAL, false);
        //添加布局管理器
        myxRecyclerView.setLayoutManager(gridLayoutManager);
        myNearbyAdapter = new MyNearbyAdapter(getContext(), listBeans);
        myxRecyclerView.setAdapter(myNearbyAdapter);

    }

    @Override
    public void onListFailed(String err) {

    }

    @Override
    public void onCommentSuccess(CommentBean commentBean) {

    }

    @Override
    public void onCommentFailed(String err) {

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}

