package cn.xz.core.delegates.web;

import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;

/**
 * webView接口
 * Created by xiongz on 2018/3/5.
 */
public interface IWebViewInitializer {

    WebView initWebView(WebView webView);

    //浏览器本身行为控制
    WebViewClient initWebViewClient();

    //内部页面网页控制
    WebChromeClient initWebChromeClient();
}
