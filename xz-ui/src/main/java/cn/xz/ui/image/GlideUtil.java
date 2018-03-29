package cn.xz.ui.image;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

/**
 * Glide工具类
 * Created by xiongz on 2017/12/19.
 */
public class GlideUtil {

    /**
     * 加载
     *
     * @param context   上下文
     * @param url       图片链接
     * @param imageView 图片显示控件
     */
    public static void with(Context context,
                            Object url,
                            ImageView imageView) {
        Glide.with(context)
                .load(url)
                .into(imageView);
    }

    /**
     * 加载(带占位符)
     *
     * @param context   上下文
     * @param url       图片链接
     * @param imageView 图片显示控件
     * @param options   占位符
     */
    public static void withOption(Context context,
                                  Object url,
                                  ImageView imageView,
                                  RequestOptions options) {
        Glide.with(context)
                .load(url)
                .apply(options)
                .into(imageView);
    }

    /**
     * 加载gif图片
     *
     * @param context   上下文
     * @param url       图片链接
     * @param imageView 图片显示控件
     */
    public static void withGif(Context context,
                                  Object url,
                                  ImageView imageView) {
        Glide.with(context)
                .asGif()
                .load(url)
                .into(imageView);
    }
}
