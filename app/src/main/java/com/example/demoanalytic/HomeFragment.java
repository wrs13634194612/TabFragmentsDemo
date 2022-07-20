package com.example.demoanalytic;

import android.content.Context;
import android.os.Bundle;
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

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;


public class HomeFragment extends Fragment {
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
        loadJsonFromAssests();
        MyPagerAdapter mMyPagerAdapter = new MyPagerAdapter(getChildFragmentManager());
        mViewPager.setAdapter(mMyPagerAdapter);
        tl_2();
        return view;
    }

    public void loadJsonFromAssests() {
        String json = null;
        try {
            InputStream inputStream = mContext.getAssets().open("update.json");
            int size = inputStream.available();
            byte[] buffer = new byte[size];
            inputStream.read(buffer);
            inputStream.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        Gson gson = new Gson();
        ClodAirBean mClodAirBean = gson.fromJson(json, ClodAirBean.class);
        Log.e("TAG", "loadJsonFromAssests:" + mClodAirBean);
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
        /*
        这个问题已经解决了  接下来 做什么呢  顶部的数量 已经对应上了  接下来是处理   功能问题
        比如   动态按键 顺序  其他的有可能是跳着来  key 那么
        固定  不同品牌的空调
        * */
        /*
        我怎么设置隐藏呢   必须使用key-value  对应才行
        1 空调
        2 电视
        3 机顶盒
        4 DVD
        5 电风扇
        6 空气净化器
        7 iptv
        8 投影仪
        9 功放
        10 热水器
        11 灯泡
        12 插座
        13 扫地机
        14 加湿器
        15 台灯
        我存东西  是根据id存的    每一个id  对应每一个设备
        比如1是空调  对应空调的对象  比如空调id  空调名称  选中图片  未选中图片
        * */
        /*map2 = new HashMap<>();
        allGroups = new ArrayList<>();*/
        ///     map2.put(allGroups.get(position).getKeyIndex(), allGroups.get(position));
        //      public RcDeviceBean(int deviceId, String deviceName, int deviceIcon, int deviceIconCheck) {
        /*
         private String[] mTitles = {"空调", "风扇", "电视机", "机顶盒", "热水器", "灯泡","IPTV"};
            private int[] mIconUnselectIds = {R.mipmap.rc_iptving, R.mipmap.rc_cold_airing, R.mipmap.rc_faning,
            R.mipmap.rc_tving, R.mipmap.rc_top_boxing, R.mipmap.rc_watering, R.mipmap.rc_lighting};
            private int[] mIconSelectIds = {R.mipmap.rc_iptved, R.mipmap.rc_cold_aired, R.mipmap.rc_faned,
            R.mipmap.rc_tved, R.mipmap.rc_top_boxed, R.mipmap.rc_watered, R.mipmap.rc_lighted};
        * */
         /*     map2.put("1", new RcDeviceBean(1, "空调", R.mipmap.rc_cold_airing, R.mipmap.rc_cold_aired));
                map2.put("2", new RcDeviceBean(2, "电视", R.mipmap.rc_tving, R.mipmap.rc_tved));
                map2.put("3", new RcDeviceBean(3, "机顶盒", R.mipmap.rc_top_boxing, R.mipmap.rc_top_boxed));
                map2.put("4", new RcDeviceBean(4, "DVD", R.mipmap.rc_cold_airing, R.mipmap.rc_cold_aired));
                map2.put("5", new RcDeviceBean(5, "电风扇", R.mipmap.rc_faning, R.mipmap.rc_faned));
                map2.put("6", new RcDeviceBean(6, "空气净化器", R.mipmap.rc_cold_airing, R.mipmap.rc_cold_aired));
                map2.put("7", new RcDeviceBean(7, "iptv", R.mipmap.rc_iptving, R.mipmap.rc_iptved));
                map2.put("8", new RcDeviceBean(8, "投影仪", R.mipmap.rc_cold_airing, R.mipmap.rc_cold_aired));
                map2.put("9", new RcDeviceBean(9, "功放", R.mipmap.rc_cold_airing, R.mipmap.rc_cold_aired));
                map2.put("10", new RcDeviceBean(10, "热水器", R.mipmap.rc_watering, R.mipmap.rc_watered));
                map2.put("11", new RcDeviceBean(11, "灯泡", R.mipmap.rc_lighting, R.mipmap.rc_lighted));
                map2.put("12", new RcDeviceBean(12, "插座", R.mipmap.rc_cold_airing, R.mipmap.rc_cold_aired));
                map2.put("13", new RcDeviceBean(13, "扫地机", R.mipmap.rc_cold_airing, R.mipmap.rc_cold_aired));
                map2.put("14", new RcDeviceBean(14, "加湿器", R.mipmap.rc_cold_airing, R.mipmap.rc_cold_aired));
                map2.put("15", new RcDeviceBean(15, "台灯", R.mipmap.rc_lighting, R.mipmap.rc_lighted));
        */
        /*
         * map数据已经搞定  接下来   是对比  看看 哪些值需要传递过去
         * 遍历hash   然后把hash  存储成list  然后
         * 一个list  就把对象存起来就行   然后遍历list   然后传递传图片
         * 把东西存一下
         *  mClodAirBean.getData().getModes().get(0).getEntity().getDeviceId()
         * 两个  三个
         * 假如有两个的话   我应该存成一个list  然后遍历
         *
         * */
  /*      if (!allGroups.isEmpty()) {
            allGroups.clear();
        }
        Iterator<String> iter = map2.keySet().iterator();
        String ids = "1";
        String ids_2 = "2";

        // mClodAirBean.getData().getModes().get(0).getEntity().getDeviceId();
        System.out.println(ids);
        while (iter.hasNext()) {
            String key = iter.next();
            if (TextUtils.equals(key, ids) || TextUtils.equals(key, ids_2)) {
                allGroups.add(map2.get(key));
            }
        }
        System.out.println(allGroups);
        for (int position = 0; position < allGroups.size(); position++) {
            mFragments.add(SimpleCardFragment.getInstance(allGroups.get(position).getDeviceName()));
            mTabEntities.add(new TabEntity(allGroups.get(position).getDeviceName()
                    , allGroups.get(position).getDeviceIconCheck()
                    , allGroups.get(position).getDeviceIcon()

            ));
        }
       */
     /*   for (String title : mTitles) {
        }
        for (int i = 0; i < mTitles.length; i++) {
            ///      public TabEntity(String title, int selectedIcon, int unSelectedIcon) {
        }

        接下来  试试  如果存在两个json  应该怎么处理  这个0 不能写死  应该遍历成一个list


        比如我  我有两个\

        用1  2 3  4   5   做判啊   反正 最多判断6个
       */
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

        mViewPager.setCurrentItem(0);
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
