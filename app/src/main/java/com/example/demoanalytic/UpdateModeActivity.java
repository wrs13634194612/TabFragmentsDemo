package com.example.demoanalytic;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.gson.Gson;
import com.lzy.okgo.OkGo;

public class UpdateModeActivity extends AppCompatActivity {
    private String brandId, productId, equipmentId, modeId, nick, rcRoom, kfid, modeHead, rcOperateCode, deviceId, userId;
    private int infraredBinId;
    private String url = "http://tt.mindordz.com:6361/api/hac/createAndUpdateMode";
    private Button btn_save_rc;
    private EditText et_rc_nick;
    private String rcNickString;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_mode);

        btn_save_rc = findViewById(R.id.btn_save_rc);
        et_rc_nick = findViewById(R.id.et_rc_nick);

        brandId = getIntent().getStringExtra("brandId");
        productId = getIntent().getStringExtra("productId");
        equipmentId = getIntent().getStringExtra("equipmentId");
        modeId = getIntent().getStringExtra("modeId");
        nick = getIntent().getStringExtra("nick");
        rcRoom = getIntent().getStringExtra("rcRoom");
        infraredBinId = getIntent().getIntExtra("infraredBinId", 0);
        kfid = getIntent().getStringExtra("kfid");
        deviceId = getIntent().getStringExtra("deviceId");
        modeHead = getIntent().getStringExtra("modeHead");
        rcOperateCode = getIntent().getStringExtra("rcOperateCode");
        userId = getIntent().getStringExtra("userId");
        Log.e("TAG","测试"+brandId + "\t" + brandId + "\t" + productId + "\t" + equipmentId + "\t" + modeId + "\t" + nick + "\t" + rcRoom + "\t" + infraredBinId + "\t" + kfid + "\t" + deviceId + "\t" + modeHead + "\t" + rcOperateCode + "\t" + userId);

        btn_save_rc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rcNickString = et_rc_nick.getText().toString();
                Log.e("TAG","测试上传数据："+brandId + "\t" + brandId + "\t" + productId + "\t" + equipmentId + "\t" + modeId + "\t" + nick + "\t" + rcRoom + "\t" + infraredBinId + "\t" + kfid + "\t" + deviceId + "\t" + modeHead + "\t" + rcOperateCode + "\t" + userId);
                getData(rcNickString);
            }
        });


    }


    private void getData(String rcNickString) {
        RemoteConfirmBean mRemoteConfirmBean = new RemoteConfirmBean();
        mRemoteConfirmBean.setUserId(userId);
        mRemoteConfirmBean.setProductId(productId);
        mRemoteConfirmBean.setEquipmentId(equipmentId);
        mRemoteConfirmBean.setModeId(modeId);
        mRemoteConfirmBean.setNick(rcNickString);
        mRemoteConfirmBean.setRcRoom(rcRoom);
        mRemoteConfirmBean.setInfraredBinId(infraredBinId);
        mRemoteConfirmBean.setBrandId(brandId);
        mRemoteConfirmBean.setKfId(kfid);
        mRemoteConfirmBean.setDeviceId(deviceId);
        mRemoteConfirmBean.setModeHead(modeHead);
        mRemoteConfirmBean.setRcOperateCode(rcOperateCode);
        Gson gson = new Gson();
        String jsonString = gson.toJson(mRemoteConfirmBean);
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

    /*
    {
    "code":200,
    "data":{
        "equipmentNote":"红外遥控",
        "device_id":"5",
        "kfid":"50000",
        "userId":"minsApp1a08881",
        "productId":"zcz004",
        "infraredBinId":69,
        "equipmentId":"zcz004100411",
        "mac":"df3c66db8eded811",
        "brand_id":"1119",
        "nick":"美的电风扇",
        "equipmentState":2,
        "rcRoom":"客厅",
        "rcOperateCode":"05,00,00,00,01,01",
        "modeId":"6a56dfd96d1657882000851",
        "modeHead":"013D,12",
        "rcCreateTime":"1658386190280"
    },
    "message":"操作成功"
}
    * */

}
