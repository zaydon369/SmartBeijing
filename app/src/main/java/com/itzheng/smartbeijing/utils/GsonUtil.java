package com.itzheng.smartbeijing.utils;

import com.google.gson.Gson;

/**
 * Created by asus on 2015/11/27.
 */
public class GsonUtil {
    /**
     * 将json 转换成对应类的javabean
     * @param json
     * @param clazz
     * @param <T>
     * @return
     */
    public static <T> T jsonToBean(String json,Class<T> clazz){
        Gson gson=new Gson();
        return gson.fromJson(json,clazz);
    }
}
