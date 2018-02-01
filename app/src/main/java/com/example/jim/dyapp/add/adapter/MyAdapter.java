package com.example.jim.dyapp.add.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.jim.dyapp.R;
import com.example.jim.dyapp.add.bean.JsonBean;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;



/**
 * Created by han on 2018/2/1.
 */

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {
    private List<JsonBean.McListBean> datas;
    private Context context;


    public MyAdapter(List<JsonBean.McListBean> datas, Context context) {
        this.datas = datas;
        this.context = context;
    }

    //创建新View，被LayoutManager所调用
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = View.inflate(context, R.layout.item_plus, null);
        ViewHolder vh = new ViewHolder(view);
        return vh;
    }

    //将数据与界面进行绑定的操作
    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int position) {
        viewHolder.mTextView.setText(datas.get(position).getMc_name());
        viewHolder.item_img.setImageURI(datas.get(position).getAweme_cover().getUrl_list().get(0));
        viewHolder.item_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i("yyy",datas.get(position).toString());
            }
        });

    }

    //获取数据的数量
    @Override
    public int getItemCount() {
        return datas.size();
    }

    //自定义的ViewHolder，持有每个Item的的所有界面元素
    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView mTextView;
        private SimpleDraweeView item_img;
        public ViewHolder(View view) {
            super(view);
            mTextView = (TextView) view.findViewById(R.id.tv_text);
            item_img = view.findViewById(R.id.item_img);
        }
    }
}
