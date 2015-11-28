package com.itzheng.smartbeijing.bean;

import java.util.List;

/**
 * Created by asus on 2015/11/27.
 */
public class NewsBean {
    public NewsBeanItem data;
    public String retcode;
    public class NewsBeanItem{
        public String countcommenturl;
        //上拉加载
        public String more;
        public String title;

        public List<News> news;
        public List<Topic> topic;
        public List<Topnews> topnews;
    }
    public class News{
        public  String comment;
        public String commentlist;
        public String commenturl;
        public String id;
        public String listimage;
        public String pubdate;
        public String title;
        public String type;
        public String url;
    }
    public class Topic{
        public String description;
        public String id;
        public String listimage;
        public String sort;
        public String title;
        public String url;
    }

    public class Topnews{
        public String comment;
        public String commentlist;
        public String commenturl;
        public String id;
        //轮播图图片链接地址
        public String topimage;
        //时间
        public String pubdate;
        //新闻列表的标题文字
        public String title;
        public String type;
        public String url;
    }
}
