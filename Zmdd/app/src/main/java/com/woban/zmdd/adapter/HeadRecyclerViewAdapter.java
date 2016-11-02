package com.woban.zmdd.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.woban.zmdd.R;
import com.woban.zmdd.bean.TabRecyItem;

import java.util.List;

/**
 * Created by Administrator on 2016/6/27.
 * @author wsw
 * 攻略界面head 左右滑动适配器
 */
public class HeadRecyclerViewAdapter  extends RecyclerView.Adapter {

    public static interface OnRecyclerViewListener {
        void onItemClick(View v,int position);
        boolean onItemLongClick(int position);
    }

    private OnRecyclerViewListener onRecyclerViewListener;

    public void setOnRecyclerViewListener(OnRecyclerViewListener onRecyclerViewListener) {
        this.onRecyclerViewListener = onRecyclerViewListener;
    }
    private static final String TAG = RecyclerViewAdapter.class.getSimpleName();
    private List<TabRecyItem> list;
    private Context mContext;
    public HeadRecyclerViewAdapter(List<TabRecyItem> list, Context context) {
        this.list = list;
        this.mContext = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.recycler_head_item, null);
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        view.setLayoutParams(lp);
        return new PersonViewHolder(view);
    }
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int i) {
        Log.d(TAG, "onBindViewHolder, i: " + i + ", viewHolder: " + viewHolder);
        PersonViewHolder holder = (PersonViewHolder) viewHolder;
        holder.position = i;
        TabRecyItem tabRecyItem = list.get(i);
        /*if (tabRecyItem.onclicked){
            holder.imageView.setImageDrawable(tabRecyItem.onDrawable);
        }else {
            holder.imageView.setImageDrawable(tabRecyItem.unDrawable);
        }
        holder.titleTv.setText(tabRecyItem.tabName);*/



    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class PersonViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener
    {
        public View rootView;
        public ImageView head_img;
        public TextView titleTv;//标题
        public int position;

        public PersonViewHolder(View itemView) {
            super(itemView);
            head_img = (ImageView) itemView.findViewById(R.id.head_img);
            //titleTv = (TextView) itemView.findViewById(R.id.recyclerView_title1);
            rootView = itemView.findViewById(R.id.headrecyclerviewadapter_rel);
            rootView.setOnClickListener(this);
            // synopsisTv = (TextView) itemView.findViewById(R.id.recyclerView_synopsis);
        }

        @Override
        public void onClick(View v) {
            if (null != onRecyclerViewListener) {
                onRecyclerViewListener.onItemClick(v,position);
            }
        }

        @Override
        public boolean onLongClick(View v) {
            if(null != onRecyclerViewListener){
                return onRecyclerViewListener.onItemLongClick(position);
            }
            return false;
        }
    }
}
