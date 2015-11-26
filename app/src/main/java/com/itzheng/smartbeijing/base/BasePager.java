package com.itzheng.smartbeijing.base;

import android.content.Context;
import android.view.View;

import com.itzheng.smartbeijing.MainActivity;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;

/**
 * Created by asus on 2015/11/26.
 */
public abstract class BasePager {
    public  Context context;
    public View view;
    public SlidingMenu slidingMenu;

    public BasePager(Context context){
        this.context=context;
        slidingMenu = ((MainActivity) context).getSlidingMenu();
        view = initView();
    }
    public View getRootView(){
        return  view;
    }
//    public View initTitleBar(){
//
//    }
    protected abstract View initView();

}
