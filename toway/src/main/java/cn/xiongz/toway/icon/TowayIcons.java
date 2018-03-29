package cn.xiongz.toway.icon;

import com.joanzapata.iconify.Icon;

/**
 * 突围活动图标库
 * Created by xiongz on 2017/12/10
 */
public enum TowayIcons implements Icon {

    icon_scan('\ue602'),//扫描
    icon_ali_pay('\ue606');//阿里支付

    private char character;

    TowayIcons(char character) {
        this.character = character;
    }

    @Override
    public String key() {
        return name().replace('_', '-');
    }

    @Override
    public char character() {
        return character;
    }
}
