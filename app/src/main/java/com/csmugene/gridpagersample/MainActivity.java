package com.csmugene.gridpagersample;

import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.csmugene.gridpagerview.GridPagerViewFactory;
import com.csmugene.gridpagerview.adapter.AdapterViewHolder;
import com.csmugene.gridpagerview.listener.OnBinderViewHolderListener;
import com.csmugene.gridpagerview.listener.OnIndicatorItemClickListener;
import com.csmugene.gridpagerview.model.GridConfig;
import com.csmugene.gridpagerview.view.GridViewPager;
import com.csmugene.gridpagerview.view.IndicatorView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    ArrayList<String> testArr = new ArrayList<>();
    ArrayList<String> testArr2 = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        int[] margin = {0, 0, 20, 0};

        for(int i = 0; i < 31; i++){
            testArr.add("https://csmugene.github.io/static/assets/img/default.jpg");
            testArr2.add(String.format("%d", i));
        }


        GridConfig gridConfig = new GridConfig(testArr, testArr2 , 3, 3);
        IndicatorView indicatorView = (IndicatorView)findViewById(R.id.indicatorView);
        GridViewPager gridViewPager = (GridViewPager)findViewById(R.id.gridViewPager);
        gridViewPager.setFragmentManager(getSupportFragmentManager());
        gridViewPager.setGridConfig(gridConfig);


        GridPagerViewFactory gridPagerViewFactory = new GridPagerViewFactory(this);
        gridPagerViewFactory.init(gridViewPager);
        gridPagerViewFactory.setOnBinderViewHolderListener(new OnBinderViewHolderListener() {

            @Override
            public void onBindViewHolder(AdapterViewHolder holder, int position, List<String> iconUrlArrayList, List<String> titleArrayList) {
                String imageUrl = iconUrlArrayList.get(position);
                String title = titleArrayList.get(position);
                ViewHolder dd = (ViewHolder)holder;
                dd.textView.setText(title);
                Glide.with(MainActivity.this)
                        .load(imageUrl)
                        .dontAnimate()
                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                        .skipMemoryCache(true)
                        .into(dd.imageView);
            }

            @Override
            public AdapterViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
                View v = LayoutInflater.from(MainActivity.this).inflate(R.layout.grid_cell, parent, false);
                return new ViewHolder(v);
            }
        });
        FragmentStatePagerAdapter adapter = gridPagerViewFactory.getFragmentAdapter(getSupportFragmentManager());
        gridViewPager.setAdapter(adapter);
        gridPagerViewFactory.makeIndicator(indicatorView, margin, new OnIndicatorItemClickListener() {
            @Override
            public void onItemClick(int position) {

            }
        });
    }

    @Override
    public void onResume(){
        super.onResume();
        Log.e("TAG", "onResume");

    }

    public class ViewHolder extends AdapterViewHolder{

        TextView textView;
        ImageView imageView;

        public ViewHolder(View itemView) {
            super(itemView);
            textView = (TextView)itemView.findViewById(R.id.title);
            imageView = (ImageView) itemView.findViewById(R.id.image);

        }
    }
}
