package com.woban.zmdd.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.woban.zmdd.R;
import com.woban.zmdd.activity.SearchActivity;
import com.woban.zmdd.bean.TabRecyItem;

import java.util.ArrayList;

/**
 * Created by Administrator on 2016/5/31.
 * @author wsw
 * 攻略Fragment
 */
public class StrategyFragment extends Fragment implements View.OnClickListener{

    private static  final  String TAG = "StrategyFragment";
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private String mParam1;
    private String mParam2;
    private Context mContext;
    private View mView;
    private ImageView buttonSearch;

    int index = 0;
    int lastIndex = 0;
    private ArrayList<Fragment> fragments = new ArrayList<Fragment>();
    private FragmentManager fragmentManager;
    private SecondStrategyFragment secondStrategyFragment;
    private SubjectFragment subjectFragment;
    private IntelligentFragment intelligentFragment;

    private LinearLayout strategy_lin3,strategy_lin2,strategy_lin1;
    private TextView strategy_tab1,strategy_tab2,strategy_tab3;
    private View strategy_line1,strategy_line2,strategy_line3;
    private ArrayList<LinearLayout> strategy_lins = new ArrayList<LinearLayout>();
    private ArrayList<TextView> strategy_tabs = new ArrayList<TextView>();
    private ArrayList<View> strategy_lines = new ArrayList<View>();

    public static StrategyFragment newInstance(String param1, String param2) {
        StrategyFragment fragment = new StrategyFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = getActivity();
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_strategy,container,false);
        fragmentManager = getActivity().getSupportFragmentManager();
        initializeView();
        initFragments();
        return mView;
    }
    /**
     * 初始化 fragment
     */
    private void initFragments(){
        secondStrategyFragment =SecondStrategyFragment.newInstance("","");
        subjectFragment = SubjectFragment.newInstance("","");
        intelligentFragment = IntelligentFragment.newInstance("","");
        fragments.add(secondStrategyFragment);
        fragments.add(subjectFragment);
        fragments.add(intelligentFragment);
        index = 0;
        FragmentTransaction ft = fragmentManager.beginTransaction();
        setAnim(lastIndex, index, ft);
        if(!secondStrategyFragment.isAdded()){
            ft.add(R.id.fragment_strategy, secondStrategyFragment, StrategyFragment.TAG);
        }
        showFragment(index);
        ft.commit();
        setButton(index);
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
    /**
     * 初始化布局
     */
    private void initializeView(){
        buttonSearch = (ImageView) mView.findViewById(R.id.button_search);
        buttonSearch.setOnClickListener(this);
        //设置点击tab事件，和切换的需要用到的view
        strategy_lin3 = (LinearLayout) mView.findViewById(R.id.strategy_lin3);
        strategy_lin1 = (LinearLayout) mView.findViewById(R.id.strategy_lin1);
        strategy_lin2 = (LinearLayout) mView.findViewById(R.id.strategy_lin2);
        strategy_lin1.setOnClickListener(this);
        strategy_lin2.setOnClickListener(this);
        strategy_lin3.setOnClickListener(this);
        strategy_lins.add(strategy_lin1);
        strategy_lins.add(strategy_lin2);
        strategy_lins.add(strategy_lin3);
        strategy_tab1 = (TextView) mView.findViewById(R.id.strategy_tab1);
        strategy_tab2 = (TextView) mView.findViewById(R.id.strategy_tab2);
        strategy_tab3 = (TextView) mView.findViewById(R.id.strategy_tab3);
        strategy_tabs.add(strategy_tab1);
        strategy_tabs.add(strategy_tab2);
        strategy_tabs.add(strategy_tab3);
        strategy_line1 = mView.findViewById(R.id.strategy_line1);
        strategy_line2 = mView.findViewById(R.id.strategy_line2);
        strategy_line3 = mView.findViewById(R.id.strategy_line3);
        strategy_lines.add(strategy_line1);
        strategy_lines.add(strategy_line2);
        strategy_lines.add(strategy_line3);

    }

    /**
     * 初始化滑动tab
     */
    private void initializeTab(){
        for (int i = 0;i < 18;i++){
            TabRecyItem tabRecyItem = new TabRecyItem();
            switch (i){
                case 0:
                    break;
                case 1:
                    break;
                case 2:
                    break;
                case 3:
                    break;
                case 4:
                    break;
                case 5:
                    break;
                case 6:
                    break;
                case 7:
                    break;
                case 8:
                    break;
                default:
                    break;

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

    @Override
    public void onClick(View v) {
        FragmentTransaction ft = fragmentManager.beginTransaction();
        switch (v.getId()){
            case R.id.strategy_lin1:
                index = 0;
                if(!secondStrategyFragment.isAdded()){
                    ft.add(R.id.fragment_strategy, secondStrategyFragment, StrategyFragment.TAG);
                }
                showFragment(index);
                ft.commit();
                setTabChange(index);
                setButton(index);
                break;
            case R.id.strategy_lin2:
                index = 1;
                if(!subjectFragment.isAdded()){
                    ft.add(R.id.fragment_strategy, subjectFragment, StrategyFragment.TAG);
                }
                showFragment(index);
                ft.commit();
                setTabChange(index);
                setButton(index);
                break;
            case R.id.strategy_lin3:
                index = 2;
                if(!intelligentFragment.isAdded()){
                    ft.add(R.id.fragment_strategy, intelligentFragment, StrategyFragment.TAG);
                }
                showFragment(index);
                ft.commit();
                setTabChange(index);
                setButton(index);
                break;
            case R.id.button_search:
                Intent intent = new Intent(mContext, SearchActivity.class);
                mContext.startActivity(intent);
                break;
            default:
                break;
        }
    }

    /**
     * 设置点击tab时候的控件变化，和显示
     */
    private void setTabChange(int point){
        //设置字体颜色
        for (int i = 0 ;i < strategy_tabs.size();i++){
            if(point == i){
                strategy_tabs.get(i).setTextColor(getResources().getColor(R.color.sku_line));
            }else {
                strategy_tabs.get(i).setTextColor(getResources().getColor(R.color.black));
            }
        }
        //设置下划线的显示
        for (int i = 0 ;i < strategy_lines.size();i++){
            if(point == i){
                strategy_lines.get(i).setVisibility(View.VISIBLE);
            }else {
                strategy_lines.get(i).setVisibility(View.INVISIBLE);
            }
        }
    }
    private void setButton(int subscript){
        for (int i = 0;i<strategy_lins.size();i++){
            if (subscript == i){
                strategy_lins.get(subscript).setEnabled(false);
            }else {
                strategy_lins.get(i).setEnabled(true);
            }
        }
    }
}
