package com.itzheng.smartbeijing.pager;

import android.support.v4.app.FragmentActivity;
import android.view.View;

import com.itzheng.smartbeijing.R;
import com.itzheng.smartbeijing.base.BasePager;

/**
 * Created by asus on 2015/11/26.
 */
public class NewsCenterPager extends BasePager {
    @Override
    public View initView() {
        view = View.inflate(context, R.layout.news_center_frame, null);

        initTitleBar();
        return view;
    }

    @Override
    public void initData() {

    }

    public NewsCenterPager(FragmentActivity activity) {
        super(activity);
    }
}
