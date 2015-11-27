package com.itzheng.smartbeijing.pager.news;

import android.content.Context;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.itzheng.smartbeijing.R;
import com.itzheng.smartbeijing.base.BasePager;
import com.itzheng.smartbeijing.view.pullrefreshview.PullToRefreshListView;

/**
 * Created by asus on 2015/11/27.
 */
public class NewsItemPager extends BasePager {
    private  String url;
    private TextView textView;
    private View layout_roll_view;
    //lalyout_roll_view
    private LinearLayout dots_ll;
    private TextView top_news_title;
    private LinearLayout top_news_viewpager;
    private PullToRefreshListView lv_item_news;
    //

    public NewsItemPager(Context context,String url) {
        super(context);
        this.url=url;
    }

    @Override
    public View initView() {
        layout_roll_view=View.inflate
                (context, R.layout.layout_roll_view, null);
        //
        top_news_viewpager = (LinearLayout) layout_roll_view.findViewById(R.id.top_news_viewpager);
        top_news_title = (TextView) layout_roll_view.findViewById(R.id.top_news_title);
        dots_ll = (LinearLayout) layout_roll_view.findViewById(R.id.dots_ll);
       //
        view=View.inflate(context,R.layout.frag_item_news,null);
        //
        lv_item_news = (PullToRefreshListView) view.findViewById(R.id.lv_item_news);
        return view;
    }

    @Override
    public void initData() {

    }
}
