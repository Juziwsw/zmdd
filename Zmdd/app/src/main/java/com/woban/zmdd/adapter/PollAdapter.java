package com.woban.zmdd.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.woban.zmdd.R;
import com.woban.zmdd.bean.PollItem;
import com.woban.zmdd.utils.PollChat;

import java.util.ArrayList;

/**
 * Created by Administrator on 2016/6/8.
 * @author wsw
 * 投票结果适配器
 */
public class PollAdapter extends BaseAdapter {
    private Context mContext;
    private ArrayList<PollItem>items;
    private LayoutInflater mInflater;

    public PollAdapter(Context mContext,ArrayList<PollItem>items){
        this.mContext = mContext;
        this.items = items;
        this.mInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

    }
    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public Object getItem(int position) {
        return items.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null){
            viewHolder = new ViewHolder();
            convertView = mInflater.inflate(R.layout.item_poll,null);
            viewHolder.poll_chat = (LinearLayout) convertView.findViewById(R.id.poll_chat);
            viewHolder.txt_haved = (TextView) convertView.findViewById(R.id.txt_haved);
            viewHolder.txt_title = (TextView) convertView.findViewById(R.id.txt_title);
            convertView.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        PollItem pollItem = items.get(position);
        viewHolder.poll_chat.removeAllViews();
        //viewHolder.poll_chat.setBackgroundColor(Color.BLACK);
        viewHolder.poll_chat.addView(new PollChat().execute(mContext,pollItem.haves,(double) 100 - pollItem.haves),
                new LinearLayout.LayoutParams(LinearLayout.LayoutParams.FILL_PARENT,
                        LinearLayout.LayoutParams.FILL_PARENT));
        viewHolder.txt_haved.setText(pollItem.haves + "%");
        viewHolder.txt_title.setText(pollItem.title);
        return convertView;
    }
    private static class ViewHolder{
        public LinearLayout poll_chat;
        public TextView txt_haved;
        public TextView txt_title;
    }
}
