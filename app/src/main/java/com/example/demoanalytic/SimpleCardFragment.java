package com.example.demoanalytic;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;


@SuppressLint("ValidFragment")
public class SimpleCardFragment extends Fragment {
    private String mTitle;
    private List<String> mDevices;
    private Context mContext;
    private Button btn_air0, btn_air1, btn_air2,
            btn_air3, btn_air4, btn_air5,
            btn_air6, btn_air7, btn_air8,
            btn_air9, btn_air10, btn_air11,
            btn_air12, btn_air13, btn_air14, btn_air15;


    private List<ColdAirBean> allGroups;
    private HashMap<Integer, ColdAirBean> map2;

    public static SimpleCardFragment getInstance(String title) {
        SimpleCardFragment sf = new SimpleCardFragment();
        sf.mTitle = title;
        return sf;
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.mContext = context;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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


        return view;
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        allGroups = new ArrayList<>();
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
            map2.put(allGroups.get(position).getKeyIndex(), allGroups.get(position));
        }
        System.out.println(map2);
        Iterator<Integer> iter = map2.keySet().iterator();
        while (iter.hasNext()) {
            int key = iter.next();
            isFunView(key, map2.get(key).isView(), map2.get(key).getKeyName());

        }

        btn_air0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ColdAirBean mColdAirBean =   map2.get(0);

                System.out.println(mColdAirBean);
            }
        });
    }

    private void isFunView(int index, boolean isView, String name) {
        if (index == 0) {
            if (isView) {
                btn_air0.setText(name);
                btn_air0.setVisibility(View.VISIBLE);
            } else {
                btn_air0.setText(name);
                btn_air0.setVisibility(View.GONE);
            }
        } else if (index == 1) {
            if (isView) {
                btn_air1.setText(name);
                btn_air1.setVisibility(View.VISIBLE);
            } else {
                btn_air1.setText(name);
                btn_air1.setVisibility(View.GONE);
            }
        } else if (index == 2) {
            if (isView) {
                btn_air2.setText(name);
                btn_air2.setVisibility(View.VISIBLE);
            } else {
                btn_air2.setText(name);
                btn_air2.setVisibility(View.GONE);
            }
        } else if (index == 3) {
            if (isView) {
                btn_air3.setText(name);
                btn_air3.setVisibility(View.VISIBLE);
            } else {
                btn_air3.setText(name);
                btn_air3.setVisibility(View.GONE);
            }
        } else if (index == 4) {
            if (isView) {
                btn_air4.setText(name);
                btn_air4.setVisibility(View.VISIBLE);
            } else {
                btn_air4.setText(name);
                btn_air4.setVisibility(View.GONE);
            }
        } else if (index == 5) {
            if (isView) {
                btn_air5.setText(name);
                btn_air5.setVisibility(View.VISIBLE);
            } else {
                btn_air5.setText(name);
                btn_air5.setVisibility(View.GONE);
            }
        } else if (index == 6) {
            if (isView) {
                btn_air6.setText(name);
                btn_air6.setVisibility(View.VISIBLE);
            } else {
                btn_air6.setText(name);
                btn_air6.setVisibility(View.GONE);
            }
        } else if (index == 7) {
            if (isView) {
                btn_air7.setText(name);
                btn_air7.setVisibility(View.VISIBLE);
            } else {
                btn_air7.setText(name);
                btn_air7.setVisibility(View.GONE);
            }
        } else if (index == 8) {
            if (isView) {
                btn_air8.setText(name);
                btn_air8.setVisibility(View.VISIBLE);
            } else {
                btn_air8.setText(name);
                btn_air8.setVisibility(View.GONE);
            }
        } else if (index == 9) {
            if (isView) {
                btn_air9.setVisibility(View.VISIBLE);
                btn_air9.setText(name);
            } else {
                btn_air9.setVisibility(View.GONE);
                btn_air9.setText(name);
            }
        } else if (index == 10) {
            if (isView) {
                btn_air10.setVisibility(View.VISIBLE);
                btn_air10.setText(name);
            } else {
                btn_air10.setVisibility(View.GONE);
                btn_air10.setText(name);
            }
        } else if (index == 11) {
            if (isView) {
                btn_air11.setVisibility(View.VISIBLE);
                btn_air11.setText(name);
            } else {
                btn_air11.setVisibility(View.GONE);
                btn_air11.setText(name);
            }
        } else if (index == 12) {
            if (isView) {
                btn_air12.setVisibility(View.VISIBLE);
                btn_air12.setText(name);
            } else {
                btn_air12.setVisibility(View.GONE);
                btn_air12.setText(name);
            }
        } else if (index == 13) {
            if (isView) {
                btn_air13.setText(name);
                btn_air13.setVisibility(View.VISIBLE);
            } else {
                btn_air13.setVisibility(View.GONE);
                btn_air13.setText(name);
            }
        } else if (index == 14) {
            if (isView) {
                btn_air14.setText(name);
                btn_air14.setVisibility(View.VISIBLE);
            } else {
                btn_air14.setVisibility(View.GONE);
                btn_air14.setText(name);
            }
        } else if (index == 15) {
            if (isView) {
                btn_air15.setText(name);
                btn_air15.setVisibility(View.VISIBLE);
            } else {
                btn_air15.setText(name);
                btn_air15.setVisibility(View.GONE);
            }
        }
    }
}