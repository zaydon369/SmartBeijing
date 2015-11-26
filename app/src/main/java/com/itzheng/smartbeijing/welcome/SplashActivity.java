package com.itzheng.smartbeijing.welcome;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;

import com.itzheng.smartbeijing.MainActivity;
import com.itzheng.smartbeijing.R;

/**
 * Created by asus on 2015/11/26.
 * Splash界面,用于初始化整个应用程序
 */
public class SplashActivity extends Activity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
      //添加布局文件
        setContentView(R.layout.activity_splash);
        //开启子线程,1秒钟后进入指定页面
        new Thread(new Runnable() {
            @Override
            public void run() {
                SystemClock.sleep(1000);
                enterActivity(true);
            }
        }).start();

    }

    /**
     * 根据是否第一次进入,跳转到不同页面
     */
    private void enterActivity(boolean isFirst) {
        if(isFirst){
            //第一次进入,跳到导航页
            Intent intent =new Intent
                    (SplashActivity.this,GuideActivity.class);
            startActivity(intent);

        }else{
            //不是第一次进入,进入详情页
            Intent intent=new Intent
                    (SplashActivity.this, MainActivity.class);
            startActivity(intent);
        }
        //关闭当前界面
        finish();
    }

}
