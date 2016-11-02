package com.woban.zmdd.bean;

import android.graphics.drawable.Drawable;

import java.io.Serializable;

/**
 * Created by Administrator on 2016/6/8.
 * @author  wsw
 * 首页滑动tab  bean
 */
public class TabRecyItem implements Serializable {
    public Boolean onclicked;//点击状态
    public String tabName;//Tab  名称
    public Drawable onDrawable;//点击状态的图
    public Drawable unDrawable;//没有点击状态的图
}
