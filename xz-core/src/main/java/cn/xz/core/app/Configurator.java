package cn.xz.core.app;

import android.app.Activity;
import android.app.Application;
import android.os.Handler;
import android.support.annotation.NonNull;

import com.blankj.utilcode.util.Utils;
import com.joanzapata.iconify.IconFontDescriptor;
import com.joanzapata.iconify.Iconify;
import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.Logger;

import java.util.ArrayList;
import java.util.HashMap;

import cn.xz.core.delegates.web.event.Event;
import cn.xz.core.delegates.web.event.EventManager;
import okhttp3.Interceptor;

/**
 * 配置者
 * Created by xiongz on 2017/12/10
 */
public final class Configurator {

    //配置项列表
    private static final HashMap<Object, Object> XZ_CONFIGS = new HashMap<>();
    //handler
    private static final Handler HANDLER = new Handler();
    //字体图标列表
    private static final ArrayList<IconFontDescriptor> ICONS = new ArrayList<>();
    //拦截器列表
    private static final ArrayList<Interceptor> INTERCEPTORS = new ArrayList<>();

    private Configurator() {
        XZ_CONFIGS.put(ConfigKeys.CONFIG_READY, false);
        XZ_CONFIGS.put(ConfigKeys.HANDLER, HANDLER);
    }

    /**
     * 配置者单例
     * @return
     */
    static Configurator getInstance() {
        return Holder.INSTANCE;
    }

    /**
     * 获取配置
     * @return
     */
    final HashMap<Object, Object> getXzConfigs() {
        return XZ_CONFIGS;
    }

    private static class Holder {
        private static final Configurator INSTANCE = new Configurator();
    }

    /**
     * 配置
     */
    public final void configure() {
        initIcons();
        Logger.addLogAdapter(new AndroidLogAdapter());
        XZ_CONFIGS.put(ConfigKeys.CONFIG_READY, true);
        Utils.init(Xz.getApplication());
    }

    /**
     * 配置网络请求
     * @param host
     * @return
     */
    public final Configurator withApiHost(String host) {
        XZ_CONFIGS.put(ConfigKeys.API_HOST, host);
        return this;
    }

    /**
     * 配置applicaiton
     * @param application
     * @return
     */
    public final Configurator withApplication(Application application) {
        XZ_CONFIGS.put(ConfigKeys.APPLICATION, application);
        return this;
    }

    /**
     * 配置加载延时
     * @param delayed
     * @return
     */
    public final Configurator withLoaderDelayed(long delayed) {
        XZ_CONFIGS.put(ConfigKeys.LOADER_DELAYED, delayed);
        return this;
    }

    /**
     * 初始化字体图标
     */
    private void initIcons() {
        if (ICONS.size() > 0) {
            final Iconify.IconifyInitializer initializer = Iconify.with(ICONS.get(0));
            for (int i = 1; i < ICONS.size(); i++) {
                initializer.with(ICONS.get(i));
            }
        }
    }

    /**
     * 设置图标库
     * @param descriptor
     * @return
     */
    public final Configurator withIcon(IconFontDescriptor descriptor) {
        ICONS.add(descriptor);
        return this;
    }

    /**
     * 设置网络拦截器
     * @param interceptor
     * @return
     */
    public final Configurator withInterceptor(Interceptor interceptor) {
        INTERCEPTORS.add(interceptor);
        XZ_CONFIGS.put(ConfigKeys.INTERCEPTOR, INTERCEPTORS);
        return this;
    }

    /**
     * 设置网络拦截器列表
     * @param interceptors
     * @return
     */
    public final Configurator withInterceptors(ArrayList<Interceptor> interceptors) {
        INTERCEPTORS.addAll(interceptors);
        XZ_CONFIGS.put(ConfigKeys.INTERCEPTOR, INTERCEPTORS);
        return this;
    }

//    public final Configurator withWeChatAppId(String appId) {
//        LATTE_CONFIGS.put(ConfigKeys.WE_CHAT_APP_ID, appId);
//        return this;
//    }
//
//    public final Configurator withWeChatAppSecret(String appSecret) {
//        LATTE_CONFIGS.put(ConfigKeys.WE_CHAT_APP_SECRET, appSecret);
//        return this;
//    }

    /**
     * 设置整个项目的单Activity
     * @param activity
     * @return
     */
    public final Configurator withActivity(Activity activity) {
        XZ_CONFIGS.put(ConfigKeys.ACTIVITY, activity);
        return this;
    }

    /**
     * 设置webview注入项
     * @param name
     * @return
     */
    public Configurator withJavascriptInterface(@NonNull String name) {
        XZ_CONFIGS.put(ConfigKeys.JAVASCRIPT_INTERFACE, name);
        return this;
    }

    /**
     * 设置webview事件处理
     * @param name
     * @return
     */
    public Configurator withWebEvent(@NonNull String name, @NonNull Event event) {
        final EventManager manager = EventManager.getInstance();
        manager.addEvent(name, event);
        return this;
    }

    /**
     * 设置webview浏览器加载的HOST
     * @param name
     * @return
     */
    public Configurator withWebHost(@NonNull String name) {
        XZ_CONFIGS.put(ConfigKeys.WEB_HOST, name);
        return this;
    }

    /**
     * 设置webview user_agent标识
     * @param name
     * @return
     */
    public Configurator withWebUserAgent(@NonNull String name) {
        XZ_CONFIGS.put(ConfigKeys.WEB_USER_AGENT, name);
        return this;
    }

    /**
     * 检查App相关配置项是否已经配置好
     */
    private void checkConfiguration() {
        final boolean isReady = (boolean) XZ_CONFIGS.get(ConfigKeys.CONFIG_READY);
        if (!isReady) {
            throw new RuntimeException("Configuration is not ready,call configure");
        }
    }

    /**
     * 获取相关配置项
     * @param key
     * @param <T>
     * @return
     */
    @SuppressWarnings("unchecked")
    final <T> T getConfiguration(Object key) {
        checkConfiguration();
        final Object value = XZ_CONFIGS.get(key);
        if (value == null) {
            throw new NullPointerException(key.toString() + " IS NULL");
        }
        return (T) XZ_CONFIGS.get(key);
    }
}
