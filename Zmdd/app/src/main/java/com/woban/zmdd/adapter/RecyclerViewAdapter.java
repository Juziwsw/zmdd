package com.woban.zmdd.adapter;

import android.content.Context;
import android.os.Environment;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;
import com.woban.zmdd.R;
import com.woban.zmdd.bean.TeaTimeItem;

import java.io.File;
import java.util.List;

/**
 * Created by Administrator on 2016/4/22.
 * @author wsw
 * 下午茶界面的左右滑动适配器
 */
public class RecyclerViewAdapter extends RecyclerView.Adapter {

    ImageLoader imageLoader = ImageLoader.getInstance();
    DisplayImageOptions options = new DisplayImageOptions.Builder()
            .cacheInMemory(true).cacheOnDisc(true).build();

    public static interface OnRecyclerViewListener {
        void onItemClick(int position);
        boolean onItemLongClick(int position);
    }

    private OnRecyclerViewListener onRecyclerViewListener;

    public void setOnRecyclerViewListener(OnRecyclerViewListener onRecyclerViewListener) {
        this.onRecyclerViewListener = onRecyclerViewListener;
    }

    private static final String TAG = RecyclerViewAdapter.class.getSimpleName();
    private List<TeaTimeItem> list;
    File mCache;
    private Context mContext;

    public RecyclerViewAdapter(List<TeaTimeItem> list,Context context) {
        this.list = list;
        this.mContext = context;
        mCache = new File(Environment.getExternalStorageDirectory() + "/"
                + "zmdd/adapter");
        if (!mCache.exists()) {
            mCache.mkdirs();
        }
        initImageLoader(mContext);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.recycler_teatime_item, null);
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        view.setLayoutParams(lp);
        return new PersonViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int i) {
        Log.d(TAG, "onBindViewHolder, i: " + i + ", viewHolder: " + viewHolder);
        PersonViewHolder holder = (PersonViewHolder) viewHolder;
        holder.position = i;
        TeaTimeItem teaTimeItem = list.get(i);
        holder.titleTv.setText(teaTimeItem.title);

        /*if (!LookListItem.cover.equals("")){
            //多线程加载图片
            holder.imageView.setTag(LookListItem.cover);//给imageview设置一个名为url的tag,就是相对应的进行绑定
            imageLoader.displayImage(LookListItem.cover, holder.imageView, options);
        }*/
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class PersonViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener
    {
        public View rootView;
        public ImageView imageView;
        public TextView titleTv;//标题
        public TextView synopsisTv;//简介
        public int position;

        public PersonViewHolder(View itemView) {
            super(itemView);
            titleTv = (TextView) itemView.findViewById(R.id.recyclerView_title);
            synopsisTv = (TextView) itemView.findViewById(R.id.recyclerView_synopsis);
        }

        @Override
        public void onClick(View v) {
            if (null != onRecyclerViewListener) {
                onRecyclerViewListener.onItemClick(position);
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

    /*
	 * ImageLoader 图片组件初始化
	 *
	 * @param context
	 */
    public static void initImageLoader(Context context) {
        // This configuration tuning is custom. You can tune every option, you
        // may tune some of them,
        // or you can create default configuration by
        // ImageLoaderConfiguration.createDefault(this);
        // method.
        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(context)
                .threadPriority(Thread.NORM_PRIORITY - 2)
                .denyCacheImageMultipleSizesInMemory()
                .discCacheFileNameGenerator(new Md5FileNameGenerator())
                .tasksProcessingOrder(QueueProcessingType.LIFO)
                .writeDebugLogs() // Remove
                .build();
        // Initialize ImageLoader with configuration.
        ImageLoader.getInstance().init(config);
    }
}
