package com.example.jim.dyapp.homepage.play.view;


import com.example.jim.dyapp.appnet.bean.CategoryBean;
import com.example.jim.dyapp.appnet.bean.CommentBean;

public interface SyViewAPI {
    void onListSuccess(CategoryBean categoryBean);

    void onListFailed(String err);

    void onCommentSuccess(CommentBean commentBean);

    void onCommentFailed(String err);
}
