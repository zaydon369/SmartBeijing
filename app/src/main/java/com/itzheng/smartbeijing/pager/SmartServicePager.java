package com.itzheng.smartbeijing.pager;

import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.TextView;

import com.itzheng.smartbeijing.base.BasePager;

/**
 * Created by asus on 2015/11/26.
 */
public class SmartServicePager extends BasePager {
    @Override
    public View initView() {
        TextView textView = new TextView(context);
        textView.setText("智慧服务");
        return textView;
    }

    @Override
    public void initData() {

    }

    public SmartServicePager(FragmentActivity activity) {
        super(activity);
    }
}
