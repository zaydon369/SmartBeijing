package com.itzheng.smartbeijing.pager;

import android.support.v4.app.FragmentActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;

import com.itzheng.smartbeijing.R;
import com.itzheng.smartbeijing.base.BasePager;
import com.itzheng.smartbeijing.bean.NewsCenter;
import com.itzheng.smartbeijing.utils.GsonUtil;
import com.itzheng.smartbeijing.utils.HMApi;
import com.itzheng.smartbeijing.utils.SPUtil;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest;

import java.util.ArrayList;


/**
 * Created by asus on 2015/11/26.
 */
public class NewsCenterPager extends BasePager {

    private String tag =NewsCenterPager.class.getSimpleName();

    @Override
    public View initView() {
        view = View.inflate(context, R.layout.news_center_frame, null);
        //加载标题栏
        initTitleBar();
        initData();
        return view;
    }

    @Override
    public void initData() {
        //
        String result= SPUtil.getStringData
                (context,HMApi.NEWS_CENTER_CATEGORIES,"");
        if(!TextUtils.isEmpty(result)){
            //有数据,解析数据
            processData(result);
        }
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
                        Log.i(tag, responseInfo.result);
                        //将数据保存的sp文件中
                        SPUtil.saveStringData(context,HMApi.NEWS_CENTER_CATEGORIES,
                                responseInfo.result);
                        //解析数据
                        processData(responseInfo.result);
                    }

                    @Override
                    public void onFailure(HttpException error, String msg) {
                        //请求失败
                        Log.i(tag, msg);
                    }
                });
    }

    /**
     * 解析数据
     * @param result
     */
    private void processData(String result) {
        NewsCenter newsCenter = GsonUtil.jsonToBean(result, NewsCenter.class);
        ArrayList<String> titleList = new ArrayList<String>();
        //从javabean里面获取菜单标题
        for(int i=0;i<newsCenter.data.size();i++){
            //将菜单标题遍历存进list集合中
            titleList.add(newsCenter.data.get(i).title);
        }
        Log.i(tag,"javabean获取到的菜单标题"+titleList.toString());
    }
    public NewsCenterPager(FragmentActivity activity) {
        super(activity);
    }
}
