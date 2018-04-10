package cn.xiongz.toway.activities;

import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;

import java.util.List;

import cn.xiongz.toway.R;
import cn.xiongz.toway.common.constant.GlideOptions;
import cn.xz.core.util.log.XzLogger;
import cn.xz.ui.image.GlideUtil;
import cn.xz.ui.recycler.ViewHolder;

/**
 * 搜索店铺列表适配器
 * Created by xiongz on 2018/1/4.
 */
public class ActivityAdapter extends BaseQuickAdapter<ActivityEntity, ViewHolder> {

    public ActivityAdapter(int layoutResId, List<ActivityEntity> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(ViewHolder helper, ActivityEntity item) {
        XzLogger.e("position", helper.getAdapterPosition() + "position");
        ImageView headImg = helper.getView(R.id.iv_item_activity_head_img);
        GlideUtil.withOption(mContext, item.getUser().getAvatar_url(), headImg, GlideOptions.options);
        helper.setText(R.id.tv_item_activity_name, item.getUser().getName());
        helper.setText(R.id.tv_item_activity_publish_time, item.getStart_date_text());
        ImageView img = helper.getView(R.id.iv_item_activity_img);
        GlideUtil.withOption(mContext, item.getPic(), img, GlideOptions.options);
        helper.setText(R.id.tv_item_activity_address, item.getAddress());
        helper.setText(R.id.tv_item_activity_title, item.getTitle());
        helper.setText(R.id.tv_item_activity_time, item.getActivity_date_text());
        String priceStr = item.getPrice();
        if (TextUtils.isEmpty(priceStr)) {
            helper.setText(R.id.tv_item_activity_fee, "免费");
        } else {
            Float price = Float.parseFloat(priceStr);
            if (price > 0) {
                helper.setText(R.id.tv_item_activity_fee, "¥" + priceStr + "元/人");
            } else {
                helper.setText(R.id.tv_item_activity_fee, "免费");
            }
        }
        helper.setText(R.id.tv_item_activity_apply, item.getNum() + "");
        int total = item.getTotal();
        if (total == 0) { //0表示报名人数没有限制
            helper.setText(R.id.tv_item_activity_total, "/∞人报名");
        } else {
            helper.setText(R.id.tv_item_activity_total, "/" + item.getTotal() + "人报名");
        }
        int status = item.getStatus();
        TextView textView = helper.getView(R.id.tv_item_activity_status);
        if (status == 1) {
            textView.setVisibility(View.VISIBLE);
            helper.setText(R.id.tv_item_activity_status, "报名中");
            helper.setTextColor(R.id.tv_item_activity_status, mContext.getResources().getColor(R.color.c_app_theme));
        } else if (status == 2) {
            textView.setVisibility(View.VISIBLE);
            helper.setText(R.id.tv_item_activity_status, "活动中");
            helper.setTextColor(R.id.tv_item_activity_status, mContext.getResources().getColor(R.color.c_app_theme));
        } else if (status == 3) {
            textView.setVisibility(View.VISIBLE);
            helper.setText(R.id.tv_item_activity_status, "已结束");
            helper.setTextColor(R.id.tv_item_activity_status, mContext.getResources().getColor(R.color.c_808082));
        } else {
            textView.setVisibility(View.GONE);
        }
    }
}
