package cn.xz.core.mvp.proxy;

import cn.xz.core.mvp.view.BaseView;
import cn.xz.core.mvp.factory.PresenterMvpFactory;
import cn.xz.core.mvp.presenter.BasePresenter;

/**
 * Presenter代理接口
 * Created by xiongz on 2017/12/18.
 */
public interface PresenterProxyInterface<V extends BaseView, P extends BasePresenter<V>> {

    /**
     * 设置创建Presenter的工厂
     *
     * @param presenterFactory PresenterFactory类型
     */
    void setPresenterFactory(PresenterMvpFactory<V, P> presenterFactory);

    /**
     * 获取Presenter的工厂类
     *
     * @return 返回PresenterMvpFactory类型
     */
    PresenterMvpFactory<V, P> getPresenterFactory();

    /**
     * 获取创建的Presenter
     *
     * @return 指定类型的Presenter
     */
    P getPresenter();
}
