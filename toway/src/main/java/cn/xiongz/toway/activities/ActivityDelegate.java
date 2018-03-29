package cn.xiongz.toway.activities;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;

import com.alibaba.fastjson.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.WeakHashMap;
import java.util.logging.Logger;

import butterknife.BindView;
import cn.xiongz.toway.R;
import cn.xiongz.toway.common.net.ConstUrl;
import cn.xiongz.toway.publish.PublishDelegate;
import cn.xz.core.delegates.XzDelegate;
import cn.xz.core.net.rx.ObserverProxy;
import cn.xz.core.net.rx.RxNetClient;
import cn.xz.core.util.json.FastjsonUtil;
import cn.xz.core.util.log.XzLogger;
import cn.xz.core.util.string.StringUtil;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * 活动页面
 * Created by xiongz on 2018/3/28.
 */
public class ActivityDelegate extends XzDelegate implements Toolbar.OnMenuItemClickListener {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.rv_activity)
    RecyclerView rvActivity;

    //活动列表
    private List<ActivityEntity> mDatas = new ArrayList<>();

    /**
     * Instance
     *
     * @return
     */
    public static ActivityDelegate newInstance() {
        ActivityDelegate fragment = new ActivityDelegate();
        return fragment;
    }

    @Override
    public Object setLayout() {
        return R.layout.delegate_activity;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, @NonNull View rootView) {
        setSwipeBackEnable(false);

        toolbar.inflateMenu(R.menu.activity);
        toolbar.setOnMenuItemClickListener(this);

        getActivityList();
    }

    @Override
    public boolean onMenuItemClick(MenuItem item) {
        switch (item.getItemId()) {
            //发布活动
            case R.id.menu_publish_activity:
                getParentDelegate().start(PublishDelegate.newInstance());
                return true;
            default:
                return false;
        }
    }

    /**
     * 获取活动列表信息
     */
    public void getActivityList() {
        final WeakHashMap<String, Object> params = new WeakHashMap<>();
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
                        parseActivityList(s);
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
     * 解析活动列表信息数据
     */
    private void parseActivityList(String response) {
        JSONObject jsonObject = FastjsonUtil.parseObject(response);
        final int code = jsonObject.getInteger("code");
        if (code == 200) {
            final String data = jsonObject.getJSONObject("data").getString("data");
            List<ActivityEntity> shopList = FastjsonUtil.parseArray(data, ActivityEntity.class);

        }
    }
}
