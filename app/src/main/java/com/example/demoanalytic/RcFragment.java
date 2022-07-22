package com.example.demoanalytic;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.flyco.tablayout.CommonTabLayout;
import com.flyco.tablayout.listener.CustomTabEntity;
import com.flyco.tablayout.listener.OnTabSelectListener;
import com.google.gson.Gson;
import com.lzy.okgo.OkGo;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;


public class RcFragment extends Fragment {
    private ArrayList<Fragment> mFragments = new ArrayList<>();


    private String[] mTitles = {"空调", "风扇", "电视机", "机顶盒", "热水器", "灯泡", "IPTV"};
    private int[] mIconUnselectIds = {R.mipmap.rc_iptving, R.mipmap.rc_cold_airing, R.mipmap.rc_faning,
            R.mipmap.rc_tving, R.mipmap.rc_top_boxing, R.mipmap.rc_watering, R.mipmap.rc_lighting};
    private int[] mIconSelectIds = {R.mipmap.rc_iptved, R.mipmap.rc_cold_aired, R.mipmap.rc_faned,
            R.mipmap.rc_tved, R.mipmap.rc_top_boxed, R.mipmap.rc_watered, R.mipmap.rc_lighted};

    private ArrayList<CustomTabEntity> mTabEntities = new ArrayList<>();
    private ViewPager mViewPager;
    private CommonTabLayout mTabLayout_2;
    private Context mContext;
    private HashMap<String, RcDeviceBean> map2;
    private List<RcDeviceBean> allGroups;
    private List<ClodAirBean.DataBean.ModesBean> devices;
    private String url = "http://tt.mindordz.com:6361/api/hac/findModeByUserId";


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
        // loadJsonFromAssests();
        getData();

        return view;
    }


    private void getData() {
        OkGo.<String>get(url)
                .params("userId", "minApp125106")
                .params("rooms", "客厅")
                .execute(new com.lzy.okgo.callback.StringCallback() {
                    @Override
                    public void onSuccess(com.lzy.okgo.model.Response<String> response) {
                        //AllBean mAllBean = JSONObject.parseObject(response.body(), AllBean.class);
                        Log.e("TAG", "onSuccess:" + response.body());
                        Message msg = new Message();
                        msg.what = 471;
                        msg.obj = response.body();
                        mHandler.sendMessage(msg);
                    }

                    @Override
                    public void onError(com.lzy.okgo.model.Response<String> response) {
                        super.onError(response);
                        Log.e("TAG", "onError:" + response);
                    }
                });
    }


    private Handler mHandler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(@NonNull Message message) {
            if (message.what == 471) {
                String post = (String) message.obj;
                Gson gson = new Gson();
                ClodAirBean mClodAirBean = gson.fromJson(post, ClodAirBean.class);
                devices = new ArrayList<>();
                if (!devices.isEmpty()) {
                    devices.clear();
                }
                devices.addAll(mClodAirBean.getData().getModes());
                for (int position = 0; position < devices.size(); position++) {
                    mFragments.add(SimpleCardFragment.getInstance(devices.get(position)));
                    mTabEntities.add(new TabEntity(devices.get(position).getEntity().getNick(),
                            devices.get(position).getEntity().getIconed(devices.get(position).getEntity().getDeviceId()),
                            devices.get(position).getEntity().getIconing(devices.get(position).getEntity().getDeviceId())
                    ));
                }

                MyPagerAdapter mMyPagerAdapter = new MyPagerAdapter(getChildFragmentManager());
                mViewPager.setAdapter(mMyPagerAdapter);
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
                mViewPager.setCurrentItem(0);
            }
            return false;
        }
    });




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
