package cn.xiongz.toway.login;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;

import butterknife.BindView;
import butterknife.OnClick;
import cn.xiongz.toway.R;
import cn.xiongz.toway.activities.ActivityDelegate;
import cn.xiongz.toway.launcher.LauncherDelegate;
import cn.xiongz.toway.main.MainDelegate;
import cn.xz.core.delegates.XzDelegate;

/**
 * 登录页面（仅有微信登录）
 * Created by xiongz on 2018/3/28.
 */
public class LoginDelegate extends XzDelegate {

    @BindView(R.id.btn_login)
    Button btnLogin;

    @OnClick(R.id.btn_login)
    public void onViewClicked() {
        popTo(LoginDelegate.class, true, new Runnable() {
            @Override
            public void run() {
                start(MainDelegate.newInstance());
            }
        });
    }

    /**
     * Instance
     *
     * @return
     */
    public static LoginDelegate newInstance() {
        LoginDelegate fragment = new LoginDelegate();
        return fragment;
    }

    @Override
    public Object setLayout() {
        return R.layout.delegate_login;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, @NonNull View rootView) {
        setSwipeBackEnable(false);

    }
}
