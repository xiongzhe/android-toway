package cn.xiongz.toway.activities.detail;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.View;

import butterknife.BindView;
import cn.xiongz.toway.R;
import cn.xz.core.delegates.XzDelegate;

/**
 * 活动详情页面
 * Created by xiongz on 2018/3/28.
 */
public class DetailDelegate extends XzDelegate {

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    /**
     * Instance
     *
     * @return
     */
    public static DetailDelegate newInstance() {
        DetailDelegate fragment = new DetailDelegate();
        return fragment;
    }

    @Override
    public Object setLayout() {
        return R.layout.delegate_detail;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, @NonNull View rootView) {
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pop();
            }
        });
    }
}
