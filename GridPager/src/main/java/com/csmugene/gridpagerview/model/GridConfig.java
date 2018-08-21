package com.csmugene.gridpagerview.model;

import android.view.View;
import android.widget.Adapter;

import com.csmugene.gridpagerview.adapter.AdapterViewHolder;

import java.util.ArrayList;

/**
 * Created by ichungseob on 2018. 8. 20..
 */

public class GridConfig {

    private ArrayList<String> mIconArr;
    private  ArrayList<String> mTitleArr;
    private int mSpan;
    private int mLine;

    public GridConfig(ArrayList<String> iconArr, ArrayList<String> titleArr, int span, int line){
        mIconArr = iconArr;
        mTitleArr = titleArr;
        mSpan = span;
        mLine = line;
    }

    public ArrayList<String> getIconArr() {
        return mIconArr;
    }

    public ArrayList<String> getTitleArr() {
        return mTitleArr;
    }

    public int getSpan() {
        if(mSpan == 0){
            return 3;
        }
        return mSpan;
    }

    public int getLine() {
        if(mLine == 0){
            return 2;
        }
        return mLine;
    }

    @Override
    public String toString() {
        return "GridConfig{" +
                "IconArr=" + mIconArr +
                ", TitleArr=" + mTitleArr +
                ", Span=" + mSpan +
                ", Line=" + mLine +
                '}';
    }
}
