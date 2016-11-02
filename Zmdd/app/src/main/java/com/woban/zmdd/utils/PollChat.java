package com.woban.zmdd.utils;

import android.content.Context;
import android.view.View;

import com.woban.zmdd.R;

import org.achartengine.ChartFactory;
import org.achartengine.model.CategorySeries;
import org.achartengine.renderer.DefaultRenderer;
import org.achartengine.renderer.SimpleSeriesRenderer;

/**
 * Created by Administrator on 2016/6/8.
 * @author wsw
 * 生成饼状图
 */
public class PollChat {
    public View execute(Context context,double a,double b ) {
        int[] colors = new int[] {context.getResources().getColor(R.color.color_255_68_92),
                             context.getResources().getColor(R.color.color_255_163_174)};
        DefaultRenderer renderer = buildCategoryRenderer(colors);
        double[] values={a,b};
        CategorySeries dataset= buildCategoryDataset("zmdd", values);
        return ChartFactory
                .getPieChartView(context, dataset, renderer);
    }

    protected CategorySeries buildCategoryDataset(String title, double[] values) {
        CategorySeries series = new CategorySeries(title);
        series.add("占有", values[0]);
        series.add("未占有", values[1]);
        return series;
    }

    protected DefaultRenderer buildCategoryRenderer(int[] colors) {
        DefaultRenderer renderer = new DefaultRenderer();

        renderer.setLegendTextSize(10);//设置左下角表注的文字大小
        renderer.setShowLegend(false);//不显示底部说明
        //renderer.setZoomButtonsVisible(true);//设置显示放大缩小按钮
        renderer.setZoomEnabled(false);//设置不允许放大缩小.
        renderer.setChartTitleTextSize(30);//设置图表标题的文字大小
        renderer.setShowLabels(false);
        //renderer.setChartTitle("统计结果");//设置图表的标题  默认是居中顶部显示
        renderer.setLabelsTextSize(20);//饼图上标记文字的字体大小
        //renderer.setLabelsColor(Color.WHITE);//饼图上标记文字的颜色
        renderer.setPanEnabled(false);//设置是否可以平移
        renderer.setDisplayValues(false);//是否显示值
        renderer.setClickEnabled(true);//设置是否可以被点击
        renderer.setMargins(new int[] { 0, 0, 10,10 });

        //margins - an array containing the margin size values, in this order: top, left, bottom, right
        for (int color : colors) {
            SimpleSeriesRenderer r = new SimpleSeriesRenderer();
            r.setColor(color);
            renderer.addSeriesRenderer(r);
        }
        return renderer;
    }
}
