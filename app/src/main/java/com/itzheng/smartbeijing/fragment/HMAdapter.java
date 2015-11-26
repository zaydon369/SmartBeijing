package com.itzheng.smartbeijing.fragment;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.List;

/**
 * Created by asus on 2015/11/26.
 */
public abstract class HMAdapter<T> extends BaseAdapter{
    public  Context context;
    public  List<T> list;

    public HMAdapter(Context context,List<T> list){
        this.list=list;
        this.context=context;
    }
    @Override
    public int getCount() {

        return list.size();
    }

    @Override
    public Object getItem(int position) {

        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public abstract View getView(int position, View convertView, ViewGroup parent) ;
}
