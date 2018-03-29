package cn.xz.core.delegates.web.route;

import android.support.annotation.StringDef;

/**
 * WebView 路由 枚举
 * Created by xiongz on 2018/3/5.
 */
@StringDef({RouteKeys.url})
public @interface RouteKeys {

    /**
     * 跳转必须传递的参数
     */
    String url = "url";
}
