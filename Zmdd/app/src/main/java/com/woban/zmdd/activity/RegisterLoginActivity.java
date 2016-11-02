package com.woban.zmdd.activity;

import android.app.ActionBar;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.woban.zmdd.R;
import com.woban.zmdd.utils.Logs;

/**
 * Created by Administrator on 2016/6/21.
 * @author wsw
 * 注册登录界面
 */
public class RegisterLoginActivity extends SecondBaseActivity {
    private ActionBar mActionBar;
    private TextView mActionTitle,mActionSend;
    private ImageView homeView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registerlogin);
        findview();
    }
    private void findview() {
        mActionBar = this.getActionBar();
        Logs.e("mActionBar=="+mActionBar.toString());
        mActionBar.setBackgroundDrawable(getResources().getDrawable(R.drawable.ic_app_title_bg));
        mActionSend = (TextView) mActionBar.getCustomView().findViewById(R.id.action_send);
        mActionSend.setVisibility(View.GONE);
        mActionTitle = (TextView)mActionBar.getCustomView().findViewById(R.id.action_title);
        mActionTitle.setText(R.string.button_login);
        homeView = (ImageView) mActionBar.getCustomView().findViewById(R.id.action_home);
        homeView.setVisibility(View.VISIBLE);
        homeView.setOnClickListener(this);
        // contentLayout.setOnClickListener(this);
    }
}
