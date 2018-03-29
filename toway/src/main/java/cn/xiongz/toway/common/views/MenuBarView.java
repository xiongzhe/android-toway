package cn.xiongz.toway.common.views;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import cn.xiongz.toway.R;

/**
 * 主界面底部导航
 * Created by xiongz on 2015/10/28.
 */
public class MenuBarView extends FrameLayout implements View.OnClickListener {

    private TextView[] tvs;
    private View[] layouts;
    private View[] ivParents;
    private ImageView[] imageViews;
    private int[] normalIds = {R.drawable.ic_activity_normal, R.drawable.ic_my_normal};
    private int[] pressIds = {R.drawable.ic_activity_press, R.drawable.ic_my_press};
    private OnMenuItemClickListener mClickListener;
    private Context mContext;

    /**
     * @param context
     */
    public MenuBarView(Context context) {
        super(context);
        initView(context);
    }

    /**
     * @param context
     * @param attributeSet
     */
    public MenuBarView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        initView(context);
    }

    /**
     * 初始化
     *
     * @param context
     */
    private void initView(Context context) {
        mContext = context;
        int size = normalIds.length;
        tvs = new TextView[size];
        layouts = new View[size];
        ivParents = new View[size];
        imageViews = new ImageView[size];

        View view = LayoutInflater.from(context).inflate(R.layout.view_menu_bar, this, true);
        layouts[0] = view.findViewById(R.id.ll_menu_activity);
        ivParents[0] = view.findViewById(R.id.fl_menu_activity);
        tvs[0] = view.findViewById(R.id.tv_menu_activity);
        imageViews[0] = view.findViewById(R.id.iv_menu_activity);
        layouts[0].setOnClickListener(this);
        layouts[1] = view.findViewById(R.id.ll_menu_my);
        ivParents[1] = view.findViewById(R.id.fl_menu_my);
        tvs[1] = view.findViewById(R.id.tv_menu_my);
        imageViews[1] = view.findViewById(R.id.iv_menu_my);
        layouts[1].setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int viewId = v.getId();
        setMenuBackgroud(viewId);
        mClickListener.onMenuItemClick(viewId);
    }

    /**
     * 改变menu背景
     *
     * @param viewId
     */
    private void setMenuBackgroud(int viewId) {
        for (int i = 0; i < layouts.length; i++) {
            Drawable drawableTop;
            if (layouts[i].getId() == viewId) {
                layouts[i].setBackgroundResource(R.color.white);
                tvs[i].setTextColor(getResources().getColor(R.color.c_app_theme));
                drawableTop = getResources().getDrawable(pressIds[i]);
            } else {
                layouts[i].setBackgroundResource(R.color.white);
                tvs[i].setTextColor(getResources().getColor(R.color.c_333333));
                drawableTop = getResources().getDrawable(normalIds[i]);
            }
            drawableTop.setBounds(0, 0, drawableTop.getMinimumWidth(), drawableTop.getMinimumHeight());
            //tvs[i].setCompoundDrawables(null, drawableTop, null, null);
            imageViews[i].setImageDrawable(drawableTop);
        }
    }

    public interface OnMenuItemClickListener {
        void onMenuItemClick(int viewId);
    }

    public void setOnMenuItemClickListener(OnMenuItemClickListener onMenuItemClickListener) {
        mClickListener = onMenuItemClickListener;
    }

    /**
     * 设置当前item
     *
     * @param position 0~1
     */
    public void setCurrentMenuItem(int position) {
        if (position < 0 || position > normalIds.length) {
            position = 0;
        }
        onClick(layouts[position]);
    }

    /**
     * 设置按钮未选中和选中时的图片
     *
     * @param normalIds
     */
    public void setImgIds(int[] normalIds, int[] pressIds) {
        this.normalIds = normalIds;
        this.pressIds = pressIds;
        this.invalidate();
    }
}
