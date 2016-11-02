package com.woban.zmdd.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.woban.zmdd.R;
import com.woban.zmdd.activity.SettingActivity;
import com.woban.zmdd.adapter.MeItemAdapter;
import com.woban.zmdd.bean.MelistItem;
import com.woban.zmdd.wights.FloatGridView;

import java.util.ArrayList;

/**
 * Created by Administrator on 2016/5/31.
 * @author wsw
 * 我的Fragment
 */
public class MeFragment extends Fragment implements View.OnClickListener{

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private String mParam1;
    private String mParam2;
    private View meView;
    private Context mContext;
    private LinearLayout but_setting;
    private RelativeLayout me_like_strategy,me_like_sku;
    private View line_lift,line_right;
    private ArrayList<MelistItem> itemsStrategy = new ArrayList<MelistItem>();
    private ArrayList<MelistItem> itemsSku = new ArrayList<MelistItem>();
    private FloatGridView myLikeSku,myLikeStrategy;//喜欢的单品，喜欢的攻略
    private MeItemAdapter skuAdapter,strategyAdapter;//单品适配器，攻略适配器

    public static MeFragment newInstance(String param1, String param2) {
        MeFragment fragment = new MeFragment();
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
    public View onCreateView(LayoutInflater inflater,  ViewGroup container,  Bundle savedInstanceState) {
        meView = inflater.inflate(R.layout.fragment_me,container,false);
        getData();
        initialize();
        return meView;
    }

    private void initialize(){
        but_setting = (LinearLayout) meView.findViewById(R.id.lin_setting);
        but_setting.setOnClickListener(this);
        me_like_strategy = (RelativeLayout) meView.findViewById(R.id.me_like_strategy);
        me_like_sku = (RelativeLayout) meView.findViewById(R.id.me_like_sku);
        me_like_strategy.setOnClickListener(this);
        me_like_sku.setOnClickListener(this);
        line_right = meView.findViewById(R.id.line_right);
        line_lift = meView.findViewById(R.id.line_lift);
        myLikeStrategy = (FloatGridView) meView.findViewById(R.id.listView_strategy);
        myLikeSku = (FloatGridView) meView.findViewById(R.id.listView_sku);
        myLikeStrategy.setVisibility(View.VISIBLE);
        myLikeSku.setVisibility(View.GONE);

        strategyAdapter = new MeItemAdapter(mContext, itemsStrategy, R.layout.me_item);
        myLikeStrategy.setAdapter(strategyAdapter);


        skuAdapter = new MeItemAdapter(mContext, itemsSku, R.layout.me_item);
        myLikeSku.setAdapter(skuAdapter);

    }
    private void getData(){
        for (int i = 0;i<9;i++){
            MelistItem melistItem = new MelistItem();
            melistItem.title = "SWER干时俩用赛SWER干时俩用赛"+i;
            itemsStrategy.add(melistItem);
        }
        for (int i = 0;i<9;i++){
            MelistItem melistItem = new MelistItem();
            melistItem.title = "SWER干时俩用赛SWER干时俩用赛"+i+10;
            itemsSku.add(melistItem);
        }
    }
    /**
     * 点击事件处理
     * @param v
     */
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.me_like_strategy:
                myLikeStrategy.setVisibility(View.VISIBLE);
                myLikeSku.setVisibility(View.GONE);
                line_lift.setVisibility(View.VISIBLE);
                line_right.setVisibility(View.GONE);
                break;
            case R.id.me_like_sku:
                myLikeStrategy.setVisibility(View.GONE);
                myLikeSku.setVisibility(View.VISIBLE);
                line_lift.setVisibility(View.GONE);
                line_right.setVisibility(View.VISIBLE);
                break;
            case R.id.lin_setting:
                Intent intent = new Intent(mContext, SettingActivity.class);
                startActivity(intent);
                break;
            default:
                break;
        }

    }
}
