package cn.xz.core.delegates.web.event;

import cn.xz.core.util.log.XzLogger;

/**
 * 找不到事件
 * Created by xiongz on 2018/3/6.
 */
public class UndefindEvent extends Event {

    @Override
    public String execute(String params) {
        XzLogger.e("UndefindEvent", params);
        return null;
    }
}
