package cn.xiongz.toway.activities;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;

import com.blankj.utilcode.util.ToastUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import cn.xiongz.toway.R;
import cn.xiongz.toway.activities.detail.DetailDelegate;
import cn.xiongz.toway.publish.PublishDelegate;
import cn.xz.core.delegates.XzDelegate;
import cn.xz.core.mvp.factory.CreatePresenter;
import cn.xz.core.ui.loader.XzLoader;
import cn.xz.ui.refresh.RefreshHandler;

/**
 * 活动页面
 * Created by xiongz on 2018/3/28.
 */
@CreatePresenter(ActivityPresenter.class)
public class ActivityDelegate extends XzDelegate<ActivityContract.View, ActivityPresenter>
        implements ActivityContract.View, Toolbar.OnMenuItemClickListener, SwipeRefreshLayout.OnRefreshListener {

    @BindView(R.id.swipe_activity)
    SwipeRefreshLayout swipeRefresh;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.rv_activity)
    RecyclerView rvActivity;
    //刷新
    private RefreshHandler mRefreshHandler;
    //列表适配器
    private ActivityAdapter mActivityAdapter;
    //数据条数
    private int mSize = 0;

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
        final LinearLayoutManager manager = new LinearLayoutManager(getContext());
        rvActivity.setLayoutManager(manager);
        rvActivity.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL));
        mRefreshHandler = new RefreshHandler(swipeRefresh, this);

        XzLoader.showLoading(getContext());
        getPresenter().init();
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

    @Override
    public void setRecyclerView(List<ActivityEntity> data, int total) {
        if (mActivityAdapter == null) {
            mSize = 0;
            mActivityAdapter = new ActivityAdapter(R.layout.item_recycler_activity, data);
            mActivityAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
                @Override
                public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                    getParentDelegate().start(DetailDelegate.newInstance());
                }
            });
            mActivityAdapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
                @Override
                public void onLoadMoreRequested() {
                    getPresenter().loadMore();
                }
            }, rvActivity);
            rvActivity.setAdapter(mActivityAdapter);
        } else {
            if (mSize >= total) {
                mActivityAdapter.loadMoreEnd();
            } else {
                mActivityAdapter.addData(data);
                mSize = mActivityAdapter.getData().size();
                mActivityAdapter.loadMoreComplete();
            }
        }
        mRefreshHandler.stopRefreshNow();
        XzLoader.stopLoading();
    }

    @Override
    public void onRefresh() {
        mActivityAdapter = null;
        getPresenter().init();
        mRefreshHandler.stopRefresh();
    }
}
