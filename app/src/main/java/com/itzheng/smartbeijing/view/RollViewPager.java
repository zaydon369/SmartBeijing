package com.itzheng.smartbeijing.view;

import android.content.Context;
import android.os.Message;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
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
//    private RunnableTask runnableTask;
    private  int currentPosition=0;
    String tag=RollViewPager.class.getSimpleName();
    //自动滚动
    private android.os.Handler handler=new android.os.Handler(){
        @Override
        public void handleMessage(Message msg) {
           // super.handleMessage(msg);
            //延续当前3秒一次的滚动状态
            RollViewPager.this.setCurrentItem(currentPosition);
            startRoll();
        }
    };


    private MyRollPagerAdapter myRollPagerAdapter;


    public RollViewPager(Context context,final List<View> viewList) {
        super(context);
        this.context=context;
        this.viewList=viewList;
        bitmapUtils = new BitmapUtils(context);
        //添加定时滚动
//        runnableTask = new RunnableTask();

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

    @Override
    protected void onDetachedFromWindow() {
        handler.removeCallbacksAndMessages(null);
        super.onDetachedFromWindow();

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

        Log.i(tag,"图片地址:"+urlImgList.toString());
        this.urlImgList=urlImgList;
    }
    //开始滚动
    public void startRoll(){
        if(myRollPagerAdapter ==null){
            myRollPagerAdapter=new MyRollPagerAdapter();
            this.setAdapter(myRollPagerAdapter);
        }else{
            myRollPagerAdapter.notifyDataSetChanged();
        }
    //定时滚动
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                //当前的指针+1
                currentPosition = (currentPosition + 1) % (urlImgList.size());
                //发送一条空消息
                handler.obtainMessage().sendToTarget();
                Log.i(tag,"定时发送消息");
            }
        },3000);

    }

//    class RunnableTask implements Runnable{
//
//        //滚动viewPager
//        @Override
//        public void run() {
////            while (true) {
//            //    SystemClock.sleep(3000);
//                //当前的指针+1
//                currentPosition = (currentPosition + 1) % (urlImgList.size());
//                //发送一条空消息
//                handler.obtainMessage().sendToTarget();
////            }
//
//        }
//    }

    class MyRollPagerAdapter extends PagerAdapter{

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
