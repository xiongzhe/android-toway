package cn.xz.core.net.rx;

import android.support.v4.util.ArrayMap;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import io.reactivex.disposables.Disposable;

/**
 * Rx网络请求api管理类
 * Created by xiongz on 2017/12/18.
 */
public class RxApiManager implements RxActionManager<Object> {

    private static RxApiManager mInstance = null;

    //网络请求key值列表
    private List<String> keys = new ArrayList<>();

    //请求列表
    private ArrayMap<Object, Disposable> mMap;

    /**
     * 获取单例
     *
     * @return
     */
    public static RxApiManager getInstance() {
        if (mInstance == null) {
            synchronized (RxApiManager.class) {
                if (mInstance == null) {
                    mInstance = new RxApiManager();
                }
            }
        }
        return mInstance;
    }

    private RxApiManager() {
        mMap = new ArrayMap<>();
    }

    @Override
    public void add(Object tag, Disposable disposable) {
        mMap.put(tag, disposable);
    }

    @Override
    public void remove(Object tag) {
        if (!mMap.isEmpty()) {
            mMap.clear();
        }
    }

    @Override
    public void cancel(Object tag) {
        if (mMap.isEmpty()) {
            return;
        }
        if (mMap.get(tag) == null) {
            return;
        }
        if (!mMap.get(tag).isDisposed()) {
            mMap.get(tag).dispose();
            mMap.remove(tag);
        }
    }

    @Override
    public void cancelAll() {
        if (mMap.isEmpty()) {
            return;
        }
        Set<Object> keys = mMap.keySet();
        for (Object apiKey : keys) {
            cancel(apiKey);
        }
    }
}
