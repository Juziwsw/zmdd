package com.woban.zmdd.bean;

import java.io.Serializable;

/**
 * Created by Administrator on 2016/6/24.
 * @author wsw
 * 搜索列表listview,大图的item
 */
public class SearchListItem implements Serializable {
    public String bigImgUrl;
    public String title;
    public String headUrl;
    public int lookNum;//观看数
    public int likeNum;//喜欢数、
    public int shareNum;//分享数
}
