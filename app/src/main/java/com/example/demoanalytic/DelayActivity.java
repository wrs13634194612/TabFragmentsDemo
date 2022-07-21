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

public class DelayActivity extends AppCompatActivity {

    private Button btn_delete_delay, btn_save_delay;
    private EditText et_time_delay, et_delay_isRun;
    private String timeDelayString, delayIsRunString;
    private String url = "http://tt.mindordz.com:6361/api/time/createAndUpdateRcDelay";
    private String urlDelete = "http://tt.mindordz.com:6361/api/time/deleteTimeById";
    private String brandId;
    private String modeId;
    private String productId;
    private String equipmentId;
    private int delayId;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_delay);

        btn_delete_delay = findViewById(R.id.btn_delete_delay);
        btn_save_delay = findViewById(R.id.btn_save_delay);
        et_time_delay = findViewById(R.id.et_time_delay);
        et_delay_isRun = findViewById(R.id.et_delay_isRun);
        /*
                   intent.putExtra("brandId",mEntityBean.getBrandId());
                intent.putExtra("modeId",mEntityBean.getModeId());
                intent.putExtra("productId",mEntityBean.getProductId());
                intent.putExtra("equipmentId",mEntityBean.getEquipmentId());
        * * */

        brandId = getIntent().getStringExtra("brandId");
        modeId = getIntent().getStringExtra("modeId");
        productId = getIntent().getStringExtra("productId");
        equipmentId = getIntent().getStringExtra("equipmentId");
        delayId = getIntent().getIntExtra("delayId", 0);

        System.out.println(brandId + modeId + productId + equipmentId);

        //1119 6a56dfd96d1657882000851 zcz004 zcz004100411


        btn_save_delay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*网络请求  保存 数据
                定时和倒计时  缓存的数据  无法 刷新
                //private String endTimeString, startTimeString, isRunString, weekString;
                    private EditText et_time_delay, et_delay_isRun;
                     private String timeDelayString, delayIsRunString;
                * * */
                timeDelayString = et_time_delay.getText().toString();
                delayIsRunString = et_delay_isRun.getText().toString();
                getDelay(timeDelayString, Integer.parseInt(delayIsRunString));
            }
        });

        btn_delete_delay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getDelete();

            }
        });
    }


    private void getDelay(String timeDelayString, int delayIsRunInteger) {
        /**
         {
         "brandId": "1",
         "modeId": "0f30aaaa5d1655867968558",
         "productId": "zcz004",
         "equipmentId": "zcz00410tt0",
         "executeTime": "13:00",
         "switchStatus": 1
         }
         */
        CountDownBean mCountDownBean = new CountDownBean();
        mCountDownBean.setBrandId(brandId);
        mCountDownBean.setModeId(modeId);
        mCountDownBean.setProductId(productId);
        mCountDownBean.setEquipmentId(equipmentId);
        mCountDownBean.setExecuteTime(timeDelayString);
        mCountDownBean.setSwitchStatus(delayIsRunInteger);
        Gson gson = new Gson();
        String jsonString = gson.toJson(mCountDownBean);
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

    private void getDelete() {
        OkGo.<String>delete(urlDelete)
                .params("id", delayId)
                .params("type", "delay")
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

}

/*
{
    "code":200,
    "data":{
        "id":87,
        "brandId":"1119",
        "modeId":"6a56dfd96d1657882000851",
        "productId":"zcz004",
        "equipmentId":"zcz004100411",
        "executeTime":"00:01",
        "executeTimePoint":"12:20:20",
        "switchStatus":2,
        "createTime":"1658377177914"
    },
    "message":"操作成功"
}

{"code":200,"message":"数据操作成功"}

* */