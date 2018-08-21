package com.csmugene.gridpagerview.view;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.csmugene.gridpagerview.model.GridConfig;
import com.csmugene.gridpagerview.view.GridPager;

import java.util.List;
import java.util.Locale;

/**
 * Created by ichungseob on 2018. 8. 17..
 */

class SectionPagerAdapter extends FragmentStatePagerAdapter {
    public GridConfig mGridConfig;
    public List<Fragment> fragments;

    public SectionPagerAdapter(FragmentManager fm, GridConfig gridConfig, List<Fragment> fragments) {
        super(fm);
        mGridConfig = gridConfig;
        this.fragments = fragments;
    }

    @Override
    public Fragment getItem(int position) {
        return this.fragments.get(position);
    }

    @Override
    public int getCount() {
        return this.fragments.size();
    }

}
