package com.woban.zmdd.activity;

import android.app.ActionBar;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.woban.zmdd.R;
import com.woban.zmdd.utils.Logs;

/**
 * Created by Administrator on 2016/4/5.
 * 产品反馈
 * wsw
 */
public class FeedBackActivity extends SecondBaseActivity implements View.OnClickListener{
    private ActionBar mActionBar;
    private TextView mActionTitle,mActionSend;
    private ImageView homeView;
    private EditText tetFeedBack,tetFeedbackTantact;
    private String strContent,strContact;
    private LinearLayout contentLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback);
        findview();
    }
    private void findview() {
        mActionBar = this.getActionBar();
        Logs.e("mActionBar=="+mActionBar.toString());
        mActionBar.setBackgroundDrawable(getResources().getDrawable(R.drawable.ic_app_title_bg));
        mActionSend = (TextView) mActionBar.getCustomView().findViewById(R.id.action_send);
        mActionSend.setOnClickListener(this);
        mActionTitle = (TextView)mActionBar.getCustomView().findViewById(R.id.action_title);
        mActionTitle.setText(R.string.title_feedback);
        homeView = (ImageView) mActionBar.getCustomView().findViewById(R.id.action_home);
        homeView.setVisibility(View.VISIBLE);
        homeView.setOnClickListener(this);
        tetFeedBack = (EditText) findViewById(R.id.txt_feedback);

        tetFeedbackTantact = (EditText) findViewById(R.id.feedback_tantact);
        contentLayout = (LinearLayout) findViewById(R.id.content_layout);
       // contentLayout.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.action_home:
               // colseActivity();
                break;
            case R.id.action_send:
                strContent = tetFeedBack.getText().toString().trim();
                strContact = tetFeedbackTantact.getText().toString().trim();
              /*  if (strContact.equals("") ||strContent.equals(""))
                    Toast.makeText(FeedBackActivity.this,getResources().getString(R.string.toast_information_notall),Toast.LENGTH_SHORT).show();
                else
                    sugg();*/
                break;
            case R.id.content_layout:
                tetFeedBack.setFocusable(true);
                tetFeedBack.setFocusableInTouchMode(true);
                tetFeedBack.requestFocus();
                tetFeedBack.findFocus();
                InputMethodManager imm = (InputMethodManager) mContext
                        .getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);

                break;
        }
    }
    Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case 101:
                    //netDialog.dismiss();
                    //Toast.makeText(FeedBackActivity.this,getResources().getText(R.string.send_succeed),Toast.LENGTH_SHORT).show();
                    //finish();
                   // dialogexit();
                    break;
            }
        }
    };



/*    *//**
     * 退出登录
     *//*
    private void dialogexit(){
        final Dialog dialog = new Dialog(FeedBackActivity.this);
        LayoutInflater factory = LayoutInflater.from(FeedBackActivity.this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        final View textEntryView = factory.inflate(R.layout.dialog_success, null);
        Button buttonEnsure = (Button) textEntryView.findViewById(R.id.btn_ensure);
        buttonEnsure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                dialog.dismiss();
            }
        });

        dialog.setContentView(textEntryView);

        // * 将对话框的大小按屏幕大小的百分比设置

        Window dialogWindow = dialog.getWindow();

        WindowManager m = FeedBackActivity.this.getWindowManager();
        Display d = m.getDefaultDisplay(); // 获取屏幕宽、高用
        WindowManager.LayoutParams p = dialogWindow.getAttributes(); // 获取对话框当前的参数值
        //p.height = (int) (d.getHeight() * 0.3); // 高度设置为屏幕的0.6
        p.width = (int) (d.getWidth() * 0.8); // 宽度设置为屏幕的0.65
        dialogWindow.setAttributes(p);
        dialog.show();

    }*/


/*    private void sugg(){
        netDialog = new NetDialog(FeedBackActivity.this,R.style.dialog);
        netDialog.show();


        StringRequest stringRequest = new StringRequest(Request.Method.POST, NetUrl.posturl, new Response.Listener<String>() {
            @Override
            public void onResponse(String s) {
                try {
                    JSONObject jsonObject = new JSONObject(s);
                    Boolean data = jsonObject.optBoolean("data");
                    if(data){
                        handler.sendEmptyMessage(101);
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {

            }
        })
        {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> map = new HashMap<String, String>();
                map.put("service",NetUrl.sugg);
                map.put("userId", SharePreService.getuID(FeedBackActivity.this));
                map.put("content", tetFeedBack.getText().toString().trim());
                map.put("contact",tetFeedbackTantact.getText().toString().trim());
                return map;
            }
        };
        stringRequest.setShouldCache(false);
        FocusApplication.getInstance().addToRequestQueue(stringRequest);
    }*/
   /* private void colseActivity(){
        finish();
        overridePendingTransition(R.anim.push_right_in, R.anim.push_right_out);
    }
    public void onResume() {
        super.onResume();
        if (!FocusApplication.isApkDebugable(FeedBackActivity.this)) {
            MobclickAgent.onPageStart("FeedBackActivity"); //统计页面(仅有Activity的应用中SDK自动调用，不需要单独写。"SplashScreen"为页面名称，可自定义)
            MobclickAgent.onResume(this);          //统计时长
        }
    }
    public void onPause() {
        super.onPause();
        if (!FocusApplication.isApkDebugable(FeedBackActivity.this)) {
            MobclickAgent.onPageEnd("FeedBackActivity"); // （仅有Activity的应用中SDK自动调用，不需要单独写）保证 onPageEnd 在onPause 之前调用,因为 onPause 中会保存信息。"SplashScreen"为页面名称，可自定义
            MobclickAgent.onPause(this);
        }
    }*/

}
