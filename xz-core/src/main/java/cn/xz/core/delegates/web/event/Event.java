package cn.xz.core.delegates.web.event;

import android.content.Context;
import android.webkit.WebView;

import cn.xz.core.delegates.XzDelegate;
import cn.xz.core.delegates.web.WebDelegate;

/**
 * webview js与原生事件
 * Created by xiongz on 2018/3/6.
 */
public abstract class Event implements IEvent {

    private Context mContext = null;
    private String mAction = null;
    private WebDelegate mDelegate = null;
    private String mUrl = null;
    private WebView mWebView = null;

    public Context getContext() {
        return mContext;
    }

    public void setContext(Context context) {
        this.mContext = mContext;
    }

    public String getAction() {
        return mAction;
    }

    public void setAction(String action) {
        this.mAction = action;
    }

    public WebDelegate getDelegate() {
        return mDelegate;
    }

    public void setDelegate(WebDelegate delegate) {
        this.mDelegate = delegate;
    }

    public String getUrl() {
        return mUrl;
    }

    public void setUrl(String url) {
        this.mUrl = url;
    }

    public WebView getWebView() {
        return mDelegate.getWebView();
    }
}
