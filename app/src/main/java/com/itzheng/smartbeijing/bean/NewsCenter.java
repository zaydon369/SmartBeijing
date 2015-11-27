package com.itzheng.smartbeijing.bean;

import java.util.List;

public class NewsCenter {
    public List<NewsCenterItem> data;
    public List<String> extend;
    public String retcode;

    public class NewsCenterItem{
        public List<Children> children;
        public String id;
        public String title;
        public String type;
        public String url;
        public String url1;
        public String dayurl;
        public String excurl;
        public String weekurl;
    }

    public class Children{
        public String id;
        public String title;
        public String type;
        public String url;
    }
}
