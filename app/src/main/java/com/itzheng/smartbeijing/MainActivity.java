package com.itzheng.smartbeijing;

import android.os.Bundle;
import android.view.Window;

import com.itzheng.smartbeijing.fragment.HomeFragment;
import com.itzheng.smartbeijing.fragment.MenuFragment;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.jeremyfeinstein.slidingmenu.lib.app.SlidingFragmentActivity;

public class MainActivity extends SlidingFragmentActivity {

    private SlidingMenu slidingMenu;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //去掉标题栏
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.content);
        //
        setBehindContentView(R.layout.menu_frame);
        slidingMenu = getSlidingMenu();
        //左侧菜单
        slidingMenu.setMode(SlidingMenu.LEFT);
        //移动距离
        slidingMenu.setBehindOffsetRes(R.dimen.slidingmenu_offset);
        //设置边缘阴影
        slidingMenu.setShadowDrawable(R.drawable.shadow);
        //边缘的宽度
        slidingMenu.setShadowWidthRes(R.dimen.shadow_width);
        //全屏触摸有效
        slidingMenu.setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);;
        //使用Fragment填充菜单
        MenuFragment menuFragment=new MenuFragment();
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.menu,menuFragment,"MENU")
                .commit();
        //使用Fragment填充主界面
        HomeFragment homeFragment = new HomeFragment();
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.content_frame,homeFragment,"HOME")
                .commit();
    }
}
