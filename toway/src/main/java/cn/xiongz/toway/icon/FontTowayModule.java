package cn.xiongz.toway.icon;

import com.joanzapata.iconify.Icon;
import com.joanzapata.iconify.IconFontDescriptor;

/**
 * 突围活动自定义字体图标库module
 * Created by xiongz on 2017/12/10
 */
public class FontTowayModule implements IconFontDescriptor {
    @Override
    public String ttfFileName() {
        return "iconfont.ttf";
    }

    @Override
    public Icon[] characters() {
        return TowayIcons.values();
    }
}
