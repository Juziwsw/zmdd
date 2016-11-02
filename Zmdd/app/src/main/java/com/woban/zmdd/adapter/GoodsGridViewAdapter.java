package com.woban.zmdd.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.woban.zmdd.R;
import com.woban.zmdd.bean.TabRecyItem;

import java.util.List;

/**
 * Created by Administrator on 2016/6/27.
 */
public class GoodsGridViewAdapter extends ImagesBaseAdapter {

    public GoodsGridViewAdapter(Context mContext, List<TabRecyItem> mListData, int mItem) {
        super(mContext, mListData, mItem);
    }
    @Override
    public View getView(final int position, View convertView, ViewGroup arg2) {
        final ViewHolder viewHolder;
        if (convertView == null) {
            viewHolder = new ViewHolder();
            convertView = mInflater.inflate(mItem, null);
            viewHolder.txtTitile = (TextView) convertView.findViewById(R.id.title_text);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        TabRecyItem tabRecyItem = (TabRecyItem) mListData.get(position);
        viewHolder.txtTitile.setText(tabRecyItem.tabName);
        return convertView;
    }
    private static class ViewHolder{
        public TextView txtTitile;
    }
}
