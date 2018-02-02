package com.example.jim.dyapp.homepage.search.view;

import com.example.jim.dyapp.appnet.bean.CategoryBean;
import com.example.jim.dyapp.appnet.bean.XbannerBean;

/**
 * Created by han on 2018/2/2.
 */

public interface FxViewAPI {
    void onBannerSuccess(XbannerBean xbannerBean);

    void onBannerFailed(String err);

    void onListSuccess(CategoryBean categoryBean);

    void onListFailed(String err);
}
