package cn.xz.core.net;

import java.util.ArrayList;
import java.util.WeakHashMap;
import java.util.concurrent.TimeUnit;

import cn.xz.core.app.ConfigKeys;
import cn.xz.core.app.Xz;
import cn.xz.core.net.rx.RxNetService;
import io.reactivex.internal.schedulers.RxThreadFactory;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

/**
 * 网络构建者
 * Created by xiongz on 2017/12/13.
 */
public final class NetCreator {

    /**
     * 参数容器
     */
    private static final class ParamsHolder {
        private static final WeakHashMap<String, Object> PARAMS = new WeakHashMap<>();
    }

    public static WeakHashMap<String, Object> getParams() {
        return ParamsHolder.PARAMS;
    }


    /**
     * 构建OkHttp
     */
    private static final class OKHttpHolder {
        private static final int TIME_OUT = 60;
        private static final OkHttpClient.Builder BUILDER = new OkHttpClient.Builder();
        private static final ArrayList<Interceptor> INTERCEPTORS = Xz.getConfiguration(ConfigKeys.INTERCEPTOR);

        //添加拦截器
        private static OkHttpClient.Builder addInterceptor() {
            if (INTERCEPTORS != null && !INTERCEPTORS.isEmpty()) {
                for (Interceptor interceptor : INTERCEPTORS) {
                    BUILDER.addInterceptor(interceptor);
                }
            }
            return BUILDER;
        }

        private static final OkHttpClient OK_HTTP_CLIENT = addInterceptor()
                .connectTimeout(TIME_OUT, TimeUnit.SECONDS)
                .build();
    }

    /**
     * 构建全局Retrofit客户端
     */
    private static final class RetrofitHolder {
        private static final String BASE_URL = Xz.getConfiguration(ConfigKeys.API_HOST);
        private static final Retrofit RETROFIT_CLIENT = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(OKHttpHolder.OK_HTTP_CLIENT)
                .addConverterFactory(ScalarsConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
    }

    /**
     * Service接口
     */
    private static final class NetServiceHolder {
        private static final NetService NET_SERVICE =
                RetrofitHolder.RETROFIT_CLIENT.create(NetService.class);
    }

    public static NetService getNetService() {
        return NetServiceHolder.NET_SERVICE;
    }

    /**
     * Rx Service接口
     */
    private static final class RxNetServiceHolder {
        private static final RxNetService NET_SERVICE =
                RetrofitHolder.RETROFIT_CLIENT.create(RxNetService.class);
    }

    public static RxNetService getRxNetService() {
        return RxNetServiceHolder.NET_SERVICE;
    }
}
