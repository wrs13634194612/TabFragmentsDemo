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

public class TimeActivity extends AppCompatActivity {

    private Button btn_save_time;
    private EditText et_end_time, et_start_time, et_end_isRun, et_start_isRun, et_week;
    private String endTimeString, startTimeString, isStartRunString, isEndRunString, weekString;
    private String url = "http://tt.mindordz.com:6361/api/time/createAndUpdateRcTiming";
    private String brandId;
    private String modeId;
    private String productId;
    private String equipmentId;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_time);

        btn_save_time = findViewById(R.id.btn_save_time);
        et_end_time = findViewById(R.id.et_end_time);
        et_start_time = findViewById(R.id.et_start_time);
        et_start_isRun = findViewById(R.id.et_start_isRun);
        et_end_isRun = findViewById(R.id.et_end_isRun);

        et_week = findViewById(R.id.et_week);

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


        System.out.println(brandId + modeId + productId + equipmentId);

        //1119 6a56dfd96d1657882000851 zcz004 zcz004100411


        btn_save_time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*网络请求  保存 数据
                定时和倒计时  缓存的数据  无法 刷新
                //private String endTimeString, startTimeString, isRunString, weekString;
                * * */
                endTimeString = et_end_time.getText().toString();
                startTimeString = et_start_time.getText().toString();
                isStartRunString = et_start_isRun.getText().toString();
                isEndRunString = et_end_isRun.getText().toString();
                weekString = et_week.getText().toString();
                getData(endTimeString, startTimeString, Integer.parseInt(isStartRunString), Integer.parseInt(isEndRunString), weekString);
            }
        });
    }

    private void getData(String endTimeString, String startTimeString, int isStartRunInteger, int isEndRunInteger, String weekString) {
        ScheduleBean mScheduleBean = new ScheduleBean();
        mScheduleBean.setBrandId(brandId);
        mScheduleBean.setModeId(modeId);
        mScheduleBean.setProductId(productId);
        mScheduleBean.setEquipmentId(equipmentId);
        mScheduleBean.setStartTime(startTimeString);
        mScheduleBean.setEndTime(endTimeString);
        mScheduleBean.setOpenIf(isStartRunInteger);
        mScheduleBean.setCloseIf(isEndRunInteger);
        mScheduleBean.setWeek(weekString);
        mScheduleBean.setId(0);  //0新增 1 是修改
        Gson gson = new Gson();
        String jsonString = gson.toJson(mScheduleBean);
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

}
