package cn.xz.core.net;

import android.support.annotation.StringDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * 请求类型
 * Created by xiongz on 2017/12/13.
 */
@StringDef({HttpMethod.GET, HttpMethod.GET_HEAERS, HttpMethod.POST, HttpMethod.POST_RAW,
        HttpMethod.PUT, HttpMethod.PUT_RAW, HttpMethod.DELETE, HttpMethod.UPLOAD})
@Retention(RetentionPolicy.SOURCE)
public @interface HttpMethod {
    String GET = "GET";
    String GET_HEAERS = "GET_HEAERS";
    String POST = "POST";
    String POST_RAW = "POST_RAW";
    String PUT = "PUT";
    String PUT_RAW = "PUT_RAW";
    String DELETE = "DELETE";
    String UPLOAD = "UPLOAD";
}
