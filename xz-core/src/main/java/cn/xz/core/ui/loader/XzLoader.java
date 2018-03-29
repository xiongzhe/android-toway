package cn.xz.core.ui.loader;

import android.content.Context;
import android.support.v7.app.AppCompatDialog;
import android.view.Gravity;
import android.view.Window;
import android.view.WindowManager;

import com.wang.avi.AVLoadingIndicatorView;

import java.util.ArrayList;

import cn.xz.core.R;
import cn.xz.core.app.ConfigKeys;
import cn.xz.core.app.Xz;
import cn.xz.core.net.NetCreator;
import cn.xz.core.util.dimen.DimenUtil;

/**
 * 加载框
 * Created by xiongz on 2017/12/13
 */
public class XzLoader {

    private static final int LOADER_SIZE_SCALE = 8;
    private static final int LOADER_OFFSET_SCALE = 10;

    //加载框列表
    private static final ArrayList<AppCompatDialog> LOADERS = new ArrayList<>();

    //默认加载框风格
    private static final String DEFAULT_LOADER = LoaderStyle.BallClipRotatePulseIndicator;

    public static void showLoading(Context context, LoaderStyle type) {
        showLoading(context, type);
    }

    public static void showLoading(Context context, String type) {

        final AppCompatDialog dialog = new AppCompatDialog(context, R.style.dialog);

        final AVLoadingIndicatorView avLoadingIndicatorView = LoaderCreator.create(type, context);
        dialog.setContentView(avLoadingIndicatorView);

        int deviceWidth = DimenUtil.getScreenWidth();
        int deviceHeight = DimenUtil.getScreenHeight();

        final Window dialogWindow = dialog.getWindow();

        if (dialogWindow != null) {
            final WindowManager.LayoutParams lp = dialogWindow.getAttributes();
            lp.width = deviceWidth / LOADER_SIZE_SCALE;
            lp.height = deviceHeight / LOADER_SIZE_SCALE;
            lp.height = lp.height + deviceHeight / LOADER_OFFSET_SCALE;
            lp.gravity = Gravity.CENTER;
        }
        LOADERS.add(dialog);
        dialog.show();
    }

    public static void showLoading(Context context) {
        showLoading(context, DEFAULT_LOADER);
    }

    /**
     * 停止加载
     */
    public static void stopLoading() {
        for (AppCompatDialog dialog : LOADERS) {
            if (dialog != null) {
                if (dialog.isShowing()) {
                    dialog.cancel();
                }
            }
        }
    }

    /**
     * 停止加载，并移除网络参数
     */
    public static void dismissDialog() {
        final long delayed = Xz.getConfiguration(ConfigKeys.LOADER_DELAYED);
        Xz.getHandler().postDelayed(new Runnable() {
            @Override
            public void run() {
                NetCreator.getParams().clear();
                XzLoader.stopLoading();
            }
        }, delayed);
    }

}
