package cn.xz.core.delegates;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.gyf.barlibrary.ImmersionBar;
import com.orhanobut.logger.Logger;

import cn.xz.core.mvp.presenter.BasePresenter;
import cn.xz.core.mvp.proxy.BaseMvpProxy;
import cn.xz.core.mvp.view.BaseView;
import cn.xz.core.mvp.factory.PresenterMvpFactory;
import cn.xz.core.mvp.factory.PresenterMvpFactoryImpl;
import cn.xz.core.mvp.proxy.PresenterProxyInterface;

/**
 * App fragment
 * Created by xiongz on 2017/12/9.
 */
public abstract class XzDelegate<V extends BaseView, P extends BasePresenter<V>>
        extends PermissionCheckerDelegate implements PresenterProxyInterface<V, P> {

    private static final String PRESENTER_SAVE_KEY = "presenter_save_key";
    //创建被代理对象,传入默认Presenter的工厂
    private BaseMvpProxy<V, P> mProxy = new BaseMvpProxy<>(PresenterMvpFactoryImpl.<V, P>createFactory(getClass()));

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        //绑定view
        if (this instanceof BaseView) {
            mProxy.onAttach((V) this);
        }
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //保存数据
        if (savedInstanceState != null) {
            mProxy.onRestoreInstanceState(savedInstanceState.getBundle(PRESENTER_SAVE_KEY));
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        Logger.d("perfect-mvp", "V onResume");
        if (!mProxy.ismIsAttchView() && this instanceof BaseView) {
            mProxy.onResume((V) this);
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Logger.d("perfect-mvp", "V onDestroy = ");
        if (this instanceof BaseView) {
            mProxy.onDestroy();
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        Logger.d("perfect-mvp", "V onSaveInstanceState");
        if (this instanceof BaseView) {
            outState.putBundle(PRESENTER_SAVE_KEY, mProxy.onSaveInstanceState());
        }
    }

    @Override
    public void setPresenterFactory(PresenterMvpFactory<V, P> presenterFactory) {
        Logger.d("perfect-mvp", "V setPresenterFactory");
        if (this instanceof BaseView) {
            mProxy.setPresenterFactory(presenterFactory);
        }
    }

    @Override
    public PresenterMvpFactory<V, P> getPresenterFactory() {
        Logger.d("perfect-mvp", "V getPresenterFactory");
        if (this instanceof BaseView) {
            return mProxy.getPresenterFactory();
        } else {
            return null;
        }
    }

    @Override
    public P getPresenter() {
        Logger.d("perfect-mvp", "V getMvpPresenter");
        if (this instanceof BaseView) {
            return mProxy.getPresenter();
        } else {
            return null;
        }
    }

    /**
     * 获取父类代理
     *
     * @param <T>
     * @return
     */
    public <T extends XzDelegate> T getParentDelegate() {
        return (T) getParentFragment();
    }
}
