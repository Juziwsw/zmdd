package com.woban.zmdd.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.widget.Toast;

import com.umeng.socialize.UMAuthListener;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.woban.zmdd.R;
import com.woban.zmdd.utils.NetUtils;

import java.util.Map;

/**
 * Created by Administrator on 2016/7/4.
 * @author wsw
 * 友盟登陆界面
 */
public class YMLoginActivity extends Activity {
    private CoordinatorLayout coor;
    private String openFrom;
    public static UMShareAPI mShareAPI = null;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ymlogin);
        coor = (CoordinatorLayout) findViewById(R.id.coor);
        mShareAPI = UMShareAPI.get(this);
        /*LoginUtils loginUtils = new LoginUtils(LoginActivity.this,"qq");
        loginUtils.login();*/
        if (!NetUtils.isNetworkAvailable(YMLoginActivity.this)){
            Toast.makeText(YMLoginActivity.this, getResources().getString(R.string.toast_network_notavailable), Toast.LENGTH_SHORT).show();
            return;
        }
        openFrom = getIntent().getStringExtra("openFrom");
        if (openFrom.equals("qq"))
            mShareAPI.doOauthVerify(YMLoginActivity.this, SHARE_MEDIA.QQ, umAuthListener);
        else if(openFrom.equals("wx"))
            mShareAPI.doOauthVerify(YMLoginActivity.this, SHARE_MEDIA.WEIXIN, umAuthListener);
    }
    private UMAuthListener umAuthListener = new UMAuthListener() {
        @Override
        public void onComplete(SHARE_MEDIA platform, int action, Map<String, String> data) {
            mShareAPI.getPlatformInfo(YMLoginActivity.this, platform, umUserInfoListener);
        }
        @Override
        public void onError(SHARE_MEDIA platform, int action, Throwable t) {
            Toast.makeText(YMLoginActivity.this, "登陆发生错误", Toast.LENGTH_SHORT).show();
            YMLoginActivity.this.finish();
        }
        @Override
        public void onCancel(SHARE_MEDIA platform, int action) {
            //Snackbar.make(coor,"登陆取消了",Snackbar.LENGTH_LONG).show();
            /*Snackbar.make(coor, "Replace with your own action", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show();*/
            Toast.makeText(YMLoginActivity.this, "登陆取消了", Toast.LENGTH_SHORT).show();
            YMLoginActivity.this.finish();
        }
    };

    private UMAuthListener umUserInfoListener = new UMAuthListener() {
        @Override
        public void onComplete(SHARE_MEDIA platform, int action, Map<String, String> data) {
            if (data != null) {
                if (platform == SHARE_MEDIA.QQ){
                    openFrom = "qq";
                   /* userName = data.get("screen_name");
                    userAvatar = data.get("profile_image_url");
                    openId = data.get("openid");
                    gender = data.get("gender");*/
                } else if(platform == SHARE_MEDIA.WEIXIN){
                    openFrom = "wx";
                   /* userName = data.get("nickname");
                    userAvatar = data.get("headimgurl");
                    openId = data.get("openid");
                    gender = data.get("sex");*/
                }
               /* Message message = new Message();
                message.what = 0;
                message.obj = openFrom;
                handler.sendMessage(message);*/
            }
        }

        @Override
        public void onError(SHARE_MEDIA platform, int action, Throwable t) {
        }
        @Override
        public void onCancel(SHARE_MEDIA platform, int action) {
        }
    };
}
