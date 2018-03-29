package cn.xz.core.delegates.web;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.webkit.WebView;

import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;

import cn.xz.core.app.ConfigKeys;
import cn.xz.core.app.Xz;
import cn.xz.core.delegates.XzDelegate;
import cn.xz.core.delegates.web.route.RouteKeys;

/**
 * webView通用Delegate
 * Created by xiongz on 2018/3/5.
 */
public abstract class WebDelegate extends XzDelegate {

    private WebView mWebView = null;
    private final ReferenceQueue<WebView> WEB_VIEW_QUEUE = new ReferenceQueue<>();
    private String mUrl = null;
    private boolean mIsWebViewAbailable = true;
    private XzDelegate mTopDelegate = null;

    public abstract IWebViewInitializer setInitializer();

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final Bundle args = getArguments();
        mUrl = args.getString(RouteKeys.url);
        initWebView();
    }

    @SuppressWarnings("JavascriptInterface")
    private void initWebView() {
        if (mWebView != null) {
            mWebView.removeAllViews();
            mWebView.destroy();
        } else {
            final IWebViewInitializer initializer = setInitializer();
            if (initializer != null) {
                final WeakReference<WebView> webViewWeakReference =
                        new WeakReference<>(new WebView(getContext()), WEB_VIEW_QUEUE);
                mWebView = webViewWeakReference.get();
                mWebView = initializer.initWebView(mWebView);
                mWebView.setWebViewClient(initializer.initWebViewClient());
                mWebView.setWebChromeClient(initializer.initWebChromeClient());

                String name = Xz.getConfiguration(ConfigKeys.JAVASCRIPT_INTERFACE);
                XzWebInterface webInterface = XzWebInterface.create(this);
                mWebView.addJavascriptInterface(webInterface, name);

                mIsWebViewAbailable = true;
            } else {
                throw new NullPointerException("Initializer is null!");
            }
        }
    }

    /**
     * 设置TopDelegate
     *
     * @param delegate
     */
    public void setTopDelegate(XzDelegate delegate) {
        mTopDelegate = delegate;
    }

    /**
     * 获取当前TopDelegate
     *
     * @return
     */
    public XzDelegate getTopDelegate() {
        if (mTopDelegate == null) {
            return this;
        } else {
            return mTopDelegate;
        }
    }

    public WebView getWebView() {
        if (mWebView == null) {
            throw new NullPointerException("WebView is null!");
        }
        return mIsWebViewAbailable ? mWebView : null;
    }

    public String getUrl() {
        if (mUrl == null) {
            throw new NullPointerException("Url is null!");
        }
        return mUrl;
    }

    @Override
    public void onPause() {
        super.onPause();
        if (mWebView != null) {
            mWebView.onPause();
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        if (mWebView != null) {
            mWebView.onResume();
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mIsWebViewAbailable = false;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mWebView != null) {
            mWebView.removeAllViews();
            mWebView.destroy();
            mWebView = null;
        }
    }
}
