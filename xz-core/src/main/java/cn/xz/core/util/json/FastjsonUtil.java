package cn.xz.core.util.json;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import java.util.List;

/**
 * Fastjson工具类
 * Created by xiongz on 2017/12/19.
 */
public class FastjsonUtil {

    /**
     * 解析jsonObject
     *
     * @param text
     * @return
     */
    public static final JSONObject parseObject(String text) {
        return JSON.parseObject(text);
    }

    /**
     * 解析jsonObject
     *
     * @param text
     * @param clazz
     * @param <T>
     * @return
     */
    public static final <T> T parseObject(String text, Class<T> clazz) {
        return JSON.parseObject(text, clazz);
    }

    /**
     * 解析jsonArray
     *
     * @param text
     * @param clazz
     * @param <T>
     * @return
     */
    public static final <T> List<T> parseArray(String text, Class<T> clazz) {
        return JSON.parseArray(text, clazz);
    }
}
