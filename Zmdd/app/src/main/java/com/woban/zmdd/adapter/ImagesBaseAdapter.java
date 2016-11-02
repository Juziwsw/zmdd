package com.woban.zmdd.adapter;

import android.content.Context;
import android.os.Environment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;

import java.io.File;
import java.util.List;


public class ImagesBaseAdapter extends BaseAdapter {
	ImageLoader imageLoader = ImageLoader.getInstance();
	DisplayImageOptions options = new DisplayImageOptions.Builder()
			.cacheInMemory(true).cacheOnDisc(true).build();
	Context mContext;
	File mCache;
	List<?> mListData;
	int mItem;
	LayoutInflater mInflater;

    public ImagesBaseAdapter(Context mContext, List<?> mListData, int mItem) {
		this.mContext = mContext;
		mCache = new File(Environment.getExternalStorageDirectory() + "/"
				+ "focus/adapter");
		if (!mCache.exists()) {
			mCache.mkdirs();
		}
		this.mListData = mListData;
		this.mItem = mItem;
		this.mInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		initImageLoader(mContext);
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return mListData.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return mListData.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup arg2) {
		// TODO Auto-generated method stub
		ViewHolder viewHolder;
		if (convertView == null) {
			viewHolder = new ViewHolder();
			convertView = mInflater.inflate(mItem, null);
//			viewHolder.image = (ImageView) convertView.findViewById(R.id.action_right);
			convertView.setTag(viewHolder);
		} else {
			viewHolder = (ViewHolder) convertView.getTag();
		}
//		ImageWithId mImageWithHref = (ImageWithId)mListData.get(position);
//		DataServer.asyncImageLoad(viewHolder.image, mImageWithHref.mImgurl, mCache);
//		imageLoader.displayImage(mImageWithHref.mImgUrl, viewHolder.image, options);
		return convertView;
	}



	private static class ViewHolder{
		public ImageView image;
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
	/**
	 * 初始化ImageLoader
	 */
	/*public static void initImageLoader(Context context) {
		File cacheDir = StorageUtils.getOwnCacheDirectory(context,
				"bee_k77/Cache");// 获取到缓存的目录地址
		Log.e("cacheDir", cacheDir.getPath());
		// 创建配置ImageLoader(所有的选项都是可选的,只使用那些你真的想定制)，这个可以设定在APPLACATION里面，设置为全局的配置参数
		ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(
				context)
				// max width, max height，即保存的每个缓存文件的最大长宽
				.memoryCacheExtraOptions(480, 800)
				// Can slow ImageLoader, use it carefully (Better don't use it)设置缓存的详细信息，最好不要设置这个
				// .discCacheExtraOptions(480, 800, CompressFormat.JPEG, 75, null)
				// 线程池内加载的数量
				.threadPoolSize(3)
						// 线程优先级
				.threadPriority(Thread.NORM_PRIORITY - 2)
                *//*
                 * When you display an image in a small ImageView
                 *  and later you try to display this image (from identical URI) in a larger ImageView
                 *  so decoded image of bigger size will be cached in memory as a previous decoded image of smaller size.
                 *  So the default behavior is to allow to cache multiple sizes of one image in memory.
                 *  You can deny it by calling this method:
                 *  so when some image will be cached in memory then previous cached size of this image (if it exists)
                 *   will be removed from memory cache before.
                 *//*
				// .denyCacheImageMultipleSizesInMemory()

				// You can pass your own memory cache implementation你可以通过自己的内存缓存实现
				// .memoryCache(new UsingFreqLimitedMemoryCache(2 * 1024 * 1024))
				// .memoryCacheSize(2 * 1024 * 1024)
				//硬盘缓存50MB
				.discCacheSize(50 * 1024 * 1024)
				//将保存的时候的URI名称用MD5
				.discCacheFileNameGenerator(new Md5FileNameGenerator())
				// 加密
				.discCacheFileNameGenerator(new HashCodeFileNameGenerator())//将保存的时候的URI名称用HASHCODE加密
				.tasksProcessingOrder(QueueProcessingType.LIFO)
				.discCacheFileCount(100) //缓存的File数量
				.discCache(new UnlimitedDiscCache(cacheDir))// 自定义缓存路径
						// .defaultDisplayImageOptions(DisplayImageOptions.createSimple())
						// .imageDownloader(new BaseImageDownloader(context, 5 * 1000,
						// 30 * 1000)) // connectTimeout (5 s), readTimeout (30 s)超时时间
				.writeDebugLogs() // Remove for release app
				.build();
		// Initialize ImageLoader with configuration.
		ImageLoader.getInstance().init(config);// 全局初始化此配置
	}*/
}
