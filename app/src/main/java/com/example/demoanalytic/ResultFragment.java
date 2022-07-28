package com.example.demoanalytic;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.google.gson.Gson;
import com.lzy.okgo.OkGo;

import java.util.ArrayList;


public class ResultFragment extends Fragment {
    private Context mContext;
    private Button btn_brand_connect, btn_res_all,btn_rc_delete,btn_smart_connect;
    private String url = "http://tt.mindordz.com:6361/api/hac/findModeByUserId";
    private String urlDeleteRc = "http://tt.mindordz.com:6361/api/hac/deleteRc";
    private String urlSmart = "http://tt.mindordz.com:6361/api/hac/learnCode";


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment2, null);

        btn_brand_connect = view.findViewById(R.id.btn_brand_connect);
        btn_res_all = view.findViewById(R.id.btn_res_all);
        btn_rc_delete = view.findViewById(R.id.btn_rc_delete);
        btn_smart_connect = view.findViewById(R.id.btn_smart_connect);
        btn_brand_connect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, BrandConnectActivity.class);
                startActivity(intent);
            }
        });

        btn_res_all.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getData();
            }
        });

        btn_rc_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getRcDelete();
            }
        });

        btn_smart_connect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getSmartData();
            }
        });
        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.mContext = context;
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
                    }

                    @Override
                    public void onError(com.lzy.okgo.model.Response<String> response) {
                        super.onError(response);
                        Log.e("TAG", "onError:" + response);
                    }
                });
    }



    private void getRcDelete() {
        OkGo.<String>delete(urlDeleteRc)
                .params("userId", "minApp108881")
                .params("modeId", "6a56dfd96d1657882000851")
                .params("id", 76)
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


    private void getSmartData() {
        OkGo.<String>get(urlSmart)
                .params("deviceId", "5")
                .params("code", "13,04DD,F00FF807F807FC")
                .params("equipmentId", "zcz004100629")
                .params("userId", "minApp108881")
                .execute(new com.lzy.okgo.callback.StringCallback() {
                    @Override
                    public void onSuccess(com.lzy.okgo.model.Response<String> response) {
                        //AllBean mAllBean = JSONObject.parseObject(response.body(), AllBean.class);
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
* {"code":200,"message":"数据操作成功"}
* */