package com.itzheng.smartbeijing.fragment;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.itzheng.smartbeijing.MainActivity;
import com.itzheng.smartbeijing.R;
import com.itzheng.smartbeijing.base.BaseFragment;

import java.util.List;

/**
 * Created by asus on 2015/11/26.
 */
public class MenuFragment extends BaseFragment{


    private List<String> titleList;
    private MyAdapter adapter;
    private ListView lv_menu_news_center;
    private int currentPosition=0;
    private String tag = MenuFragment.class.getSimpleName();

    @Override
    public View initView(LayoutInflater inflater) {
        view= inflater.inflate(R.layout.layout_left_menu,null);
       // ViewUtils.inject(this,view);
        //初始化控件
        lv_menu_news_center = (ListView) view.findViewById
                (R.id.lv_menu_news_center);
        return view;
    }

    @Override
    public void initData(Bundle savedInstanceState) {

    }
    // TODO 哪里调用这方法
    //将底部条目关联过来的数据去填充左边条目,哪里调用这个方法???
    public void initMenu(List<String> titleList){
        this.titleList=titleList;
        Log.i(tag,"标题长度.."+titleList.size()+titleList.toString());
        adapter = new MyAdapter(context, titleList);
        lv_menu_news_center.setAdapter(adapter);
        //给list里面的条目添加点击事件
        lv_menu_news_center.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                currentPosition = position;
                adapter.notifyDataSetChanged();
                ((MainActivity)context).switchHomeFragment().
                        switchNewsCenterPager().switchPager(position);
                slidingMenu.toggle();
            }
        });

    }

    /**
     * 标题listview的适配器
     */
    class MyAdapter extends HMAdapter{
        public MyAdapter(Context context, List<String> list) {
            super(context, list,null);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder holder=null;
            if(convertView==null){
                holder=new ViewHolder();
                //找到布局中对应的控件
                convertView=View.inflate(context,R.layout.layout_item_menu,null);
                holder.iv_menu_item= (ImageView) convertView.findViewById
                        (R.id.iv_menu_item);
                holder.tv_menu_item= (TextView) convertView.findViewById
                        (R.id.tv_menu_item);
                //将holder添加到tag,下次直接获取
                convertView.setTag(holder);
            }else{
                holder= (ViewHolder) convertView.getTag();

            }
            holder.tv_menu_item.setText(titleList.get(position));
            if(position==currentPosition){
                holder.iv_menu_item.setImageResource
                        (R.drawable.menu_arr_select);
                holder.tv_menu_item.setTextColor
                        (context.getResources().getColor(R.color.red));
                convertView.setBackgroundResource
                        (R.drawable.menu_item_bg_select);

            }else{
                holder.iv_menu_item.setImageResource
                        (R.drawable.menu_arr_normal);
                holder.tv_menu_item.setTextColor
                        (context.getResources().getColor(R.color.white));
                convertView.setBackgroundResource(R.drawable.transparent);
            }
            return convertView;
        }
    }
    class ViewHolder{
        ImageView iv_menu_item;
        TextView tv_menu_item;
    }
}
