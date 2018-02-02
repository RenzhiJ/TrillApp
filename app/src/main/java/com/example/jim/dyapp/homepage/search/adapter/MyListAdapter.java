package com.example.jim.dyapp.homepage.search.adapter;

import android.content.Context;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.jim.dyapp.R;
import com.example.jim.dyapp.appnet.bean.CategoryBean;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

/**
 * Created by han on 2018/2/2.
 */

public class MyListAdapter extends RecyclerView.Adapter<MyListAdapter.ViewHolder> {
    private List<CategoryBean.CategoryListBean> list;
    private Context context;

    public MyListAdapter(List<CategoryBean.CategoryListBean> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @Override
    public MyListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.item_recycler, parent, false);
        ViewHolder viewHolder = new ViewHolder(inflate);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(MyListAdapter.ViewHolder holder, int position) {
        holder.name.setText(list.get(position).getDesc());
        if (list.get(position).getDesc().equals("热门音乐")) {
            Uri uri = Uri.parse(list.get(position).getMusic_info().getCover_large().getUrl_list().get(0));
            holder.icon.setImageURI(uri);
        } else {
            Uri uri = Uri.parse(list.get(position).getAweme_list().get(position).getVideo().getCover().getUrl_list().get(0));
            holder.icon.setImageURI(uri);
        }
    }

    @Override
    public int getItemCount() {
        return list != null ? list.size() : 0;
    }

    //自定义的ViewHolder，持有每个Item的的所有界面元素
    public static class ViewHolder extends RecyclerView.ViewHolder {

        private final TextView name;
        private final SimpleDraweeView icon;

        public ViewHolder(View view) {
            super(view);
            name = view.findViewById(R.id.name);
            icon = view.findViewById(R.id.icon);
        }
    }
}
