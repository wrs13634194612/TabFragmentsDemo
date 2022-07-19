package com.example.demoanalytic;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

/**
 * online devices
 * Created by Administrator on 2016/10/25.
 */
public class OnlineDeviceListAdapter extends BaseRecyclerViewAdapter<OnlineDeviceListAdapter.ViewHolder> {
    List<String> mDevices;
    Context mContext;

    public OnlineDeviceListAdapter(Context context, List<String> devices) {
        mContext = context;
        mDevices = devices;
    }

    public void resetDevices(List<String> devices) {
        this.mDevices = devices;
        notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(mContext).inflate(R.layout.item_online_device, null, false);
        ViewHolder holder = new ViewHolder(itemView);
        holder.tv_name = itemView.findViewById(R.id.tv_name);
        return holder;
    }

    @Override
    public int getItemCount() {
        return mDevices == null ? 0 : mDevices.size();
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        super.onBindViewHolder(holder, position);
        holder.tv_name.setText(mDevices.get(position));
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        public TextView tv_name;

        public ViewHolder(View itemView) {
            super(itemView);
        }
    }
}
