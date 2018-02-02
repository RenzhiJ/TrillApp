package com.example.jim.dyapp.homepage.search.model;

import com.example.jim.dyapp.appnet.bean.CategoryBean;
import com.example.jim.dyapp.appnet.bean.XbannerBean;
import com.example.jim.dyapp.appnet.utils.RetrofitUtils;
import com.example.jim.dyapp.homepage.search.presenter.FxPresenterAPI;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by han on 2018/2/2.
 */

public class FxModel {

    public void getBanner(String baseUrl, String url, final FxPresenterAPI fxPresenterAPI) {

        RetrofitUtils.apiService(baseUrl).getBanner(url).enqueue(new Callback<XbannerBean>() {
            @Override
            public void onResponse(Call<XbannerBean> call, Response<XbannerBean> response) {
                if (response.body().getStatus_code() == 0) {
                    XbannerBean body = response.body();
                    fxPresenterAPI.BannerSuccess(body);
                }
            }

            @Override
            public void onFailure(Call<XbannerBean> call, Throwable t) {
                fxPresenterAPI.BannerFailed(t.toString());

            }
        });


    }

    public void getList(String baseURL, int cursor, int count, final FxPresenterAPI fxPresenterAPI) {

        RetrofitUtils.apiService(baseURL).getCategory(cursor, count).enqueue(new Callback<CategoryBean>() {
            @Override
            public void onResponse(Call<CategoryBean> call, Response<CategoryBean> response) {
                if (response.body().getStatus_code() == 0) {
                    fxPresenterAPI.ListSuccess(response.body());
                }
            }

            @Override
            public void onFailure(Call<CategoryBean> call, Throwable t) {
                fxPresenterAPI.ListFailed(t.toString());

            }
        });


    }


}
