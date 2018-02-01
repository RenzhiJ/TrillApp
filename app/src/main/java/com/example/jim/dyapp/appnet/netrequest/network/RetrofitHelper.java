package com.example.jim.dyapp.appnet.netrequest.network;

import com.example.jim.dyapp.appnet.netrequest.home.HomeApi;
import com.example.jim.dyapp.appnet.netrequest.shoot.ShootApi;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by liqy on 2018/1/27.
 */

public class RetrofitHelper {

    private static volatile OkHttpClient okHttpClient;
    private static volatile Retrofit retrofit;

    static {
        initHttp();
    }

    private static void initHttp() {
        if (okHttpClient == null) {
            synchronized (RetrofitHelper.class) {
                if (okHttpClient == null) {
                    HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
                    logging.setLevel(HttpLoggingInterceptor.Level.BODY);
                    okHttpClient = new OkHttpClient.Builder()
                            .addInterceptor(new GetParamsInterceptor())
                            .addInterceptor(logging)
                            .build();
                }
            }
        }
    }

    private static Retrofit getRetrofit() {
        if (retrofit == null) {
            synchronized (RetrofitHelper.class) {
                if (retrofit == null) {
                    retrofit = new Retrofit.Builder()
                            .client(okHttpClient)
                            .baseUrl(Constants.BASE_URL)
                            .addConverterFactory(GsonConverterFactory.create())
                            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                            .build();
                }
            }
        }
        return retrofit;
    }

    public static <T> T createApi(Class<T> clazz){
        return getRetrofit().create(clazz);
    }

    /**
     * 主页接口服务
     * @return
     */
    public static HomeApi getHomeApi() {
        return createApi(HomeApi.class);
    }

    /**
     * 拍摄页面接口服务
     * @return
     */
    public static ShootApi getShootApi() {
        return createApi(ShootApi.class);
    }




}
