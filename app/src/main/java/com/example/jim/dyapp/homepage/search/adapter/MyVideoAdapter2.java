package com.example.jim.dyapp.homepage.search.adapter;

import android.content.Context;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.jim.dyapp.R;
import com.example.jim.dyapp.appnet.bean.CategoryBean;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.controller.AbstractDraweeController;
import com.facebook.drawee.view.SimpleDraweeView;
import com.facebook.imagepipeline.request.ImageRequest;
import com.facebook.imagepipeline.request.ImageRequestBuilder;

import java.util.List;

/**
 * Created by han on 2018/2/2.
 */

public class MyVideoAdapter2 extends RecyclerView.Adapter<MyVideoAdapter2.ViewHolder> {
    private Context context;
    private List<CategoryBean.CategoryListBean.AwemeListBean> list;

    public MyVideoAdapter2(Context context, List<CategoryBean.CategoryListBean.AwemeListBean> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public MyVideoAdapter2.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.layout_video2, null);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(MyVideoAdapter2.ViewHolder holder, final int position) {
        String s = list.get(position).getVideo().getCover().getUrl_list().get(0);

        Uri parse = Uri.parse(s);
        // holder.sim.setImageURI(parse);
        //也可以控制图片请求的一些特性
        ImageRequest imageRequest = ImageRequestBuilder.newBuilderWithSource(parse)
                //设置支持jpeg渐进式展示（从模糊到清晰）
                .setProgressiveRenderingEnabled(true)
                .build();
        AbstractDraweeController builder = Fresco.newDraweeControllerBuilder()
                //图片的地址
                .setImageRequest(imageRequest)
                .setUri(parse)
                //设置图片自动播放属性
                .setAutoPlayAnimations(true)
                .build();
        holder.sim.setController(builder);

        holder.sim.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intent = new Intent(context, PlayVideoActivity.class);
//                intent.putExtra("url", list.get(position).getVideo().getDownload_addr().getUrl_list().get(0));
//                intent.putExtra("pic", list.get(position).getVideo().getCover().getUrl_list().get(0));
//                intent.putExtra("desc", list.get(position).getDesc());
//                context.startActivity(intent);
                Toast.makeText(context, "点击播放", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        SimpleDraweeView sim;

        public ViewHolder(View itemView) {
            super(itemView);
            sim = (SimpleDraweeView) itemView.findViewById(R.id.sim);
        }
    }
}