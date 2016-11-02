package com.woban.zmdd.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.woban.zmdd.R;
import com.woban.zmdd.adapter.SearchAdapter;
import com.woban.zmdd.bean.SearchListItem;

import java.util.ArrayList;

/**
 * Created by Administrator on 2016/6/23.
 * @author wsw
 * 搜索界面攻略fragment
 */
public class SearchLeftFragment extends Fragment {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private String mParam1;
    private String mParam2;
    private Context mContext;
    private View mView;

    private ArrayList<SearchListItem> listItems = new ArrayList<SearchListItem>();
    private ListView mListView;

    public static SearchLeftFragment newInstance(String param1, String param2) {
        SearchLeftFragment fragment = new SearchLeftFragment();
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
        mView = inflater.inflate(R.layout.fragment_search,container,false);
        setData();
        initializeView();
        return mView;
    }

    /**
     * 初始化控件
     */
    private void initializeView(){
        mListView = (ListView) mView.findViewById(R.id.listview_search);
        SearchAdapter searchAdapter = new SearchAdapter(mContext,listItems,R.layout.item_search);
        mListView.setAdapter(searchAdapter);
    }

    private void setData(){
        for (int i = 0;i < 12;i++){
            SearchListItem searchListItem = new SearchListItem();
            searchListItem.title = "盘点“贵妇”面膜，让你美美美一整夏盘点";
            searchListItem.lookNum = 1300+i;
            searchListItem.likeNum = 120+i;
            searchListItem.shareNum = 11+i;
            listItems.add(searchListItem);
        }
    }
}
