package cn.xiongz.toway;

import android.support.multidex.MultiDexApplication;

import com.facebook.stetho.Stetho;
import com.joanzapata.iconify.fonts.FontAwesomeModule;

import cn.xiongz.toway.icon.FontTowayModule;
import cn.xz.core.BuildConfig;
import cn.xz.core.app.Xz;
import cn.xz.core.net.interceptors.DebugInterceptor;
import okhttp3.logging.HttpLoggingInterceptor;

/**
 * 美丽加商家版App入口
 * Created by xiongz on 2017/12/13
 */
public class TowayApp extends MultiDexApplication {

    @Override
    public void onCreate() {
        super.onCreate();

        //App配置初始化
        Xz.init(this)
                .withIcon(new FontAwesomeModule())
                .withIcon(new FontTowayModule())
                .withApplication(this)
                .withLoaderDelayed(100)
                .withApiHost("http://www.baidu.com")
                .withInterceptor(new DebugInterceptor("testtest", R.raw.test))//注意一定要用testtest测试
                .withInterceptor(new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BASIC))
                .configure();

//        Fragmentation.builder()
//                // 设置 栈视图 模式为 悬浮球模式   SHAKE: 摇一摇唤出  默认NONE：隐藏， 仅在Debug环境生效
//                .stackViewMode(Fragmentation.SHAKE)
//                // 开发环境：true时，遇到异常："Can not perform this action after onSaveInstanceState!"时，抛出，并Crash;
//                // 生产环境：false时，不抛出，不会Crash，会捕获，可以在handleException()里监听到
//                .debug(true) // 实际场景建议.debug(BuildConfig.DEBUG)
//                // 生产环境时，捕获上述异常（避免crash），会捕获
//                // 建议在回调处上传下面异常到崩溃监控服务器
//                .handleException(new ExceptionHandler() {
//                    @Override
//                    public void onException(Exception e) {
//                        // 以Bugtags为例子: 把捕获到的 Exception 传到 Bugtags 后台。
//                        // Bugtags.sendException(e);
//                    }
//                })
//                .install();

        //调试工具初始化
//        if (BuildConfig.isDebug) {
//            initStetho();
//        }
    }

    /**
     * 初始化调试工具
     */
    private void initStetho() {
        Stetho.initialize(
                Stetho.newInitializerBuilder(this)
                        .enableDumpapp(Stetho.defaultDumperPluginsProvider(this))
                        .enableWebKitInspector(Stetho.defaultInspectorModulesProvider(this))
                        .build());
    }
}
