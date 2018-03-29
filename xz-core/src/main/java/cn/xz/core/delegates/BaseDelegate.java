package cn.xz.core.delegates;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.blankj.utilcode.util.KeyboardUtils;
import com.gyf.barlibrary.ImmersionBar;
import com.hwangjr.rxbus.Bus;
import com.hwangjr.rxbus.RxBus;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import cn.xz.core.R;
import cn.xz.core.activities.ProxyActivity;
import cn.xz.core.net.rx.RxApiManager;
import me.yokeyword.fragmentation.ExtraTransaction;
import me.yokeyword.fragmentation.ISupportFragment;
import me.yokeyword.fragmentation.SupportFragmentDelegate;
import me.yokeyword.fragmentation.anim.FragmentAnimator;
import me.yokeyword.fragmentation_swipeback.SwipeBackFragment;

/**
 * Fragment基类
 * Created by xiongz on 2017/10/12
 */
public abstract class BaseDelegate extends SwipeBackFragment {

    //delegate
    private final SupportFragmentDelegate DELEGATE = new SupportFragmentDelegate(this);
    //Activity
    protected FragmentActivity _mActivity = null;
    //ButterKnife绑定
    private Unbinder mUnbinder = null;
    //状态栏
    private ImmersionBar mImmersionBar;
    //是否注册rxBus
    protected boolean mIsRegisterBus;
    private Bus mBus;

    public abstract Object setLayout();

    public abstract void onBindView(@Nullable Bundle savedInstanceState, @NonNull View rootView);

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        DELEGATE.onAttach((Activity) context);
        _mActivity = DELEGATE.getActivity();
        //rxBus注册
        if (mIsRegisterBus) {
            mBus = RxBus.get();
            mBus.register(this);
        }
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        DELEGATE.onCreate(savedInstanceState);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        DELEGATE.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        DELEGATE.onSaveInstanceState(outState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View rootView;
        if (setLayout() instanceof Integer) {
            rootView = inflater.inflate((int) setLayout(), container, false);
        } else if (setLayout() instanceof View) {
            rootView = (View) setLayout();
        } else {
            throw new ClassCastException("type of setLayout() must be int or View!");
        }

        //设置状态栏
        setStatusBar(rootView);

        //绑定
        mUnbinder = ButterKnife.bind(this, rootView);
        onBindView(savedInstanceState, rootView);

        return attachToSwipeBack(rootView);
    }

    /**
     * 设置状态栏
     * 有toolbar时，显示状态栏，没有toolbar时，不显示状态栏
     */
    protected void setStatusBar(View view) {
        if (view != null) {
            View titleBar = view.findViewById(R.id.toolbar);
            if (titleBar != null) {
                ImmersionBar.setTitleBar(_mActivity, titleBar);
            }
        }
    }

    /**
     * 设置标题
     */
    protected void setTitle(View view, String title) {
        Toolbar toolbar = view.findViewById(R.id.toolbar);
        TextView tvTitle = view.findViewById(R.id.tv_toolbar_title);
        tvTitle.setText(title);
        toolbar.setNavigationIcon(R.drawable.ic_back_white);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pop();
            }
        });
    }

    /**
     * 获取整个App唯一一个Activity
     *
     * @return
     */
    public final ProxyActivity getProxyActivity() {
        return (ProxyActivity) _mActivity;
    }

    @Override
    public void onResume() {
        super.onResume();
        DELEGATE.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        DELEGATE.onPause();
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        DELEGATE.onHiddenChanged(hidden);
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        DELEGATE.setUserVisibleHint(isVisibleToUser);
    }

    @Override
    public void onDestroyView() {
        //设置键盘消失
        KeyboardUtils.hideSoftInput(_mActivity);
        //状态栏
        if (mImmersionBar != null) {
            mImmersionBar.destroy();
        }
        //销毁rxBus
        if (mIsRegisterBus && mBus != null) {
            mBus.unregister(this);
        }
        //解绑
        if (mUnbinder != null) {
            mUnbinder.unbind();
        }
        //取消网络请求
        RxApiManager rxApiManager = RxApiManager.getInstance();
        if (rxApiManager != null) {
            rxApiManager.cancelAll();
        }

        super.onDestroyView();
    }

    @Override
    public SupportFragmentDelegate getSupportDelegate() {
        return DELEGATE;
    }

    @Override
    public ExtraTransaction extraTransaction() {
        return DELEGATE.extraTransaction();
    }

    @Override
    public void enqueueAction(Runnable runnable) {
        DELEGATE.enqueueAction(runnable);
    }

    @Override
    public void onEnterAnimationEnd(@Nullable Bundle savedInstanceState) {
        DELEGATE.onEnterAnimationEnd(savedInstanceState);
    }

    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        DELEGATE.onLazyInitView(savedInstanceState);
    }

    @Override
    public void onSupportVisible() {

        //如果要在Fragment单独使用沉浸式，请在onSupportVisible实现沉浸式
        mImmersionBar = ImmersionBar.with(this);
        mImmersionBar.keyboardEnable(true);//解决软键盘与底部输入框冲突问题
        mImmersionBar.navigationBarWithKitkatEnable(false).init();

        DELEGATE.onSupportVisible();
    }

    @Override
    public void onSupportInvisible() {
        DELEGATE.onSupportInvisible();
    }

    @Override
    public FragmentAnimator onCreateFragmentAnimator() {
        return DELEGATE.onCreateFragmentAnimator();
    }

    @Override
    public FragmentAnimator getFragmentAnimator() {
        return DELEGATE.getFragmentAnimator();
    }

    @Override
    public void setFragmentAnimator(FragmentAnimator fragmentAnimator) {
        DELEGATE.setFragmentAnimator(fragmentAnimator);
    }

    @Override
    public void setFragmentResult(int resultCode, Bundle bundle) {
        DELEGATE.setFragmentResult(resultCode, bundle);
    }

    @Override
    public void onFragmentResult(int requestCode, int resultCode, Bundle data) {
        DELEGATE.onFragmentResult(requestCode, resultCode, data);
    }

    @Override
    public void onNewBundle(Bundle args) {
        DELEGATE.onNewBundle(args);
    }

    @Override
    public void putNewBundle(Bundle newBundle) {
        DELEGATE.putNewBundle(newBundle);
    }

    @Override
    public boolean onBackPressedSupport() {
        return DELEGATE.onBackPressedSupport();
    }

}
