package com.csmugene.gridpagerview.view;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.csmugene.gridpagerview.listener.OnBinderViewHolderListener;
import com.csmugene.gridpagerview.listener.OnIndicatorItemClickListener;
import com.csmugene.gridpagerview.view.IndicatorView;

/**
 * Created by ichungseob on 2018. 8. 20..
 */

public abstract class GridPager {

    public abstract void setOnBinderViewHolderListener(OnBinderViewHolderListener onBinderViewHolderListener);
    public abstract FragmentStatePagerAdapter getFragmentAdapter(FragmentManager fragmentManager);
    public abstract void makeIndicator(IndicatorView indicatorView, int[] margin);
    public abstract void setOnIndicatorItemClickListener(OnIndicatorItemClickListener onIndicatorItemClickListener);
}
