package com.example.jim.dyapp.add.model;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by han on 2018/2/1.
 */

public class RetrofitUtils {
    private static volatile RetrofitUtils instance;
    private Retrofit retrofit;

    private RetrofitUtils(){

    }
    private RetrofitUtils(String baseUrl){
        OkHttpClient client = new OkHttpClient();
        retrofit = new Retrofit.Builder()
                .client(client)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(baseUrl)
                .build();
    }
    public static RetrofitUtils getInstance(String baseUrl){
        if (instance==null){
            synchronized (RetrofitUtils.class){
                if (null==instance){
                    instance = new RetrofitUtils(baseUrl);

                }
            }
        }
        return instance;

    }
    public static RetrofitUtils getInstance(){
        if (null == instance){
            return  getInstance("http://aweme.snssdk.com/");
        }
        return instance;
    }
    public Retrofit getRetrofit(){
        return retrofit;
    }
}