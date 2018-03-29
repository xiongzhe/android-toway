package cn.xz.core.net.rx;

import java.io.File;
import java.util.Map;
import java.util.WeakHashMap;

import cn.xz.core.net.NetCreator;
import io.reactivex.Observable;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;

import static cn.xz.core.net.HttpMethod.DELETE;
import static cn.xz.core.net.HttpMethod.GET;
import static cn.xz.core.net.HttpMethod.GET_HEAERS;
import static cn.xz.core.net.HttpMethod.POST;
import static cn.xz.core.net.HttpMethod.POST_RAW;
import static cn.xz.core.net.HttpMethod.PUT;
import static cn.xz.core.net.HttpMethod.PUT_RAW;
import static cn.xz.core.net.HttpMethod.UPLOAD;

/**
 * 网络请求客户端
 * Created by xiong on 2017/12/12
 */
public final class RxNetClient {

    private static final WeakHashMap<String, Object> PARAMS = NetCreator.getParams();
    private final String URL;
    private final WeakHashMap<String, Object> HEADERS = new WeakHashMap<>();
    private final RequestBody BODY;
    private final File FILE;

    RxNetClient(String url,
                Map<String, Object> params,
                Map<String, Object> headers,
                RequestBody body,
                File file) {
        this.URL = url;
        PARAMS.putAll(params);
        HEADERS.putAll(headers);
        this.BODY = body;
        this.FILE = file;
    }

    public static RxNetClientBuilder builder() {
        return new RxNetClientBuilder();
    }

    /**
     * 请求
     *
     * @param method
     * @return
     */
    private Observable<String> request(String method) {
        final RxNetService service = NetCreator.getRxNetService();
        Observable<String> observable = null;

        switch (method) {
            case GET:
                observable = service.get(URL, PARAMS);
                break;
            case GET_HEAERS:
                observable = service.getWithHeader(HEADERS, URL, PARAMS);
                break;
            case POST:
                observable = service.post(URL, PARAMS);
                break;
            case POST_RAW:
                observable = service.postRaw(URL, BODY);
                break;
            case PUT:
                observable = service.put(URL, PARAMS);
                break;
            case PUT_RAW:
                observable = service.putRaw(URL, BODY);
                break;
            case DELETE:
                observable = service.delete(URL, PARAMS);
                break;
            case UPLOAD:
                final RequestBody requestBody =
                        RequestBody.create(MediaType.parse(MultipartBody.FORM.toString()), FILE);
                final MultipartBody.Part body =
                        MultipartBody.Part.createFormData("file", FILE.getName(), requestBody);
                observable = service.upload(URL, body);
                break;
            default:
                break;
        }

        return observable;
    }

    public final Observable<String> get() {
        return request(GET);
    }

    public final Observable<String> getWithHeaders() {
        return request(GET_HEAERS);
    }

    public final Observable<String> post() {
        if (BODY == null) {
            return request(POST);
        } else {
            if (!PARAMS.isEmpty()) {
                throw new RuntimeException("params must be null!");
            }
            return request(POST_RAW);
        }
    }

    public final Observable<String> put() {
        if (BODY == null) {
            return request(PUT);
        } else {
            if (!PARAMS.isEmpty()) {
                throw new RuntimeException("params must be null!");
            }
            return request(PUT_RAW);
        }
    }

    public final Observable<String> delete() {
        return request(DELETE);
    }

    public final Observable<String> upload() {
        return request(UPLOAD);
    }

    public final Observable<ResponseBody> download() {
        final Observable<ResponseBody> responseBodyObservable = NetCreator.getRxNetService().download(URL, PARAMS);
        return responseBodyObservable;
    }
}
