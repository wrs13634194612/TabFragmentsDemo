package com.example.demoanalytic;


import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;


@SuppressLint("ValidFragment")
public class TestFragment extends Fragment {
    private String mTitle;
    private OnlineDeviceListAdapter mAdapter;
    private List<String> mDevices;
    private Context mContext;
    private RecyclerView gv_devices;

    public static TestFragment getInstance(String title) {
        TestFragment sf = new TestFragment();
        sf.mTitle = title;
        return sf;
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.mContext = context;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fr_simple_card, null);
        gv_devices = view.findViewById(R.id.rv_online_devices);
        mDevices = new ArrayList<>();
        mDevices.add("张飞");
        mDevices.add("赵云");
        mDevices.add("魏延");
        mDevices.add("关羽");
        mDevices.add("韩红");
        mDevices.add("刘欢");
        mDevices.add("马飞");
        mDevices.add("孙楠");
        mDevices.add("吴军");
        mDevices.add("钱三");
        mDevices.add("李素");
        mDevices.add("周武");
        mDevices.add("郑寒");
        mDevices.add("王军");
        mAdapter = new OnlineDeviceListAdapter(mContext, mDevices);
        gv_devices.setNestedScrollingEnabled(false);
        gv_devices.setLayoutManager(new GridLayoutManager(mContext, 3));
        gv_devices.setAdapter(mAdapter);
        return view;
    }
}