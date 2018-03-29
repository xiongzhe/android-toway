package cn.xz.core.delegates.web;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.webkit.JavascriptInterface;

import com.blankj.utilcode.util.ToastUtils;

import cn.xz.core.delegates.web.event.Event;
import cn.xz.core.delegates.web.event.EventManager;
import cn.xz.core.util.json.FastjsonUtil;

/**
 * webview js交互 与原生交互
 * Created by xiongz on 2018/3/5.
 */
public class XzWebInterface {

    private WebDelegate DELEGATE;

    public XzWebInterface(WebDelegate delegate) {
        this.DELEGATE = delegate;
    }

    static XzWebInterface create(WebDelegate delegate) {
        return new XzWebInterface(delegate);
    }

    /**
     * js与原生交互方法
     *
     * @param params
     * @return
     */
    @SuppressWarnings("unused")
    @JavascriptInterface
    public String event(String params) {
        final String action = FastjsonUtil.parseObject(params).getString("action");
        final Event event = EventManager.getInstance().createEvent(action);
        if (event != null) {
            event.setAction(action);
            event.setDelegate(DELEGATE);
            event.setContext(DELEGATE.getContext());
            event.setUrl(DELEGATE.getUrl());
            return event.execute(params);
        }
        return null;
    }


    /************ 根据业务添加的javaInterface方法 ************/

    /**
     * 吐司
     */
    @SuppressWarnings("unused")
    @JavascriptInterface
    public void showToast(String toast) {
        ToastUtils.showShort(toast);
    }

    /**
     * 关闭商城网页
     */
    @SuppressWarnings("unused")
    @JavascriptInterface
    public void closeWindow() {
        //DELEGATE.pop();
        ToastUtils.showShort("关闭商城网页");
    }

    /**
     * 弹出登录商城失败
     */
    @SuppressWarnings("unused")
    @JavascriptInterface
    public void notifyLoginTimeOut() {
        final AlertDialog.Builder builder = new AlertDialog.Builder(DELEGATE.getContext());
        builder.setMessage("登录商城失败")
                .setNegativeButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
    }

    /**
     * 微信支付js调用方法
     *
     * @param appid
     * @param timestamp
     * @param noncestr
     * @param partnerid
     * @param prepayid
     * @param _package
     * @param sign
     * @param return_url
     */
    @SuppressWarnings("unused")
    @JavascriptInterface
    public void wechatPay(String appid, String timestamp, String noncestr, String partnerid,
                          String prepayid, String _package, String sign, String return_url) {
        ToastUtils.showShort("微信支付js调用方法");
//        LogUtils.e("TAG", "JavaScriptInterface:openGoodDetail "
//                + "appid:" + appid + ", timestamp : " + timestamp
//                + "noncestr:" + noncestr + ", partnerid : " + partnerid
//                + "prepayid:" + prepayid + ", _package : " + _package
//                + "sign:" + sign + ", return_url : " + return_url);
//        WXPayResponseBean.DataBean dataBean = new WXPayResponseBean.DataBean();
//        dataBean.setAppid(appid);
//        dataBean.setTimestamp(timestamp);
//        dataBean.setNoncestr(noncestr);
//        dataBean.setPartnerid(partnerid);
//        dataBean.setPrepayid(prepayid);
//        dataBean.setPackageStr(_package);
//        dataBean.setSign(sign);
//        dataBean.setReturn_url(return_url);
//        WXPayManager wXPayManager = new WXPayManager(mContext);
//        wXPayManager.requestWXPay(dataBean);
        //mContext.finish();
    }

    /**
     * 显示TabBar
     */
    @SuppressWarnings("unused")
    @JavascriptInterface
    public void showTabBar() {
        ToastUtils.showShort("显示tabbar");
//        App.fireEvent(ConstEvent.HOME_TABBAR_VISIBLE, View.VISIBLE);
    }

    /**
     * 隐藏TabBar
     */
    @SuppressWarnings("unused")
    @JavascriptInterface
    public void hideTabBar() {
        ToastUtils.showShort("隐藏tabbar");
//        if (type != null && type.length() > 0) {
//            return;
//        }
//        App.fireEvent(ConstEvent.HOME_TABBAR_VISIBLE, View.GONE);
    }

    /**
     * 切换到Tab
     * tabName "ShopBench":工作台 "Market":美业商城 "ShopCart":购物车 "Message":消息 "My":我
     */
    @SuppressWarnings("unused")
    @JavascriptInterface
    public void switchToTab(String tabName) {
        ToastUtils.showShort("切换tabbar  " + tabName);
//        App.fireEvent(ConstEvent.HOME_TABBAR_SWITCH, tabName);
    }

    /**
     * 设置购物车数量
     * badgeString 购物车数量
     */
    @SuppressWarnings("unused")
    @JavascriptInterface
    public void cartBadgeString(String badgeString) {
        ToastUtils.showShort("设置购物车数量  " + badgeString);
//        App.fireEvent(ConstEvent.HOME_TABBAR_SET_CART_NUM, badgeString);
    }

    /**
     * 保存accesstoken
     */
    @SuppressWarnings("unused")
    @JavascriptInterface
    public void setCookieForWebView(String accessToken_key, String accessToken_value, String expire_time) {
        ToastUtils.showShort("保存accesstoken accessToken_key " + accessToken_key +
                " accessToken_value " + accessToken_value + " expire_time " + expire_time);
//        CookieForWebViewEntity entity = CookieForWebViewEntity.getInstance();
//        entity.setAccessToken_key(accessToken_key);
//        entity.setAccessToken_value(accessToken_value);
//        entity.setExpire_time(expire_time);
    }

    /**
     * 跳转到客服页面
     */
    @SuppressWarnings("unused")
    @JavascriptInterface
    public void callOnlineCS() {
        ToastUtils.showShort("跳转到客服页面");
//        App.fireEvent(ConstEvent.CALL_ONLINE_CS);
    }

    /**
     * 重定向webivew
     */
    @SuppressWarnings("unused")
    @JavascriptInterface
    public void openNewWebview(String url) {
        ToastUtils.showShort("重定向webivew");
//        App.fireEvent(ConstEvent.OPEN_NEW_WEBVIEW, url);
    }
}
