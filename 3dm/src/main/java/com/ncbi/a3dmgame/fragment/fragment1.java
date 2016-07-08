package com.ncbi.a3dmgame.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.ncbi.a3dmgame.R;
import com.ncbi.a3dmgame.adapter.MainActivityImageViewPagerAdapter;
import com.ncbi.a3dmgame.utils.MyLog;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by acer on 2016/7/6.
 */

public class Fragment1 extends Fragment {
    private View view;
    private ViewPager imageViewPager;
    private MainActivityImageViewPagerAdapter mainActivityImageViewPagerAdapter;
    private List<ImageView> imageViewList;

    private int typeId = 0;

    public Fragment1() {
    }

    public Fragment1(int typeId) {
        this.typeId = typeId;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = View.inflate(getContext(), R.layout.fragment1, null);
        imageViewPager = (ViewPager) view.findViewById(R.id.artival_top_viewpager);
        int imageRsId[] = {R.drawable.banner1, R.drawable.banner2, R.drawable.banner3};

        imageViewList = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            ImageView imageView = new ImageView(getActivity());
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            imageView.setImageResource(imageRsId[i]);
            imageViewList.add(imageView);
        }

        mainActivityImageViewPagerAdapter = new MainActivityImageViewPagerAdapter(imageViewList);
        imageViewPager.setAdapter(mainActivityImageViewPagerAdapter);

        TextView textView = (TextView) view.findViewById(R.id.main_artival_tv);
        MyLog.i("aaa", "TypeId=" + typeId);
        textView.setText(typeId+"");
        return view;
    }
}
