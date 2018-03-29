package cn.xz.core.util.string;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.text.Html;

import java.lang.reflect.Field;
import java.math.BigDecimal;

import cn.xz.core.R;

/**
 * 字符串工具类
 * Created by xiongz on 2017/4/19.
 */
public class StringUtil {

    /**
     * 判断字符串是否是整数
     */
    public static boolean isInteger(String value) {
        try {
            Integer.parseInt(value);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    /**
     * 判断字符串是否是浮点数
     */
    public static boolean isDouble(String value) {
        try {
            Double.parseDouble(value);
            if (value.contains("."))
                return true;
            return false;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    /**
     * 判断字符串是否是数字
     */
    public static boolean isNumber(String value) {
        return isInteger(value) || isDouble(value);
    }


    /**
     * 将double精确到小数点后一位
     *
     * @param value
     * @return
     */
    public static String formatDouble1(double value) {
        BigDecimal decimal = new BigDecimal(value);
        decimal = decimal.setScale(1, BigDecimal.ROUND_HALF_UP);
        return "" + decimal;
    }

    /**
     * 将double精确到小数点后两位
     *
     * @param value
     * @return
     */
    public static String formatDouble2(double value) {
        BigDecimal decimal = new BigDecimal(value);
        decimal = decimal.setScale(2, BigDecimal.ROUND_HALF_UP);
        return "" + decimal;
    }

    /**
     * html转化成文本
     * @param context
     * @param htmlInput
     * @return
     */
    public static CharSequence getEmolHtml(final Context context, String htmlInput) {
        CharSequence charSequence = Html.fromHtml(htmlInput, new Html.ImageGetter() {
            @Override
            public Drawable getDrawable(String source) {
                // 获得系统资源的信息，比如图片信息
                Drawable drawable = context.getResources().getDrawable(getResourceId(source));
                drawable.setBounds(0, 0, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());
                return drawable;
            }
        }, null);
        return charSequence;
    }

    /**
     * 通过反射机制获取资源id
     * @param name
     * @return
     */
    public static int getResourceId(String name) {
        try {
            // 根据资源的ID的变量名获得Field的对象,使用反射机制来实现的
            Field field = R.drawable.class.getField(name);
            // 取得并返回资源的id的字段(静态变量)的值，使用反射机制
            return Integer.parseInt(field.get(null).toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }
}
