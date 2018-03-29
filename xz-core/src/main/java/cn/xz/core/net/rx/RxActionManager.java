package cn.xz.core.net.rx;

import io.reactivex.disposables.Disposable;

/**
 * rx模式网络请求管理接口
 * Created by xiongz on 2017/12/18.
 */
public interface RxActionManager<T> {

    void add(T tag, Disposable disposable);

    void remove(T tag);

    void cancel(T tag);

    void cancelAll();
}
