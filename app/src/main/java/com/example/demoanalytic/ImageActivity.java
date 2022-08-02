package com.example.demoanalytic;

        import android.graphics.Bitmap;
        import android.graphics.BitmapFactory;
        import android.graphics.drawable.BitmapDrawable;
        import android.graphics.drawable.Drawable;
        import android.graphics.drawable.StateListDrawable;
        import android.os.Bundle;
        import android.os.Handler;
        import android.os.Message;
        import android.support.annotation.NonNull;
        import android.support.annotation.Nullable;
        import android.support.constraint.ConstraintLayout;
        import android.support.v7.app.AppCompatActivity;
        import android.text.TextUtils;
        import android.util.Log;
        import android.view.View;
        import android.widget.Button;
        import android.widget.ImageView;
        import android.widget.RelativeLayout;
        import android.widget.TextView;

        import com.bumptech.glide.Glide;
        import com.bumptech.glide.load.resource.gif.GifDrawable;
        import com.bumptech.glide.request.target.ImageViewTarget;
        import com.bumptech.glide.request.target.SimpleTarget;
        import com.bumptech.glide.request.transition.Transition;
        import org.jetbrains.annotations.NotNull;

public class ImageActivity extends AppCompatActivity {
    private int position = 0;
    private int etInt = 0;
    private int deviceInt = 2;
    private TextView tv_data;
    private boolean isRun = true;


    ConstraintLayout mConstraintLayout;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image);
        Button btn_img = findViewById(R.id.btn_img);
        final RelativeLayout rl_img = findViewById(R.id.rl_img);
        final ImageView iv_rc_home = findViewById(R.id.iv_rc_home);

        tv_data = findViewById(R.id.tv_data);
        /*  GlideDrawableImageViewTarget imageViewTarget = new GlideDrawableImageViewTarget(imageView);
        Glide.with(this).load(R.drawable.fan_five).into(imageViewTarget);
        */
        /*
        Glide.with(ImageActivity.this).asGif().load(R.drawable.fan_five).into(iv_rc_home);
        addSeletorFromNet("http://www.gov.cn/xinwen/2015-12/19/5025873/images/9cf4c22842974027a8e2ec0b5f7d4290.gif",
                "http://www.gov.cn/xinwen/2022-06/26/5697838/images/b63857995c49409cb6478096e79af02e.jpg",iv_rc_home);
        // ImageLoader.load(iv_rc_home, R.drawable.fan_five, ImageActivity.this);
         * 写一个线程 自动跑程序   每两秒 变一次设备类型   点击事件 依然是我自己写
         * */
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    while (isRun) {
                        etInt++;
                        deviceInt = etInt % 7;
                        /*0  1  2 3 4 5 6*/
                        Log.e("TAG", "thread:" + etInt + "\t" + deviceInt);

                        Message msg = new Message();
                        msg.what = 472;
                        msg.obj = deviceInt;
                        mHandler.sendMessage(msg);
                        Thread.sleep(1000);
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();

       /*
       * home_select_light   home_select_lighting
        home_select_water   home_select_watering
        home_select_fan   home_select_faning
        home_select_tv   home_select_tving
        home_select_air   home_select_airing
        home_select_box   home_select_boxing
        home_select_iptv   home_select_iptving
       * */
        ImageLoader.load(iv_rc_home, R.drawable.home_select_water, ImageActivity.this);

        btn_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
        /*        if (deviceInt == 0) {
                    Glide.with(ImageActivity.this).load(R.drawable.home_select_light).into(iv_rc_home);
                } else if (deviceInt == 1) {
                    Glide.with(ImageActivity.this).load(R.drawable.home_select_water).into(iv_rc_home);
                } else if (deviceInt == 2) {

*//*
                 * 换句话说  我必须使用比较笨的办法  来控制数据切换
                 *
                 * 不能使用这种selected的方法  这很烦  但是也是目前唯一的的办法
                 *
                 * 如果选中 不选中
                 *
                 * 判断设备的类型
                 *
                 *
                 * *//*
                    iv_gif.setImageResource(R.drawable.home_select_fan);
                    //  Glide.with(ImageActivity.this).asGif().load(R.drawable.home_select_fan).into(iv_gif);
                   // Glide.with(ImageActivity.this).load(R.drawable.home_select_fan).into(iv_rc_home);
                } else if (deviceInt == 3) {
                    Glide.with(ImageActivity.this).load(R.drawable.home_select_tv).into(iv_rc_home);
                } else if (deviceInt == 4) {
                    Glide.with(ImageActivity.this).load(R.drawable.home_select_air).into(iv_rc_home);
                } else if (deviceInt == 5) {
                    Glide.with(ImageActivity.this).load(R.drawable.home_select_box).into(iv_rc_home);
                } else if (deviceInt == 6) {
                    Glide.with(ImageActivity.this).load(R.drawable.home_select_iptv).into(iv_rc_home);
                }*/
                if (deviceInt == 0) {
                    ImageLoader.load(iv_rc_home, R.drawable.home_select_light, ImageActivity.this);
                } else if (deviceInt == 1) {
                    ImageLoader.load(iv_rc_home, R.drawable.home_select_water, ImageActivity.this);
                } else if (deviceInt == 2) {
                    /*
                     * 如果是2 藏起来   如果是其他 显示
                     * */
                    if (rl_img.isSelected()) {
                        ImageLoader.load(iv_rc_home, R.drawable.fan_five, ImageActivity.this);
                    } else {
                        ImageLoader.load(iv_rc_home, R.drawable.home_select_fan, ImageActivity.this);
                    }
                } else if (deviceInt == 3) {
                    iv_rc_home.setVisibility(View.VISIBLE);
                    ImageLoader.load(iv_rc_home, R.drawable.home_select_tv, ImageActivity.this);
                } else if (deviceInt == 4) {
                    iv_rc_home.setVisibility(View.VISIBLE);
                    ImageLoader.load(iv_rc_home, R.drawable.home_select_air, ImageActivity.this);
                } else if (deviceInt == 5) {
                    iv_rc_home.setVisibility(View.VISIBLE);
                    ImageLoader.load(iv_rc_home, R.drawable.home_select_box, ImageActivity.this);
                } else if (deviceInt == 6) {
                    iv_rc_home.setVisibility(View.VISIBLE);
                    ImageLoader.load(iv_rc_home, R.drawable.home_select_iptv, ImageActivity.this);
                }

            /*    if (deviceInt == 0) {
                    iv_rc_home.setImageResource(R.drawable.home_select_light);
                } else if (deviceInt == 1) {
                    iv_rc_home.setImageResource(R.drawable.home_select_water);
                } else if (deviceInt == 2) {
                    iv_rc_home.setImageResource(R.drawable.home_select_fan);
                } else if (deviceInt == 3) {
                    iv_rc_home.setImageResource(R.drawable.home_select_tv);
                } else if (deviceInt == 4) {
                    iv_rc_home.setImageResource(R.drawable.home_select_air);
                } else if (deviceInt == 5) {
                    iv_rc_home.setImageResource(R.drawable.home_select_box);
                } else if (deviceInt == 6) {
                    iv_rc_home.setImageResource(R.drawable.home_select_iptv);
                }*/
                /*
                 * 点击   变色  0 false
                 * 还有一个判断  不同的设备 显示不同的图片 比如1  2  3  4 5
                 * */
                position++;
                if (position % 2 == 0) {
                    rl_img.setSelected(false);
//                    ImageLoader.load(iv_rc_home, R.mipmap.home_rc_fan, ImageActivity.this);
                } else {
                    /*
                    ImageLoader.load(iv_rc_home, R.drawable.fan_five, ImageActivity.this);
                     * 如果为1 true  显示动态态图片
                     * */
                    rl_img.setSelected(true);
                }

//                Glide.with(ImageActivity.this).asGif().load(R.drawable.fan_five).into(iv_rc_home);
             /*   Glide.with(ImageActivity.this)
                        .asGif()
                        .load(iv_rc_home)
                        .placeholder(ResourcesCompat.getDrawable(getResources(),
                                R.drawable.home_select_fan, null))
                        .centerCrop()
                        .into(new ImageViewTarget<GifDrawable>(iv_rc_home) {
                            @Override
                            protected void setResource(@Nullable GifDrawable resource) {
                                iv_rc_home.setImageDrawable(resource);
                            }
                        });
*/
            }
        });
    }

    private Handler mHandler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(@NonNull Message message) {
            if (message.what == 472) {
                int post = (Integer) message.obj;
                Log.e("TAG", "mHandler:" + post + "\t");
                tv_data.setText(String.valueOf(post));
            }
            return false;
        }
    });


}