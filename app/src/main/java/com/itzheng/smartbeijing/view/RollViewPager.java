package com.itzheng.smartbeijing.view;

import android.content.Context;
import android.os.Handler;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.itzheng.smartbeijing.R;
import com.lidroid.xutils.BitmapUtils;

import java.util.List;

/**
 * Created by asus on 2015/11/27.
 */
public class RollViewPager extends ViewPager{

    private List<View> viewList;
    private  Context context;
    private TextView top_news_title;
    private List<String> titleList;
    private List<String> urlImgList;
    private BitmapUtils bitmapUtils;
    private RunnableTask runnableTask;
    private int currentPosition=0;
    private android.os.Handler handler=new Handler();
    class RunnableTask implements Runnable{

        @Override
        public void run() {
            //滚动viewPager

        }
    }
    public RollViewPager(Context context,final List<View> viewList) {
        super(context);
        this.context=context;
        this.viewList=viewList;
        bitmapUtils = new BitmapUtils(context);
        runnableTask = new RunnableTask();
        this.setOnPageChangeListener(new OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

                top_news_title.setText(titleList.get(position));
                for (int i = 0; i < urlImgList.size(); i++) {
                    if (i == position) {
                        viewList.get(position).setBackgroundResource
                                (R.drawable.dot_focus);
                    } else {
                        viewList.get(i).setBackgroundResource
                                (R.drawable.dot_normal);
                    }
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }
    //将图片关联说明的文字集合,需要显示的控件,传递进来
    public void initTitleList(TextView top_news_title,List<String> titleList){
        if(null != top_news_title && null != titleList && titleList.size()>0){
            top_news_title.setText(titleList.get(0));
        }
        this.top_news_title=top_news_title;
        this.titleList=titleList;

    }
    //显示图片的url地址的集合
    public void initImgUrlList(List<String> urlImgList){
        this.urlImgList=urlImgList;
    }
    //开始滚动
    public void staetRoll(){

    }
    class MyAdapter extends PagerAdapter{

        @Override
        public int getCount() {
            return urlImgList.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view==object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            View view=View.inflate(context,R.layout.viewpager_item,null);
            ImageView imageView= (ImageView) view.findViewById(R.id.image);
            bitmapUtils.display(imageView,urlImgList.get(position));
            ((RollViewPager)container).addView(view);
            return view;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            ((RollViewPager)container).removeView((View) object);
        }
    }
}
