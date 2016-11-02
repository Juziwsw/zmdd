package com.woban.zmdd.bean;

import java.io.Serializable;

/**
 * Created by Administrator on 2016/6/7.
 * @author wsw
 * MeFragment  list item 对象
 */
public class MelistItem implements Serializable {
    public String title;//标题
    public String price;//价钱
    public int likenum;//喜欢数；
    public  boolean isliked ;//是否收藏

}
