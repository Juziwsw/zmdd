package com.woban.zmdd.wights;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.GridView;

/**
 * Created by Administrator on 2016/4/8.
 */
public class FloatGridView extends GridView {
    public FloatGridView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public FloatGridView(Context context) {
        super(context);
    }

    public FloatGridView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    public void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

        int expandSpec = MeasureSpec.makeMeasureSpec(
                Integer.MAX_VALUE >> 2, MeasureSpec.AT_MOST);
        super.onMeasure(widthMeasureSpec, expandSpec);
    }

}
