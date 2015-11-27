package com.itzheng.smartbeijing.base;

import android.content.Context;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.itzheng.smartbeijing.MainActivity;
import com.itzheng.smartbeijing.R;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest;

/**
 * Created by asus on 2015/11/26.
 */
public abstract class BasePager {
    public  Context context;
    public View view;
    public SlidingMenu slidingMenu;
    public TextView txt_title;
    public ImageButton imgbtn_text;
    public ImageButton imgbtn_right;
    public ImageButton imgbtn_right2;

    public BasePager(Context context){
        this.context=context;
        slidingMenu = ((MainActivity) context).getSlidingMenu();
        view = initView();

    }

    public View getRootView(){

        return  view;
    }

    /**
     * 初始化标题,当子类导入标题布局时,可以直接调用
     */
    public void initTitleBar(){
        Button btn_left = (Button) view.findViewById(R.id.btn_left);
        btn_left.setVisibility(View.GONE);

        ImageButton imgbtn_left = (ImageButton) view.findViewById
                (R.id.imgbtn_left);
        imgbtn_left.setImageResource(R.drawable.img_menu);
        imgbtn_left.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //点击出现导航栏
                slidingMenu.toggle();
            }
        });
        txt_title = (TextView) view.findViewById(R.id.txt_title);

        imgbtn_text = (ImageButton) view.findViewById(R.id.imgbtn_text);
        imgbtn_text.setVisibility(View.GONE);

        imgbtn_right = (ImageButton) view.findViewById(R.id.imgbtn_right);
        imgbtn_right.setVisibility(View.GONE);

        imgbtn_right2 = (ImageButton) view.findViewById(R.id.imgbtn_right2);
        imgbtn_right2.setVisibility(View.GONE);
    }
    public abstract View initView();
    public abstract  void initData();

    /**
     * 通过第三方工具获取数据库数据
     * @param httpMethod
     * @param url 请求地址
     * @param requestParams 请求参数
     * @param callBack 请求结果回调
     */
    public void getData(HttpRequest.HttpMethod httpMethod,String url,
                        RequestParams requestParams,
                        RequestCallBack<String> callBack){
        HttpUtils httpUtils=new HttpUtils();
        httpUtils.send(httpMethod ,url,requestParams , callBack);
    }
}
