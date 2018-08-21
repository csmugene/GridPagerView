package com.csmugene.gridpagerview.view;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.csmugene.gridpagerview.R;
import com.csmugene.gridpagerview.adapter.AdapterViewHolder;
import com.csmugene.gridpagerview.listener.OnBinderViewHolderListener;
import com.csmugene.gridpagerview.listener.OnItemClickListener;

import java.util.List;

/**
 * Created by ichungseob on 2018. 8. 17..
 */

class RecycleViewAdapter extends  RecyclerView.Adapter<AdapterViewHolder>{

    private Context mContext;
    private List<String> mIconUrlArrayList;
    private List<String> mTitleArrayList;
    private OnBinderViewHolderListener mOnBinderViewHolderListener;
    private OnItemClickListener mOnItemClickListener;

    public RecycleViewAdapter(List<String> iconArray, List<String> titleArray, OnItemClickListener onItemClickListener){
        mIconUrlArrayList = iconArray;
        mTitleArrayList = titleArray;
    }

    public void setOnBinderViewHolderListener(OnBinderViewHolderListener onBinderViewHolderListener){
        mOnBinderViewHolderListener = onBinderViewHolderListener;
    }

    public class ViewHolder extends AdapterViewHolder{


        public ViewHolder(final View itemView) {
            super(itemView);
            mImageView = (ImageView)itemView.findViewById(R.id.image);
            mImageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(mOnItemClickListener != null){
                        mOnItemClickListener.onItemClick(itemView, mPosition);
                    }
                }
            });
            mTitleView = (TextView)itemView.findViewById(R.id.title);
        }
    }


    @Override
    public AdapterViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        mContext = parent.getContext();
        if(mOnBinderViewHolderListener != null){
            return  mOnBinderViewHolderListener.onCreateViewHolder(parent, viewType);
        }
        View v = LayoutInflater.from(mContext).inflate(R.layout.grid_cell, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(AdapterViewHolder holder, int position) {
        if(mOnBinderViewHolderListener != null){
            mOnBinderViewHolderListener.onBindViewHolder(holder, position, mIconUrlArrayList, mTitleArrayList);
            return;
        }
        holder.setPosition(position);
        String imageUrl = mIconUrlArrayList.get(position);
        String title = mTitleArrayList.get(position);
        holder.mTitleView.setText(title);
        Glide.with(mContext)
                .load(imageUrl)
                .dontAnimate()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .skipMemoryCache(true)
                .into(holder.mImageView);
    }

    @Override
    public int getItemCount() {
        return mIconUrlArrayList.size();
    }
}
