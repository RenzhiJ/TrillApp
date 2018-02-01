package com.example.jim.dyapp.add.view;

import com.example.jim.dyapp.add.bean.JsonBean;

import java.util.List;

/**
 * Created by han on 2018/2/1.
 */

public interface NewsView {
    void success(List<JsonBean.McListBean> data);
    void failed(String e);
}