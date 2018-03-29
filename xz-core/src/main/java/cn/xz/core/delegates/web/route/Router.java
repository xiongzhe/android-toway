package cn.xz.core.delegates.web.route;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v4.content.ContextCompat;
import android.webkit.URLUtil;
import android.webkit.WebView;

import com.blankj.utilcode.util.AppUtils;

import java.util.HashMap;
import java.util.Map;

import cn.xz.core.delegates.XzDelegate;
import cn.xz.core.delegates.web.WebDelegate;
import cn.xz.core.delegates.web.WebDelegateImpl;

/**
 * 路由者
 * Created by xiongz on 2018/3/6.
 */
public class Router {
    private Router() {

    }

    private static class Holder {
        private static final Router INSTANCE = new Router();
    }

    public static Router getInstance() {
        return Holder.INSTANCE;
    }

    /**
     * 接管webview跳转事件
     *
     * @param delegate
     * @param url
     * @return
     */
    public final boolean handleWebUrl(WebDelegate delegate, String url) {

        if (url.contains("tel:")) { //识别电话
            callPhone(delegate.getContext(), url);
            return true;
        }

        //有顶部delegate则从顶部delegate跳转过去（避免还有底部bottom存在）
        final XzDelegate topDelegate = delegate.getTopDelegate();
        final WebDelegateImpl webDelegate = WebDelegateImpl.create(url);
        topDelegate.start(webDelegate);

        return true;
    }

    /**
     * 加载web页面
     *
     * @param webView
     * @param url
     */
    private void loadWebPage(WebView webView, String url) {
        if (webView != null) {
            Map<String, String> extraHeaders = new HashMap<>();
            extraHeaders.put("Client-Device", "BeautyPlus/android/" + AppUtils.getAppVersionName());
            webView.loadUrl(url);
        } else {
            throw new NullPointerException("WebView is null!");
        }
    }

    /**
     * 加载本地页面
     */
    private void loadLocalPage(WebView webView, String url) {
        loadWebPage(webView, "file:///android_asset/" + url);
    }

    /**
     * 加载页面
     *
     * @param webView
     * @param url
     */
    private void loadPage(WebView webView, String url) {
        if (URLUtil.isNetworkUrl(url) || URLUtil.isAssetUrl(url)) {
            loadWebPage(webView, url);
        } else {
            loadLocalPage(webView, url);
        }
    }

    /**
     * 加载页面
     *
     * @param delegate
     * @param url
     */
    public final void loadPage(WebDelegate delegate, String url) {
        loadPage(delegate.getWebView(), url);
    }

    /**
     * 打电话
     *
     * @param context
     * @param uri
     */
    private void callPhone(Context context, String uri) {
        final Intent intent = new Intent(Intent.ACTION_DIAL);
        final Uri data = Uri.parse(uri);
        intent.setData(data);
        ContextCompat.startActivity(context, intent, null);
    }
}
