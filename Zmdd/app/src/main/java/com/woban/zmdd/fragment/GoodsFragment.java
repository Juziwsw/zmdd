package com.woban.zmdd.fragment;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager.LayoutParams;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;

import com.woban.zmdd.R;
import com.woban.zmdd.activity.SearchActivity;
import com.woban.zmdd.adapter.GoodsGridViewAdapter;
import com.woban.zmdd.adapter.GoodsHeadRecyclerViewAdapter;
import com.woban.zmdd.adapter.GoodsListviewAdapter;
import com.woban.zmdd.adapter.GoodsRecyclerViewAdapter;
import com.woban.zmdd.bean.TabRecyItem;
import com.woban.zmdd.utils.Logs;
import com.woban.zmdd.wights.HightListview;

import java.util.ArrayList;

/**
 * Created by Administrator on 2016/5/31.
 * @author wsw
 * 好货Fragment
 */
public class GoodsFragment extends Fragment implements View.OnClickListener,
        GoodsHeadRecyclerViewAdapter.OnRecyclerViewListener,
        GoodsRecyclerViewAdapter.OnRecyclerViewListener{
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private String mParam1;
    private String mParam2;
    private Context mContext;
    private View mView;
    private ImageView buttonSearch;

    private RecyclerView recyclerView_goodshead;
    private GoodsHeadRecyclerViewAdapter goodsHeadRecyclerViewAdapter;
    private ArrayList<TabRecyItem> goodsFirstHeadRecyItems = new ArrayList<TabRecyItem>();
    private ArrayList<TabRecyItem> goodsAllHeadRecyItems = new ArrayList<TabRecyItem>();


    //从网络获取的左右滑动，只在最新里面有
    private RecyclerView recyclerView_goods;
    private GoodsRecyclerViewAdapter goodsRecyclerViewAdapter;
    private ArrayList<TabRecyItem> goodsRecyItems = new ArrayList<TabRecyItem>();


    private HightListview mListview;

    int index = 0;
    int unOnClick = 0;//不能点击的tab;
    private String[] items={"最新1","最新2"
            ,"最新13","最新4","最新4","最新4","最新4","最新4","最新4","最新4","最新4","最新4","最新4","最新4","最新4"};
    private LinearLayout lin_point_down;
    private PopupWindow popWindow;
    private Boolean is_spreaded = false;//记录PopupWindow是否是弹出状态

    //除了点击最新之外的标签，需要隐藏的部分
    private RelativeLayout rel_popularity_goods,rel_global_goods;


    public static GoodsFragment newInstance(String param1, String param2) {
        GoodsFragment fragment = new GoodsFragment();
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
        mView = inflater.inflate(R.layout.fragment_goods,container,false);
      /*  //设置广播器
        IntentFilter filter = new IntentFilter();
        filter.addAction(Constants.INTENTACTION);
        getActivity().registerReceiver(broadcastReceiver, filter);*/
        setfirstData();
        setsecondData();
        initializeView();
        return mView;
    }
/*    BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            int index = intent.getExtras().getInt("index");
            switch (index){
                case 2:
                    if (is_spreaded){
                        popWindow.dismiss();
                    }
                    break;
            }
        }
    };*/

    /**
     * 初始化布局
     */
    private void initializeView(){
        buttonSearch = (ImageView) mView.findViewById(R.id.button_search);
        buttonSearch.setOnClickListener(this);

        //初始化RecyclerView，设置横向滑动tab左滑动，给定的数据，
        recyclerView_goodshead = (RecyclerView) mView.findViewById(R.id.recyclerView_goodshead);
        recyclerView_goodshead.setHasFixedSize(true);
        LinearLayoutManager layoutManager_tab = new LinearLayoutManager(mContext);
        layoutManager_tab.setOrientation(LinearLayoutManager.HORIZONTAL);
        recyclerView_goodshead.setLayoutManager(layoutManager_tab);
        goodsHeadRecyclerViewAdapter = new GoodsHeadRecyclerViewAdapter(goodsFirstHeadRecyItems,mContext);
        recyclerView_goodshead.setAdapter(goodsHeadRecyclerViewAdapter);
        goodsHeadRecyclerViewAdapter.setOnRecyclerViewListener(this);

        lin_point_down = (LinearLayout) mView.findViewById(R.id.lin_point_down);
        lin_point_down.setOnClickListener(this);


        //初始化RecyclerView，设置横向滑动tab左滑动，给定的数据，
        recyclerView_goods = (RecyclerView) mView.findViewById(R.id.recyclerView_goods);
        recyclerView_goods.setHasFixedSize(true);
        LinearLayoutManager layoutManager_goods = new LinearLayoutManager(mContext);
        layoutManager_goods.setOrientation(LinearLayoutManager.HORIZONTAL);
        recyclerView_goods.setLayoutManager(layoutManager_goods);
        goodsRecyclerViewAdapter = new GoodsRecyclerViewAdapter(goodsAllHeadRecyItems,mContext);
        recyclerView_goods.setAdapter(goodsRecyclerViewAdapter);
        goodsRecyclerViewAdapter.setOnRecyclerViewListener(this);


        //初始化listview,最基本的表
        mListview = (HightListview) mView.findViewById(R.id.listView_goods);
        GoodsListviewAdapter goodsListviewAdapter = new GoodsListviewAdapter(mContext,goodsAllHeadRecyItems,R.layout.goods_listview_item);
        mListview.setAdapter(goodsListviewAdapter);

        rel_popularity_goods = (RelativeLayout) mView.findViewById(R.id.rel_popularity_goods);
        rel_global_goods = (RelativeLayout) mView.findViewById(R.id.rel_global_goods);
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.lin_point_down:
                LayoutInflater layoutInflater = (LayoutInflater)mContext
                        .getSystemService(mContext.LAYOUT_INFLATER_SERVICE);
                // 获取自定义布局文件poplayout.xml的视图
                final View popview = layoutInflater.inflate(R.layout.poplayout, null);
                popWindow = new PopupWindow(popview,
                        LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT,true);
                //ColorDrawable colorDrawable = new ColorDrawable(getResources().getColor(R.color.red));
                // ColorDrawable dw = new ColorDrawable(getResources().getColor(R.color.gray));
                ColorDrawable dw = new ColorDrawable(0000);
                popWindow.setBackgroundDrawable(dw);
                //规定弹窗的位置
                popWindow.showAsDropDown(mView.findViewById(R.id.fragment_head));
                popview.setOnTouchListener(new View.OnTouchListener() {
                    @Override
                    public boolean onTouch(View v, MotionEvent event) {
                        popWindow.dismiss();
                        return false;
                    }
                });
                is_spreaded = true;
                GridView gridView = (GridView) popview.findViewById(R.id.gridview);
                GoodsGridViewAdapter goodsGridViewAdapter = new GoodsGridViewAdapter(mContext,goodsAllHeadRecyItems,R.layout.goods_popwindow);                //ArrayAdapter<String > arrayAdapter = new ArrayAdapter<String >(mContext,android.R.layout.simple_list_item_1, items);
                gridView.setAdapter(goodsGridViewAdapter);
                gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        Logs.e("position=="+position);
                        is_spreaded = false;
                        popWindow.dismiss();
                    }
                });
                break;
            case R.id.button_search:
                Intent intent = new Intent(mContext, SearchActivity.class);
                mContext.startActivity(intent);
            default:
                break;
        }
    }
    @Override
    public void onItemClick(View v, int position) {
        if (unOnClick == position){
            return;
        }
        switch (v.getId()){
            case R.id.goodsheadrecyclerviewadapter_lin:
                for (int i = 0;i < goodsFirstHeadRecyItems.size();i++){
                    if (i == position){
                        goodsFirstHeadRecyItems.get(i).onclicked = true;
                    }else {
                        goodsFirstHeadRecyItems.get(i).onclicked = false;
                    }
                }
                if (position == 0){
                    rel_global_goods.setVisibility(View.VISIBLE);
                    rel_popularity_goods.setVisibility(View.VISIBLE);
                    recyclerView_goods.setVisibility(View.VISIBLE);
                }else {
                    rel_global_goods.setVisibility(View.GONE);
                    rel_popularity_goods.setVisibility(View.GONE);
                    recyclerView_goods.setVisibility(View.GONE);

                }
                index = position;
                unOnClick = position;
                goodsHeadRecyclerViewAdapter.notifyDataSetChanged();
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

    private void setfirstData(){
        for (int i = 0;i < 9;i++){
            TabRecyItem tabRecyItem = new TabRecyItem();
            tabRecyItem.tabName = "健康"+i;
            if (i == 0){
                tabRecyItem.onclicked = true;
            }else {
                tabRecyItem.onclicked = false;
            }
            goodsFirstHeadRecyItems.add(tabRecyItem);
        }
    }
    private void setsecondData(){
        for (int i = 0;i < 14;i++){
            TabRecyItem tabRecyItem = new TabRecyItem();
            tabRecyItem.tabName = "健康"+i;
            if (i == 0){
                tabRecyItem.onclicked = true;
            }else {
                tabRecyItem.onclicked = false;
            }
            goodsAllHeadRecyItems.add(tabRecyItem);

        }
        int sise = goodsAllHeadRecyItems.size()%4;
        if (sise != 0){
            for (int i = 0;i < 4-sise;i++){
                Logs.e("i=="+i);
                TabRecyItem tabRecyItem = new TabRecyItem();
                goodsAllHeadRecyItems.add(tabRecyItem);
            }
        }
    }
}
