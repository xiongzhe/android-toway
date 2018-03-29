package cn.xiongz.toway.main;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.FrameLayout;

import com.blankj.utilcode.util.ToastUtils;

import butterknife.BindView;
import cn.xiongz.toway.R;
import cn.xiongz.toway.activities.ActivityDelegate;
import cn.xiongz.toway.common.views.MenuBarView;
import cn.xiongz.toway.my.MyDelegate;
import cn.xz.core.delegates.XzDelegate;

/**
 * 主页面
 * Created by xiongz on 2018/3/28.
 */
public class MainDelegate extends XzDelegate implements MenuBarView.OnMenuItemClickListener {

    // 再点一次退出程序时间设置
    private static final long WAIT_TIME = 2000L;
    private long TOUCH_TIME = 0;

    @BindView(R.id.menu_bar_main)
    MenuBarView menuBar;
    @BindView(R.id.fl_menu)
    FrameLayout flMenu;

    //delegate列表
    private XzDelegate[] mDelegate = new XzDelegate[2];
    //当前Delegate
    private int mIndexDelegate = 0;

    /**
     * Instance
     *
     * @return
     */
    public static MainDelegate newInstance() {
        MainDelegate fragment = new MainDelegate();
        return fragment;
    }

    @Override
    public Object setLayout() {
        return R.layout.delegate_main;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, @NonNull View rootView) {
        setSwipeBackEnable(false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        initMenuLayout();
        initDelegates();
    }

    /**
     * 初始化底部导航栏
     */
    private void initMenuLayout() {
        menuBar.setOnMenuItemClickListener(this);
        menuBar.setCurrentMenuItem(0);
    }

    /**
     * 初始化子页面
     */
    private void initDelegates() {
        XzDelegate workBenchDelegate = findChildFragment(ActivityDelegate.class);
        if (workBenchDelegate == null) {
            mDelegate[0] = ActivityDelegate.newInstance();
            mDelegate[1] = MyDelegate.newInstance();
            loadMultipleRootFragment(R.id.fl_menu, mIndexDelegate, mDelegate[0], mDelegate[1]);
        } else {
            mDelegate[0] = workBenchDelegate;
            mDelegate[1] = findChildFragment(MyDelegate.class);
        }
    }

    @Override
    public void onMenuItemClick(int viewId) {
        int tag = 0;
        switch (viewId) {
            //活动
            case R.id.ll_menu_activity:
                tag = 0;
                break;
            //我
            case R.id.ll_menu_my:
                tag = 1;
                break;
        }
        getSupportDelegate().showHideFragment(mDelegate[tag], mDelegate[mIndexDelegate]);
        mIndexDelegate = tag;
    }

    @Override
    public boolean onBackPressedSupport() {
        if (System.currentTimeMillis() - TOUCH_TIME < WAIT_TIME) {
            return false;
        } else {
            TOUCH_TIME = System.currentTimeMillis();
            ToastUtils.showShort("在按一次退出应用");
            return true;
        }
    }
}
