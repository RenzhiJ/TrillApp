package com.example.jim.dyapp.homepage;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.jim.dyapp.R;
import com.example.jim.dyapp.homepage.author.OtherMineFragment;
import com.example.jim.dyapp.homepage.camera.CameraFragment;
import com.example.jim.dyapp.homepage.play.PlayFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by jim on 2018/1/26.
 */

public class RecommentFragment extends Fragment {

    @BindView(R.id.vp_recoment)
    ViewPager vpRecoment;
    Unbinder unbinder;
    private List<Fragment> list;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View inflate = inflater.inflate(R.layout.fragment_recomment, null);
        unbinder = ButterKnife.bind(this, inflate);
        if (list == null) {
            list = new ArrayList();
            list.add(new CameraFragment());
            list.add(new PlayFragment());
            list.add(new OtherMineFragment());
        }
        vpRecoment.setAdapter(new MyVpAdapter(getActivity().getSupportFragmentManager(), list));

        vpRecoment.setCurrentItem(1);
        return inflate;

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
