package cn.xz.core.mvp.presenter;

import android.os.Bundle;
import android.support.annotation.Nullable;

import cn.xz.core.mvp.view.BaseView;
import cn.xz.core.util.log.XzLogger;

/**
 * 所有BasePresenter基类 负责presenter生命周期
 * 指定绑定的View必须继承自BaseView
 * Created by xiongz on 2017/12/18.
 */
public abstract class BasePresenter<V extends BaseView> implements IBasePresenter {

    private V mView;

    /**
     * Presenter被创建后调用
     *
     * @param savedState 被意外销毁后重建后的Bundle
     */
    public void onCreatePersenter(@Nullable Bundle savedState) {
        XzLogger.d("perfect-mvp", "P onCreatePersenter = ");
    }

    /**
     * 绑定View
     */
    public void onAttachMvpView(V view) {
        mView = view;
        XzLogger.d("perfect-mvp", "P onResume");
    }

    /**
     * 解除绑定View
     */
    public void onDetachMvpView() {
        mView = null;
        XzLogger.d("perfect-mvp", "P onDetachMvpView = ");
    }

    /**
     * Presenter被销毁时调用
     */
    public void onDestroyPersenter() {
        XzLogger.d("perfect-mvp", "P onDestroy = ");
    }

    /**
     * 在Presenter意外销毁的时候被调用，它的调用时机和Activity、Fragment、View中的onSaveInstanceState
     * 时机相同
     *
     * @param outState
     */
    public void onSaveInstanceState(Bundle outState) {
        XzLogger.d("perfect-mvp", "P onSaveInstanceState = ");
    }

    /**
     * 获取V层接口View
     *
     * @return 返回当前MvpView
     */
    public V getView() {
        return mView;
    }
}
