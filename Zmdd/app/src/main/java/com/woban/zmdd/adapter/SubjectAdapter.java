package com.woban.zmdd.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.woban.zmdd.bean.SearchListItem;

import java.util.List;

/**
 * Created by Administrator on 2016/6/24.
 * @author wsw
 * 专题适配器
 */
public class SubjectAdapter extends ImagesBaseAdapter{

    public SubjectAdapter(Context mContext, List<SearchListItem> mListData, int mItem) {
        super(mContext, mListData, mItem);
    }
    @Override
    public View getView(final int position, View convertView, ViewGroup arg2) {
        final ViewHolder viewHolder;
        if (convertView == null) {
            viewHolder = new ViewHolder();
            convertView = mInflater.inflate(mItem, null);
           /* viewHolder.imageView = (ImageView) convertView.findViewById(R.id.image_find_up_avatar);
            viewHolder.imageView.setLayoutParams(DensityUtil.rateParams(mContext, MainActivity.screenWidth, 2, 1, 0, 0, 0));*/
           /* viewHolder.txtName = (TextView)convertView.findViewById(R.id.intelligent_name);
            viewHolder.txtPersonagelabel = (TextView)convertView.findViewById(R.id.intelligent_personagelabel);
            viewHolder.txtIntro = (TextView)convertView.findViewById(R.id.intelligent_intro);*/
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
       /* IntelligentListItem item = (IntelligentListItem) mListData.get(position);
        viewHolder.txtName.setText(item.name);
        viewHolder.txtPersonagelabel.setText(item.personagelabel);
        viewHolder.txtIntro.setText(item.intro);*/
        return convertView;
    }
    private static class ViewHolder{
        public TextView txtName;
        public TextView txtPersonagelabel;
        public TextView txtIntro;

    }
}
