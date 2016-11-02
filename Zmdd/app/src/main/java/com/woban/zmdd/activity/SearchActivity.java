package com.woban.zmdd.activity;

import android.annotation.TargetApi;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.woban.zmdd.R;
import com.woban.zmdd.fragment.SearchLeftFragment;
import com.woban.zmdd.fragment.SearchRightFragment;
import com.woban.zmdd.utils.SystemBarTintManager;

import java.util.ArrayList;

/**
 * Created by Administrator on 2016/6/8.
 */
public class SearchActivity extends FragmentActivity implements View.OnClickListener {
    private static final String TAG ="SearchActivity";
    //tab切换
    private RelativeLayout search_left_rel,search_right_rel;
    private TextView txt_left,txt_right;
    private View line_left,line_right;
    private ArrayList<RelativeLayout>tab_rels = new ArrayList<RelativeLayout>();

    int index = 0;
    int lastIndex = 0;
    private ArrayList<Fragment> fragments = new ArrayList<Fragment>();
    private FragmentManager fragmentManager;
    private SearchLeftFragment leftFragment;
    private SearchRightFragment rightFragment;

    private Button button_cancel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            setTranslucentStatus(true);
        }
        SystemBarTintManager tintManager = new SystemBarTintManager(this);
        //tintManager.setNavigationBarTintEnabled(true);
        //tintManager.setTintAlpha(0);
        tintManager.setStatusBarTintEnabled(true);
        tintManager.setStatusBarTintResource(R.color.base_bgcolor);//通知栏所需颜色
        fragmentManager = getSupportFragmentManager();
        initializeView();
        initFragments();
    }
    /**
     * 初始化控件
     */
    private void initializeView(){
        search_left_rel = (RelativeLayout) findViewById(R.id.search_left_rel);
        search_right_rel = (RelativeLayout) findViewById(R.id.search_right_rel);
        tab_rels.add(search_left_rel);
        tab_rels.add(search_right_rel);
        search_left_rel.setOnClickListener(this);
        search_right_rel.setOnClickListener(this);
        txt_left = (TextView) findViewById(R.id.tab_txt_left);
        txt_right = (TextView) findViewById(R.id.tab_txt_right);
        line_left = findViewById(R.id.line_lift);
        line_right = findViewById(R.id.line_right);
        button_cancel = (Button) findViewById(R.id.button_cancel);
        button_cancel.setOnClickListener(this);
    }
    /**
     * 初始化 fragment
     */
    private void initFragments(){
        leftFragment = SearchLeftFragment.newInstance("","");
        rightFragment = SearchRightFragment.newInstance("","");
        fragments.add(leftFragment);
        fragments.add(rightFragment);
        index = 0;
        FragmentTransaction ft = fragmentManager.beginTransaction();
        setAnim(lastIndex, index, ft);
        if(!leftFragment.isAdded()){
            ft.add(R.id.fragment_search, leftFragment,SearchActivity.TAG);
        }
        showFragment(index);
        ft.commit();
        setButton(index);
    }
    @Override
    public void onClick(View v) {
        FragmentTransaction ft = fragmentManager.beginTransaction();
        switch (v.getId()){
            case R.id.search_left_rel:
                index = 0;
                if(!leftFragment.isAdded()){
                    ft.add(R.id.fragment_search, leftFragment, SearchActivity.TAG);
                }
                showFragment(index);
                ft.commit();
                txt_left.setTextColor(getResources().getColor(R.color.sku_line));
                line_left.setVisibility(View.VISIBLE);
                txt_right.setTextColor(getResources().getColor(R.color.black));
                line_right.setVisibility(View.INVISIBLE);
                setButton(index);
                break;
            case R.id.search_right_rel:
                index = 1;
                if(!rightFragment.isAdded()){
                    ft.add(R.id.fragment_search, rightFragment, SearchActivity.TAG);
                }
                showFragment(index);
                ft.commit();
                txt_left.setTextColor(getResources().getColor(R.color.black));
                line_left.setVisibility(View.INVISIBLE);
                txt_right.setTextColor(getResources().getColor(R.color.sku_line));
                line_right.setVisibility(View.VISIBLE);
                setButton(index);
                break;
            case R.id.button_cancel:
                finish();
                break;
            default:
                break;
        }
    }
    private void setButton(int subscript){
        for (int i = 0;i<tab_rels.size();i++){
            if (subscript == i){
                tab_rels.get(subscript).setEnabled(false);
            }else {
                tab_rels.get(i).setEnabled(true);
            }
        }
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
