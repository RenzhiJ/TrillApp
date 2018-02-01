package com.example.jim.dyapp.homepage.play;


import com.example.jim.dyapp.appnet.bean.CategoryBean;
import com.example.jim.dyapp.appnet.bean.CommentBean;
import com.example.jim.dyapp.appnet.utils.RetrofitUtils;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class SyModel {


    public void getList(String baseURL, int cursor, int count, final SyPresenterAPI syPresenterAPI) {

        RetrofitUtils.apiService(baseURL).getCategory(cursor, count).enqueue(new Callback<CategoryBean>() {
            @Override
            public void onResponse(Call<CategoryBean> call, Response<CategoryBean> response) {
                if (response.body().getStatus_code() == 0) {
                    syPresenterAPI.ListSuccess(response.body());
                }
            }

            @Override
            public void onFailure(Call<CategoryBean> call, Throwable t) {
                syPresenterAPI.ListFailed(t.toString());
            }
        });

    }

    public void getCommentData(String baseUrl, String url, final SyCommentAPI syCommentAPI) {

        RetrofitUtils.apiService(baseUrl).getCommentBean(url).enqueue(new Callback<CommentBean>() {
            @Override
            public void onResponse(Call<CommentBean> call, Response<CommentBean> response) {
                syCommentAPI.CommentSuccess(response.body());
            }

            @Override
            public void onFailure(Call<CommentBean> call, Throwable t) {
                syCommentAPI.CommentFailed(t.toString());
            }
        });
    }


}
