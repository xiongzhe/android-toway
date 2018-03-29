package cn.xiongz.toway;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;

import cn.xiongz.toway.launcher.LauncherDelegate;
import cn.xz.core.activities.ProxyActivity;
import cn.xz.core.app.Xz;
import cn.xz.core.delegates.XzDelegate;

/**
 * 突围活动入口Activity
 */
public class TowayActivity extends ProxyActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }
        Xz.getConfigurator().withActivity(this);
    }

    @Override
    public XzDelegate setRootDelegate() {
        return new LauncherDelegate();
    }
}