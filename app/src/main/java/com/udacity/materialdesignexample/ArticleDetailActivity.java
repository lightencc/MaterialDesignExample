package com.udacity.materialdesignexample;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.app.LoaderManager;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import java.util.List;

import butterknife.BindView;

/**
 * Created by 陈超 on 2017/3/7.
 */

public class ArticleDetailActivity extends AppCompatActivity  {
    public final static String EXTRA_CURRENT_ID = "EXTRA_CURRENT_ID";

    private int mCurrentIndex;
    private MyPagerAdapter mPagerAdapter;
    private ViewPager mPager;
    private List<ArticleModel> articles;

    public static Intent newInstance(Context context, int index){
        Intent intent = new Intent(context,ArticleDetailActivity.class);
        intent.putExtra("index",index);
        return intent;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_article_detail);

        articles = DataProvider.getArticles(this);
        mCurrentIndex = getIntent().getIntExtra("index",-1);

        mPager = (ViewPager) findViewById(R.id.pager);
        mPagerAdapter = new MyPagerAdapter(getSupportFragmentManager());
        mPager.setAdapter(mPagerAdapter);
        mPager.addOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
            @Override
            public void onPageScrollStateChanged(int state) {
                super.onPageScrollStateChanged(state);
            }

            @Override
            public void onPageSelected(int position) {
                mCurrentIndex = position;
            }
        });

        mPagerAdapter.notifyDataSetChanged();
        mPager.setCurrentItem(mCurrentIndex, false);
    }

    private class MyPagerAdapter extends FragmentStatePagerAdapter {
        public MyPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return ArticleDetailFragment.newInstance(position);
        }

        @Override
        public int getCount() {
            return articles.size();
        }
    }

}
