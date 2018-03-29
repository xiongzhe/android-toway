package cn.xz.core.delegates.web.event;

import android.support.annotation.NonNull;

import java.util.HashMap;

/**
 * webview js与原生事件管理
 * Created by xiongz on 2018/3/6.
 */
public class EventManager {

    private static final HashMap<String, Event> EVENTS = new HashMap<>();

    private EventManager() {

    }

    private static class Holder {
        private static final EventManager INSTANCE = new EventManager();
    }

    public static EventManager getInstance() {
        return Holder.INSTANCE;
    }

    /**
     * 添加事件
     * @param name
     * @param event
     * @return
     */
    public EventManager addEvent(@NonNull String name, @NonNull Event event) {
        EVENTS.put(name, event);
        return this;
    }

    /**
     * 创建事件
     * @param action
     * @return
     */
    public Event createEvent(@NonNull String action) {
        final Event event = EVENTS.get(action);
        if (event == null) {
            return new UndefindEvent();
        }
        return event;
    }
}
