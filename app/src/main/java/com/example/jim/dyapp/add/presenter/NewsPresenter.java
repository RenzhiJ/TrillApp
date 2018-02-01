package com.example.jim.dyapp.add.presenter;


import com.example.jim.dyapp.add.bean.JsonBean;
import com.example.jim.dyapp.add.http.BlogService;
import com.example.jim.dyapp.add.model.RetrofitUtils;
import com.example.jim.dyapp.add.view.NewsView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Retrofit;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/**
 * Created by han on 2018/2/1.
 */

public class NewsPresenter {
    private NewsView inv;
    private Subscription subscribe;

    public void attachView(NewsView inv){
        this.inv = inv;
    }
    public void getNews(){
        Retrofit retrofit = RetrofitUtils.getInstance().getRetrofit();
        BlogService service = retrofit.create(BlogService.class);
        Map<String, String> map = new HashMap<>();
        map.put("cursor","0");
        map.put("retry_type","no_retry");
        map.put("iid","23796080841");
        map.put("device_id","42386607829");
        map.put("ac","wifi");
        map.put("channel","xiaomi");
        map.put("aid","1128");
        map.put("app_name","aweme");
        map.put("version_code","171");
        map.put("version_name","1.7.1");
        map.put("device_platform","android");
        map.put("ssmix","a");
        map.put("device_type","Redmi+Note+4");
        map.put("device_brand","Xiaomi");
        map.put("language","zh");
        map.put("os_api","23");
        map.put("os_version","6.0");
        map.put("uuid","863411038560129");
        map.put("openudid","87f33cdff2475c29");
        map.put("manifest_version_code","171");
        map.put("resolution","1080*1920");
        map.put("dpi","480");
        map.put("update_version_code","1712");
        map.put("_rticket","1516850206602");
        map.put("ts","1516850206");
        map.put("as","a1c544d6aef1dacca90585");
        map.put("cp","4517af5be09a6acae1emyd");
        map.put("mas","005cc35def3eba55ce38113bf3a3db1ce8ac1cac0c9c86c6c68626");
        subscribe = service.getUrl(map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<JsonBean>() {
                    @Override
                    public void call(JsonBean jsonBean) {
                        List<JsonBean.McListBean> list = jsonBean.getMc_list();
                        inv.success(list);
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        inv.failed(throwable.getMessage());
                    }
                });

    }
    public void detachView(){
        // 当Activity销毁的时候取消订阅时间，防止内存泄漏
        if (subscribe != null) {
            if (subscribe.isUnsubscribed()) {
                subscribe.unsubscribe();
            }
        }
        if (inv!=null){
            inv = null;
        }
    }
}
