package com.csmugene.gridpagerview;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.csmugene.gridpagerview.listener.OnBinderViewHolderListener;
import com.csmugene.gridpagerview.listener.OnIndicatorItemClickListener;
import com.csmugene.gridpagerview.view.GridPager;
import com.csmugene.gridpagerview.view.GridPagerImpl;
import com.csmugene.gridpagerview.view.GridViewPager;
import com.csmugene.gridpagerview.view.IndicatorView;

/**
 * Created by ichungseob on 2018. 8. 17..
 */

public class GridPagerViewFactory {

    private Context mContext;
    private GridPager mGridPager;

    public GridPagerViewFactory(@NonNull Context context){
        mContext = context;
    }

    public void init(@NonNull GridViewPager gridViewPager){
        mGridPager = new GridPagerImpl(mContext, gridViewPager);
    }

    public FragmentStatePagerAdapter getFragmentAdapter(@NonNull FragmentManager fragmentManager){
        FragmentStatePagerAdapter adapter = mGridPager.getFragmentAdapter(fragmentManager);
        return adapter;
    }

    public void makeIndicator(@NonNull IndicatorView indicatorView, @NonNull int[] margin, OnIndicatorItemClickListener onIndicatorItemClickListener){
        mGridPager.makeIndicator(indicatorView, margin);
        mGridPager.setOnIndicatorItemClickListener(onIndicatorItemClickListener);
    }

    public void setOnBinderViewHolderListener(OnBinderViewHolderListener onBinderViewHolderListener) {
        mGridPager.setOnBinderViewHolderListener(onBinderViewHolderListener);
    }



}
