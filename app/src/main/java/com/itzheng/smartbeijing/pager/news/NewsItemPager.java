package com.itzheng.smartbeijing.pager.news;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.itzheng.smartbeijing.R;
import com.itzheng.smartbeijing.base.BasePager;
import com.itzheng.smartbeijing.bean.NewsBean;
import com.itzheng.smartbeijing.fragment.HMAdapter;
import com.itzheng.smartbeijing.utils.CommonUtil;
import com.itzheng.smartbeijing.utils.GsonUtil;
import com.itzheng.smartbeijing.utils.HMApi;
import com.itzheng.smartbeijing.utils.SPUtil;
import com.itzheng.smartbeijing.view.RollViewPager;
import com.itzheng.smartbeijing.view.pullrefreshview.PullToRefreshListView;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by asus on 2015/11/27.
 */
public class NewsItemPager extends BasePager {
    private String url;
//    private TextView textView;
    private View layout_roll_view;
    //lalyout_roll_view
//    private LinearLayout dots_ll;
//    private TextView top_news_title;
//    private LinearLayout top_news_viewpager;
    //找到控件
    // @ViewInject(R.id.top_news_viewpager)
    private LinearLayout top_news_viewpager;

    //  @ViewInject(R.id.top_news_title)
    private TextView top_news_title;


    //   @ViewInject(R.id.dots_ll)
    private LinearLayout dots_ll;

    //  @ViewInject(R.id.lv_item_news)
    private PullToRefreshListView ptrlv;
    private PullToRefreshListView lv_item_news;
    //
    private List<String> titleList = new ArrayList<String>();
    private List<String> urlImagList = new ArrayList<String>();
    private List<View> viewList = new ArrayList<View>();



    private MyBaseAdapter myBaseAdapter;

    public NewsItemPager(Context context, String url) {
        super(context);
        this.url = url;
    }

    @Override
    public View initView() {
        layout_roll_view = View.inflate
                (context, R.layout.layout_roll_view, null);
        //使用注解
       // ViewUtils.inject(this,layout_roll_view);

        //
        top_news_viewpager = (LinearLayout) layout_roll_view.findViewById(R.id.top_news_viewpager);
        top_news_title = (TextView) layout_roll_view.findViewById(R.id.top_news_title);
        dots_ll = (LinearLayout) layout_roll_view.findViewById(R.id.dots_ll);

        //
        view = View.inflate(context, R.layout.frag_item_news, null);
        ptrlv= (PullToRefreshListView) view.findViewById(R.id.lv_item_news);
        //
        lv_item_news = (PullToRefreshListView) view.findViewById(R.id.lv_item_news);
        return view;
    }

    @Override
    public void initData() {
        String result = SPUtil.getStringData(context, HMApi.BASE_URL + url, "");
        if (!TextUtils.isEmpty(result)) {
            processData(result);
            return;
        }
        getNewsItemPager();
    }

    private void getNewsItemPager() {
        getData(HttpRequest.HttpMethod.GET, HMApi.BASE_URL + url, null,
                new RequestCallBack<String>() {
                    @Override
                    public void onSuccess(ResponseInfo<String> responseInfo) {
                        SPUtil.saveStringData(context, HMApi.BASE_URL + url, responseInfo.result);
                    }

                    @Override
                    public void onFailure(HttpException error, String msg) {

                    }
                });
    }

    private void processData(String result) {
        NewsBean bean = GsonUtil.jsonToBean(result, NewsBean.class);
        //将当前对应bean中的轮播图取的title,url单独获取出来
        if (bean.data.topnews.size() > 0) {
            for (int i = 0; i < bean.data.topnews.size(); i++) {

                titleList.add(bean.data.topnews.get(i).title);
                urlImagList.add(bean.data.topnews.get(i).url);
            }
            initDot();
            //组装
            RollViewPager rollViewPager = new RollViewPager
                    (context, viewList);
            rollViewPager.initTitleList(top_news_title,titleList);
            rollViewPager.initImgUrlList(urlImagList);
            rollViewPager.staetRoll();

            top_news_viewpager.removeAllViews();
            top_news_viewpager.addView(rollViewPager);

            //需要添加到listView上面去
            //layout_roll_view就是个listview
            if(ptrlv.getRefreshableView().getHeaderViewsCount()<1){
                ptrlv.getRefreshableView().addHeaderView(layout_roll_view);
            }
        }
        //填充后,头部轮转图才会显示
        if(bean.data.news.size()>0){
            if(myBaseAdapter==null){
                myBaseAdapter=new MyBaseAdapter(context,bean.data.news);
                ptrlv.getRefreshableView().setAdapter(myBaseAdapter);
            }else{
                myBaseAdapter.notifyDataSetChanged();
            }
        }
    }
    class MyBaseAdapter extends HMAdapter<NewsBean.News>{

        public MyBaseAdapter(Context context, List<NewsBean.News> list) {
            super(context, list);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View view=View.inflate(context,R.layout.layout_news_item,null);
//            ImageView iv_img= (ImageView) view.findViewById(R.id.iv_img);
//            TextView tv_title= (TextView) view.findViewById(R.id.tv_title);
//           TextView tv_pub_date= (TextView) view.findViewById(R.id.tv_pub_date);

            return view;
        }
    }
    //实例化点
    private void initDot(){
        dots_ll.removeAllViews();
        viewList.clear();
        for(int i=0;i<urlImagList.size();i++){
            View view=new View(context);
            if(i==0){
                view.setBackgroundResource(R.drawable.dot_focus);
            }else{
                view.setBackgroundResource(R.drawable.dot_normal);
            }
        }
        LinearLayout.LayoutParams layoutParams=
                new LinearLayout.LayoutParams
                        (CommonUtil.dip2px(context, 6),CommonUtil.dip2px(context,6));
        view.setLayoutParams(layoutParams);
        layoutParams.setMargins(5, 0, 5, 0);
        dots_ll.addView(view);
        viewList.add(view);



    }
}
