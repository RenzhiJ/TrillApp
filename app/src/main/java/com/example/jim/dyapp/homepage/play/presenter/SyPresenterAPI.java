package com.example.jim.dyapp.homepage.play.presenter;


import com.example.jim.dyapp.appnet.bean.CategoryBean;

public interface SyPresenterAPI {

    void ListSuccess(CategoryBean categoryBean);

    void ListFailed(String err);
}
