package cn.xz.core.app;

import android.support.annotation.IntDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * 配置key
 * Created by xiongz on 2017/11/13.
 */
@IntDef({ConfigKeys.API_HOST, ConfigKeys.APPLICATION_CONTEXT, ConfigKeys.CONFIG_READY,
        ConfigKeys.ICON, ConfigKeys.INTERCEPTOR, ConfigKeys.HANDLER, ConfigKeys.ACTIVITY,
        ConfigKeys.JAVASCRIPT_INTERFACE, ConfigKeys.WEB_HOST, ConfigKeys.WEB_USER_AGENT})
@Retention(RetentionPolicy.SOURCE)
public @interface ConfigKeys {
    int API_HOST = 1;
    int APPLICATION_CONTEXT = 2;
    int CONFIG_READY = 3;
    int ICON = 4;
    int INTERCEPTOR = 5;
    int HANDLER = 6;
    int LOADER_DELAYED = 7;
    int APPLICATION = 8;
    int ACTIVITY = 9;
    int JAVASCRIPT_INTERFACE = 10;
    int WEB_HOST = 11;
    int WEB_USER_AGENT = 12;
}
