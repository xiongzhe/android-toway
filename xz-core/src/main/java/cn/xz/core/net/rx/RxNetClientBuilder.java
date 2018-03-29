package cn.xz.core.net.rx;

import android.content.Context;

import java.io.File;
import java.util.WeakHashMap;

import cn.xz.core.net.NetClient;
import cn.xz.core.net.NetCreator;
import cn.xz.core.net.callback.IError;
import cn.xz.core.net.callback.IFailure;
import cn.xz.core.net.callback.IRequest;
import cn.xz.core.net.callback.ISuccess;
import cn.xz.core.ui.loader.LoaderStyle;
import okhttp3.MediaType;
import okhttp3.RequestBody;

/**
 * 网络请求客户端建造者
 * Created by xiongz on 2017/12/13
 */
public final class RxNetClientBuilder {

    private static final WeakHashMap<String, Object> PARAMS = NetCreator.getParams();
    private static final WeakHashMap<String, Object> HEDERS = new WeakHashMap<>();
    private String mUrl = null;
    private RequestBody mBody = null;
    private Context mContext = null;
    private File mFile = null;

    RxNetClientBuilder() {
    }

    public final RxNetClientBuilder url(String url) {
        this.mUrl = url;
        return this;
    }

    public final RxNetClientBuilder headers(WeakHashMap<String, Object> headers) {
        HEDERS.putAll(headers);
        return this;
    }

    public final RxNetClientBuilder params(WeakHashMap<String, Object> params) {
        PARAMS.putAll(params);
        return this;
    }

    public final RxNetClientBuilder params(String key, Object value) {
        PARAMS.put(key, value);
        return this;
    }

    public final RxNetClientBuilder file(File file) {
        this.mFile = file;
        return this;
    }

    public final RxNetClientBuilder file(String file) {
        this.mFile = new File(file);
        return this;
    }

    public final RxNetClientBuilder raw(String raw) {
        this.mBody = RequestBody.create(MediaType.parse("application/json;charset=UTF-8"), raw);
        return this;
    }

    public final RxNetClient build() {
        return new RxNetClient(mUrl, PARAMS, HEDERS, mBody, mFile);
    }
}
