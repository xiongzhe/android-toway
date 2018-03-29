package cn.xz.core.delegates.web;

import android.os.Build;
import android.view.View;
import android.webkit.CookieManager;
import android.webkit.WebSettings;
import android.webkit.WebView;

import cn.xz.core.app.ConfigKeys;
import cn.xz.core.app.Xz;

/**
 * 初始化webview
 * Created by xiongz on 2018/3/6.
 */
public class WebViewInitializer {

    /**
     * 创建webview
     *
     * @param webView
     * @param customUa 自定义添加的UserAgent后缀
     * @return
     */
    @SuppressWarnings("SetJavaScriptEnabled")
    public WebView createWebView(WebView webView, String customUa) {
        //调试
        WebView.setWebContentsDebuggingEnabled(true);

        //cookie
        final CookieManager cookieManager = CookieManager.getInstance();
        cookieManager.setAcceptCookie(true);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {//5.0后跨域cookie默认关闭的，需要打开
            cookieManager.setAcceptThirdPartyCookies(webView, true);
        }
        CookieManager.setAcceptFileSchemeCookies(true);

        //不能横向滚动
        webView.setHorizontalScrollBarEnabled(false);
        //不能横向滚动
        webView.setVerticalScrollBarEnabled(false);
        //允许截图
        webView.setDrawingCacheEnabled(true);
        //屏蔽长按事件
        webView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                return true;
            }
        });
        //初始化WebSettings
        final WebSettings settings = webView.getSettings();
        settings.setJavaScriptEnabled(true);
        final String ua = settings.getUserAgentString();
        settings.setUserAgentString(ua + customUa);
        //隐藏缩放控件
        settings.setBuiltInZoomControls(false);
        settings.setDisplayZoomControls(false);
        //禁止缩放
        settings.setSupportZoom(false);
        //文件权限
        settings.setAllowFileAccess(true);
        settings.setAllowFileAccessFromFileURLs(true);
        settings.setAllowUniversalAccessFromFileURLs(true);
        settings.setAllowContentAccess(true);
        //缓存相关
        settings.setAppCacheEnabled(true);
        settings.setDomStorageEnabled(true);
        settings.setDatabaseEnabled(true);
        settings.setCacheMode(WebSettings.LOAD_DEFAULT);

        return webView;
    }

}
