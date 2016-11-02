package com.woban.zmdd.activity;

import android.annotation.TargetApi;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.woban.zmdd.R;
import com.woban.zmdd.fragment.GoodsFragment;
import com.woban.zmdd.fragment.MeFragment;
import com.woban.zmdd.fragment.StrategyFragment;
import com.woban.zmdd.fragment.TeatimeFragment;
import com.woban.zmdd.utils.SystemBarTintManager;

import java.util.ArrayList;

/**
 * Created by Administrator on 2016/5/31.
 * @author  wsw
 * 主Activity
 */
public class MainActivity extends FragmentActivity implements View.OnClickListener {

    private static  final  String TAG = "MainActivity";


    private LinearLayout mLinStrategy,mLinSku,mLinMe;
    private TextView mtxtStrategy,mtxtSku,mtxtMe;
    private ImageView mImgStrategy,mImgSku,mImgMe;
    int index = 0;
    int lastIndex = 0;
    private  ArrayList<Fragment> fragments = new ArrayList<Fragment>();
    private FragmentManager fragmentManager;
    private StrategyFragment strategyFragment;
    private GoodsFragment skuFragment;
    private TeatimeFragment teatimeFragment;
    private MeFragment meFragment;
    private ArrayList<LinearLayout> tabLins = new ArrayList<LinearLayout>();
    private ArrayList<TextView> tabTxts = new ArrayList<TextView>();

    OnClosePopWinDowsListener onClosePopWinDowsListener;


    public interface OnClosePopWinDowsListener {
        public void callBackByTel(String answer);
    }
    public void setcallBackByTel(OnClosePopWinDowsListener onClosePopWinDowsListener) {
        this.onClosePopWinDowsListener = onClosePopWinDowsListener;
    }
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
       // StatusBarUtil.setTransparent(MainActivity.this);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            setTranslucentStatus(true);
        }
        SystemBarTintManager tintManager = new SystemBarTintManager(this);
        tintManager.setNavigationBarTintEnabled(true);
        tintManager.setTintAlpha(0);


        /*tintManager.setStatusBarTintEnabled(true);
        tintManager.setStatusBarTintResource(R.color.black);//通知栏所需颜色*/
        fragmentManager = getSupportFragmentManager();
        findView();
        initFragments();
    }
    @TargetApi(19)
    private void setTranslucentStatus(boolean on) {
        Window win = getWindow();
        WindowManager.LayoutParams winParams = win.getAttributes();
        final int bits = WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS;
        if (on) {
            winParams.flags |= bits;
        } else {
            winParams.flags &= ~bits;
        }
        win.setAttributes(winParams);
    }
    @Override
    public void onClick(View v) {
        FragmentTransaction ft = fragmentManager.beginTransaction();
        switch (v.getId()){
            case R.id.tab_strategy:
                index = 0;
              /*  Intent intent = new Intent(Constants.INTENTACTION);
                intent.putExtra("index", 2);
                sendBroadcast(intent);*/
                if(!strategyFragment.isAdded()){
                    ft.add(R.id.fragment_home, strategyFragment,MainActivity.TAG);
                }
                showFragment(index);
                ft.commit();
                setTabTextColor(index);
                //NavImg(index);
                setButton(index);
                break;
            case R.id.tab_sku:
                index = 1;
                if(!skuFragment.isAdded()){
                    ft.add(R.id.fragment_home, skuFragment,MainActivity.TAG);
                }
                showFragment(index);
                ft.commit();
                setTabTextColor(index);
                //NavImg(index);
                setButton(index);
                break;
           /* case R.id.tab_tea:
                index = 2;
                if(!teatimeFragment.isAdded()){
                    ft.add(R.id.fragment_home, teatimeFragment,MainActivity.TAG);
                }
                showFragment(index);
                ft.commit();
                setTabTextColor(index);
                //NavImg(index);
                setButton(index);
                onClosePopWinDowsListener.callBackByTel("");
                break;*/
            case R.id.tab_me:
                index = 2;
                if(!meFragment.isAdded()){
                    ft.add(R.id.fragment_home, meFragment,MainActivity.TAG);
                }
                showFragment(index);
                ft.commit();
                setTabTextColor(index);
                //NavImg(index);
                setButton(index);
                break;
            default:
                break;
        }
    }
    /**
     * 设置tab显示情况
     * @param subscript
     */
    private void NavImg(int subscript){
        switch (subscript){
            case 0:
              /*  mImgStrategy.setImageResource(R.drawable.ic_tab_find_click);
                mImgSku.setImageResource(R.drawable.ic_tab_find);
                mImgTeatime.setImageResource(R.drawable.ic_tab_find);
                mImgMe.setImageResource(R.drawable.ic_tab_find);*/
                break;
            case 1:
               /* mImgStrategy.setImageResource(R.drawable.ic_tab_find);
                mImgSku.setImageResource(R.drawable.ic_tab_find_click);
                mImgTeatime.setImageResource(R.drawable.ic_tab_find);
                mImgMe.setImageResource(R.drawable.ic_tab_find);*/
                break;
            case 2:
               /* mImgStrategy.setImageResource(R.drawable.ic_tab_find);
                mImgSku.setImageResource(R.drawable.ic_tab_find);
                mImgTeatime.setImageResource(R.drawable.ic_tab_find_click);
                mImgMe.setImageResource(R.drawable.ic_tab_find);*/
                break;
            case 3:
               /* mImgStrategy.setImageResource(R.drawable.ic_tab_find);
                mImgSku.setImageResource(R.drawable.ic_tab_find);
                mImgTeatime.setImageResource(R.drawable.ic_tab_find);
                mImgMe.setImageResource(R.drawable.ic_tab_find_click);*/
                break;
            default:
                break;
        }

    }
    /**
     * 初始化 fragment
     */
    private void initFragments(){
        strategyFragment =StrategyFragment.newInstance("","");
        skuFragment = GoodsFragment.newInstance("","");
        teatimeFragment = TeatimeFragment.newInstance("","");
        meFragment = MeFragment.newInstance("","");
        fragments.add(strategyFragment);
        fragments.add(skuFragment);
       // fragments.add(teatimeFragment);
        fragments.add(meFragment);
        index = 0;
        FragmentTransaction ft = fragmentManager.beginTransaction();
        setAnim(lastIndex, index, ft);
        if(!strategyFragment.isAdded()){
            ft.add(R.id.fragment_home, strategyFragment,MainActivity.TAG);
        }
        showFragment(index);
        ft.commit();
        setButton(index);
    }
    /**
     * 初始化控件
     */
    private void findView() {
		/* 引入控件ID */
        mLinStrategy = (LinearLayout) findViewById(R.id.tab_strategy);
        mLinStrategy.setOnClickListener(this);
        mLinSku = (LinearLayout) findViewById(R.id.tab_sku);
        mLinSku.setOnClickListener(this);
       /* mLinTeatime = (LinearLayout) findViewById(R.id.tab_tea);
        mLinTeatime.setOnClickListener(this);*/
        mLinMe = (LinearLayout) findViewById(R.id.tab_me);
        mLinMe.setOnClickListener(this);
        tabLins.add(mLinStrategy);
        tabLins.add(mLinSku);
        tabLins.add(mLinMe);
        //初始化点击设置控件
        mtxtStrategy = (TextView) findViewById(R.id.tv_strategy);
        mtxtSku = (TextView) findViewById(R.id.tv_sku);
        mtxtMe = (TextView) findViewById(R.id.tv_me);
        tabTxts.add(mtxtStrategy);
        tabTxts.add(mtxtSku);
        tabTxts.add(mtxtMe);
        mImgStrategy = (ImageView) findViewById(R.id.iv_strategy);
        mImgSku = (ImageView) findViewById(R.id.iv_sku);
        mImgMe = (ImageView) findViewById(R.id.iv_me);

    }

    /**
     * 为了使其隐藏或者显示，不销毁fragment
     * @param index :当前点击的fragment 下标
     */
    private void  showFragment(int index){
        for(int i = 0; i < fragments.size() ; i ++){
            Fragment fragment = fragments.get(i);
            FragmentTransaction transaction = fragmentManager.beginTransaction();
            setAnim(lastIndex, index, transaction);
            if(index == i){
                transaction.show(fragment);
                fragment.setUserVisibleHint(true);
            }else{
                transaction.hide(fragment);
                fragment.setUserVisibleHint(false);
            }
            transaction.commit();
        }
        lastIndex = index;
    }
    private void setButton(int subscript){
        for (int i = 0;i<tabLins.size();i++){
            if (subscript == i){
                tabLins.get(subscript).setEnabled(false);
            }else {
                tabLins.get(i).setEnabled(true);
            }
        }
    }
    private void setTabTextColor(int subscript){
        for (int i = 0;i<tabTxts.size();i++){
            if (subscript == i){
                tabTxts.get(subscript).setTextColor(getResources().getColor(R.color.red));
            }else {
                tabTxts.get(i).setTextColor(getResources().getColor(R.color.gray_main_bs));
            }
        }
    }

    /**
     * 小动画
     * @param lastIndex 之前展示的页面下标
     * @param currentIndex   现在即将要展示的页面下标
     * @param ft
     */
    private void setAnim(int lastIndex, int currentIndex ,FragmentTransaction ft){
        if(currentIndex > lastIndex){
            ft.setCustomAnimations(R.anim.slide_left_in,R.anim.slide_left_out) ;
        }else if(currentIndex < lastIndex){
            ft.setCustomAnimations(R.anim.slide_right_in,R.anim.slide_right_out);
        }
    }
}
