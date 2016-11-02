package com.woban.zmdd.adapter;

import android.content.Context;
import android.text.TextPaint;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.woban.zmdd.R;
import com.woban.zmdd.bean.MelistItem;

import java.util.List;

/**
 * Created by Administrator on 2016/4/6.
 */
public class MeItemAdapter extends ImagesBaseAdapter {
   /* Context mContext;
    List<FindGridviewItem> mListData;
    int mItem;
    LayoutInflater mInflater;*/


    public MeItemAdapter(Context mContext, List<MelistItem> mListData, int mItem) {
        super(mContext,mListData,mItem);
        /*this.mContext = mContext;
        this.mListData = mListData;
        this.mItem = mItem;
        this.mInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);*/
    }
/*    @Override
    public int getCount() {
        return mListData.size();
    }

    @Override
    public Object getItem(int position) {
        return mListData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }*/

    @Override
    public View getView(final int position, View convertView, ViewGroup arg2) {
        final ViewHolder viewHolder;
        if (convertView == null) {
            viewHolder = new ViewHolder();
            convertView = mInflater.inflate(mItem, null);
           /* viewHolder.imageView = (ImageView) convertView.findViewById(R.id.image_find_up_avatar);


            viewHolder.imageView.setLayoutParams(DensityUtil.rateParams(mContext, MainActivity.screenWidth, 2, 1, 0, 0, 0));*/
            viewHolder.txtTitile = (TextView)convertView.findViewById(R.id.me_item_title);
            TextPaint textPaint = viewHolder.txtTitile.getPaint();
            textPaint.setFakeBoldText(true);
            /*viewHolder.txtContent = (TextView)convertView.findViewById(R.id.txt_content);
            viewHolder.btnif = (Button) convertView.findViewById(R.id.btn_subscription);
            viewHolder.type_img = (ImageView) convertView.findViewById(R.id.type_img);*/
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        final MelistItem item = (MelistItem) mListData.get(position);
        viewHolder.txtTitile.setText(item.title);
        return convertView;
    }
    private static class ViewHolder{
        public TextView txtTitile;
        public TextView txtContent;
        public Button btnif;
        public ImageView imageView;
        public ImageView type_img;

    }
}
