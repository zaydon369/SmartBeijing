package com.itzheng.smartbeijing.pager.news;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;

import com.itzheng.smartbeijing.R;
import com.itzheng.smartbeijing.base.BasePager;
import com.itzheng.smartbeijing.bean.NewsCenter;
import com.itzheng.smartbeijing.view.pagerindicator.TabPageIndicator;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by asus on 2015/11/27.
 */
public class NewsPager extends BasePager {

    private NewsCenter.NewsCenterItem newsCenterItem;
    private TabPageIndicator indicator;
    private ViewPager viewPager;
    private List<String> titleList=new ArrayList<String>();
    private List<BasePager> pagerList=new ArrayList<BasePager>();


    private MyAdapter myAdapter;

    public NewsPager(Context context, NewsCenter.NewsCenterItem newsCenterItem) {
        super(context);
        this.newsCenterItem=newsCenterItem;
    }

    @Override
    public View initView() {
        view=View.inflate(context, R.layout.frag_news, null);
        indicator = (TabPageIndicator) view.findViewById(R.id.indicator);
        viewPager = (ViewPager) view.findViewById(R.id.pager);
        return view;
    }

    @Override
    public void initData() {
        //底部viewpager需要去显示一个界面,并且当前界面继续继承basepager,
        // 显示具体内容封装在centerItem对应children节点中url所指向的地址
        for(int i=0;i<newsCenterItem.children.size();i++){
            titleList.add(newsCenterItem.children.get(i).title);
            pagerList.add(new NewsItemPager
                    (context,newsCenterItem.children.get(i).url));
        }
        if(myAdapter ==null){
            myAdapter = new MyAdapter();
            viewPager.setAdapter(myAdapter);
        }else{
            myAdapter.notifyDataSetChanged();
        }
        //指针需要和viewpager进行绑定,
        //即指针指向那页,viewpager页处在那页
        indicator.setViewPager(viewPager);
        indicator.setCurrentItem(0);
        //
        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
            //只有在第一页的时候才可以拖拽出左侧侧拉栏目
                if(position==0){
                    slidingMenu.setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);
                }else{
                    slidingMenu.setTouchModeAbove(SlidingMenu.TOUCHMODE_NONE);
                }
                indicator.setCurrentItem(position);
                BasePager basePager=pagerList.get(position);
                basePager.initData();

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        BasePager basePager=pagerList.get(0);
        basePager.initData();
    }
    class MyAdapter extends PagerAdapter{
        @Override
        public CharSequence getPageTitle(int position) {
            return titleList.get(position);
        }

        @Override
        public int getCount() {
            return titleList.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view==object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {

            return pagerList.get(position).getRootView();
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            ((ViewPager)container).removeView((View) object);
        }
    }
}
