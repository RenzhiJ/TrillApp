package com.example.jim.dyapp.homepage.play;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.support.v4.view.PagerAdapter;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.PopupWindow;

import com.bumptech.glide.Glide;
import com.example.jim.dyapp.R;
import com.example.jim.dyapp.appnet.bean.CategoryBean;
import com.example.jim.dyapp.appnet.bean.CommentBean;

import java.util.List;

import fm.jiecao.jcvideoplayer_lib.JCVideoPlayer;
import fm.jiecao.jcvideoplayer_lib.JCVideoPlayerStandard;

public class MyVpAdapter extends PagerAdapter implements View.OnClickListener {
    private Context context;
    private List<CategoryBean.CategoryListBean.AwemeListBean> list;
    private CommentBean commentBean;
    private PopupWindow mPopWindow;
    private ImageView like;

    public MyVpAdapter(Context context, List<CategoryBean.CategoryListBean.AwemeListBean> list, CommentBean commentBean) {
        this.context = context;
        this.list = list;
        this.commentBean = commentBean;
    }

    @Override
    public int getCount() {
        return Integer.MAX_VALUE;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, final int position) {
        View view = View.inflate(context, R.layout.vp_layout, null);
        like = view.findViewById(R.id.img_like);
        //pop
        ImageView img_pl = view.findViewById(R.id.img_pinglun);
        img_pl.setOnClickListener(this);
        like.setOnClickListener(this);
        final JCVideoPlayerStandard videoplayer = view.findViewById(R.id.videoplayer);
        WebView webView = new WebView(context);
        webView.loadUrl(list.get(position % list.size()).getVideo().getDownload_addr().getUrl_list().get(0));
        webView.getSettings().setJavaScriptEnabled(true);
        webView.setWebViewClient(new WebViewClient() {
            //页面加载开始
            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
            }

            //页面加载完成
            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                //这个realUrl即为重定向之后的地址
                boolean setUp = videoplayer.setUp(url, JCVideoPlayer.SCREEN_LAYOUT_LIST, list.get(position % list.size()).getDesc());
                if (setUp) {
                    videoplayer.thumbImageView.setScaleType(ImageView.ScaleType.FIT_XY);
                    Glide.with(context).load(list.get(position % list.size()).getVideo().getCover().getUrl_list().get(0)).into(videoplayer.thumbImageView);
                    videoplayer.startPlayLogic();
                }
            }
        });
        container.addView(view);
        return view;
    }

    //showpop
    //显示popupWindow
    private void showPopupWindow() {
        //设置contentView
        View contentView = LayoutInflater.from(context).inflate(R.layout.pl_pop_view, null);
        mPopWindow = new PopupWindow(contentView,
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT, true);
        // 给popupWindow设置焦点--->解决第一个问题
        mPopWindow.setTouchable(true);// 这个方法,,,默认就是true
        mPopWindow.setFocusable(true);
        // 给popupWindow设置空白处可以点击....注意:只写这个方法是无效的,,,必须要给popupWindow添加一个背景--->解决第二个问题
        mPopWindow.setOutsideTouchable(true);
        // new BitmapDrawable()--->给popupWindow设置一个空白背景
        mPopWindow.setBackgroundDrawable(new BitmapDrawable());
        mPopWindow.setContentView(contentView);
        //设置各个控件的点击响应
        RecyclerView recyclerView = contentView.findViewById(R.id.recycler_pinglun);
        recyclerView.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false));
        List<CommentBean.DataBean.TopCommentsBean> top_comments = commentBean.getData().getTop_comments();

        recyclerView.setAdapter(new PlAdapter(top_comments, context));

        //显示PopupWindow
        View rootview = LayoutInflater.from(context).inflate(R.layout.vp_layout, null);
        mPopWindow.showAtLocation(rootview, Gravity.BOTTOM, 0, 0);

    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.img_pinglun:
                showPopupWindow();
                break;
            case R.id.img_like:
                like.setImageResource(R.mipmap.hongxin);
//                Toast.makeText(context, "点赞了", Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
