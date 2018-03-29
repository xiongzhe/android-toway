package cn.xz.ui.recycler;

import android.view.View;

import com.chad.library.adapter.base.BaseViewHolder;

/**
 * recyclerView公共ViewHolder
 * Created by xiongz on 2018/1/4.
 */
public class ViewHolder extends BaseViewHolder {

    public ViewHolder(View view) {
        super(view);
    }

    public static ViewHolder create(View view) {
        return new ViewHolder(view);
    }
}
