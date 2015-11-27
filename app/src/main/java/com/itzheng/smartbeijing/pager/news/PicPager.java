package com.itzheng.smartbeijing.pager.news;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.itzheng.smartbeijing.base.BasePager;
import com.itzheng.smartbeijing.bean.NewsCenter;

/**
 * Created by asus on 2015/11/27.
 */
public class PicPager extends BasePager {
    public PicPager(Context context, NewsCenter.NewsCenterItem newsCenterItem) {
        super(context);
    }

    @Override
    public View initView() {
        TextView textView=new TextView(context);
        textView.setText("组图");
        return textView;
    }

    @Override
    public void initData() {

    }
}
