package com.itzheng.smartbeijing.pager;

import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.TextView;

import com.itzheng.smartbeijing.base.BasePager;

/**
 * Created by asus on 2015/11/26.
 */
public class SettingPager extends BasePager {
    public SettingPager(FragmentActivity activity) {
        super(activity);
    }

    @Override
    public View initView() {
        TextView textView = new TextView(context);
        textView.setText("设置");
        return textView;
    }

    @Override
    public void initData() {

    }
}
