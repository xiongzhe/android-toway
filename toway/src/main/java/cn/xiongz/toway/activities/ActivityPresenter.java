package cn.xiongz.toway.activities;

import java.util.List;

import cn.xz.core.mvp.presenter.BasePresenter;

/**
 * 首页活动列表 P层
 * Created by xiongz on 2017/12/15.
 */
public class ActivityPresenter extends BasePresenter<ActivityContract.View> implements ActivityContract.Presenter {

    private ActivityModel mModel;

    //当前页数
    private int mPage = 1;
    //总记录数
    private int mTotal;

    public ActivityPresenter() {
        this.mModel = new ActivityModel();
    }

    /**
     * 初始化活动列表
     */
    public void init() {
        mPage = 1;
        mModel.getActivityData(this, mPage);
    }

    @Override
    public void getActivityData(List<ActivityEntity> data, int total) {
        if (data != null) {
            mTotal = total;
            getView().setRecyclerView(data, mTotal);
        }
    }

    /**
     * 加载更多
     */
    public void loadMore() {
        mPage++;
        mModel.getActivityData(this, mPage);
    }
}
