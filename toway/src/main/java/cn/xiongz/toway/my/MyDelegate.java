package cn.xiongz.toway.my;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;

import com.blankj.utilcode.util.ToastUtils;

import butterknife.BindView;
import cn.xiongz.toway.R;
import cn.xz.core.delegates.XzDelegate;

/**
 * 我的 页面
 * Created by xiongz on 2018/3/28.
 */
public class MyDelegate extends XzDelegate {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.vp_my)
    ViewPager vpActivity;

    /**
     * Instance
     *
     * @return
     */
    public static MyDelegate newInstance() {
        MyDelegate fragment = new MyDelegate();
        return fragment;
    }

    @Override
    public Object setLayout() {
        return R.layout.delegate_my;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, @NonNull View rootView) {
        setSwipeBackEnable(false);

    }
}
