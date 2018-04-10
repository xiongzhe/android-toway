package cn.xiongz.toway.common.constant;

import com.bumptech.glide.request.RequestOptions;

import cn.xiongz.toway.R;

/**
 * Glide默认配置项
 * Created by xiongz on 2018/1/4.
 */
public class GlideOptions {


    //Glide
    public static RequestOptions options = new RequestOptions()
            .placeholder(R.color.c_808082)
            .error(R.color.c_808082);
}
