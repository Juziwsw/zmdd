package com.woban.zmdd.fragment;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.woban.zmdd.R;
import com.woban.zmdd.adapter.HeadRecyclerViewAdapter;
import com.woban.zmdd.adapter.SearchAdapter;
import com.woban.zmdd.adapter.TabRecyclerViewAdapter;
import com.woban.zmdd.bean.SearchListItem;
import com.woban.zmdd.bean.TabRecyItem;
import com.woban.zmdd.utils.Logs;

import java.util.ArrayList;

/**
 * Created by Administrator on 2016/6/21.
 * @author wsw
 * 里层的攻略界面
 */
public class SecondStrategyFragment extends Fragment implements TabRecyclerViewAdapter.OnRecyclerViewListener,
                      AdapterView.OnItemClickListener,HeadRecyclerViewAdapter.OnRecyclerViewListener{

    private static  final  String TAG = "SecondStrategyFragment";
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private String mParam1;
    private String mParam2;
    private Context mContext;
    private View mView;
    private RecyclerView recyclerView_tab;
    private TabRecyclerViewAdapter tabRecyclerViewAdapter;
    private ArrayList<TabRecyItem> tabRecyItems = new ArrayList<TabRecyItem>();

    private RecyclerView recyclerView_head;
    private HeadRecyclerViewAdapter headRecyclerViewAdapter;
    private ArrayList<TabRecyItem> headRecyItems = new ArrayList<TabRecyItem>();

    int index = 0;
    int unOnClick = 0;//不能点击的tab;

    private ListView listView_second_strategy;

    private ArrayList<SearchListItem> listItems = new ArrayList<SearchListItem>();


    public static SecondStrategyFragment newInstance(String param1, String param2) {
        SecondStrategyFragment fragment = new SecondStrategyFragment();
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
        mView = inflater.inflate(R.layout.fragment_second_strategy,container,false);
        setData();
        setHeadData();
        setListviewDta();
        initializeView();
        return mView;
    }

    private void setListviewDta() {
        for (int i = 0;i < 12;i++){
            SearchListItem searchListItem = new SearchListItem();
            searchListItem.title = "盘点“贵妇”面膜，让你美美美一整夏盘点";
            searchListItem.lookNum = 1300+i;
            searchListItem.likeNum = 120+i;
            searchListItem.shareNum = 11+i;
            listItems.add(searchListItem);
        }
    }

    private void initializeView(){
        //初始化RecyclerView，设置横向滑动tab左滑动，给定的数据，
        recyclerView_tab = (RecyclerView) mView.findViewById(R.id.recyclerView);
        recyclerView_tab.setHasFixedSize(true);
        LinearLayoutManager layoutManager_tab = new LinearLayoutManager(mContext);
        layoutManager_tab.setOrientation(LinearLayoutManager.HORIZONTAL);
        recyclerView_tab.setLayoutManager(layoutManager_tab);
        tabRecyclerViewAdapter = new TabRecyclerViewAdapter(tabRecyItems,mContext);
        recyclerView_tab.setAdapter(tabRecyclerViewAdapter);
        tabRecyclerViewAdapter.setOnRecyclerViewListener(this);

        //初始化RecyclerView，设置横向滑动tab左滑动，给定的数据，
        recyclerView_head = (RecyclerView) mView.findViewById(R.id.recyclerView_head);
        recyclerView_head.setHasFixedSize(true);
        LinearLayoutManager layoutManager_head = new LinearLayoutManager(mContext);
        layoutManager_head.setOrientation(LinearLayoutManager.HORIZONTAL);
        recyclerView_head.setLayoutManager(layoutManager_head);
        headRecyclerViewAdapter = new HeadRecyclerViewAdapter(headRecyItems,mContext);
        recyclerView_head.setAdapter(headRecyclerViewAdapter);
        headRecyclerViewAdapter.setOnRecyclerViewListener(this);


        //下方listview,点击tab,刷新数据
        listView_second_strategy = (ListView) mView.findViewById(R.id.listView_second_strategy);
        SearchAdapter searchAdapter = new SearchAdapter(mContext,listItems,R.layout.item_search);
        listView_second_strategy.setAdapter(searchAdapter);
        listView_second_strategy.setOnItemClickListener(this);

    }


    @Override
    public void onItemClick(View v, int position) {

        if (unOnClick == position){
            return;
        }
        switch (v.getId()){
            case R.id.tabrecyclerviewadapter_rel:
                for (int i = 0;i < tabRecyItems.size();i++){
                    if (i == position){
                        tabRecyItems.get(i).onclicked = true;
                    }else {
                        tabRecyItems.get(i).onclicked = false;
                    }
                }
                index = position;
                unOnClick = position;
                tabRecyclerViewAdapter.notifyDataSetChanged();
                Logs.e("position=="+position);
                break;
            case R.id.headrecyclerviewadapter_rel:
                Logs.e("position");
                break;
        }
    }

    @Override
    public boolean onItemLongClick(int position) {
        return false;
    }


    private void setData(){
        for (int i = 0;i < 9;i++){
            TabRecyItem tabRecyItem = new TabRecyItem();
            Drawable onDrawable = null;
            Drawable unDrawable = null;
            switch (i){
                case 0:
                    tabRecyItem.onclicked = true;
                    onDrawable = getResources().getDrawable(R.drawable.ic_top_tab_strike_0);
                    unDrawable = getResources().getDrawable(R.drawable.ic_top_tab_0);
                    tabRecyItem.tabName = "每日更新";
                    break;
                case 1:
                    tabRecyItem.onclicked = false;
                    onDrawable = getResources().getDrawable(R.drawable.ic_top_tab_strike_1);
                    unDrawable = getResources().getDrawable(R.drawable.ic_top_tab_1);
                    tabRecyItem.tabName = "礼物";
                    break;
                case 2:
                    tabRecyItem.onclicked = false;
                    onDrawable = getResources().getDrawable(R.drawable.ic_top_tab_strike_2);
                    unDrawable = getResources().getDrawable(R.drawable.ic_top_tab_2);
                    tabRecyItem.tabName = "吃货";
                    break;
                case 3:
                    tabRecyItem.onclicked = false;
                    onDrawable = getResources().getDrawable(R.drawable.ic_top_tab_strike_3);
                    unDrawable = getResources().getDrawable(R.drawable.ic_top_tab_3);
                    tabRecyItem.tabName = "生活家";
                    break;
                case 4:
                    tabRecyItem.onclicked = false;
                    onDrawable = getResources().getDrawable(R.drawable.ic_top_tab_strike_4);
                    unDrawable = getResources().getDrawable(R.drawable.ic_top_tab_4);
                    tabRecyItem.tabName = "爱美";
                    break;
                case 5:
                    tabRecyItem.onclicked = false;
                    onDrawable = getResources().getDrawable(R.drawable.ic_top_tab_strike_5);
                    unDrawable = getResources().getDrawable(R.drawable.ic_top_tab_5);
                    tabRecyItem.tabName = "穿搭";
                    break;
                case 6:
                    tabRecyItem.onclicked = false;
                    onDrawable = getResources().getDrawable(R.drawable.ic_top_tab_strike_2);
                    unDrawable = getResources().getDrawable(R.drawable.ic_top_tab_2);
                    tabRecyItem.tabName = "重点";
                    break;
                case 7:
                    tabRecyItem.onclicked = false;
                    onDrawable = getResources().getDrawable(R.drawable.ic_top_tab_strike_2);
                    unDrawable = getResources().getDrawable(R.drawable.ic_top_tab_2);
                    tabRecyItem.tabName = "爱美";
                    break;
                case 8:
                    tabRecyItem.onclicked = false;
                    onDrawable = getResources().getDrawable(R.drawable.ic_top_tab_strike_2);
                    unDrawable = getResources().getDrawable(R.drawable.ic_top_tab_2);
                    tabRecyItem.tabName = "生活家";
                    break;
                default:
                    break;

            }
            tabRecyItem.onDrawable = onDrawable;
            tabRecyItem.unDrawable = unDrawable;
            tabRecyItems.add(tabRecyItem);
        }
    }

    private void setHeadData(){
        for (int i = 0;i < 9;i++){
            TabRecyItem tabRecyItem = new TabRecyItem();
            headRecyItems.add(tabRecyItem);
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Logs.e("position=="+position);

    }
}
