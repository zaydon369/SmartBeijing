package com.itzheng.smartbeijing.pager;

import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.TextView;

import com.itzheng.smartbeijing.base.BasePager;

/**
 * Created by asus on 2015/11/26.
 */
public class NewsCenterPager extends BasePager {
    @Override
    protected View initView() {
        TextView textView = new TextView(context);
        textView.setText("新闻中心");
        return textView;
    }

    public NewsCenterPager(FragmentActivity activity) {
        super(activity);
    }
}
