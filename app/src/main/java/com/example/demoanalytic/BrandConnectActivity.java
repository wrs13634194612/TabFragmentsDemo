package com.example.demoanalytic;


import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import com.google.gson.Gson;
import com.lzy.okgo.OkGo;

import java.util.ArrayList;

public class BrandConnectActivity extends AppCompatActivity {

    private Button btn_save_brand;
    private EditText et_device_id, et_brand_id, et_user_id, et_equipment_id;
    private String deviceIdString,brandIdString,userIdString,equipmentIdString;
    private String url = "http://tt.mindordz.com:6361/api/hac/brandMatch";


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_brand_connect);

        btn_save_brand = findViewById(R.id.btn_save_brand);
        et_device_id = findViewById(R.id.et_device_id);
        et_brand_id = findViewById(R.id.et_brand_id);
        et_user_id = findViewById(R.id.et_user_id);
        et_equipment_id = findViewById(R.id.et_equipment_id);

        btn_save_brand.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*网络请求  保存 数据
                定时和倒计时  缓存的数据  无法 刷新
                //private String endTimeString, startTimeString, isRunString, weekString;
                * * */
                deviceIdString = et_device_id.getText().toString();
                brandIdString = et_brand_id.getText().toString();
                userIdString = et_user_id.getText().toString();
                equipmentIdString = et_equipment_id.getText().toString();
                getData();
            }
        });
    }

    private Handler mHandler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(@NonNull Message message) {
            if (message.what == 472) {
                String post = (String) message.obj;
                Gson gson = new Gson();
                BrandConnectBean mBrandConnectBean = gson.fromJson(post, BrandConnectBean.class);
                Intent intent = new Intent(BrandConnectActivity.this,UpdateModeActivity.class);
                intent.putExtra("brandId",mBrandConnectBean.getData().getEntity().getBrandId());
                intent.putExtra("productId",mBrandConnectBean.getData().getEntity().getProductId());
                intent.putExtra("equipmentId",mBrandConnectBean.getData().getEntity().getEquipmentId());
                intent.putExtra("modeId",mBrandConnectBean.getData().getEntity().getModeId());
                intent.putExtra("nick",mBrandConnectBean.getData().getEntity().getNick());
                intent.putExtra("rcRoom",mBrandConnectBean.getData().getEntity().getRcRoom());
                intent.putExtra("infraredBinId",mBrandConnectBean.getData().getEntity().getInfraredBinId());
                intent.putExtra("kfid",mBrandConnectBean.getData().getEntity().getKfid());
                intent.putExtra("deviceId",mBrandConnectBean.getData().getEntity().getDeviceId());
                intent.putExtra("modeHead",mBrandConnectBean.getData().getEntity().getModeHead());
                intent.putExtra("rcOperateCode",mBrandConnectBean.getData().getEntity().getRcOperateCode());
                intent.putExtra("userId",mBrandConnectBean.getData().getEntity().getUserId());
                startActivity(intent);
            }
            return false;
        }
    });

    private void getData() {
        OkGo.<String>get(url)
                .params("deviceId", 5)
                .params("brandId", 1119)
                .params("userId", "minApp125106")
                .params("equipmentId", "zcz004100411")
                .execute(new com.lzy.okgo.callback.StringCallback() {
                    @Override
                    public void onSuccess(com.lzy.okgo.model.Response<String> response) {
                        //AllBean mAllBean = JSONObject.parseObject(response.body(), AllBean.class);
                        Log.e("TAG", "onSuccess:" + response.body());
                        Message msg = new Message();
                        msg.what = 472;
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
}

/*
*
* {
    "code":200,
    "data":{
        "rooms":"次卧,厨房,阳台,洗手间,工作间",
        "delays":null,
        "timings":null,
        "rcRooms":"厨房",
        "list":[
            {
                "keyIndex":0,
                "keyName":"开"
            },
            {
                "keyIndex":1,
                "keyName":"关"
            },
            {
                "keyIndex":2,
                "keyName":"摆风"
            },
            {
                "keyIndex":3,
                "keyName":"风速"
            },
            {
                "keyIndex":4,
                "keyName":"风类"
            },
            {
                "keyIndex":5,
                "keyName":"静音"
            }
        ],
        "entity":{
            "equipmentNote":"红外遥控",
            "device_id":"5",
            "kfid":"50000",
            "userId":"mianApp125s106",
            "productId":"zcz004",
            "infraredBinId":0,
            "equipmentId":"zcz004100411",
            "mac":"df3c66db8eded811",
            "brand_id":"1119",
            "nick":"美的电风扇",
            "equipmentState":2,
            "rcRoom":"客厅",
            "rcOperateCode":"05,00,00,00,01,01",
            "modeId":"6a56dfd96d1657882000851",
            "modeHead":"013D,12",
            "rcCreateTime":"1658383396777"
        }
    },
    "message":"操作成功"
}
* */