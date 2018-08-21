package com.csmugene.gridpagerview.view;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.csmugene.gridpagerview.R;
import com.csmugene.gridpagerview.listener.OnBinderViewHolderListener;
import com.csmugene.gridpagerview.listener.OnIndicatorItemClickListener;
import com.csmugene.gridpagerview.listener.OnItemClickListener;
import com.csmugene.gridpagerview.model.GridConfig;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ichungseob on 2018. 8. 20..
 */

public class GridPagerImpl extends GridPager {

    private Context mContext;
    private GridConfig mGridConfig;
    private OnIndicatorItemClickListener mOnIndicatorItemClickListener;
    private GridViewPager mGridViewPager;
    private ImageView[] mIndicatorList;
    private OnBinderViewHolderListener mOnBinderViewHolderListener;
    private OnItemClickListener mOnItemClickListener;

    public GridPagerImpl(@NonNull Context context, @NonNull GridViewPager gridViewPager){
        mContext = context;
        mGridViewPager = gridViewPager;
        mGridConfig = gridViewPager.getGridConfig();
        if(mGridConfig.getIconArr() == null || mGridConfig.getTitleArr() == null){
            throw new RuntimeException("GridConfig doesn't have list");
        }
        if(mGridConfig.getIconArr().size() != mGridConfig.getTitleArr().size()  ){
            throw new RuntimeException("Both ArrayList must agree in size");
        }
        gridViewPager.addOnPageChangeListener(mOnPageChangeListener);
    }


    @Override
    public void setOnBinderViewHolderListener(OnBinderViewHolderListener onBinderViewHolderListener) {
        mOnBinderViewHolderListener = onBinderViewHolderListener;
    }

    @Override
    public FragmentStatePagerAdapter getFragmentAdapter(FragmentManager fragmentManager) {
        int page = getPage();
        List<Fragment> fragments = new ArrayList<>();
        for(int i = 0; i < page; i++){
            int fromIndex = i * (mGridConfig.getSpan() * mGridConfig.getLine());
            int toIndex = fromIndex + (mGridConfig.getSpan() * mGridConfig.getLine());
            if(toIndex >= mGridConfig.getIconArr().size()){
                toIndex = mGridConfig.getIconArr().size();
            }
            List<String> iconList = mGridConfig.getIconArr().subList(fromIndex, toIndex);
            List<String> titleList = mGridConfig.getTitleArr().subList(fromIndex, toIndex);
            SelectionFragment selectionFragment = new SelectionFragment(iconList, titleList, mGridConfig.getSpan());
            selectionFragment.setOnItemClickListener(mGridViewPager.getOnItemClickListener());
            selectionFragment.setOnBinderViewHolderListener(mOnBinderViewHolderListener);
            fragments.add(selectionFragment);
        }
        SectionPagerAdapter sectionsPagerAdapter = new SectionPagerAdapter(fragmentManager, null, fragments);
        return sectionsPagerAdapter;
    }

    //margin int left, int top, int right, int bottom
    @Override
    public void makeIndicator(IndicatorView indicatorView, int[] margin) {
        if(indicatorView == null){
            throw new RuntimeException("IndicatorView is null");
        }

        if(margin == null || margin.length < 4){
            throw new RuntimeException("Margin is null or length < 4");
        }

        indicatorView.removeAllViews();
        int page = getPage();
        mIndicatorList = new ImageView[page];
        for(int i = 0; i < page; i++){
            mIndicatorList[i] = new ImageView(mContext);
            mIndicatorList[i].setTag(i);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            params.setMargins(margin[0],margin[1], margin[2], margin[3]);
            mIndicatorList[i].setImageResource(R.drawable.indicator_default);
            if(i == 0){
                mIndicatorList[i].setImageResource(R.drawable.indicator_selected);
            }
            mIndicatorList[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(mOnIndicatorItemClickListener != null){
                        mOnIndicatorItemClickListener.onItemClick((int)v.getTag());
                    }
                    for (int i = 0; i < mIndicatorList.length; i++){
                        mIndicatorList[i].setImageResource(R.drawable.indicator_default);
                    }
                    mIndicatorList[(int)v.getTag()].setImageResource(R.drawable.indicator_selected);
                }
            });
            indicatorView.addView(mIndicatorList[i], params);
        }


    }

    @Override
    public void setOnIndicatorItemClickListener(OnIndicatorItemClickListener onIndicatorItemClickListener) {
        mOnIndicatorItemClickListener = onIndicatorItemClickListener;
    }


    private int getPage(){
        int size = mGridConfig.getIconArr().size();
        int page = 0;
        if(size > 0){
            page = size / (mGridConfig.getSpan() * mGridConfig.getLine());
            if(size % (mGridConfig.getSpan() * mGridConfig.getLine()) > 0){
                page = page + 1;
            }
        }
        return page;
    }

    private ViewPager.OnPageChangeListener mOnPageChangeListener = new ViewPager.OnPageChangeListener(){

        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {
            for (int i=0; i<mIndicatorList.length; i++){
                mIndicatorList[i].setImageResource(R.drawable.indicator_default);
            }
            if(position >= mIndicatorList.length){
                return;
            }
            mIndicatorList[position].setImageResource(R.drawable.indicator_selected);
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };
}
