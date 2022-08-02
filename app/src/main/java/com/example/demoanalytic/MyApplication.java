package com.example.demoanalytic;


import android.app.Activity;
import android.app.Application;
import android.content.Context;


import android.app.Activity;
import android.app.Application;

import java.lang.ref.WeakReference;
import java.util.Stack;

import com.scwang.smart.refresh.footer.ClassicsFooter;
import com.scwang.smart.refresh.header.ClassicsHeader;
import com.scwang.smart.refresh.layout.SmartRefreshLayout;
import com.scwang.smart.refresh.layout.api.RefreshFooter;
import com.scwang.smart.refresh.layout.api.RefreshHeader;
import com.scwang.smart.refresh.layout.api.RefreshLayout;
import com.scwang.smart.refresh.layout.listener.DefaultRefreshFooterCreator;
import com.scwang.smart.refresh.layout.listener.DefaultRefreshHeaderCreator;

import java.lang.ref.WeakReference;
import java.util.Stack;


/**
 * MyApplication应用 基本
 * Created by 刘楠 on 2016/7/28 0028.21:45
 */
public class MyApplication extends Application {

    //static 代码段可以防止内存泄露
    static {
        //设置全局的Header构建器
        SmartRefreshLayout.setDefaultRefreshHeaderCreator(new DefaultRefreshHeaderCreator() {
            @Override
            public RefreshHeader createRefreshHeader(Context context, RefreshLayout layout) {
                layout.setPrimaryColorsId(R.color.purple_200, android.R.color.white);//全局设置主题颜色
                return new ClassicsHeader(context);//.setTimeFormat(new DynamicTimeFormat("更新于 %s"));//指定为经典Header，默认是 贝塞尔雷达Header
            }
        });
        //设置全局的Footer构建器
        SmartRefreshLayout.setDefaultRefreshFooterCreator(new DefaultRefreshFooterCreator() {
            @Override
            public RefreshFooter createRefreshFooter(Context context, RefreshLayout layout) {
                //指定为经典Footer，默认是 BallPulseFooter
                return new ClassicsFooter(context).setDrawableSize(20);
            }
        });
    }

    private static MyApplication INSTANCE;
    /**
     * 任务栈
     */
    Stack<WeakReference<Activity>> mTasks = new Stack<WeakReference<Activity>>();


    @Override
    public void onCreate() {
        super.onCreate();
        INSTANCE = this;

    }

    public static MyApplication getInstance() {
        return INSTANCE;
    }

    /**
     * 添加一个Activity
     *
     * @param task
     */
    public void pushTask(WeakReference<Activity> task) {
        mTasks.push(task);
    }

    /**
     * 移除指定的Activity
     *
     * @param task
     */
    public void removeTask(WeakReference<Activity> task) {
        mTasks.remove(task);
    }


    /**
     * 移动指定位置的TAskActivity
     *
     * @param poistion
     */
    public void removeIndexTask(int poistion) {
        if (mTasks.size() > poistion) {
            mTasks.remove(poistion);
        }
    }

    /**
     * 移除全部的Activity
     */
    public void removeAllTask() {


        for (WeakReference<Activity> task : mTasks) {
            /**
             * 判断有没有结束没结果就结束
             */
            if (!task.get().isFinishing()) {
                //结束
                task.get().finish();
            }
        }
    }

    /**
     * 从顶部开始移除
     */
    public void removeTop() {
        int start = 1;
        int end = mTasks.size();

        for (int i = end - 1; i >= start; i--) {
            /**
             * 判断有没有结束没结果就结束
             */
            if (!mTasks.get(i).get().isFinishing()) {

                mTasks.get(i).get().finish();

            }
        }
    }
}