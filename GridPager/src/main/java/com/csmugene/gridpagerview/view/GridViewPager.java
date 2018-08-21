package com.csmugene.gridpagerview.view;

import android.content.Context;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.View;

import com.csmugene.gridpagerview.listener.OnItemClickListener;
import com.csmugene.gridpagerview.model.GridConfig;

/**
 * Created by ichungseob on 2018. 8. 20..
 */

public class GridViewPager extends ViewPager {

    private GridConfig mGridConfig;
    private FragmentManager mFragmentManager;
    private OnItemClickListener mOnItemClickListener;

    public GridViewPager(Context context) {
        super(context);
    }

    public GridViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public void setGridConfig(GridConfig gridConfig){
        mGridConfig = gridConfig;
    }

    public void setFragmentManager(FragmentManager fragmentManager){
        mFragmentManager = fragmentManager;
    }

    public FragmentManager getFragmentManager(){
        return mFragmentManager;
    }

    public GridConfig getGridConfig(){
        return mGridConfig;
    }

    @Override
    public void addOnPageChangeListener(OnPageChangeListener onPageChangeListener){
        super.addOnPageChangeListener(onPageChangeListener);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec)
    {
        int height = 0;
        for(int i = 0; i < getChildCount(); i++)
        {
            View child = getChildAt(i);
            child.measure(widthMeasureSpec, MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED));
            int h = child.getMeasuredHeight();
            if(h > height)
            {
                height = h;
            }
        }
        heightMeasureSpec = MeasureSpec.makeMeasureSpec(height, MeasureSpec.EXACTLY);
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener){
        mOnItemClickListener = onItemClickListener;
    }

    public OnItemClickListener getOnItemClickListener(){
        return mOnItemClickListener;
    }

}
