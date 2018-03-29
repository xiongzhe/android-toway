package cn.xz.core.mvp.factory;

import cn.xz.core.mvp.presenter.BasePresenter;
import cn.xz.core.mvp.view.BaseView;

/**
 * Presenter工厂接口
 * Created by xiongz on 2017/12/18.
 */
public interface PresenterMvpFactory<V extends BaseView, P extends BasePresenter<V>> {

    /**
     * 创建Presenter的接口方法
     *
     * @return 需要创建的Presenter
     */
    P createMvpPresenter();
}
