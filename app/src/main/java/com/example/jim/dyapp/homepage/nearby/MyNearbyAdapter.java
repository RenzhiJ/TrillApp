package com.example.jim.dyapp.homepage.nearby;

import android.content.Context;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.jim.dyapp.R;
import com.example.jim.dyapp.appnet.bean.CategoryBean;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

/**
 * Created by han on 2018/2/2.
 */

public class MyNearbyAdapter extends RecyclerView.Adapter<MyNearbyAdapter.ViewHolder> {
    private Context context;
    private List<CategoryBean.CategoryListBean> listBeans;

    public MyNearbyAdapter(Context context, List<CategoryBean.CategoryListBean> listBeans) {
        this.context = context;
        this.listBeans = listBeans;
    }

    @Override
    public MyNearbyAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_nearby, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(MyNearbyAdapter.ViewHolder holder, int position) {
        holder.simImg.setImageURI(Uri.parse(listBeans.get(position).getAweme_list().get(0).getVideo().getCover().getUrl_list().get(0)));
    }

    @Override
    public int getItemCount() {
        return listBeans != null ? listBeans.size() : 0;
    }

    //自定义的ViewHolder，持有每个Item的的所有界面元素
    public static class ViewHolder extends RecyclerView.ViewHolder {

        private final SimpleDraweeView simImg;

        public ViewHolder(View view) {
            super(view);
            simImg = view.findViewById(R.id.nearby_img);
        }
    }
}
