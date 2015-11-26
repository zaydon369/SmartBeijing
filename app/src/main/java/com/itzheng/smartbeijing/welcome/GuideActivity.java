package com.itzheng.smartbeijing.welcome;

import android.app.Activity;
import android.os.Bundle;
import android.os.PersistableBundle;

import com.itzheng.smartbeijing.R;

/**
 * Created by asus on 2015/11/26.
 * 引导页
 */
public class GuideActivity extends Activity{
    @Override
    public void onCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        setContentView(R.layout.guide);

    }
}
