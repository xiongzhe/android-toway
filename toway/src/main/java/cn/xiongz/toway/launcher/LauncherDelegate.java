package cn.xiongz.toway.launcher;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;

import cn.xiongz.toway.R;
import cn.xiongz.toway.login.LoginDelegate;
import cn.xz.core.delegates.XzDelegate;

/**
 * 启动页面
 * Created by xiongz on 2018/3/28.
 */
public class LauncherDelegate extends XzDelegate {

    @Override
    public Object setLayout() {
        return R.layout.delegate_launcher;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, @NonNull View rootView) {
        setSwipeBackEnable(false);

        //延迟两秒跳转
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                toLogin();
            }
        }, 2000);
    }

    /**
     * 跳转到登录页面
     */
    public void toLogin() {
        popTo(LauncherDelegate.class, true, new Runnable() {
            @Override
            public void run() {
                start(LoginDelegate.newInstance());
            }
        });
    }

    /**
     * 跳转到主页面
     */
    private void toMain() {
//        XzLoader.showLoading(getContext());
//        popTo(LauncherDelegate.class, true, new Runnable() {
//            @Override
//            public void run() {
//                start(MainDelegate.newInstance());
//            }
//        });
    }
}
