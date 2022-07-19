package com.example.demoanalytic;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.flyco.tablayout.CommonTabLayout;
import com.flyco.tablayout.listener.CustomTabEntity;
import com.flyco.tablayout.listener.OnTabSelectListener;

import java.util.ArrayList;


public class HomeFragment extends Fragment {
    private ArrayList<Fragment> mFragments = new ArrayList<>();
    private ArrayList<Fragment> mFragments2 = new ArrayList<>();
    private String[] mTitles = {"空调", "风扇", "电视机", "机顶盒", "热水器", "灯泡","IPTV"};
    private int[] mIconUnselectIds = {R.mipmap.rc_iptving, R.mipmap.rc_cold_airing, R.mipmap.rc_faning,
            R.mipmap.rc_tving, R.mipmap.rc_top_boxing, R.mipmap.rc_watering, R.mipmap.rc_lighting};
    private int[] mIconSelectIds = {R.mipmap.rc_iptved, R.mipmap.rc_cold_aired, R.mipmap.rc_faned,
            R.mipmap.rc_tved, R.mipmap.rc_top_boxed, R.mipmap.rc_watered, R.mipmap.rc_lighted};

    private ArrayList<CustomTabEntity> mTabEntities = new ArrayList<>();
    private ViewPager mViewPager;
    private CommonTabLayout mTabLayout_2;
    private Context mContext;


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.mContext = context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment1, null);

        mViewPager = view.findViewById(R.id.vp_2);
        mTabLayout_2 = view.findViewById(R.id.tl_2);

        for (String title : mTitles) {
            mFragments.add(SimpleCardFragment.getInstance("Switch ViewPager " + title));
            mFragments2.add(SimpleCardFragment.getInstance("Switch Fragment " + title));
        }
        for (int i = 0; i < mTitles.length; i++) {
            mTabEntities.add(new TabEntity(mTitles[i], mIconSelectIds[i], mIconUnselectIds[i]));
        }
        MyPagerAdapter mMyPagerAdapter = new MyPagerAdapter(getChildFragmentManager());
        mViewPager.setAdapter(mMyPagerAdapter);
        tl_2();
        return view;
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
