package cn.xz.core.net;

import android.content.Context;

import java.io.File;
import java.util.WeakHashMap;

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
public final class NetClientBuilder {

    private static final WeakHashMap<String, Object> PARAMS = NetCreator.getParams();
    private String mUrl = null;
    private IRequest mIRequest = null;
    private ISuccess mISuccess = null;
    private IFailure mIFailure = null;
    private IError mIError = null;
    private RequestBody mBody = null;
    private Context mContext = null;
    private File mFile = null;
    private String mDownloadDir = null;
    private String mExtension = null;
    private String mName = null;

    NetClientBuilder() {
    }

    public final NetClientBuilder url(String url) {
        this.mUrl = url;
        return this;
    }

    public final NetClientBuilder params(WeakHashMap<String, Object> params) {
        PARAMS.putAll(params);
        return this;
    }

    public final NetClientBuilder params(String key, Object value) {
        PARAMS.put(key, value);
        return this;
    }

    public final NetClientBuilder file(File file) {
        this.mFile = file;
        return this;
    }

    public final NetClientBuilder file(String file) {
        this.mFile = new File(file);
        return this;
    }

    public final NetClientBuilder name(String name) {
        this.mName = name;
        return this;
    }

    public final NetClientBuilder dir(String dir) {
        this.mDownloadDir = dir;
        return this;
    }

    public final NetClientBuilder extension(String extension) {
        this.mExtension = extension;
        return this;
    }

    public final NetClientBuilder raw(String raw) {
        this.mBody = RequestBody.create(MediaType.parse("application/json;charset=UTF-8"), raw);
        return this;
    }

    public final NetClientBuilder onRequest(IRequest iRequest) {
        this.mIRequest = iRequest;
        return this;
    }

    public final NetClientBuilder success(ISuccess iSuccess) {
        this.mISuccess = iSuccess;
        return this;
    }

    public final NetClientBuilder failure(IFailure iFailure) {
        this.mIFailure = iFailure;
        return this;
    }

    public final NetClientBuilder error(IError iError) {
        this.mIError = iError;
        return this;
    }

    public final NetClient build() {
        return new NetClient(mUrl, PARAMS,
                mDownloadDir, mExtension, mName,
                mIRequest, mISuccess, mIFailure,
                mIError, mBody, mFile, mContext);
    }
}
