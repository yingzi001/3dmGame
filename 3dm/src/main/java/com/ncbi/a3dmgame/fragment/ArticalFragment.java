package com.ncbi.a3dmgame.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.print.PrintHelper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ncbi.a3dmgame.R;

import java.util.List;

public class ArticalFragment extends Fragment {

    private List<Fragment> mainFragments;
    private ArticalFragment articalFragment;
    private View view;

    public ArticalFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_artical, container, false);
        return view;
    }

}
