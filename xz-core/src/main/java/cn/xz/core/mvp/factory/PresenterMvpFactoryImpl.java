package cn.xz.core.mvp.factory;

import cn.xz.core.mvp.presenter.BasePresenter;
import cn.xz.core.mvp.view.BaseView;

/**
 * 指定子类具体的View必须继承自MvpBaseView
 * 指定子类具体的Presenter必须继承自BasePresenter
 * Created by xiongz on 2017/12/18.
 */
public class PresenterMvpFactoryImpl<V extends BaseView, P extends BasePresenter<V>> implements PresenterMvpFactory<V, P> {

    /**
     * 需要创建的Presenter的类型
     */
    private final Class<P> mPresenterClass;

    /**
     * 根据注解创建Presenter的工厂实现类
     *
     * @param viewClazz 需要创建Presenter的V层实现类
     * @param <V>       当前View实现的接口类型
     * @param <P>       当前要创建的Presenter类型
     * @return 工厂类
     */
    public static <V extends BaseView, P extends BasePresenter<V>> PresenterMvpFactoryImpl<V, P> createFactory(Class<?> viewClazz) {
        CreatePresenter annotation = viewClazz.getAnnotation(CreatePresenter.class);
        Class<P> aClass = null;
        if (annotation != null) {
            aClass = (Class<P>) annotation.value();
        }
        return aClass == null ? null : new PresenterMvpFactoryImpl<>(aClass);
    }

    private PresenterMvpFactoryImpl(Class<P> presenterClass) {
        this.mPresenterClass = presenterClass;
    }

    @Override
    public P createMvpPresenter() {
        try {
            return mPresenterClass.newInstance();
        } catch (Exception e) {
            throw new RuntimeException("Presenter创建失败!，检查是否声明了@CreatePresenter(xx.class)注解");
        }
    }
}
