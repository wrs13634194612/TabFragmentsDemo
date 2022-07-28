package com.example.demoanalytic;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.google.gson.Gson;
import com.lzy.okgo.OkGo;


public class AddBrandRcActivity extends AppCompatActivity {
    private String urlDeleteRc = "http://tt.mindordz.com:6361/api/hac/deleteRc";

    private String brandId, productId, equipmentId, modeId, nick, rcRoom, kfid, modeHead, rcOperateCode, deviceId, userId;
    private int infraredBinId;

    private Button btn_delete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);
        modeId = getIntent().getStringExtra("modeId");
        userId = getIntent().getStringExtra("userId");
        infraredBinId = getIntent().getIntExtra("infraredBinId", 0);

        btn_delete = findViewById(R.id.btn_delete);

        btn_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getRcDelete();
            }
        });


    }

    private void getRcDelete() {
        OkGo.<String>delete(urlDeleteRc)
                .params("userId", userId)
                .params("modeId", modeId)
                .params("id", infraredBinId)
                .execute(new com.lzy.okgo.callback.StringCallback() {
                    @Override
                    public void onSuccess(com.lzy.okgo.model.Response<String> response) {
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


    private Handler mHandler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(@NonNull Message message) {
            if (message.what == 472) {
                String post = (String) message.obj;
                Gson gson = new Gson();
                DeleteRcBean mBrandConnectBean = gson.fromJson(post, DeleteRcBean.class);
                if (mBrandConnectBean.getCode() == 200) {
                    finish();
                }
            }
            return false;
        }
    });


}
