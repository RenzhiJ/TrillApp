package com.example.jim.dyapp.homepage.search.presenter;

import android.content.Context;

import com.example.jim.dyapp.appnet.bean.CategoryBean;
import com.example.jim.dyapp.appnet.bean.XbannerBean;
import com.example.jim.dyapp.homepage.search.view.FxViewAPI;
import com.example.jim.dyapp.homepage.search.model.FxModel;

/**
 * Created by han on 2018/2/2.
 */

public class FxPresenter {

    private FxViewAPI fxViewAPI;
    private Context context;
    private final FxModel fxModel;

    public FxPresenter(FxViewAPI fxViewAPI, Context context) {
        this.fxViewAPI = fxViewAPI;
        this.context = context;
        fxModel = new FxModel();
    }

    public void getBanner(String baseUrl, String url) {
        fxModel.getBanner(baseUrl, url, new FxPresenterAPI() {
            @Override
            public void BannerSuccess(XbannerBean xbannerBean) {
                fxViewAPI.onBannerSuccess(xbannerBean);
            }

            @Override
            public void BannerFailed(String err) {
                fxViewAPI.onBannerFailed(err);
            }

            @Override
            public void ListSuccess(CategoryBean categoryBean) {

            }

            @Override
            public void ListFailed(String err) {

            }
        });
    }

    public void getList(String baseurl, int cursor, int count) {
        fxModel.getList(baseurl, cursor, count, new FxPresenterAPI() {
            @Override
            public void BannerSuccess(XbannerBean xbannerBean) {

            }

            @Override
            public void BannerFailed(String err) {

            }

            @Override
            public void ListSuccess(CategoryBean categoryBean) {
                fxViewAPI.onListSuccess(categoryBean);
            }

            @Override
            public void ListFailed(String err) {
                fxViewAPI.onListFailed(err);
            }
        });
    }

}
