package com.example.demoanalytic;



        import android.content.Context;
        import android.content.res.Resources;
        import android.graphics.Bitmap;
        import android.graphics.BitmapFactory;
        import android.graphics.drawable.BitmapDrawable;
        import android.graphics.drawable.Drawable;
        import android.util.Log;
        import android.view.View;
        import android.widget.ImageView;

        import com.bumptech.glide.Glide;
        import com.bumptech.glide.load.DecodeFormat;
        import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
        import com.bumptech.glide.request.RequestOptions;

        import java.io.InputStream;

/**
 * @author caibou
 */
public class ImageLoader {

    public static void load(ImageView imageView, String url, boolean... isAnimate) {
        RequestOptions requestOptions = RequestOptions.placeholderOf(R.drawable.ic_composition);
        if (isAnimate.length > 0 && !isAnimate[0]) {
            requestOptions = RequestOptions.placeholderOf(R.drawable.ic_composition)
                    .dontAnimate()
                    .placeholder(imageView.getDrawable());
        }
        Glide.with(MyApplication.getInstance()).load(url).apply(requestOptions).into(imageView);
    }

    public static void load(ImageView imageView, int path) {
        Glide.with(MyApplication.getInstance()).load(path).into(imageView);
    }

    public static void load(View imageView, Object path) {
        Glide.with(MyApplication.getInstance()).load(path)
                .into((ImageView) imageView);
    }


    public static void load(ImageView imageView, String url, int defaultId) {
        Glide.with(MyApplication.getInstance()).load(url).into(imageView);
    }

    public static void loadCircle(ImageView imageView, String url, int defaultId) {
        RequestOptions requestOptions = RequestOptions.circleCropTransform().error(defaultId);
        Glide.with(MyApplication.getInstance()).load(url).apply(requestOptions).into(imageView);
    }

    public static void load(ImageView imageView, int path, Context context) {
        Glide.with(context).load(path).into(imageView);
    }

    public static void loadRound(ImageView imageView, String url, int radius) {
        RoundedCorners roundedCorners = new RoundedCorners(radius);
        RequestOptions options = RequestOptions.bitmapTransform(roundedCorners);
        Glide.with(MyApplication.getInstance()).load(url).apply(options).into(imageView);
    }

    public static void loadRound(ImageView imageView, Bitmap bitmap, int radius) {
        RoundedCorners roundedCorners = new RoundedCorners(radius);
        RequestOptions options = RequestOptions.bitmapTransform(roundedCorners);
        Glide.with(MyApplication.getInstance()).load(bitmap).apply(options).into(imageView);
    }

}
