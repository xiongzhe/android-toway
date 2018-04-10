package cn.xz.ui.refresh;

import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;

import cn.xz.ui.R;

/**
 * 刷新处理类
 * Created by xiongz on 2018/1/11.
 */
public class RefreshHandler {

    //默认刷新时间
    private static final long DEFAULT_REFRESH_TIMES = 3000;

    private SwipeRefreshLayout mSwipeRefresh;

    public RefreshHandler(SwipeRefreshLayout mSwipeRefresh,
                          SwipeRefreshLayout.OnRefreshListener onRefreshListener) {
        this.mSwipeRefresh = mSwipeRefresh;
        mSwipeRefresh.setOnRefreshListener(onRefreshListener);
        mSwipeRefresh.setColorSchemeResources(R.color.c_app_theme);
    }

    /**
     * 停止刷新
     */
    public void stopRefresh(long times) {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                // 如果 3s 后还在刷新的话，取消
                if (mSwipeRefresh != null && mSwipeRefresh.isRefreshing()) {
                    mSwipeRefresh.setRefreshing(false);
                }
            }
        }, times);
    }

    /**
     * 停止刷新
     */
    public void stopRefresh() {
        stopRefresh(DEFAULT_REFRESH_TIMES);
    }

    /**
     * 立即停止刷新
     */
    public void stopRefreshNow() {
        if (mSwipeRefresh != null && mSwipeRefresh.isRefreshing()) {
            mSwipeRefresh.setRefreshing(false);
        }
    }
}
