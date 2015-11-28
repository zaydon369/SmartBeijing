package com.itzheng.smartbeijing.fragment;

import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioGroup;

import com.itzheng.smartbeijing.R;
import com.itzheng.smartbeijing.base.BaseFragment;
import com.itzheng.smartbeijing.base.BasePager;
import com.itzheng.smartbeijing.pager.FuctionPager;
import com.itzheng.smartbeijing.pager.GovAffairsPager;
import com.itzheng.smartbeijing.pager.NewsCenterPager;
import com.itzheng.smartbeijing.pager.SettingPager;
import com.itzheng.smartbeijing.pager.SmartServicePager;
import com.itzheng.smartbeijing.view.LazyViewPager;
import com.itzheng.smartbeijing.view.MyViewPager;

import java.util.ArrayList;

/**
 * Created by asus on 2015/11/26.
 */
public class HomeFragment extends BaseFragment{

    private RadioGroup radioGroup;
    private MyViewPager viewPagerlayout_content;
    private ArrayList<BasePager> pagersList;

    @Override
    public View initView(LayoutInflater inflater) {
        view = inflater.inflate(R.layout.frag_home, null);
        //初始化控件
        viewPagerlayout_content = (MyViewPager) view.findViewById(R.id.layout_content);
        radioGroup = (RadioGroup) view.findViewById(R.id.main_radio);
        return view;
    }

    @Override
    public void initData(Bundle savedInstanceState) {
        radioGroup.check(R.id.rb_function);
        //用来保存各个pager
        pagersList = new ArrayList<BasePager>();
        pagersList.add(new FuctionPager(getActivity()));//首页
        pagersList.add(new NewsCenterPager(getActivity()));//新闻中心
        pagersList.add(new SmartServicePager(getActivity()));//智慧服务
        pagersList.add(new GovAffairsPager(getActivity()));//政务指南
        pagersList.add(new SettingPager(getActivity()));//设置
        //给自定义viewpager添加适配器,实现页面切换
        viewPagerlayout_content.setAdapter(new MyAdapter());
        //设置页面更改监听
        viewPagerlayout_content.setOnPageChangeListener
                (new LazyViewPager.OnPageChangeListener() {
                    @Override
                    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

                    }

                    //当前选中的页面
                    @Override
                    public void onPageSelected(int position) {
                        BasePager basePager=pagersList.get(position);
                        basePager.initData();
                    }

                    @Override
                    public void onPageScrollStateChanged(int state) {

                    }
                });
        //
        //radioGroup,按钮组,查看当前选中的按钮
        radioGroup.setOnCheckedChangeListener
                (new RadioGroup.OnCheckedChangeListener() {
                    //当前选中的按钮id
            @Override
            public void onCheckedChanged
                    (RadioGroup group, int checkedId) {
            switch (checkedId){
                case R.id.rb_function:
                    viewPagerlayout_content.setCurrentItem(0);
                    break;
                case R.id.rb_news_center:
                    viewPagerlayout_content.setCurrentItem(1);
                    break;
                case R.id.rb_smart_service:
                    viewPagerlayout_content.setCurrentItem(2);
                    break;
                case R.id.rb_gov_affairs:
                    viewPagerlayout_content.setCurrentItem(3);
                    break;
                case R.id.rb_setting:
                    viewPagerlayout_content.setCurrentItem(4);
                    break;
            }

            }
        });

    }

    /**
     * viewpager适配器
     */
    class MyAdapter extends PagerAdapter{
        @Override
        public int getCount() {
            return pagersList.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view==object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
           // return super.instantiateItem(container, position);
            ((MyViewPager)container).
                    addView(pagersList.get(position).getRootView());
            return pagersList.get(position).getRootView();
        }


        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
           // super.destroyItem(container, position, object);
            ((MyViewPager)container).removeView((View) object);
        }
    }

    /**
     * 返回新闻中心页面
     * @return
     */
    public NewsCenterPager switchNewsCenterPager(){
        return (NewsCenterPager) pagersList.get(1);
    }
}
