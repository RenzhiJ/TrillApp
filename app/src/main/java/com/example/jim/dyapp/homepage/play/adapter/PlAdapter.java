package com.example.jim.dyapp.homepage.play.adapter;

import android.content.Context;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.jim.dyapp.R;
import com.example.jim.dyapp.appnet.bean.CommentBean;
import com.facebook.drawee.view.SimpleDraweeView;
import com.jaren.lib.view.LikeView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class PlAdapter extends RecyclerView.Adapter<PlAdapter.ViewHolder> {
    private List<CommentBean.DataBean.TopCommentsBean> list;
    private Context context;

    public PlAdapter(List<CommentBean.DataBean.TopCommentsBean> list, Context context) {
        this.list = list;
        this.context = context;
    }


    //创建新View，被LayoutManager所调用
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.pl_item, viewGroup, false);
        ViewHolder vh = new ViewHolder(view);
        return vh;
    }

    //将数据与界面进行绑定的操作
    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int position) {
        viewHolder.mTextView.setText(list.get(position).getText());
        viewHolder.plImg.setImageURI(Uri.parse(list.get(position).getAvatar_url()));
        viewHolder.plName.setText(list.get(position).getUser_name());
        int create_time = list.get(position).getCreate_time();
        String s = timeslashData(create_time + "");
        viewHolder.plTime.setText(s);

    }

    //http://blog.csdn.net/emptoney/article/details/51943515
    //时间戳转换为日期格式
    public static String timeslashData(String time) {

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日");

        String sd = sdf.format(new Date(Long.parseLong(time)));

        return sd;
    }

    //获取数据的数量
    @Override
    public int getItemCount() {
        return list != null ? list.size() : 0;
    }

    //自定义的ViewHolder，持有每个Item的的所有界面元素
    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView mTextView;
        private final SimpleDraweeView plImg;
        private final TextView plName;
        private final TextView plTime;
        private LikeView lv;
        public ViewHolder(View view) {
            super(view);
            plImg = view.findViewById(R.id.pl_img);
            mTextView = view.findViewById(R.id.tv_pl_content);
            plName = view.findViewById(R.id.tv_pl_user);
            plTime = view.findViewById(R.id.tv_pl_time);
            lv = view.findViewById(R.id.lv);


        }
    }
}
