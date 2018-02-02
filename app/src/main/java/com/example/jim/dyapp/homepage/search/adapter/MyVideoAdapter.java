package com.example.jim.dyapp.homepage.search.adapter;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.jim.dyapp.R;
import com.example.jim.dyapp.appnet.bean.CategoryBean;

import java.util.List;

/**
 * Created by han on 2018/2/2.
 */

public class MyVideoAdapter extends RecyclerView.Adapter<MyVideoAdapter.ViewHolder> {
    private Context context;
    private List<CategoryBean.CategoryListBean> list;

    public MyVideoAdapter(Context context, List<CategoryBean.CategoryListBean> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public MyVideoAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.layout_video, null);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(MyVideoAdapter.ViewHolder holder, int position) {
        holder.text.setText(list.get(position).getDesc());
        List<CategoryBean.CategoryListBean.AwemeListBean> aweme_list = list.get(position).getAweme_list();
        for (int i = 0; i < aweme_list.size(); i++) {
            String desc = aweme_list.get(i).getDesc();
            holder.content.setText(desc);
        }
        List<CategoryBean.CategoryListBean.AwemeListBean> aweme_list1 = list.get(position).getAweme_list();
        MyVideoAdapter2 myVideoAdapter2 = new MyVideoAdapter2(context, aweme_list1);
        holder.rc_video.setAdapter(myVideoAdapter2);
        holder.rc_video.setLayoutManager(new GridLayoutManager(context, 1, GridLayoutManager.HORIZONTAL, false));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        RecyclerView rc_video;
        TextView text;
        TextView content;

        public ViewHolder(View itemView) {
            super(itemView);
            rc_video = (RecyclerView) itemView.findViewById(R.id.rc_video);
            text = (TextView) itemView.findViewById(R.id.user_text);
            content = (TextView) itemView.findViewById(R.id.user_content);
        }
    }
}