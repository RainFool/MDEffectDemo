package com.rainfool.md.spannable;

import android.content.Context;
import android.os.Build;
import android.support.v7.widget.AppCompatTextView;
import android.text.Layout;
import android.text.SpannedString;
import android.text.style.ClickableSpan;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * No pains, no gains.
 * <p>
 * Created by Rod on 2017/11/25.
 */

public class ClickableSpanTextView extends AppCompatTextView {
    private ClickableSpan mClickableSpan;

    public ClickableSpanTextView(Context context) {
        this(context,null);
    }

    public ClickableSpanTextView(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public ClickableSpanTextView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            setLayoutDirection(LAYOUT_DIRECTION_LTR);
            setTextDirection(TEXT_DIRECTION_LTR);
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        CharSequence text = getText();
        if (!(text instanceof SpannedString)) {
            return super.onTouchEvent(event);
        }

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                mClickableSpan = getClickableSpan(event);
                updateClickSpanState(true);
                return mClickableSpan != null || super.onTouchEvent(event);
            case MotionEvent.ACTION_MOVE:
                return mClickableSpan != null || super.onTouchEvent(event);
            case MotionEvent.ACTION_UP:
                if (mClickableSpan != null) {
                    if (mClickableSpan == getClickableSpan(event)) {
                        mClickableSpan.onClick(this);
                    }
                    updateClickSpanState(false);
                    return true;
                } else {
                    return super.onTouchEvent(event);
                }
            case MotionEvent.ACTION_CANCEL:
            default:
                if (mClickableSpan != null) {
                    updateClickSpanState(false);
                    mClickableSpan = null;
                }
                return super.onTouchEvent(event);
        }
    }

    private void updateClickSpanState(boolean pressed) {
        if (mClickableSpan instanceof CustomClickableSpan) {
            ((CustomClickableSpan) mClickableSpan).setPressed(pressed);
            invalidate();
        }
    }

    private ClickableSpan getClickableSpan(MotionEvent event) {
        int x = (int) event.getX();
        int y = (int) event.getY();

        x -= getTotalPaddingLeft();
        y -= getTotalPaddingTop();

        x += getScrollX();
        y += getScrollY();

        Layout layout = getLayout();
        int line = layout.getLineForVertical(y);
        int off = layout.getOffsetForHorizontal(line, x);

        ClickableSpan[] link = ((SpannedString) getText()).getSpans(off, off, ClickableSpan.class);

        return link.length > 0 ? link[0] : null;
    }
}
