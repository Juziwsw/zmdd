package com.woban.zmdd.secondfragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.woban.zmdd.R;
import com.woban.zmdd.adapter.IntelligentAdapter;
import com.woban.zmdd.bean.IntelligentListItem;
import com.woban.zmdd.utils.ListViewUtil;

import java.util.ArrayList;

/**
 * Created by Administrator on 2016/6/23.
 * @author wsw
 * 每日更新Fragment
 */
public class EveryDayUpdataFagment extends Fragment{
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private String mParam1;
    private String mParam2;
    private Context mContext;
    private View mView;
    private ListView mListView;
    private ArrayList<IntelligentListItem> listItems = new ArrayList<IntelligentListItem>();

    public static EveryDayUpdataFagment newInstance(String param1, String param2) {
        EveryDayUpdataFagment fragment = new EveryDayUpdataFagment();
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
        mView = inflater.inflate(R.layout.fragment_everydayupdata,container,false);
        setData();
        initializeView();
        return mView;
    }
    private void initializeView(){
        mListView = (ListView) mView.findViewById(R.id.listview_everydayupdata);
        IntelligentAdapter intelligentAdapter = new IntelligentAdapter(mContext,listItems,R.layout.item_intelligent);
        mListView.setAdapter(intelligentAdapter);
        new ListViewUtil().setListViewHeightBasedOnChildren(mListView);
    }
    private void setData(){
        for (int i = 0 ;i<12;i++){
            IntelligentListItem item = new IntelligentListItem();
            item.name = "蘑菇街";
            item.personagelabel = "学生，"+i+12+"后，"+"狮子座";
            item.intro = "这是蘑菇街这是蘑菇街这是蘑菇街这是蘑菇街这是蘑菇街";
            listItems.add(item);
        }

    }
}
