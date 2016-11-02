package com.woban.zmdd.activity;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;
import com.woban.zmdd.R;
import com.woban.zmdd.ZmddApplication;


/**
 * Created by Administrator on 2016/4/5.
 */
public class SecondBaseActivity  extends Activity implements View.OnClickListener {
    public ActionBar mActionBar;
    public Context mContext;
    ImageLoader imageLoader = ImageLoader.getInstance();
    DisplayImageOptions options = new DisplayImageOptions.Builder()
            .cacheInMemory(false).cacheOnDisc(true).build();
    Handler mBaseHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 101:
                    Toast.makeText(SecondBaseActivity.this, "" + msg.obj.toString(), Toast.LENGTH_SHORT).show();
                    break;
            }
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // 添加Activity到堆栈
        ZmddApplication.getInstance().addActivity(this);
        //setTranslucentStatus();
        mContext = getApplicationContext();
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
        initImageLoader(getApplicationContext());
        mActionBar = getActionBar();
        if (null != mActionBar) {
            mActionBar.setDisplayHomeAsUpEnabled(false);
            mActionBar.setDisplayShowHomeEnabled(false);
            mActionBar.setDisplayShowCustomEnabled(false);
            mActionBar.setDisplayShowTitleEnabled(false);
            mActionBar.setDisplayUseLogoEnabled(false);
            mActionBar.setDisplayShowCustomEnabled(true);
            mActionBar.setCustomView(R.layout.actionbar_layout);
            TextView mActionTitle = (TextView)mActionBar.getCustomView().findViewById(R.id.action_title);
            mActionTitle.setText("");
            /*TextPaint textPaint = mActionTitle.getPaint();
            textPaint.setFakeBoldText(true); */
            TextView mActionSend = (TextView)mActionBar.getCustomView().findViewById(R.id.action_send);
            //mActionBar.hide();
        }


    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.action_home:
                finish();
                break;
            default:
                break;
        }
    }
    /**
     * ImageLoader 图片组件初始化
     *
     * @param context
     */
    public static void initImageLoader(Context context) {
        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(context)
                .threadPriority(Thread.NORM_PRIORITY - 2)
                .denyCacheImageMultipleSizesInMemory()
                .discCacheFileNameGenerator(new Md5FileNameGenerator())
                .tasksProcessingOrder(QueueProcessingType.LIFO)
                .writeDebugLogs() // Remove
                .build();
        ImageLoader.getInstance().init(config);
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        // 结束Activity&从堆栈中移除
        ZmddApplication.getInstance().finishActivity(this);
    }
}
