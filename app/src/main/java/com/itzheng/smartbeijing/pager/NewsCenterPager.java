package com.itzheng.smartbeijing.pager;

import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.View;

import com.itzheng.smartbeijing.R;
import com.itzheng.smartbeijing.base.BasePager;
import com.itzheng.smartbeijing.utils.HMApi;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest;


/**
 * Created by asus on 2015/11/26.
 */
public class NewsCenterPager extends BasePager {

    private String tag =NewsCenterPager.class.getSimpleName();

    @Override
    public View initView() {
        view = View.inflate(context, R.layout.news_center_frame, null);

        initTitleBar();
        return view;
    }

    @Override
    public void initData() {
        getNewsCenterData();
    }

    /**
     * 获取新闻数据
     */
    private void getNewsCenterData(){
        getData(HttpRequest.HttpMethod.GET, HMApi.NEWS_CENTER_CATEGORIES,
                null, new RequestCallBack<String>() {
                    @Override
                    public void onSuccess(ResponseInfo<String> responseInfo) {
                        //请求成功
                        Log.i(tag,responseInfo.result);
                    }

                    @Override
                    public void onFailure(HttpException error, String msg) {
                        //请求失败
                        Log.i(tag,msg);
                    }
                });
    }

    public NewsCenterPager(FragmentActivity activity) {
        super(activity);
    }
}
