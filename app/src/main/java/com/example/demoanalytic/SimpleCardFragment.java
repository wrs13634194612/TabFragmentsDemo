package com.example.demoanalytic;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import com.google.gson.Gson;
import com.lzy.okgo.OkGo;

import java.util.ArrayList;
import java.util.List;


@SuppressLint("ValidFragment")
public class SimpleCardFragment extends Fragment {
    private Context mContext;
    private Button btn_air0, btn_air1, btn_air2,
            btn_air3, btn_air4, btn_air5,
            btn_air6, btn_air7, btn_air8,
            btn_air9, btn_air10, btn_air11,
            btn_air12, btn_air13, btn_air14,
            btn_air15, btn_air16, btn_air17, btn_air18;
    private LinearLayout ll_air2, ll_air3, ll_air4, ll_air5, ll_air6;
    private Button btn_countdown_status, btn_countdown_time;
    private Button btn_time_end_status, btn_time_end,
            btn_time_start_status, btn_time_start, btn_time_week;
    private Button btn_set_time,btn_set_delay;
    private String url = "http://tt.mindordz.com:6361/api/hac/rcControl";

    private List<ClodAirBean.DataBean.ModesBean.ListBean> keyboardList = new ArrayList<>();
    private ClodAirBean.DataBean.ModesBean.ListBean mListBean;
    private int timeId=0;
    private int delayId=0;
    private ClodAirBean.DataBean.ModesBean.DelaysBean mDelaysBean;
    private ClodAirBean.DataBean.ModesBean.EntityBean mEntityBean;
    private ClodAirBean.DataBean.ModesBean.TimingsBean mTimingsBean;

    public static SimpleCardFragment getInstance(ClodAirBean.DataBean.ModesBean mModesBean) {
        SimpleCardFragment sf = new SimpleCardFragment();
        if (!sf.keyboardList.isEmpty()) {
            sf.keyboardList.clear();
        }
        sf.keyboardList.addAll(mModesBean.getList());
        sf.mDelaysBean = mModesBean.getDelays();
        sf.mEntityBean = mModesBean.getEntity();
        sf.mTimingsBean = mModesBean.getTimings();
        return sf;
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.mContext = context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fr_simple_card, null);
        btn_air0 = view.findViewById(R.id.btn_air0);
        btn_air1 = view.findViewById(R.id.btn_air1);
        btn_air2 = view.findViewById(R.id.btn_air2);
        btn_air3 = view.findViewById(R.id.btn_air3);
        btn_air4 = view.findViewById(R.id.btn_air4);
        btn_air5 = view.findViewById(R.id.btn_air5);
        btn_air6 = view.findViewById(R.id.btn_air6);
        btn_air7 = view.findViewById(R.id.btn_air7);
        btn_air8 = view.findViewById(R.id.btn_air8);
        btn_air9 = view.findViewById(R.id.btn_air9);
        btn_air10 = view.findViewById(R.id.btn_air10);
        btn_air11 = view.findViewById(R.id.btn_air11);
        btn_air12 = view.findViewById(R.id.btn_air12);
        btn_air13 = view.findViewById(R.id.btn_air13);
        btn_air14 = view.findViewById(R.id.btn_air14);
        btn_air15 = view.findViewById(R.id.btn_air15);
        btn_air16 = view.findViewById(R.id.btn_air16);
        btn_air17 = view.findViewById(R.id.btn_air17);
        btn_air18 = view.findViewById(R.id.btn_air18);
        ll_air2 = view.findViewById(R.id.ll_air2);
        ll_air3 = view.findViewById(R.id.ll_air3);
        ll_air4 = view.findViewById(R.id.ll_air4);
        ll_air5 = view.findViewById(R.id.ll_air5);
        ll_air6 = view.findViewById(R.id.ll_air6);
        btn_countdown_status = view.findViewById(R.id.btn_countdown_status);
        btn_countdown_time = view.findViewById(R.id.btn_countdown_time);
        btn_time_end_status = view.findViewById(R.id.btn_time_end_status);
        btn_time_end = view.findViewById(R.id.btn_time_end);
        btn_time_start_status = view.findViewById(R.id.btn_time_start_status);
        btn_time_start = view.findViewById(R.id.btn_time_start);
        btn_time_week = view.findViewById(R.id.btn_time_week);
        btn_set_time = view.findViewById(R.id.btn_set_time);
        btn_set_delay = view.findViewById(R.id.btn_set_delay);
        return view;
    }

    /*
    接下来要做的  就是从上个界面传递值给他  切记   搞定

    接下来要做的 是定时 的东西  先把页面展示给做了

    * */

    private void getData(int rcId, int deviceId, String modeId, String modeHead, String keyName, int keyIndex) {
        RcControlBean mRcControlBean = new RcControlBean();
        mRcControlBean.setRcId(rcId);
        mRcControlBean.setDeviceId(deviceId);
        mRcControlBean.setModeId(modeId);
        mRcControlBean.setModeHead(modeHead);
        mRcControlBean.setKeyName(keyName);
        mRcControlBean.setKeyIndex(keyIndex);
        Gson gson = new Gson();
        String jsonString = gson.toJson(mRcControlBean);
        OkGo.<String>post(url)
                .upJson(jsonString)
                .execute(new com.lzy.okgo.callback.StringCallback() {
                    @Override
                    public void onSuccess(com.lzy.okgo.model.Response<String> response) {
                        Log.e("TAG", "onSuccess:" + response.body());
                    }

                    @Override
                    public void onError(com.lzy.okgo.model.Response<String> response) {
                        super.onError(response);
                        Log.e("TAG", "onError:" + response);
                    }
                });
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        System.out.println(keyboardList);

        /*
         * gson 解析    然后 拿到数据
         * 拿到了底部数据  接下来 做什么呢    比如有些东西显示 有些东西隐藏
         * 循环遍历  如果 存在  那么
         * 我怎么知道哪些东西 显示true  哪些东西 显示false呢
         * 查询 当前按钮显示还是隐藏 呢 也许都不虚
         * 接下来做什么呢  家人们
         * 对  接下来 做定时和倒计时的东西
         * 最起码  把数据  传递过来
         * */

        /*
        * 我忽然想起来  他的index  并不是固定的   也就是说  顺序其实不重要
        让他在这里显示就行  到时候  出了问题再调
        * 接下来 做什么 点击事件  每一个按钮都必须能控制
        * 随便点击一个按钮  拿到item数据 点击0 显示的是0
        显示   把东西 显示 过来
        * 显示  把东西  显示出来  包括  定时和倒计时
        * * */

        /*
        * 接下来做什么呢   网络请求   把东西集成进去   然后
        把代码集成进去  然后做网络请求  对了  接下来  是试验
        * 学习  遥控的功能
        * 一定要熟悉安卓的遥控的功能才能做
        * 对  接下来 学习遥控功能
        * 接下来 要做的就是看视频
        * 看明白了   接下来  应该做什么呢  忘记 了  接下来  理论上来讲 我应该睡一觉把 代码集成进来  mqtt  布局 fragment
        * 先把布局的事情 搞定  接下来  要做一下有趣的事情
        * 睡一会  然后把数据弄进来    看看接口  把数据 传递过来  把整个流程先走通
        * 对 想起来了  先看代码 逻辑   接下来  要做的是把布局合成到新的页面
        *
        *
        * * */

        /*
              android:id="@+id/btn_countdown_status"
                            android:id="@+id/btn_countdown_time"
                                    android:id="@+id/btn_time_end_status"
                                    android:id="@+id/btn_time_end"
                                    android:id="@+id/btn_time_start_status"
                                    android:id="@+id/btn_time_start"

        * */

        /*
        * 接下来  去看原来代码  的遥控 新增的接口  看看是调用的哪个接口
        * */

        if (mDelaysBean != null) {
            btn_countdown_status.setText(String.valueOf(mDelaysBean.getSwitchStatus()));
            btn_countdown_time.setText(mDelaysBean.getExecuteTime());
        }
        if (mTimingsBean != null) {
            btn_time_end_status.setText(String.valueOf(mTimingsBean.getCloseIf()));
            btn_time_end.setText(mTimingsBean.getEndTime());
            btn_time_start_status.setText(String.valueOf(mTimingsBean.getOpenIf()));
            btn_time_start.setText(mTimingsBean.getStartTime());
            btn_time_week.setText(mTimingsBean.getWeek());
        }
        for (int position = 0; position < keyboardList.size(); position++) {
            isFunViewTwo(keyboardList.get(position).getKeyName());
        }

       /* allGroups = new ArrayList<>();
        map2 = new HashMap<>();
        allGroups.add(new ColdAirBean(0, "开", true));
        allGroups.add(new ColdAirBean(1, "关", true));

        allGroups.add(new ColdAirBean(2, "自动", true));
        allGroups.add(new ColdAirBean(3, "制冷", true));
        allGroups.add(new ColdAirBean(4, "除湿", true));
        allGroups.add(new ColdAirBean(5, "送风", true));
        allGroups.add(new ColdAirBean(6, "制热", true));

        allGroups.add(new ColdAirBean(7, "温度+", true));
        allGroups.add(new ColdAirBean(8, "温度-", true));
        allGroups.add(new ColdAirBean(9, "风速+", true));
        allGroups.add(new ColdAirBean(10, "风速-", true));
        allGroups.add(new ColdAirBean(11, "上下扫风", true));
        allGroups.add(new ColdAirBean(12, "左右扫风", true));
        for (int position = 0; position < allGroups.size(); position++) {
            map2.put(allGroups.get(position).getKeyName(), allGroups.get(position));
        }
        System.out.println(map2);
        Iterator<String> iter = map2.keySet().iterator();
        while (iter.hasNext()) {
            String key = iter.next();
            isFunView(key, map2.get(key).isView(), map2.get(key).getKeyName());
        }
        btn_air0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ColdAirBean mColdAirBean = map2.get(0);
                System.out.println(mColdAirBean);
            }
        });*/


        btn_air0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for (int position = 0; position < keyboardList.size(); position++) {
                    if (TextUtils.equals(keyboardList.get(position).getKeyName(), "开")) {
                        mListBean = keyboardList.get(position);
                    }
                }
                Log.e("TAG", "click 0data:" + mListBean.getKeyName() + "\t" + mListBean.getKeyIndex());
                // 开     private void getData(int rcId,int deviceId,String modeId,String modeHead,String keyName,int keyIndex) {
                //                getData();
                // 我接下来 做什么呢  家人们  拿到我需要的值
                int keyIndex = mListBean.getKeyIndex();
                String keyName = mListBean.getKeyName();
                String modeHead = mEntityBean.getModeHead();
                String modeId = mEntityBean.getModeId();
                String deviceId = mEntityBean.getDeviceId();
                System.out.println(deviceId + modeId + modeHead + keyName + keyIndex);
                int rcId = mEntityBean.getInfraredBinId();
                getData(rcId, Integer.parseInt(deviceId), modeId, modeHead, keyName, keyIndex);


            }
        });

        btn_air1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for (int position = 0; position < keyboardList.size(); position++) {
                    if (TextUtils.equals(keyboardList.get(position).getKeyName(), "关")) {
                        mListBean = keyboardList.get(position);
                    }
                }
                Log.e("TAG", "click 1data:" + mListBean.getKeyName() + "\t" + mListBean.getKeyIndex());
                //关闭
                int keyIndex = mListBean.getKeyIndex();
                String keyName = mListBean.getKeyName();
                String modeHead = mEntityBean.getModeHead();
                String modeId = mEntityBean.getModeId();
                String deviceId = mEntityBean.getDeviceId();
                System.out.println(deviceId + modeId + modeHead + keyName + keyIndex);
                int rcId = mEntityBean.getInfraredBinId();
                getData(rcId, Integer.parseInt(deviceId), modeId, modeHead, keyName, keyIndex);

            }
        });

        btn_air2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for (int position = 0; position < keyboardList.size(); position++) {
                    if (TextUtils.equals(keyboardList.get(position).getKeyName(), "自动")) {
                        mListBean = keyboardList.get(position);
                    }
                }
                Log.e("TAG", "click2 data:" + mListBean.getKeyName() + "\t" + mListBean.getKeyIndex());
                int keyIndex = mListBean.getKeyIndex();
                String keyName = mListBean.getKeyName();
                String modeHead = mEntityBean.getModeHead();
                String modeId = mEntityBean.getModeId();
                String deviceId = mEntityBean.getDeviceId();
                System.out.println(deviceId + modeId + modeHead + keyName + keyIndex);
                int rcId = mEntityBean.getInfraredBinId();
                getData(rcId, Integer.parseInt(deviceId), modeId, modeHead, keyName, keyIndex);
            }
        });

        btn_air3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for (int position = 0; position < keyboardList.size(); position++) {
                    if (TextUtils.equals(keyboardList.get(position).getKeyName(), "制冷")) {
                        mListBean = keyboardList.get(position);
                    }
                }
                Log.e("TAG", "click 3data:" + mListBean.getKeyName() + "\t" + mListBean.getKeyIndex());
                int keyIndex = mListBean.getKeyIndex();
                String keyName = mListBean.getKeyName();
                String modeHead = mEntityBean.getModeHead();
                String modeId = mEntityBean.getModeId();
                String deviceId = mEntityBean.getDeviceId();
                System.out.println(deviceId + modeId + modeHead + keyName + keyIndex);
                int rcId = mEntityBean.getInfraredBinId();
                getData(rcId, Integer.parseInt(deviceId), modeId, modeHead, keyName, keyIndex);
            }
        });

        btn_air4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for (int position = 0; position < keyboardList.size(); position++) {
                    if (TextUtils.equals(keyboardList.get(position).getKeyName(), "除湿")) {
                        mListBean = keyboardList.get(position);
                    }
                }
                Log.e("TAG", "click 4data:" + mListBean.getKeyName() + "\t" + mListBean.getKeyIndex());
                int keyIndex = mListBean.getKeyIndex();
                String keyName = mListBean.getKeyName();
                String modeHead = mEntityBean.getModeHead();
                String modeId = mEntityBean.getModeId();
                String deviceId = mEntityBean.getDeviceId();
                System.out.println(deviceId + modeId + modeHead + keyName + keyIndex);
                int rcId = mEntityBean.getInfraredBinId();
                getData(rcId, Integer.parseInt(deviceId), modeId, modeHead, keyName, keyIndex);
            }
        });

        btn_air5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for (int position = 0; position < keyboardList.size(); position++) {
                    if (TextUtils.equals(keyboardList.get(position).getKeyName(), "送风")) {
                        mListBean = keyboardList.get(position);
                    }
                }
                Log.e("TAG", "click5 data:" + mListBean.getKeyName() + "\t" + mListBean.getKeyIndex());
                int keyIndex = mListBean.getKeyIndex();
                String keyName = mListBean.getKeyName();
                String modeHead = mEntityBean.getModeHead();
                String modeId = mEntityBean.getModeId();
                String deviceId = mEntityBean.getDeviceId();
                System.out.println(deviceId + modeId + modeHead + keyName + keyIndex);
                int rcId = mEntityBean.getInfraredBinId();
                getData(rcId, Integer.parseInt(deviceId), modeId, modeHead, keyName, keyIndex);
            }
        });

        btn_air6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for (int position = 0; position < keyboardList.size(); position++) {
                    if (TextUtils.equals(keyboardList.get(position).getKeyName(), "制热")) {
                        mListBean = keyboardList.get(position);
                    }
                }
                Log.e("TAG", "click6 data:" + mListBean.getKeyName() + "\t" + mListBean.getKeyIndex());
                int keyIndex = mListBean.getKeyIndex();
                String keyName = mListBean.getKeyName();
                String modeHead = mEntityBean.getModeHead();
                String modeId = mEntityBean.getModeId();
                String deviceId = mEntityBean.getDeviceId();
                System.out.println(deviceId + modeId + modeHead + keyName + keyIndex);
                int rcId = mEntityBean.getInfraredBinId();
                getData(rcId, Integer.parseInt(deviceId), modeId, modeHead, keyName, keyIndex);
            }
        });

        btn_air7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for (int position = 0; position < keyboardList.size(); position++) {
                    if (TextUtils.equals(keyboardList.get(position).getKeyName(), "温度+")) {
                        mListBean = keyboardList.get(position);
                    }
                }
                Log.e("TAG", "click 7data:" + mListBean.getKeyName() + "\t" + mListBean.getKeyIndex());
                int keyIndex = mListBean.getKeyIndex();
                String keyName = mListBean.getKeyName();
                String modeHead = mEntityBean.getModeHead();
                String modeId = mEntityBean.getModeId();
                String deviceId = mEntityBean.getDeviceId();
                System.out.println(deviceId + modeId + modeHead + keyName + keyIndex);
                int rcId = mEntityBean.getInfraredBinId();
                getData(rcId, Integer.parseInt(deviceId), modeId, modeHead, keyName, keyIndex);
            }
        });

        btn_air8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for (int position = 0; position < keyboardList.size(); position++) {
                    if (TextUtils.equals(keyboardList.get(position).getKeyName(), "温度-")) {
                        mListBean = keyboardList.get(position);
                    }
                }
                Log.e("TAG", "click8 data:" + mListBean.getKeyName() + "\t" + mListBean.getKeyIndex());
                int keyIndex = mListBean.getKeyIndex();
                String keyName = mListBean.getKeyName();
                String modeHead = mEntityBean.getModeHead();
                String modeId = mEntityBean.getModeId();
                String deviceId = mEntityBean.getDeviceId();
                System.out.println(deviceId + modeId + modeHead + keyName + keyIndex);
                int rcId = mEntityBean.getInfraredBinId();
                getData(rcId, Integer.parseInt(deviceId), modeId, modeHead, keyName, keyIndex);
            }
        });

        btn_air9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for (int position = 0; position < keyboardList.size(); position++) {
                    if (TextUtils.equals(keyboardList.get(position).getKeyName(), "风速+")) {
                        mListBean = keyboardList.get(position);
                    }
                }
                Log.e("TAG", "click 9data:" + mListBean.getKeyName() + "\t" + mListBean.getKeyIndex());
                int keyIndex = mListBean.getKeyIndex();
                String keyName = mListBean.getKeyName();
                String modeHead = mEntityBean.getModeHead();
                String modeId = mEntityBean.getModeId();
                String deviceId = mEntityBean.getDeviceId();
                System.out.println(deviceId + modeId + modeHead + keyName + keyIndex);
                int rcId = mEntityBean.getInfraredBinId();
                getData(rcId, Integer.parseInt(deviceId), modeId, modeHead, keyName, keyIndex);
            }
        });
        btn_air10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for (int position = 0; position < keyboardList.size(); position++) {
                    if (TextUtils.equals(keyboardList.get(position).getKeyName(), "风速-")) {
                        mListBean = keyboardList.get(position);
                    }
                }
                Log.e("TAG", "click 10data:" + mListBean.getKeyName() + "\t" + mListBean.getKeyIndex());
                int keyIndex = mListBean.getKeyIndex();
                String keyName = mListBean.getKeyName();
                String modeHead = mEntityBean.getModeHead();
                String modeId = mEntityBean.getModeId();
                String deviceId = mEntityBean.getDeviceId();
                System.out.println(deviceId + modeId + modeHead + keyName + keyIndex);
                int rcId = mEntityBean.getInfraredBinId();
                getData(rcId, Integer.parseInt(deviceId), modeId, modeHead, keyName, keyIndex);
            }
        });
        btn_air11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for (int position = 0; position < keyboardList.size(); position++) {
                    if (TextUtils.equals(keyboardList.get(position).getKeyName(), "上下扫风")) {
                        mListBean = keyboardList.get(position);
                    }
                }
                Log.e("TAG", "click11 data:" + mListBean.getKeyName() + "\t" + mListBean.getKeyIndex());
                int keyIndex = mListBean.getKeyIndex();
                String keyName = mListBean.getKeyName();
                String modeHead = mEntityBean.getModeHead();
                String modeId = mEntityBean.getModeId();
                String deviceId = mEntityBean.getDeviceId();
                System.out.println(deviceId + modeId + modeHead + keyName + keyIndex);
                int rcId = mEntityBean.getInfraredBinId();
                getData(rcId, Integer.parseInt(deviceId), modeId, modeHead, keyName, keyIndex);
            }
        });
        btn_air12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for (int position = 0; position < keyboardList.size(); position++) {
                    if (TextUtils.equals(keyboardList.get(position).getKeyName(), "左右扫风")) {
                        mListBean = keyboardList.get(position);
                    }
                }
                Log.e("TAG", "click 12data:" + mListBean.getKeyName() + "\t" + mListBean.getKeyIndex());
                int keyIndex = mListBean.getKeyIndex();
                String keyName = mListBean.getKeyName();
                String modeHead = mEntityBean.getModeHead();
                String modeId = mEntityBean.getModeId();
                String deviceId = mEntityBean.getDeviceId();
                System.out.println(deviceId + modeId + modeHead + keyName + keyIndex);
                int rcId = mEntityBean.getInfraredBinId();
                getData(rcId, Integer.parseInt(deviceId), modeId, modeHead, keyName, keyIndex);
            }
        });
        btn_air13.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for (int position = 0; position < keyboardList.size(); position++) {
                    if (TextUtils.equals(keyboardList.get(position).getKeyName(), "摆风")) {
                        mListBean = keyboardList.get(position);
                    }
                }
                Log.e("TAG", "click 13data:" + mListBean.getKeyName() + "\t" + mListBean.getKeyIndex());
                int keyIndex = mListBean.getKeyIndex();
                String keyName = mListBean.getKeyName();
                String modeHead = mEntityBean.getModeHead();
                String modeId = mEntityBean.getModeId();
                String deviceId = mEntityBean.getDeviceId();
                System.out.println(deviceId + modeId + modeHead + keyName + keyIndex);
                int rcId = mEntityBean.getInfraredBinId();
                getData(rcId, Integer.parseInt(deviceId), modeId, modeHead, keyName, keyIndex);

            }
        });
        btn_air14.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for (int position = 0; position < keyboardList.size(); position++) {
                    if (TextUtils.equals(keyboardList.get(position).getKeyName(), "风速")) {
                        mListBean = keyboardList.get(position);
                    }
                }
                Log.e("TAG", "click14 data:" + mListBean.getKeyName() + "\t" + mListBean.getKeyIndex());
                int keyIndex = mListBean.getKeyIndex();
                String keyName = mListBean.getKeyName();
                String modeHead = mEntityBean.getModeHead();
                String modeId = mEntityBean.getModeId();
                String deviceId = mEntityBean.getDeviceId();
                System.out.println(deviceId + modeId + modeHead + keyName + keyIndex);
                int rcId = mEntityBean.getInfraredBinId();
                getData(rcId, Integer.parseInt(deviceId), modeId, modeHead, keyName, keyIndex);
            }
        });
        btn_air15.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for (int position = 0; position < keyboardList.size(); position++) {
                    if (TextUtils.equals(keyboardList.get(position).getKeyName(), "风类")) {
                        mListBean = keyboardList.get(position);
                    }
                }
                Log.e("TAG", "click 15data:" + mListBean.getKeyName() + "\t" + mListBean.getKeyIndex());
                int keyIndex = mListBean.getKeyIndex();
                String keyName = mListBean.getKeyName();
                String modeHead = mEntityBean.getModeHead();
                String modeId = mEntityBean.getModeId();
                String deviceId = mEntityBean.getDeviceId();
                System.out.println(deviceId + modeId + modeHead + keyName + keyIndex);
                int rcId = mEntityBean.getInfraredBinId();
                getData(rcId, Integer.parseInt(deviceId), modeId, modeHead, keyName, keyIndex);
            }
        });
        btn_air16.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for (int position = 0; position < keyboardList.size(); position++) {
                    if (TextUtils.equals(keyboardList.get(position).getKeyName(), "静音")) {
                        mListBean = keyboardList.get(position);
                    }
                }
                Log.e("TAG", "click 16data:" + mListBean.getKeyName() + "\t" + mListBean.getKeyIndex());
                int keyIndex = mListBean.getKeyIndex();
                String keyName = mListBean.getKeyName();
                String modeHead = mEntityBean.getModeHead();
                String modeId = mEntityBean.getModeId();
                String deviceId = mEntityBean.getDeviceId();
                System.out.println(deviceId + modeId + modeHead + keyName + keyIndex);
                int rcId = mEntityBean.getInfraredBinId();
                getData(rcId, Integer.parseInt(deviceId), modeId, modeHead, keyName, keyIndex);
            }
        });
        btn_air17.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for (int position = 0; position < keyboardList.size(); position++) {
                    if (TextUtils.equals(keyboardList.get(position).getKeyName(), "备份按钮一")) {
                        mListBean = keyboardList.get(position);
                    }
                }
                Log.e("TAG", "click 17data:" + mListBean.getKeyName() + "\t" + mListBean.getKeyIndex());
            }
        });
        btn_air18.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for (int position = 0; position < keyboardList.size(); position++) {
                    if (TextUtils.equals(keyboardList.get(position).getKeyName(), "备份按钮二")) {
                        mListBean = keyboardList.get(position);
                    }
                }
                Log.e("TAG", "click 18data:" + mListBean.getKeyName() + "\t" + mListBean.getKeyIndex());
            }
        });

        btn_set_time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*
                * 定时  需要 把entity传递过来
                                 val intent = Intent(context, PlugUpdateNewActivity::class.java)
                        intent.putExtra("equipmentId",it.equipmentId)
                        intent.putExtra("productId",it.productId)
                        context?.startActivity(intent)
                        *
                        *
                    mScheduleBean.setBrandId("1");
                    mScheduleBean.setModeId("6a56dfd96d1657882000851");
                    mScheduleBean.setProductId("zcz004");
                    mScheduleBean.setEquipmentId("zcz004100411");
                * * */
                if (mTimingsBean!=null){
                    timeId=mTimingsBean.getId();
                }
                Intent intent = new Intent(mContext,TimeActivity.class);
                intent.putExtra("brandId",mEntityBean.getBrandId());
                intent.putExtra("modeId",mEntityBean.getModeId());
                intent.putExtra("productId",mEntityBean.getProductId());
                intent.putExtra("equipmentId",mEntityBean.getEquipmentId());
                intent.putExtra("timeId",timeId);
                startActivity(intent);
            }
        });

        btn_set_delay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*
                * 定时  需要 把entity传递过来
                                 val intent = Intent(context, PlugUpdateNewActivity::class.java)
                        intent.putExtra("equipmentId",it.equipmentId)
                        intent.putExtra("productId",it.productId)
                        context?.startActivity(intent)
                        *
                        *
                    mScheduleBean.setBrandId("1");
                    mScheduleBean.setModeId("6a56dfd96d1657882000851");
                    mScheduleBean.setProductId("zcz004");
                    mScheduleBean.setEquipmentId("zcz004100411");
                * * */
                if (mDelaysBean!=null){
                    delayId=mDelaysBean.getId();
                }
                Intent intent = new Intent(mContext,DelayActivity.class);
                intent.putExtra("brandId",mEntityBean.getBrandId());
                intent.putExtra("modeId",mEntityBean.getModeId());
                intent.putExtra("productId",mEntityBean.getProductId());
                intent.putExtra("equipmentId",mEntityBean.getEquipmentId());
                intent.putExtra("delayId",delayId);
                startActivity(intent);
            }
        });


    }


    private void isFunViewTwo(String keyName) {
        if (TextUtils.equals(keyName, "开")) {
            btn_air0.setText(keyName);
            btn_air0.setVisibility(View.VISIBLE);
        } else if (TextUtils.equals(keyName, "关")) {
            btn_air1.setText(keyName);
            btn_air1.setVisibility(View.VISIBLE);
        } else if (TextUtils.equals(keyName, "自动")) {
            btn_air2.setText(keyName);
            btn_air2.setVisibility(View.VISIBLE);
            ll_air2.setVisibility(View.VISIBLE);
        } else if (TextUtils.equals(keyName, "制冷")) {
            btn_air3.setText(keyName);
            btn_air3.setVisibility(View.VISIBLE);
            ll_air3.setVisibility(View.VISIBLE);
        } else if (TextUtils.equals(keyName, "除湿")) {
            btn_air4.setText(keyName);
            btn_air4.setVisibility(View.VISIBLE);
            ll_air4.setVisibility(View.VISIBLE);
        } else if (TextUtils.equals(keyName, "送风")) {
            btn_air5.setText(keyName);
            btn_air5.setVisibility(View.VISIBLE);
            ll_air5.setVisibility(View.VISIBLE);
        } else if (TextUtils.equals(keyName, "制热")) {
            btn_air6.setText(keyName);
            btn_air6.setVisibility(View.VISIBLE);
            ll_air6.setVisibility(View.VISIBLE);
        } else if (TextUtils.equals(keyName, "温度+")) {
            btn_air7.setText(keyName);
            btn_air7.setVisibility(View.VISIBLE);
        } else if (TextUtils.equals(keyName, "温度-")) {
            btn_air8.setText(keyName);
            btn_air8.setVisibility(View.VISIBLE);
        } else if (TextUtils.equals(keyName, "风速+")) {
            btn_air9.setVisibility(View.VISIBLE);
            btn_air9.setText(keyName);
        } else if (TextUtils.equals(keyName, "风速-")) {
            btn_air10.setVisibility(View.VISIBLE);
            btn_air10.setText(keyName);
        } else if (TextUtils.equals(keyName, "上下扫风")) {
            btn_air11.setVisibility(View.VISIBLE);
            btn_air11.setText(keyName);
        } else if (TextUtils.equals(keyName, "左右扫风")) {
            btn_air12.setVisibility(View.VISIBLE);
            btn_air12.setText(keyName);
        } else if (TextUtils.equals(keyName, "摆风")) {
            btn_air13.setText(keyName);
            btn_air13.setVisibility(View.VISIBLE);
        } else if (TextUtils.equals(keyName, "风速")) {
            btn_air14.setText(keyName);
            btn_air14.setVisibility(View.VISIBLE);
        } else if (TextUtils.equals(keyName, "风类")) {
            btn_air15.setText(keyName);
            btn_air15.setVisibility(View.VISIBLE);
        } else if (TextUtils.equals(keyName, "静音")) {
            btn_air16.setText(keyName);
            btn_air16.setVisibility(View.VISIBLE);
        } else if (TextUtils.equals(keyName, "备份按钮一")) {
            btn_air17.setText(keyName);
            btn_air17.setVisibility(View.VISIBLE);
        } else if (TextUtils.equals(keyName, "备份按钮二")) {
            btn_air18.setText(keyName);
            btn_air18.setVisibility(View.VISIBLE);
        }
    }

}