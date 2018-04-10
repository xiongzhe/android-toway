package cn.xiongz.toway.activities;

import com.alibaba.fastjson.JSONObject;

import java.util.List;
import java.util.WeakHashMap;

import cn.xiongz.toway.common.net.ConstUrl;
import cn.xz.core.net.rx.ObserverProxy;
import cn.xz.core.net.rx.RxNetClient;
import cn.xz.core.util.json.FastjsonUtil;
import cn.xz.core.util.log.XzLogger;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * 首页活动列表 处理
 * Created by xiongz on 2017/12/14.
 */
public class ActivityModel {

    /**
     * 获取活动列表信息
     */
    public void getActivityData(final ActivityContract.Presenter presenter, int page) {
        final WeakHashMap<String, Object> params = new WeakHashMap<>();
        params.put("page", page);
        params.put("page_size", 10);
        String url = ConstUrl.ACTIVITY_LIST;
        RxNetClient.builder()
                .url(url)
                .params(params)
                .build()
                .get()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new ObserverProxy(url, new Observer<String>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                    }

                    @Override
                    public void onNext(String s) {
                        parseActivityList(s, presenter);
                    }

                    @Override
                    public void onError(Throwable e) {
                        XzLogger.e("result", e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                }));
    }

    /**
     * 解析是否通过审核数据
     */
    private void parseActivityList(String response, final ActivityContract.Presenter presenter) {
        JSONObject jsonObject = FastjsonUtil.parseObject(response);
        final int code = jsonObject.getInteger("code");
        if (code == 200) {
            final String data = jsonObject.getJSONObject("data").getString("data");
            List<ActivityEntity> activityList = FastjsonUtil.parseArray(data, ActivityEntity.class);
            final int total = jsonObject.getJSONObject("data").getInteger("total");
            presenter.getActivityData(activityList, total);
        }
    }
}
