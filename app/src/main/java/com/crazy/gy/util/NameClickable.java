package com.crazy.gy.util;

import android.text.TextPaint;
import android.text.style.ClickableSpan;
import android.view.View;

import com.crazy.gy.App;
import com.crazy.gy.R;
import com.crazy.gy.app.AppApplication;
import com.crazy.gy.interfaces.ISpanClick;


/**
 * des:名字点击监听
 * Created by xsf
 * on 2016.07.11:14
 */
public class NameClickable extends ClickableSpan implements View.OnClickListener {
    private final ISpanClick mListener;
    private int mPosition;

    public NameClickable(ISpanClick l, int position) {
        mListener = l;
        mPosition = position;


    }

    @Override
    public void onClick(View widget) {
        mListener.onClick(mPosition);
    }

    @Override
    public void updateDrawState(TextPaint ds) {
        super.updateDrawState(ds);
        int colorValue = AppApplication.getAppResources().getColor(
                R.color.main_color);
        ds.setUnderlineText(false);
        ds.clearShadowLayer();
    }
}
