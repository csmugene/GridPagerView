package com.csmugene.gridpagerview.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by ichungseob on 2018. 8. 17..
 */

public class AdapterViewHolder extends  RecyclerView.ViewHolder{

    public TextView mTitleView;
    public ImageView mImageView;
    public int mPosition;

    public AdapterViewHolder(View itemView) {
        super(itemView);
    }

    public void setPosition(int position){
        mPosition = position;
    }
}
