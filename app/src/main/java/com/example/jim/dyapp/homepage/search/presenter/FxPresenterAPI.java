package com.example.jim.dyapp.homepage.search.presenter;

import com.example.jim.dyapp.appnet.bean.CategoryBean;
import com.example.jim.dyapp.appnet.bean.XbannerBean;

/**
 * Created by han on 2018/2/2.
 */

public interface FxPresenterAPI {
    void BannerSuccess(XbannerBean xbannerBean);

    void BannerFailed(String err);

    void ListSuccess(CategoryBean categoryBean);

    void ListFailed(String err);
}
