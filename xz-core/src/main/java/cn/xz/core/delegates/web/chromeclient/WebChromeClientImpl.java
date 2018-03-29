package cn.xz.core.delegates.web.chromeclient;

import android.webkit.JsResult;
import android.webkit.WebChromeClient;
import android.webkit.WebView;

/**
 * WebChromeClientImpl
 * Created by xiongz on 2018/3/6.
 */
public class WebChromeClientImpl extends WebChromeClient {

    //拦截alert,并自己处理
    @Override
    public boolean onJsAlert(WebView view, String url, String message, JsResult result) {
        return super.onJsAlert(view, url, message, result);
    }
}
