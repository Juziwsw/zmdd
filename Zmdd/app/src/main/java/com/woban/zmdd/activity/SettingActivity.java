package com.woban.zmdd.activity;

import android.app.ActionBar;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.woban.zmdd.R;
import com.woban.zmdd.utils.Logs;

/**
 * Created by wushi on 2016/6/18.
 * @author wsw
 * 设置界面
 */
public class SettingActivity extends SecondBaseActivity implements View.OnClickListener{
    private ActionBar mActionBar;
    private TextView mActionTitle,mActionSend;
    private ImageView homeView;
    private int []ids = {R.id.rel_me_phonenum,R.id.rel_me_password,R.id.rel_me_wb,R.id.rel_me_wx,R.id.rel_me_qq,
            R.id.rel_me_feedback,R.id.rel_me_cleanup,R.id.rel_me_agreement,R.id.button_exit};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        setOnClickListenerButton(ids);
        findview();
    }
    public void setOnClickListenerButton(int ids[]) {
        for (int i = 0; i < ids.length; i++) {
            View btn = findViewById(ids[i]);
            btn.setOnClickListener(this);
        }
    }
    private void findview() {
        mActionBar = this.getActionBar();
        Logs.e("mActionBar=="+mActionBar.toString());
        mActionBar.setBackgroundDrawable(getResources().getDrawable(R.drawable.ic_app_title_bg));
        mActionSend = (TextView) mActionBar.getCustomView().findViewById(R.id.action_send);
        mActionSend.setVisibility(View.GONE);
        mActionTitle = (TextView)mActionBar.getCustomView().findViewById(R.id.action_title);
        mActionTitle.setText(R.string.title_setting);
        homeView = (ImageView) mActionBar.getCustomView().findViewById(R.id.action_home);
        homeView.setVisibility(View.VISIBLE);
        homeView.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        super.onClick(view);
        Intent intent = new Intent();
        switch (view.getId()){
            case R.id.rel_me_phonenum:
                intent.setClass(SettingActivity.this,SetPhoneNumberActivity.class);
                startActivity(intent);
                break;
            case R.id.rel_me_password:
                intent.setClass(SettingActivity.this,SettingPassWordActivity.class);
                startActivity(intent);
                break;
            case R.id.rel_me_wb:

                break;
            case R.id.rel_me_wx:

                intent.setClass(SettingActivity.this,YMLoginActivity.class);
                intent.putExtra("openFrom","wx");
                startActivity(intent);
                break;
            case R.id.rel_me_qq:
                intent.setClass(SettingActivity.this,YMLoginActivity.class);
                intent.putExtra("openFrom","qq");
                startActivity(intent);
                break;
            case R.id.rel_me_feedback:
                intent.setClass(SettingActivity.this,FeedBackActivity.class);
                startActivity(intent);
                break;
            case R.id.rel_me_cleanup:
                break;
            case R.id.rel_me_agreement:
                break;
            case R.id.button_exit:
                break;

        }
    }
}
