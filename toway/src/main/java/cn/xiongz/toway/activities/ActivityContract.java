package cn.xiongz.toway.activities;

import java.util.List;

import cn.xz.core.mvp.presenter.IBasePresenter;
import cn.xz.core.mvp.view.BaseView;

/**
 * 首页活动列表 V层接口
 * Created by xiongz on 2018/04/08.
 */
public interface ActivityContract {

    /**
     * fragment继承
     */
    interface View extends BaseView {

        //设置店铺列表
        void setRecyclerView(List<ActivityEntity> datas, int total);
    }


    /**
     * Presenter继承
     */
    interface Presenter extends IBasePresenter {
        //获取活动信息
        void getActivityData(List<ActivityEntity> datas, int total);
    }
}

