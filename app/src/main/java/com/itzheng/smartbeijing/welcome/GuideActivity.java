package com.itzheng.smartbeijing.welcome;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import com.itzheng.smartbeijing.MainActivity;
import com.itzheng.smartbeijing.R;

import java.util.ArrayList;

/**
 * Created by asus on 2015/11/26.
 * 引导页
 */
public class GuideActivity extends Activity{

    private Button button;
    private ViewPager pager;
    private ArrayList<ImageView> imageList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.guide);

        button = (Button) findViewById(R.id.button);
        pager = (ViewPager) findViewById(R.id.view_pager);
        //引导图片
        ImageView imageView1=new ImageView(getApplication());
        imageView1.setBackgroundResource(R.drawable.guide_1);
        ImageView imageView2=new ImageView(getApplication());
        imageView2.setBackgroundResource(R.drawable.guide_2);
        ImageView imageView3=new ImageView(getApplication());
        imageView3.setBackgroundResource(R.drawable.guide_3);
        //将图片添加到list集合中
        imageList = new ArrayList<ImageView>();
        imageList.add(imageView1);
        imageList.add(imageView2);
        imageList.add(imageView3);
        //给pager添加适配器,将list图片放进去
        pager.setAdapter(new MyAdapter());
        //当页面改变时
        pager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            //滚动完成
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }
            //选中某页
            @Override
            public void onPageSelected(int position) {
                //到最后一张图片时
                if(position==imageList.size()-1){
                    button.setVisibility(View.VISIBLE);
                }else{
                    button.setVisibility(View.GONE);
                }

            }
            //滚动状态发生改变
            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
            //给Button设置点击事件,进入主页面
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =
                        new Intent(GuideActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });


    }

    /**
     * GuidePager的适配器
     */
    class MyAdapter extends PagerAdapter{
        @Override
        public int getCount() {
            return imageList.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view==object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            container.addView(imageList.get(position));
            return imageList.get(position);
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }
    }



}
