package com.example.jim.dyapp.homepage;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.jim.dyapp.R;
import com.example.jim.dyapp.homepage.nearby.NearbyFragment;
import com.example.jim.dyapp.homepage.search.SearchActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created by jim on 2018/1/26.
 * 首页
 */
public class HomePageFragment extends Fragment {
    @BindView(R.id.img_search)
    ImageView imgSearch;
    @BindView(R.id.tv_recomment)
    TextView tvRecomment;
    @BindView(R.id.tv_nearby)
    TextView tvNearby;
    Unbinder unbinder;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View inflate = inflater.inflate(R.layout.fragment_homepage, null);
        unbinder = ButterKnife.bind(this, inflate);
        setFragment(new RecommentFragment());
        return inflate;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick({R.id.img_search, R.id.tv_recomment, R.id.tv_nearby})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.img_search:
                Intent intent = new Intent(getContext(), SearchActivity.class);
                startActivity(intent);
                break;
            case R.id.tv_recomment:
                setFragment(new RecommentFragment());
                break;
            case R.id.tv_nearby:
                setFragment(new NearbyFragment());
                break;
        }
    }

    /**
     * 切换Fragment
     */
    private void setFragment(Fragment f) {
        getChildFragmentManager().beginTransaction().replace(R.id.home_page_fragment, f).commit();
    }
}
