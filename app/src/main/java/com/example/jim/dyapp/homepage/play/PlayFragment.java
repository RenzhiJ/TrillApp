package com.example.jim.dyapp.homepage.play;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.jim.dyapp.R;
import com.example.jim.dyapp.appnet.bean.CategoryBean;
import com.example.jim.dyapp.appnet.bean.CommentBean;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import fr.castorflex.android.verticalviewpager.VerticalViewPager;

/**
 * Created by jim on 2018/1/29.
 */

public class PlayFragment extends Fragment implements SyViewAPI {

    @BindView(R.id.vvp)
    VerticalViewPager vvp;
    Unbinder unbinder;
    private MyVpAdapter myVpAdapter;
    private List<CategoryBean.CategoryListBean.AwemeListBean> list = new ArrayList<>();
    private int cursor = 1;
    private int count = 5;
    private CommentBean commentBean1;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View inflate = inflater.inflate(R.layout.fragment_play, null);
        unbinder = ButterKnife.bind(this, inflate);
        SyPresenter syPresenter = new SyPresenter(this, getContext());
        syPresenter.getList("http://api.amemv.com/", cursor, count);
        syPresenter.getComment("https://iu.snssdk.com/", "https://iu.snssdk.com/neihan/comments/?group_id=75630370024&item_id=75630370024&count=20&offset=0&enable_image_comment=1&iid=22991324588&device_id=42638038240&ac=wifi&channel=app_download&aid=7&app_name=joke_essay&version_code=672&version_name=6.7.2&device_platform=android&ssmix=a&device_type=PRA-AL00XC00B160&device_brand=PRA-AL00X&language=zh&os_api=19&os_version=4.4.4&uuid=863419663747853&openudid=1010046353365109&manifest_version_code=672&resolution=576*1024&dpi=191&update_version_code=6723&_rticket=1516277254616&ts=1516277254&as=a2c5c8d606808a1ec0&cp=8301a95b680a6ee3e2");


        return inflate;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void onListSuccess(CategoryBean categoryBean) {
        List<CategoryBean.CategoryListBean> category_list = categoryBean.getCategory_list();
        for (int i = 0; i < category_list.size(); i++) {
            List<CategoryBean.CategoryListBean.AwemeListBean> aweme_list = category_list.get(i).getAweme_list();
            list.addAll(aweme_list);
        }
        myVpAdapter = new MyVpAdapter(getActivity(), list, commentBean1);
        vvp.setAdapter(myVpAdapter);
    }

    @Override
    public void onListFailed(String err) {

    }

    @Override
    public void onCommentSuccess(CommentBean commentBean) {
        commentBean1 = commentBean;

    }

    @Override
    public void onCommentFailed(String err) {

    }
}
