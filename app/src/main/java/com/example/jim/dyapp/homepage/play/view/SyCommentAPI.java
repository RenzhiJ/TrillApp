package com.example.jim.dyapp.homepage.play.view;


import com.example.jim.dyapp.appnet.bean.CommentBean;

/**
 * Created by jim on 2018/1/19.
 */

public interface SyCommentAPI {
    void CommentSuccess(CommentBean commentBean);

    void CommentFailed(String err);
}
