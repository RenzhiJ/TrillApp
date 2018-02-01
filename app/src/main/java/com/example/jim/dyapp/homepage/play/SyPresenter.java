package com.example.jim.dyapp.homepage.play;

import android.content.Context;

import com.example.jim.dyapp.appnet.bean.CategoryBean;
import com.example.jim.dyapp.appnet.bean.CommentBean;


public class SyPresenter {

    private final SyModel syModel;
    private SyViewAPI syViewAPI;
    private Context context;

    public SyPresenter(SyViewAPI syViewAPI, Context context) {
        this.syViewAPI = syViewAPI;
        this.context = context;
        syModel = new SyModel();
    }


    public void getList(String baseurl, int cursor, int count) {
        syModel.getList(baseurl, cursor, count, new SyPresenterAPI() {
            @Override
            public void ListSuccess(CategoryBean categoryBean) {
                syViewAPI.onListSuccess(categoryBean);
            }

            @Override
            public void ListFailed(String err) {
                syViewAPI.onListFailed(err);
            }
        });
    }

    public void getComment(String baseUrl, String url) {
        syModel.getCommentData(baseUrl, url, new SyCommentAPI() {
            @Override
            public void CommentSuccess(CommentBean commentBean) {
                syViewAPI.onCommentSuccess(commentBean);
            }

            @Override
            public void CommentFailed(String err) {
                syViewAPI.onCommentFailed(err);
            }
        });
    }


}
