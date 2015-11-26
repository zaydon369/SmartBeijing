package com.itzheng.smartbeijing.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

import com.itzheng.smartbeijing.R;
import com.itzheng.smartbeijing.base.BaseFragment;

/**
 * Created by asus on 2015/11/26.
 */
public class HomeFragment extends BaseFragment{
    @Override
    public View initView(LayoutInflater inflater) {
        view = inflater.inflate(R.layout.frag_home,null);
        return view;
    }

    @Override
    public void initData(Bundle savedInstanceState) {

    }
}
