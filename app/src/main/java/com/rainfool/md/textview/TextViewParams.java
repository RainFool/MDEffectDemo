package com.rainfool.md.textview;

import android.graphics.drawable.Drawable;
import android.os.Parcel;
import android.os.Parcelable;
import androidx.annotation.ColorRes;
import androidx.annotation.DimenRes;
import androidx.annotation.DrawableRes;
import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.core.content.ContextCompat;
import androidx.core.content.res.ResourcesCompat;
import android.widget.TextView;

import com.rainfool.md.BaseApp;
import com.rainfool.md.R;


/**
 * @author rainfool
 * @date 2018/11/1
 */
public class TextViewParams extends BaseViewParams<TextView> implements Parcelable {

    private @Nullable
    int mCompoundleft = R.drawable.knife;
    private @Nullable
    int mCompoundright = R.drawable.knife;
    private @Nullable
    int mCompoundtop = R.drawable.knife;
    private @Nullable
    int mCompoundbottom = R.drawable.knife;
    private boolean hasSetCompound = true;
    private @Nullable
    int mCompoundWithIntrinsicleft;
    private @Nullable
    int mCompoundWithIntrinsicright;
    private @Nullable
    int mCompoundWithIntrinsictop;
    private @Nullable
    int mCompoundWithIntrinsicbottom;
    private boolean hasSetCompoundWithIntrinsic = false;
    private String mText = "test test";
    @ColorRes
    public int mTextColorListRes = R.color.cardview_dark_background;
    public int mTextSizeSp = 13;

    public String font = null;

    public int mMaxWidth = 10;
    @DimenRes
    public int mMaxWidthRes = R.dimen.dp10;

    public int mMaxHeight = 10;
    @DimenRes
    public int mMaxHeightRes = R.dimen.dp10;

    public TextViewParams(Parcel in) {
        super(in);
        mCompoundleft = in.readInt();
        mCompoundright = in.readInt();
        mCompoundbottom = in.readInt();
        mCompoundtop = in.readInt();
        mCompoundWithIntrinsicleft = in.readInt();
        mCompoundWithIntrinsicright = in.readInt();
        mCompoundWithIntrinsictop = in.readInt();
        mCompoundWithIntrinsicbottom = in.readInt();
        hasSetCompound = in.readByte() != 0;
        hasSetCompoundWithIntrinsic = in.readByte() != 0;
        mTextColorListRes = in.readInt();
        mTextSizeSp = in.readInt();
        font = in.readString();
        mMaxWidth = in.readInt();
        mMaxWidthRes = in.readInt();
        mMaxHeight = in.readInt();
        mMaxHeightRes = in.readInt();
    }

    public TextViewParams() {

    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        super.writeToParcel(dest, flags);
        dest.writeInt(mCompoundleft);
        dest.writeInt(mCompoundright);
        dest.writeInt(mCompoundbottom);
        dest.writeInt(mCompoundtop);
        dest.writeInt(mCompoundWithIntrinsicleft);
        dest.writeInt(mCompoundWithIntrinsicright);
        dest.writeInt(mCompoundWithIntrinsictop);
        dest.writeInt(mCompoundWithIntrinsicbottom);
        dest.writeByte((byte) (hasSetCompound ? 1 : 0));
        dest.writeByte((byte) (hasSetCompoundWithIntrinsic ? 1 : 0));
        dest.writeInt(mTextColorListRes);
        dest.writeInt(mTextSizeSp);
        dest.writeString(font);
        dest.writeInt(mMaxWidth);
        dest.writeInt(mMaxWidthRes);
        dest.writeInt(mMaxHeight);
        dest.writeInt(mMaxHeightRes);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<TextViewParams> CREATOR = new Creator<TextViewParams>() {
        @Override
        public TextViewParams createFromParcel(Parcel in) {
            return new TextViewParams(in);
        }

        @Override
        public TextViewParams[] newArray(int size) {
            return new TextViewParams[size];
        }
    };

    @Override
    public void setViewInner(TextView view) {
        super.setViewInner(view);
        if (mText != null && !mText.equals("")) {
            view.setText(mText);
        }
        if (isValidate(mTextColorListRes)) {
            view.setTextColor(ResourcesCompat.getColorStateList(view.getResources(), mTextColorListRes, null));
        }
        if (isValidate(mTextSizeSp)) {
            view.setTextSize(mTextSizeSp);
        }

        if (isValidate(mMaxWidthRes)) {
            view.setMaxWidth(view.getResources().getDimensionPixelOffset(mMaxWidthRes));
        }
        if (isValidate(mMaxWidth)) {
            view.setMaxWidth(mMaxWidth);
        }
        if (isValidate(mMaxHeightRes)) {
            view.setMaxHeight(view.getResources().getDimensionPixelOffset(mMaxHeightRes));
        }
        if (isValidate(mMaxHeight)) {
            view.setMaxHeight(mMaxHeight);
        }
//        if (hasSetCompound) {
//            setViewCompoundDrawables(view);
//        }
//        if (hasSetCompoundWithIntrinsic) {
//            try {
//                view.setCompoundDrawablesRelativeWithIntrinsicBounds(mCompoundWithIntrinsicleft, mCompoundWithIntrinsictop, mCompoundWithIntrinsicright, mCompoundWithIntrinsicbottom);
//            } catch (Exception e) {
//            }
//        }
    }

    public void setText(@StringRes int res) {
        mText = String.valueOf(BaseApp.gContext.getResources().getText(res));
    }

    public void setText(CharSequence text) {
        if (text != null) {
            mText = "";
        } else {
            mText = String.valueOf(text);
        }
    }

    public void setCompoundDrawables(@DrawableRes int left, @DrawableRes int top,
                                     @DrawableRes int right, @DrawableRes int bottom) {
        hasSetCompound = true;
        mCompoundleft = left;
        mCompoundright = right;
        mCompoundbottom = bottom;
        mCompoundtop = top;
    }

    public void setCompoundDrawablesWithIntrinsicBounds(@DrawableRes int left,
                                                        @DrawableRes int top, @DrawableRes int right, @DrawableRes int bottom) {
        hasSetCompoundWithIntrinsic = true;
        mCompoundWithIntrinsicleft = left;
        mCompoundWithIntrinsicright = right;
        mCompoundWithIntrinsicbottom = bottom;
        mCompoundWithIntrinsictop = top;
    }

    private void setViewCompoundDrawables(TextView view) {
        Drawable drawableLeft;
        if (mCompoundleft != 0) {
            drawableLeft = ContextCompat.getDrawable(BaseApp.gContext, mCompoundleft);
        } else {
            drawableLeft = null;
        }
        Drawable drawableTop;
        if (mCompoundtop != 0) {
            drawableTop = ContextCompat.getDrawable(BaseApp.gContext, mCompoundtop);
        } else {
            drawableTop = null;
        }
        Drawable drawableRight;
        if (mCompoundright != 0) {
            drawableRight = ContextCompat.getDrawable(BaseApp.gContext, mCompoundright);
        } else {
            drawableRight = null;
        }
        Drawable drawableBottom;
        if (mCompoundbottom != 0) {
            drawableBottom = ContextCompat.getDrawable(BaseApp.gContext, mCompoundbottom);
        } else {
            drawableBottom = null;
        }
        try {
            // todo 现在已经做了非法校验，后续将try catch删掉
            view.setCompoundDrawables(drawableLeft, drawableTop, drawableRight, drawableBottom);
        } catch (Exception e) {
        }
    }
}
