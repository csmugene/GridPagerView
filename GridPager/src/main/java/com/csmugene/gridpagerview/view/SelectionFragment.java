package com.csmugene.gridpagerview.view;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.csmugene.gridpagerview.R;
import com.csmugene.gridpagerview.listener.OnBinderViewHolderListener;
import com.csmugene.gridpagerview.listener.OnItemClickListener;

import java.util.List;

/**
 * Created by ichungseob on 2018. 8. 17..
 */

@SuppressLint("ValidFragment")
class SelectionFragment extends Fragment {

    private RecyclerView.LayoutManager layoutManager;
    private OnItemClickListener mOnItemClickListener;
    private List<String> mIconArrayList;
    private List<String> mTitleArrayList;
    private int mSpanCount;
    private View mView;
    private OnBinderViewHolderListener mOnBinderViewHolderListener;

    public SelectionFragment(){

    }

    public SelectionFragment(List<String> iconArrayList, List<String> titleArrayList, int spanCount) {
        mIconArrayList = iconArrayList;
        mTitleArrayList = titleArrayList;
        mSpanCount = spanCount;
    }

    public int getSpanCount(){
        if(mSpanCount == 0){
            return 3;
        }
        return mSpanCount;
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener){
        mOnItemClickListener = onItemClickListener;
    }

    public void setOnBinderViewHolderListener(OnBinderViewHolderListener onBinderViewHolderListener){
        mOnBinderViewHolderListener = onBinderViewHolderListener;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_layout, null);

        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setItemViewCacheSize(1024);

        // Set layout manager
        layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);

        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), getSpanCount()));

        RecycleViewAdapter adapter = new RecycleViewAdapter(mIconArrayList, mTitleArrayList, mOnItemClickListener);
        adapter.setOnBinderViewHolderListener(mOnBinderViewHolderListener);
        recyclerView.setAdapter(adapter);
        return view;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onResume() {
        super.onResume();
    }

}