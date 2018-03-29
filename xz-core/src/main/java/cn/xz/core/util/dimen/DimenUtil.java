package cn.xz.core.util.dimen;

import android.content.res.Resources;
import android.util.DisplayMetrics;

import cn.xz.core.app.Xz;

/**
 * dimen工具类
 * Created by xiong on 2017/12/13
 */
public final class DimenUtil {

    /**
     * 获取屏幕宽
     * @return
     */
    public static int getScreenWidth() {
        final Resources resources = Xz.getApplicationContext().getResources();
        final DisplayMetrics dm = resources.getDisplayMetrics();
        return dm.widthPixels;
    }

    /**
     * 获取屏幕高
     * @return
     */
    public static int getScreenHeight() {
        final Resources resources = Xz.getApplicationContext().getResources();
        final DisplayMetrics dm = resources.getDisplayMetrics();
        return dm.heightPixels;
    }
}
