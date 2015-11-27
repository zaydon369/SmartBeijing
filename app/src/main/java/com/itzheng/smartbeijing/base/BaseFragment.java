package com.itzheng.smartbeijing.base;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.itzheng.smartbeijing.MainActivity;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;

/**
 * Created by asus on 2015/11/26.
 */
public abstract class BaseFragment extends Fragment {

    public Context context;
    public SlidingMenu slidingMenu;
    public View view;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.context = getActivity();
        //通过mainActivity获取slidingmenu
        slidingMenu = ((MainActivity)context).getSlidingMenu();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = initView(inflater);
        return view;
    }

    @Override
    public void onActivityCreated( Bundle savedInstanceState) {
        initData(savedInstanceState);
        super.onActivityCreated(savedInstanceState);
    }


    /**
     * 根据不同子类,传入不同数据
     * @param savedInstanceState
     */
    public abstract void initData(Bundle savedInstanceState);
    /**
     * 不同子类,有不同的界面
     * @param inflater
     * @return
     */
    public abstract View initView(LayoutInflater inflater);

}
