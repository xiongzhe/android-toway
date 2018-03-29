package cn.xz.core.delegates.web.client;

import android.graphics.Bitmap;
import android.os.Handler;
import android.text.TextUtils;
import android.webkit.CookieManager;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import cn.xz.core.app.ConfigKeys;
import cn.xz.core.app.Xz;
import cn.xz.core.delegates.web.IPageLoadListener;
import cn.xz.core.delegates.web.WebDelegate;
import cn.xz.core.delegates.web.route.Router;
import cn.xz.core.ui.loader.XzLoader;
import cn.xz.core.util.log.XzLogger;
import cn.xz.core.util.storage.XzPreference;

/**
 * WebViewClient实现类
 * Created by xiongz on 2018/3/5.
 */
public class WebViewClientImpl extends WebViewClient {

    private WebDelegate DELEGATE;
    private IPageLoadListener mIPageLoadListener = null;
    private static final Handler HANDLER = Xz.getHandler();

    public WebViewClientImpl(WebDelegate delegate) {
        this.DELEGATE = delegate;
    }

    @Override
    public boolean shouldOverrideUrlLoading(WebView view, String url) {
        XzLogger.d("shouldOverrideUrlLoading", url);
        return Router.getInstance().handleWebUrl(DELEGATE, url);//false由webview处理，true自己接管处理
    }

    @Override
    public void onPageStarted(WebView view, String url, Bitmap favicon) {
        super.onPageStarted(view, url, favicon);
        if (mIPageLoadListener != null) {
            mIPageLoadListener.onLoadStart();
        }
        XzLoader.showLoading(view.getContext());
    }

    /**
     * 获取浏览器Cookie
     */
    private void syncCookie() {
        final CookieManager manager = CookieManager.getInstance();
        /*
          注意，这里的cookie和API中请求的cookie是不一样的，这个在网页不可见
         */
        final String webHost = Xz.getConfiguration(ConfigKeys.WEB_HOST);
        if (webHost != null) {
            if (manager.hasCookies()) {
                final String cookieStr = manager.getCookie(webHost);
                if (!TextUtils.isEmpty(cookieStr)) {
                    XzPreference.addCustomAppProfile("cookie", cookieStr);
                }
            }
        }
    }

    @Override
    public void onPageFinished(WebView view, String url) {
        super.onPageFinished(view, url);
        //syncCookie();
        if (mIPageLoadListener != null) {
            mIPageLoadListener.onLoadStart();
        }
        HANDLER.postDelayed(new Runnable() {
            @Override
            public void run() {
                XzLoader.stopLoading();
            }
        }, 1000);
    }

    /**
     * 设置页面加载回调
     *
     * @param listener
     */
    public void setPageLoadListener(IPageLoadListener listener) {
        mIPageLoadListener = listener;
    }
}
