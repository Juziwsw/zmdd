package com.woban.zmdd.activity;

import android.app.Activity;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.woban.zmdd.R;
import com.woban.zmdd.adapter.PollAdapter;
import com.woban.zmdd.bean.PollItem;

import org.achartengine.GraphicalView;

import java.util.ArrayList;

/**
 * Created by Administrator on 2016/6/8.
 * @author wsw
 * 投票结果Activity
 */
public class PollActiviy extends Activity{

    private ListView listView;
    private LinearLayout linearLayout;
    private GraphicalView graphicalView;
    private ArrayList<PollItem> pollItems = new ArrayList<PollItem>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_poll);
        getData();
        listView = (ListView) findViewById(R.id.poll_listview);
        PollAdapter pollAdapter = new PollAdapter(PollActiviy.this,pollItems);
        listView.setAdapter(pollAdapter);
    }
    private void  getData(){
        for (int i = 0; i < 7 ;i++){
            PollItem pollItem = new PollItem();
            pollItem.haves = 12+i*2;
            pollItem.title = "这俩个人很般配"+i;
            pollItems.add(pollItem);
        }
    }
}
