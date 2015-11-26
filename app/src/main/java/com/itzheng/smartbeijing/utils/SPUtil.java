package com.itzheng.smartbeijing.utils;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by asus on 2015/11/26.
 */
public class SPUtil {
    public static String CONFIG = "config";
    private static SharedPreferences sharedPreferences;

    /**
     * 保存布尔类型的数据
     *
     * @param context
     * @param key
     * @param value
     */
    public static void saveBooleanData
    (Context context, String key, Boolean value) {
        //如果sp文件不存在,先创建一个
        initSP(context);
        //将数据保存到配置文件
        sharedPreferences.edit().putBoolean(key, value).commit();
    }

    /**
     * 获取布尔类型的数据
     * @param context
     * @param key
     * @param defValue
     * @return
     */
    public static boolean getBooleanData
            (Context context, String key, boolean defValue) {
            initSP(context);

        return sharedPreferences.getBoolean(key,defValue);
    }

    /**
     * 初始化sharedPreferences文件
     *
     * @param context
     */
    private static void initSP(Context context) {
        if (sharedPreferences == null) {
            sharedPreferences = context.getSharedPreferences
                    (CONFIG, Context.MODE_PRIVATE);
        }
    }


}
