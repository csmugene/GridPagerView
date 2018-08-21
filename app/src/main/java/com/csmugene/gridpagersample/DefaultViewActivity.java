package com.csmugene.gridpagersample;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.csmugene.gridpagerview.GridPagerViewFactory;
import com.csmugene.gridpagerview.listener.OnIndicatorItemClickListener;
import com.csmugene.gridpagerview.listener.OnItemClickListener;
import com.csmugene.gridpagerview.model.GridConfig;
import com.csmugene.gridpagerview.view.GridViewPager;
import com.csmugene.gridpagerview.view.IndicatorView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ichungseob on 2018. 8. 21..
 */

public class DefaultViewActivity extends AppCompatActivity {
    ArrayList<String> testArr = new ArrayList<>();
    ArrayList<String> testArr2 = new ArrayList<>();
    private final static String TAG = "DefaultViewActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button btn = (Button) findViewById(R.id.go_default_btn);
        btn.setText("go_custom_test");
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(DefaultViewActivity.this, MainActivity.class);
                startActivity(i);
                finish();
            }
        });
        int[] margin = {0, 0, 20, 0};

        for(int i = 32; i < 63; i++){
            testArr.add("https://csmugene.github.io/static/assets/img/default.jpg");
            testArr2.add(String.format("%d", i));
        }


        GridConfig gridConfig = new GridConfig(testArr, testArr2 , 3, 3);
        IndicatorView indicatorView = (IndicatorView)findViewById(R.id.indicatorView);
        GridViewPager gridViewPager = (GridViewPager)findViewById(R.id.gridViewPager);
        gridViewPager.setFragmentManager(getSupportFragmentManager());
        gridViewPager.setGridConfig(gridConfig);
        gridViewPager.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Log.e(TAG, String.format("position : %d", position));
            }
        });
        GridPagerViewFactory gridPagerViewFactory = new GridPagerViewFactory(this);
        gridPagerViewFactory.init(gridViewPager);
        FragmentStatePagerAdapter adapter = gridPagerViewFactory.getFragmentAdapter(getSupportFragmentManager());
        gridViewPager.setAdapter(adapter);
        gridPagerViewFactory.makeIndicator(indicatorView, margin, new OnIndicatorItemClickListener() {
            @Override
            public void onItemClick(int position) {

            }
        });
    }

}
