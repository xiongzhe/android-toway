package cn.xz.core.mvp.factory;

import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import cn.xz.core.mvp.presenter.BasePresenter;

/**
 * 标注创建Presenter的注解
 * Created by xiongz on 2017/12/18.
 */
@Inherited
@Retention(RetentionPolicy.RUNTIME)
public @interface CreatePresenter {

    Class<? extends BasePresenter> value();
}
