package com.example.demoanalytic;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.flyco.tablayout.CommonTabLayout;
import com.flyco.tablayout.listener.CustomTabEntity;
import com.flyco.tablayout.listener.OnTabSelectListener;

import java.util.ArrayList;

public class FirstActivity extends AppCompatActivity {
    private Context mContext = this;
    private ArrayList<Fragment> mFragments = new ArrayList<>();
    private ArrayList<Fragment> mFragments2 = new ArrayList<>();

    private String[] mTitles = {"首页", "消息", "联系人", "更多", "测试"};



    /*
    private int[] mIconUnselectIds = {
            R.mipmap.tab_culture, R.mipmap.tab_party,
            R.mipmap.tab_backup2, R.mipmap.tab_notice, R.mipmap.tab_backup3};
    private int[] mIconSelectIds =  {
            R.mipmap.tab_culture, R.mipmap.tab_party,
            R.mipmap.tab_backup2, R.mipmap.tab_notice, R.mipmap.tab_backup3};
*/

    /*
     imageList.add(R.drawable.ic_group);
        imageList.add(R.drawable.ic_bulb_offline);
        imageList.add(R.drawable.ic_alarm);
        imageList.add(R.drawable.ic_bulb_on_half);
        imageList.add(R.drawable.ic_composition);
        imageList.add(R.drawable.ic_mesh_ota);
        imageList.add(R.drawable.ic_edit);
        imageList.add(R.drawable.ic_low_power);
    * */

    private int[] mIconUnselectIds = {
            R.drawable.ic_group, R.drawable.ic_alarm,
            R.drawable.ic_bulb_offline, R.drawable.ic_bulb_on_half, R.drawable.ic_edit};
    private int[] mIconSelectIds = {
            R.drawable.ic_low_power, R.drawable.ic_mesh_ota,
            R.drawable.ic_bulb_on_half, R.drawable.ic_alarm, R.drawable.ic_bulb_offline};


    private ArrayList<CustomTabEntity> mTabEntities = new ArrayList<>();
    private ViewPager mViewPager;
    private CommonTabLayout mTabLayout_2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);
        mViewPager = findViewById(R.id.vp_2);
        mTabLayout_2 = findViewById(R.id.tl_2);


        for (String title : mTitles) {
            mFragments.add(SimpleCardFragment.getInstance("Switch ViewPager " + title));
            mFragments2.add(SimpleCardFragment.getInstance("Switch Fragment " + title));
        }

        for (int i = 0; i < mTitles.length; i++) {
            mTabEntities.add(new TabEntity(mTitles[i], mIconSelectIds[i], mIconUnselectIds[i]));
        }
        MyPagerAdapter mMyPagerAdapter = new MyPagerAdapter(getSupportFragmentManager());
        mViewPager.setAdapter(mMyPagerAdapter);
        tl_2();
    }


    private void tl_2() {
        mTabLayout_2.setTabData(mTabEntities);
        mTabLayout_2.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelect(int position) {
                mViewPager.setCurrentItem(position);
            }

            @Override
            public void onTabReselect(int position) {
            }
        });

        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                mTabLayout_2.setCurrentTab(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        mViewPager.setCurrentItem(1);
    }

    private class MyPagerAdapter extends FragmentPagerAdapter {
        public MyPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public int getCount() {
            return mFragments.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mTitles[position];
        }

        @Override
        public Fragment getItem(int position) {
            return mFragments.get(position);
        }
    }

}
