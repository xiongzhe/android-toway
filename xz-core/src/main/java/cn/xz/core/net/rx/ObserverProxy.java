package cn.xz.core.net.rx;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * Observer代理类（封装Observer实现类，可对网络请求做一些公共的处理，eg:加上loader处理）
 * Created by xiongz on 2017/12/15.
 */
public class ObserverProxy implements Observer<String> {

    //订阅key（这里把网络请求url作为key值得）
    private String mKey;
    //观察者
    private Observer mObserver = null;

    public ObserverProxy(String key, Observer observer) {
        mKey = key;
        mObserver = observer;
    }

    @Override
    public void onSubscribe(Disposable d) {
        RxApiManager.getInstance().add(mKey, d);
        mObserver.onSubscribe(d);
    }

    @Override
    public void onNext(String s) {
        mObserver.onNext(s);
    }

    @Override
    public void onError(Throwable e) {
        mObserver.onError(e);
    }

    @Override
    public void onComplete() {
        mObserver.onComplete();
    }
}
