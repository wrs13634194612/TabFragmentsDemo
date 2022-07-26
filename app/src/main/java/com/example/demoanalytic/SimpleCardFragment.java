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
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.Iterator;

import com.example.timerdems.CountDownTimerSupport;
import com.example.timerdems.OnCountDownTimerListener;
import com.google.gson.Gson;
import com.lzy.okgo.OkGo;

import java.util.ArrayList;
import java.util.HashMap;
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
    private RelativeLayout ll_air2, ll_air3, ll_air4, ll_air5, ll_air6, ll_time,rl_air_mode;
    private Button btn_countdown_status, btn_countdown_time;
    private Button btn_time_end_status, btn_time_end,
            btn_time_start_status, btn_time_start;
    private Button btn_set_time, btn_set_delay;
    private Button btn_rc_edit; //编辑
    private ImageView iv_rc_open, iv_rc_home; //开关
    private TextView btn_time_week; //因实际布局需要 由原本的button改为textview
    private ImageView mode_icon;
    private TextView mode_fan, mode_tmp;
    private String url = "http://tt.mindordz.com:6361/api/hac/rcControl";
    private List<ClodAirBean.DataBean.ModesBean.ListBean> keyboardList = new ArrayList<>();
    private ClodAirBean.DataBean.ModesBean.ListBean mListBean;
    private int timeId = 0;
    private int delayId = 0;
    private int openInt = 0;
    private ClodAirBean.DataBean.ModesBean.DelaysBean mDelaysBean;
    private ClodAirBean.DataBean.ModesBean.EntityBean mEntityBean;
    private ClodAirBean.DataBean.ModesBean.TimingsBean mTimingsBean;
    private boolean deviceStatus = false; //false 关闭  true打开  默认关闭
    private HashMap<String, HomeRcBean> homeMap;
    private CountDownTimerSupport mTimer;


    public static SimpleCardFragment getInstance(ClodAirBean.DataBean.ModesBean mModesBean) {
        SimpleCardFragment sf = new SimpleCardFragment();
        if (!sf.keyboardList.isEmpty()) {
            sf.keyboardList.clear();
        }
        sf.keyboardList.addAll(mModesBean.getList());
        sf.mDelaysBean = mModesBean.getDelays();
        sf.mEntityBean = mModesBean.getEntity();
        sf.mTimingsBean = mModesBean.getTimings();

        /*
         * 下午重点解决这个问题  居然框架  还存在bug 很烦
         * 这个bug 暂时没法解决  必须使用eventbus才能处理
         * */

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
        ll_time = view.findViewById(R.id.ll_time);
        rl_air_mode = view.findViewById(R.id.rl_air_mode);
        btn_countdown_status = view.findViewById(R.id.btn_countdown_status);
        btn_countdown_time = view.findViewById(R.id.btn_countdown_time);
        btn_time_end_status = view.findViewById(R.id.btn_time_end_status);
        btn_time_end = view.findViewById(R.id.btn_time_end);
        btn_time_start_status = view.findViewById(R.id.btn_time_start_status);
        btn_time_start = view.findViewById(R.id.btn_time_start);
        btn_time_week = view.findViewById(R.id.btn_time_week);
        btn_set_time = view.findViewById(R.id.btn_set_time);
        btn_set_delay = view.findViewById(R.id.btn_set_delay);
        btn_rc_edit = view.findViewById(R.id.btn_rc_edit);
        iv_rc_open = view.findViewById(R.id.iv_rc_open);
        iv_rc_home = view.findViewById(R.id.iv_rc_home);
        mode_icon = view.findViewById(R.id.mode_icon);
        mode_fan = view.findViewById(R.id.mode_fan);
        mode_tmp = view.findViewById(R.id.mode_tmp);
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


        /*
        *     public HomeRcBean(int deviceId, int checkedImg, int checkImg) {
        *         * 1 空调
            3 机顶盒
            5 风扇
            7 iptv
            2 电视
            10 热水器
        * */
        homeMap = new HashMap<>();
        homeMap.put("1", new HomeRcBean(1, R.mipmap.home_rc_airing, R.mipmap.home_rc_air));
        homeMap.put("2", new HomeRcBean(2, R.mipmap.home_rc_tving, R.mipmap.home_rc_tv));
        homeMap.put("3", new HomeRcBean(3, R.mipmap.home_rc_boxing, R.mipmap.home_rc_box));
        homeMap.put("5", new HomeRcBean(5, R.drawable.fan_five, R.drawable.home_select_fan));
        homeMap.put("7", new HomeRcBean(7, R.mipmap.home_rc_iptving, R.mipmap.home_rc_iptv));
        homeMap.put("10", new HomeRcBean(10, R.mipmap.home_rc_watering, R.mipmap.home_rc_water));
        homeMap.put("11", new HomeRcBean(11, R.mipmap.home_rc_lighting, R.mipmap.home_rc_light));

        /*
         * 存起来了  接下来是展示  拿到了 数据   id和code
         * */

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
            // 暂时注释   btn_time_week.setText(mTimingsBean.getWeek());
        }
        for (int position = 0; position < keyboardList.size(); position++) {
            isFunViewTwo(keyboardList.get(position).getKeyName());
        }
        //暂时注释  这里应该显示定时的重复周期  btn_time_week.setText(String.valueOf(mEntityBean.getInfraredBinId()));
        //ll_aisr2.ssetSelected(true);  //自动默认选中
        String air = mEntityBean.getRcOperateCode();
        //然后去除，  获取数组
        String[] mAir = air.split(",");
        if (mAir.length > 3) {
            if (TextUtils.equals(mAir[3], "00")) {
                //关闭 0
                deviceStatus = false;
            } else {
                //打开   1
                deviceStatus = true;
            }
        }
        ll_time.setSelected(deviceStatus);
        ImageLoader.load(iv_rc_home,getHomeImg(mEntityBean.getDeviceId(), deviceStatus), mContext);
     //暂时注释
         btn_time_week.setText(mEntityBean.getRcOperateCode());
        if (TextUtils.equals(mEntityBean.getDeviceId(), "1")) {
            /*
            应该让那个显示
            * * 如果是空调的话 应该把东西拿出来  比如温度   风向  模式
            * mAir[5]; 温度
            * */
            rl_air_mode.setVisibility(View.VISIBLE);
            String fanDirection = mAir[7]; //00自动 01上下摆风  02左右摆风 03前后摆风 04摆风
            mode_fan.setText(getFanDirection(fanDirection));
            mode_tmp.setText(mAir[5] + "°C");
            mode_icon.setImageResource(getImgMode(mAir[4]));

        } else {
            //不显示
            rl_air_mode.setVisibility(View.GONE);
        }
        /*
         * 先解析  把那个开关拿出来
         * */
        /*
        * 根据不同的device 显示不同的图片
         iv_rc_home.setImageResource(R.mipmap.rc_home_air)
         * 1 空调
            3 机顶盒
            5 风扇
            7 iptv
            2 电视
            10 热水器
         * */
     /*   if (TextUtils.equals(mEntityBean.getDeviceId(), "1")) {
            ImageLoader.load(iv_rc_home, R.drawable.home_select_air, mContext);
        } else if (TextUtils.equals(mEntityBean.getDeviceId(), "2")) {
            ImageLoader.load(iv_rc_home, R.drawable.home_select_tv, mContext);
        } else if (TextUtils.equals(mEntityBean.getDeviceId(), "3")) {
            ImageLoader.load(iv_rc_home, R.drawable.home_select_box, mContext);
        } else if (TextUtils.equals(mEntityBean.getDeviceId(), "5")) {
            ImageLoader.load(iv_rc_home, R.mipmap.home_rc_fan, mContext);
        } else if (TextUtils.equals(mEntityBean.getDeviceId(), "7")) {
            ImageLoader.load(iv_rc_home, R.drawable.home_select_iptv, mContext);
        } else if (TextUtils.equals(mEntityBean.getDeviceId(), "10")) {
            ImageLoader.load(iv_rc_home, R.drawable.home_select_water, mContext);
        } else if (TextUtils.equals(mEntityBean.getDeviceId(), "11")) {
            ImageLoader.load(iv_rc_home, R.drawable.home_select_light, mContext);
        }*/
        /*  if (TextUtils.equals(mEntityBean.getDeviceId(),"1")){
            iv_rc_home.setImageResource(R.mipmap.rc_home_air); //关
        }else if(TextUtils.equals(mEntityBean.getDeviceId(),"2")){
            iv_rc_home.setImageResource(R.mipmap.rc_home_tv);//开
        }else if(TextUtils.equals(mEntityBean.getDeviceId(),"3")){
            iv_rc_home.setImageResource(R.mipmap.rc_home_box);  //开
        }else if(TextUtils.equals(mEntityBean.getDeviceId(),"5")){
            iv_rc_home.setImageResource(R.mipmap.rc_home_fan);// 关  风扇开的话 还是动图
        }else if(TextUtils.equals(mEntityBean.getDeviceId(),"7")){
            iv_rc_home.setImageResource(R.mipmap.rc_home_iptv);//
        }else if(TextUtils.equals(mEntityBean.getDeviceId(),"10")){
            iv_rc_home.setImageResource(R.mipmap.rc_home_water);//
        }*/

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
        iv_rc_open.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.e("TAG", "iv_rc_open: " + mEntityBean.getDeviceId());
                /*
                开关 必须支持开关的方法并且改变颜色
                * */
                openInt++;
                if (openInt % 2 == 0) {
                    Log.e("TAG", "执行关闭空调的方法");
                    ll_time.setSelected(false);
                    ImageLoader.load(iv_rc_home,getHomeImg(mEntityBean.getDeviceId(), false), mContext);
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
                } else {
                    Log.e("TAG", "执行打开方法");
                    ll_time.setSelected(true);
                    ImageLoader.load(iv_rc_home,getHomeImg(mEntityBean.getDeviceId(), true), mContext);
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
            }
        });

        btn_rc_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*
                要想编辑遥控  必须 拿到entity的数据
                * */
                Intent intent = new Intent(mContext, AddBrandRcActivity.class);
                intent.putExtra("modeId", mEntityBean.getModeId());
                intent.putExtra("infraredBinId", mEntityBean.getInfraredBinId());
                intent.putExtra("userId", mEntityBean.getUserId());
                startActivity(intent);
            }
        });

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
                ll_air2.setSelected(true);
                if (ll_air3.isSelected() || ll_air4.isSelected() || ll_air5.isSelected() || ll_air6.isSelected()) {
                    ll_air3.setSelected(false);
                    ll_air4.setSelected(false);
                    ll_air5.setSelected(false);
                    ll_air6.setSelected(false);
                }
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
                ll_air3.setSelected(true);
                if (ll_air2.isSelected() || ll_air4.isSelected() || ll_air5.isSelected() || ll_air6.isSelected()) {
                    ll_air2.setSelected(false);
                    ll_air4.setSelected(false);
                    ll_air5.setSelected(false);
                    ll_air6.setSelected(false);
                }
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
                ll_air4.setSelected(true);
                if (ll_air2.isSelected() || ll_air3.isSelected() || ll_air5.isSelected() || ll_air6.isSelected()) {
                    ll_air2.setSelected(false);
                    ll_air3.setSelected(false);
                    ll_air5.setSelected(false);
                    ll_air6.setSelected(false);
                }
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
                ll_air5.setSelected(true);
                if (ll_air2.isSelected() || ll_air3.isSelected() || ll_air4.isSelected() || ll_air6.isSelected()) {
                    ll_air2.setSelected(false);
                    ll_air3.setSelected(false);
                    ll_air4.setSelected(false);
                    ll_air6.setSelected(false);
                }
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
                ll_air6.setSelected(true);
                if (ll_air2.isSelected() || ll_air3.isSelected() || ll_air4.isSelected() || ll_air5.isSelected()) {
                    ll_air2.setSelected(false);
                    ll_air3.setSelected(false);
                    ll_air4.setSelected(false);
                    ll_air5.setSelected(false);
                }
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
                if (mTimingsBean != null) {
                    timeId = mTimingsBean.getId();
                }
                Intent intent = new Intent(mContext, TimeActivity.class);
                intent.putExtra("brandId", mEntityBean.getBrandId());
                intent.putExtra("modeId", mEntityBean.getModeId());
                intent.putExtra("productId", mEntityBean.getProductId());
                intent.putExtra("equipmentId", mEntityBean.getEquipmentId());
                intent.putExtra("timeId", timeId);
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
             /**   if (mDelaysBean != null) {
                    delayId = mDelaysBean.getId();
                }
                Intent intent = new Intent(mContext, DelayActivity.class);
                intent.putExtra("brandId", mEntityBean.getBrandId());
                intent.putExtra("modeId", mEntityBean.getModeId());
                intent.putExtra("productId", mEntityBean.getProductId());
                intent.putExtra("equipmentId", mEntityBean.getEquipmentId());
                intent.putExtra("infraredBinId", mEntityBean.getInfraredBinId());
                intent.putExtra("delayId", delayId);
                startActivity(intent);*/

             /*点击按钮 出现弹窗  不需要华丽花哨   直接出倒计时 */

                clickStart();
            }
        });


    }


/*    @Override
    public void onResume() {
        super.onResume();
        if (mTimer != null) {
            mTimer.resume();
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        if (mTimer != null) {
            mTimer.pause();
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mTimer != null) {
            mTimer.stop();
        }
    }*/

    public void clickStart() {
        if (mTimer != null) {
            mTimer.stop();
            mTimer = null;
        }
        long millisInFuture = Long.parseLong("60000");
        long countDownInterval = Long.parseLong("1000");
        mTimer = new CountDownTimerSupport(millisInFuture, countDownInterval);
        mTimer.setOnCountDownTimerListener(new OnCountDownTimerListener() {
            @Override
            public void onTick(long millisUntilFinished) {
                Log.e("TAG", "测试数据倒计时 : "+millisUntilFinished + "ms\n" + millisUntilFinished / 1000 + "s");
            }

            @Override
            public void onFinish() {
                Log.e("TAG", "倒计时已结束");
            }

            @Override
            public void onCancel() {
                Log.e("TAG", "倒计时已手动停止");

            }
        });
        mTimer.start();
    }


    /*
     *  String fanDirection = mAir[7]; //00 自动 01 上下摆风  02 左右摆风 03 前后摆风 04 摆风
     * */
    private String getFanDirection(String fanDirection) {
        String fanString = "";
        if (TextUtils.equals(fanDirection, "00")) {
            fanString = "自动";
        } else if (TextUtils.equals(fanDirection, "01")) {
            fanString = "上下扫风";
        } else if (TextUtils.equals(fanDirection, "02")) {
            fanString = "左右扫风";
        } else if (TextUtils.equals(fanDirection, "03")) {
            fanString = "前后扫风";
        } else if (TextUtils.equals(fanDirection, "04")) {
            fanString = "扫风";
        }
        return fanString;
    }


    private int getHomeImg(String deviceId, boolean isOpen) {
        int resId = 0;
        Iterator<String> iter = homeMap.keySet().iterator();
        while (iter.hasNext()) {
            String key = iter.next();
            if (TextUtils.equals(key, deviceId)) {
                if (isOpen) {
                    resId = homeMap.get(key).getCheckedImg();
                } else {
                    resId = homeMap.get(key).getCheckImg();
                }
            }
        }
        return resId;
    }

    /*
     *   ; 00自动 01制冷 02 除湿 03送风 04制热
     * */
    private int getImgMode(String modeString) {
        int resId = 0;
        if (TextUtils.equals(modeString, "00")) {
            resId = R.mipmap.air_autoed;
            ll_air2.setSelected(true);
            ll_air3.setSelected(false);
            ll_air4.setSelected(false);
            ll_air5.setSelected(false);
            ll_air6.setSelected(false);
        } else if (TextUtils.equals(modeString, "01")) {
            resId = R.mipmap.air_colded;
            ll_air2.setSelected(false);
            ll_air3.setSelected(true);
            ll_air4.setSelected(false);
            ll_air5.setSelected(false);
            ll_air6.setSelected(false);
        } else if (TextUtils.equals(modeString, "02")) {
            resId = R.mipmap.air_damped;
            ll_air2.setSelected(false);
            ll_air3.setSelected(false);
            ll_air4.setSelected(true);
            ll_air5.setSelected(false);
            ll_air6.setSelected(false);
        } else if (TextUtils.equals(modeString, "03")) {
            resId = R.mipmap.air_faned;
            ll_air2.setSelected(false);
            ll_air3.setSelected(false);
            ll_air4.setSelected(false);
            ll_air5.setSelected(true);
            ll_air6.setSelected(false);
        } else if (TextUtils.equals(modeString, "04")) {
            resId = R.mipmap.air_hoted;
            ll_air2.setSelected(false);
            ll_air3.setSelected(false);
            ll_air4.setSelected(false);
            ll_air5.setSelected(false);
            ll_air6.setSelected(true);
        }
        return resId;
    }


    private void isFunViewTwo(String keyName) {
        if (TextUtils.equals(keyName, "开")) {
            //因为开关按钮改了 这个强行隐藏
            btn_air0.setText(keyName);
            btn_air0.setVisibility(View.GONE);
        } else if (TextUtils.equals(keyName, "关")) {
            //因为开关按钮改了 这个强行隐藏
            btn_air1.setText(keyName);
            btn_air1.setVisibility(View.GONE);
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


    /*
    * 这种倒计时  存在问题？ 不存在   就在后台跑着吧
    * 接下来 就是处理 倒计时区分的问题
    * */
}