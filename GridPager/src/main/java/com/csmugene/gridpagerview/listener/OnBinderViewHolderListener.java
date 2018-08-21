package com.csmugene.gridpagerview.listener;

import android.view.ViewGroup;

import com.csmugene.gridpagerview.adapter.AdapterViewHolder;

import java.util.List;

/**
 * Created by ichungseob on 2018. 8. 20..
 */

public interface OnBinderViewHolderListener {
    void onBindViewHolder(AdapterViewHolder holder, int position, List<String> iconUrlArrayList, List<String> titleArrayList);
    AdapterViewHolder onCreateViewHolder(ViewGroup parent, int viewType);
}
