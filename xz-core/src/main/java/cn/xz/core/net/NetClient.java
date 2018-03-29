package cn.xz.core.net;

import android.content.Context;

import java.io.File;
import java.util.Map;
import java.util.WeakHashMap;

import cn.xz.core.net.callback.IError;
import cn.xz.core.net.callback.IFailure;
import cn.xz.core.net.callback.IRequest;
import cn.xz.core.net.callback.ISuccess;
import cn.xz.core.net.callback.RequestCallbacks;
import cn.xz.core.net.download.DownloadHandler;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;

import static cn.xz.core.net.HttpMethod.DELETE;
import static cn.xz.core.net.HttpMethod.GET;
import static cn.xz.core.net.HttpMethod.POST;
import static cn.xz.core.net.HttpMethod.POST_RAW;
import static cn.xz.core.net.HttpMethod.PUT;
import static cn.xz.core.net.HttpMethod.PUT_RAW;
import static cn.xz.core.net.HttpMethod.UPLOAD;

/**
 * 网络请求客户端
 * Created by xiong on 2017/12/12
 */
public final class NetClient {

    private static final WeakHashMap<String, Object> PARAMS = NetCreator.getParams();
    private final String URL;
    private final IRequest REQUEST;
    private final String DOWNLOAD_DIR;
    private final String EXTENSION;
    private final String NAME;
    private final ISuccess SUCCESS;
    private final IFailure FAILURE;
    private final IError ERROR;
    private final RequestBody BODY;
    private final File FILE;
    private final Context CONTEXT;

    NetClient(String url,
              Map<String, Object> params,
              String downloadDir,
              String extension,
              String name,
              IRequest request,
              ISuccess success,
              IFailure failure,
              IError error,
              RequestBody body,
              File file,
              Context context) {
        this.URL = url;
        PARAMS.putAll(params);
        this.DOWNLOAD_DIR = downloadDir;
        this.EXTENSION = extension;
        this.NAME = name;
        this.REQUEST = request;
        this.SUCCESS = success;
        this.FAILURE = failure;
        this.ERROR = error;
        this.BODY = body;
        this.FILE = file;
        this.CONTEXT = context;
    }

    public static NetClientBuilder builder() {
        return new NetClientBuilder();
    }

    private void request(String method) {
        final NetService service = NetCreator.getNetService();
        Call<String> call = null;

        if (REQUEST != null) {
            REQUEST.onRequestStart();
        }

        switch (method) {
            case GET:
                call = service.get(URL, PARAMS);
                break;
            case POST:
                call = service.post(URL, PARAMS);
                break;
            case POST_RAW:
                call = service.postRaw(URL, BODY);
                break;
            case PUT:
                call = service.put(URL, PARAMS);
                break;
            case PUT_RAW:
                call = service.putRaw(URL, BODY);
                break;
            case DELETE:
                call = service.delete(URL, PARAMS);
                break;
            case UPLOAD:
                final RequestBody requestBody =
                        RequestBody.create(MediaType.parse(MultipartBody.FORM.toString()), FILE);
                final MultipartBody.Part body =
                        MultipartBody.Part.createFormData("file", FILE.getName(), requestBody);
                call = service.upload(URL, body);
                break;
            default:
                break;
        }

        if (call != null) {
            call.enqueue(getRequestCallback());
        }
    }

    private Callback<String> getRequestCallback() {
        return new RequestCallbacks(
                REQUEST,
                SUCCESS,
                FAILURE,
                ERROR
        );
    }

    public final void get() {
        request(GET);
    }

    public final void post() {
        if (BODY == null) {
            request(POST);
        } else {
            if (!PARAMS.isEmpty()) {
                throw new RuntimeException("params must be null!");
            }
            request(POST_RAW);
        }
    }

    public final void put() {
        if (BODY == null) {
            request(PUT);
        } else {
            if (!PARAMS.isEmpty()) {
                throw new RuntimeException("params must be null!");
            }
            request(PUT_RAW);
        }
    }

    public final void delete() {
        request(DELETE);
    }

    public final void upload() {
        request(UPLOAD);
    }

    public final void download() {
        new DownloadHandler(URL, REQUEST, DOWNLOAD_DIR, EXTENSION, NAME,
                SUCCESS, FAILURE, ERROR)
                .handleDownload();
    }
}
