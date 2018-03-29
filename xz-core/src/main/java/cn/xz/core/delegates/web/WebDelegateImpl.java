package cn.xz.core.delegates.web;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.blankj.utilcode.util.AppUtils;

import cn.xz.core.app.ConfigKeys;
import cn.xz.core.app.Xz;
import cn.xz.core.delegates.web.chromeclient.WebChromeClientImpl;
import cn.xz.core.delegates.web.client.WebViewClientImpl;
import cn.xz.core.delegates.web.route.RouteKeys;
import cn.xz.core.delegates.web.route.Router;

/**
 * WebDelegate实现类
 * Created by xiongz on 2018/3/5.
 */
public class WebDelegateImpl extends WebDelegate implements IWebViewInitializer {

    private IPageLoadListener mIPageLoadListener = null;

    /**
     * 创建WebDelegateImpl
     * @param url
     * @return
     */
    public static WebDelegateImpl create(String url) {
        final Bundle args = new Bundle();
        args.putString(RouteKeys.url, url);
        final WebDelegateImpl delegate = new WebDelegateImpl();
        delegate.setArguments(args);
        return delegate;
    }

    @Override
    public Object setLayout() {
        return getWebView();
    }

    /**
     * 设置页面加载回调
     *
     * @param listener
     */
    public void setPageLoadListener(IPageLoadListener listener) {
        mIPageLoadListener = listener;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, @NonNull View rootView) {
        if (getUrl() != null) {
            //用原生的方法模拟web跳转并进行页面加载
            Router.getInstance().loadPage(this, getUrl());
        }
    }

    @Override
    public IWebViewInitializer setInitializer() {
        return this;
    }

    @Override
    public WebView initWebView(WebView webView) {
        final String customUa = Xz.getConfiguration(ConfigKeys.WEB_USER_AGENT)
                + AppUtils.getAppVersionName();
        return new WebViewInitializer().createWebView(webView, customUa);
    }

    @Override
    public WebViewClient initWebViewClient() {
        final WebViewClientImpl client = new WebViewClientImpl(this);
        client.setPageLoadListener(mIPageLoadListener);
        return client;
    }

    @Override
    public WebChromeClient initWebChromeClient() {
        return new WebChromeClientImpl();
    }
}
