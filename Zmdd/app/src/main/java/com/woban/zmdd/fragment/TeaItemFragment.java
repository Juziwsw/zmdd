package com.woban.zmdd.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.woban.zmdd.R;
import com.woban.zmdd.bean.TeaTimeItem;

/**
 * Created by Administrator on 2016/6/3.
 */
public class TeaItemFragment extends Fragment {
    private static final String ARG_PARAM1 = "TeaTimeItem";
    private static final String ARG_PARAM2 = "param2";
    private String mParam2;
    private TeaTimeItem teaTimeItem;
    private Context mContext;
    public TextView titleTv;//标题
    public TextView synopsisTv;//简介

    public static TeaItemFragment newInstance(TeaTimeItem teaTimeItem, String param2) {
        TeaItemFragment fragment = new TeaItemFragment();
        Bundle args = new Bundle();
        args.putSerializable(ARG_PARAM1,teaTimeItem);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = getActivity();
        if (getArguments() != null) {
            teaTimeItem = (TeaTimeItem) getArguments().getSerializable(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater,  ViewGroup container,  Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.recycler_teatime_item,container,false);
        titleTv = (TextView) view.findViewById(R.id.recyclerView_title);
        synopsisTv = (TextView) view.findViewById(R.id.recyclerView_synopsis);
        titleTv.setText(teaTimeItem.title);
        //synopsisTv.setText(teaTimeItem.synopsis);
        return view;
    }
}
