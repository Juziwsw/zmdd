package com.woban.zmdd.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.woban.zmdd.R;
import com.woban.zmdd.adapter.PageAdapter;
import com.woban.zmdd.adapter.RecyclerViewAdapter;
import com.woban.zmdd.bean.TeaTimeItem;
import com.woban.zmdd.utils.Logs;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/5/31.
 * @author wsw
 * 下午茶Fragment
 */
public class TeatimeFragment extends Fragment {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private String mParam1;
    private String mParam2;
    private RecyclerViewAdapter recyclerViewAdapter;
    private RecyclerView recyclerView;
    private View view;
    private Context mContext;
    private ArrayList<TeaTimeItem> recyclerViewItems = new ArrayList<TeaTimeItem>();
    List<Fragment> listFragments = new ArrayList<Fragment>();
    private ArrayList<TeaTimeItem> timeItems = new ArrayList<TeaTimeItem>();
    private ViewPager viewPager;
    public static TeatimeFragment newInstance(String param1, String param2) {
        TeatimeFragment fragment = new TeatimeFragment();
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
        view = inflater.inflate(R.layout.fragment_tea,container,false);
        initializeData();
        initializeView();
        return view;
    }
    /**
     * 初始化布局
     */
    private void initializeView(){

        viewPager = (ViewPager) view.findViewById(R.id.viewpager);

        for (TeaTimeItem timeItem : recyclerViewItems) {
            listFragments.add(TeaItemFragment.newInstance(timeItem,""));
        }
        Logs.e("listFragments.size()=="+listFragments.size());

        final PageAdapter mPageAdapter = new PageAdapter(
                getActivity().getSupportFragmentManager(), listFragments);
        viewPager.setAdapter(mPageAdapter);
       /* //初始化RecyclerView，设置横向滑动
        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(mContext);
        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        recyclerView.setLayoutManager(layoutManager);
        recyclerViewAdapter = new RecyclerViewAdapter(recyclerViewItems,mContext);
        recyclerView.setAdapter(recyclerViewAdapter);*/
    }

    /**
     * 初始化模拟数据
     */
    private void initializeData(){
        for (int i = 0;i < 9 ;i++){
            TeaTimeItem teaTimeItem = new TeaTimeItem();
            teaTimeItem.title = "如何挑选适合裸妆的口红" + i;
            recyclerViewItems.add(teaTimeItem);
        }
    }
}
