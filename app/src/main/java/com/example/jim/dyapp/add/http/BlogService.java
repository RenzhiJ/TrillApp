package com.example.jim.dyapp.add.http;

import com.example.jim.dyapp.add.bean.JsonBean;

import java.util.Map;

import retrofit2.http.GET;
import retrofit2.http.QueryMap;
import rx.Observable;

/**
 * Created by han on 2018/2/1.
 */

public interface BlogService {
    @GET("aweme/v1/music/collection/")
    Observable<JsonBean> getUrl(@QueryMap Map<String, String> map);
}