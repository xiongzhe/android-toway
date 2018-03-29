package cn.xiongz.toway.publish;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.View;

import butterknife.BindView;
import cn.xiongz.toway.R;
import cn.xz.core.delegates.XzDelegate;

/**
 * 发布活动页面
 * Created by xiongz on 2018/3/28.
 */
public class PublishDelegate extends XzDelegate {

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    /**
     * Instance
     *
     * @return
     */
    public static PublishDelegate newInstance() {
        PublishDelegate fragment = new PublishDelegate();
        return fragment;
    }

    @Override
    public Object setLayout() {
        return R.layout.delegate_publish;
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
