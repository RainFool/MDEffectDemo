package com.rainfool.md.textview;

import android.annotation.SuppressLint;
import android.os.Build;
import android.os.Parcel;
import android.os.Parcelable;
import androidx.annotation.CallSuper;
import androidx.annotation.ColorRes;
import androidx.annotation.DimenRes;
import androidx.annotation.DrawableRes;
import androidx.annotation.NonNull;
import androidx.core.content.res.ResourcesCompat;
import android.view.View;
import android.view.ViewGroup;

import com.rainfool.md.R;

import java.util.ArrayList;
import java.util.List;

/**
 * 每个View的绑定抽象
 *
 * @author rainfool
 * @date 2018/11/1
 */
public class BaseViewParams<V extends View> implements Parcelable {

    public static final int INVALID_VALUE = Integer.MIN_VALUE;

    public String viewKey = "";

    @DimenRes
    public int mWidth = INVALID_VALUE;
    @DimenRes
    public int mHeight = INVALID_VALUE;

    @NonNull
    public Padding mPadding = new Padding();

    private int mMarginLeft = 10;
    private int mMarginRight = 10;
    private int mMarginTop = 10;
    private int mMarginBottom = 10;

    @ColorRes
    private int mBackgroundColor = R.color.color_aaaaaa;

    @DrawableRes
    private int mBackgroundRes = R.color.color_aaaaaa;
    @DrawableRes
    private int mForegroundRes = R.color.color_aaaaaa;

//    private Drawable mForegroundDrawable = null;

    @NonNull
    public String mAction = "";

    private int mVisibility = 0;
    private int mClickable = 0;

    public int mComponentPosition;


    public int mSelectStatus = SelectStatus.SELECTED;

    private List<View.OnClickListener> clickListeners = new ArrayList<>();
    private List<View.OnLongClickListener> longClickListeners = new ArrayList<>();

    public Object tag = null;

    protected BaseViewParams(Parcel in) {
        viewKey = in.readString();
        mWidth = in.readInt();
        mHeight = in.readInt();
        mPadding = in.readParcelable(Padding.class.getClassLoader());
        mMarginLeft = in.readInt();
        mMarginRight = in.readInt();
        mMarginTop = in.readInt();
        mMarginBottom = in.readInt();
        mBackgroundColor = in.readInt();
        mBackgroundRes = in.readInt();
        mForegroundRes = in.readInt();
        mAction = in.readString();
        mVisibility = in.readInt();
        mClickable = in.readInt();
        mComponentPosition = in.readInt();
        mSelectStatus = in.readInt();
    }

    public BaseViewParams() {
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(viewKey);
        dest.writeInt(mWidth);
        dest.writeInt(mHeight);
        dest.writeParcelable(mPadding, flags);
        dest.writeInt(mMarginLeft);
        dest.writeInt(mMarginRight);
        dest.writeInt(mMarginTop);
        dest.writeInt(mMarginBottom);
        dest.writeInt(mBackgroundColor);
        dest.writeInt(mBackgroundRes);
        dest.writeInt(mForegroundRes);
        dest.writeString(mAction);
        dest.writeInt(mVisibility);
        dest.writeInt(mClickable);
        dest.writeInt(mComponentPosition);
        dest.writeInt(mSelectStatus);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<BaseViewParams> CREATOR = new Creator<BaseViewParams>() {
        @Override
        public BaseViewParams createFromParcel(Parcel in) {
            return new BaseViewParams(in);
        }

        @Override
        public BaseViewParams[] newArray(int size) {
            return new BaseViewParams[size];
        }
    };

    /**
     * 绑定View的逻辑
     *
     * @param view 具体View
     */
    public void bindViewInner(V view) {
        setViewInner(view);
        if (tag != null) {
            view.setTag(tag);
        }
        for (View.OnClickListener listener : clickListeners) {
            view.setOnClickListener(listener);
        }
        for (View.OnLongClickListener listener : longClickListeners) {
            view.setOnLongClickListener(listener);
        }
    }

    @SuppressLint("WrongConstant")
    @CallSuper
    public void setViewInner(V view) {
        if (isValidate(mWidth)) {
            view.getLayoutParams().width = mWidth;
        }
        if (isValidate(mHeight)) {
            view.getLayoutParams().height = mHeight;
        }
        if (isValidate(mVisibility)) {
            view.setVisibility(mVisibility);
        }
        if (mPadding.isValid()) {
            view.setPadding(mPadding.left, mPadding.top, mPadding.right, mPadding.bottom);
        }
        if (isValidate(mBackgroundColor)) {
            view.setBackgroundColor(ResourcesCompat.getColor(view.getResources(), mBackgroundColor, null));
        }
        if (isValidate(mBackgroundRes)) {
            view.setBackgroundResource(mBackgroundRes);
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (isValidate(mForegroundRes)) {
                view.setForeground(ResourcesCompat.getDrawable(view.getResources(), mForegroundRes, null));
            }
//            if (mForegroundDrawable != null) {
//                view.setForeground(mForegroundDrawable);
//            }
        }
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        if (layoutParams instanceof ViewGroup.MarginLayoutParams) {
            ViewGroup.MarginLayoutParams mMarginParams = (ViewGroup.MarginLayoutParams) layoutParams;
            if (isValidate(mMarginLeft)) {
                mMarginParams.setMargins(mMarginLeft, mMarginTop, mMarginRight, mMarginBottom);
            }
        }
        if (isValidate(mSelectStatus)) {
            if (mSelectStatus == SelectStatus.UN_SELECTED) {
                view.setSelected(false);
            } else if (mSelectStatus == SelectStatus.SELECTED) {
                view.setSelected(true);
            }
        }
    }

    protected boolean isValidate(int value) {
//        if (value == INVALID_VALUE) {
//            return false;
//        }
        return true;
    }

    protected boolean isValidate(float value) {
        if (value == INVALID_VALUE) {
            return false;
        }
        return true;
    }

    public void setBackgroundResource(@DrawableRes int resid) {
        mBackgroundRes = resid;
        mBackgroundColor = INVALID_VALUE;
    }

    public void setForeground(@DrawableRes int resid) {
//        mForegroundDrawable = null;
        mForegroundRes = resid;
    }

//    public void setForeground(Drawable foreground) {
//        mForegroundDrawable = foreground;
//        mForegroundRes = INVALID_VALUE;
//    }

    public void setBackgroundColor(@ColorRes int color) {
        mBackgroundColor = color;
        mBackgroundRes = INVALID_VALUE;
    }


    public void setPadding(int left, int top, int right, int bottom) {
        mPadding.left = left;
        mPadding.right = right;
        mPadding.top = top;
        mPadding.bottom = bottom;
    }

    public void setVisibility(int visibility) {
        mVisibility = visibility;
    }

    public void setMargins(int left, int top, int right, int bottom) {
        mMarginLeft = left;
        mMarginRight = right;
        mMarginTop = top;
        mMarginBottom = bottom;
    }

    public void setSelected(boolean selected) {
        if (selected) {
            mSelectStatus = SelectStatus.SELECTED;
        } else {
            mSelectStatus = SelectStatus.UN_SELECTED;
        }
    }

    public void setClickable(boolean clickable) {
        if (clickable) {
            mClickable = ClickableStatus.CLICKABLE_ENABLE;
        } else {
            mClickable = ClickableStatus.CLICKABLE_NOT_ENABLE;
        }
    }

    public boolean isEmpty() {
        return false;
    }

    public boolean isSelected() {
        return mSelectStatus == SelectStatus.SELECTED;
    }

    public static final class SelectStatus {
        /**
         * 初始状态
         */
        public static final int INITIAL = -1;
        /**
         * 未选中
         */
        public static final int UN_SELECTED = 0;
        /**
         * 选中状态
         */
        public static final int SELECTED = 1;
    }

    public static final class ClickableStatus {

        private static final int CLICKABLE_ENABLE = 1;
        private static final int CLICKABLE_NOT_ENABLE = 2;
    }

    public static final class Padding implements Parcelable {
        public int left = INVALID_VALUE;
        public int top = INVALID_VALUE;
        public int right = INVALID_VALUE;
        public int bottom = INVALID_VALUE;

        protected Padding(Parcel in) {
            left = in.readInt();
            top = in.readInt();
            right = in.readInt();
            bottom = in.readInt();
        }

        public Padding() {

        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeInt(left);
            dest.writeInt(top);
            dest.writeInt(right);
            dest.writeInt(bottom);
        }

        @Override
        public int describeContents() {
            return 0;
        }

        public static final Creator<Padding> CREATOR = new Creator<Padding>() {
            @Override
            public Padding createFromParcel(Parcel in) {
                return new Padding(in);
            }

            @Override
            public Padding[] newArray(int size) {
                return new Padding[size];
            }
        };

        public boolean isValid() {
            return left != INVALID_VALUE
                    || top != INVALID_VALUE
                    || right != INVALID_VALUE
                    || bottom != INVALID_VALUE;
        }
    }

}