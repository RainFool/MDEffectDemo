package com.rainfool.md.spannable;

import android.content.Context;
import android.content.res.TypedArray;
import android.text.Layout;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.TextPaint;
import android.text.TextUtils;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.text.style.ForegroundColorSpan;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import androidx.appcompat.widget.AppCompatTextView;


import com.rainfool.md.R;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Copyright 2017 Tim Qi
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
public class CollapsibleTextView extends AppCompatTextView {

    private int mSuffixColor = 0xff0000ff;

    private int mCollapsedLines = 1;

    private boolean mSuffixTrigger = false;

    private CharSequence mText;

    private OnClickListener mCustomClickListener;

    private boolean mShouldInitLayout = true;

    private boolean mExpanded = false;

    private CharSequence
            mCollapsedText = " Show All",
            mExpandedText = " Hide";

    public CollapsibleTextView(Context context) {
        this(context, null);
    }

    public CollapsibleTextView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CollapsibleTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray attributes = context.getTheme()
                .obtainStyledAttributes(attrs, R.styleable.CollapsibleTextView, defStyleAttr, 0);

        mSuffixColor = attributes.getColor(R.styleable.CollapsibleTextView_suffixColor, 0xff0000ff);
        mCollapsedLines = attributes.getInt(R.styleable.CollapsibleTextView_collapsedLines, 1);
        mCollapsedText = attributes.getString(R.styleable.CollapsibleTextView_collapsedText);
        if (TextUtils.isEmpty(mCollapsedText)) mCollapsedText = " Show All";
        mExpandedText = attributes.getString(R.styleable.CollapsibleTextView_expandedText);
        if (TextUtils.isEmpty(mExpandedText)) mExpandedText = " Hide";
        mSuffixTrigger = attributes.getBoolean(R.styleable.CollapsibleTextView_suffixTrigger, false);

        this.mText = getText() == null ? null : getText().toString();
        attributes.recycle();
        setMovementMethod(LinkMovementMethod.getInstance());
        setOnClickListener(mClickListener);
    }

    private OnClickListener mClickListener = new OnClickListener() {
        @Override
        public void onClick(View v) {
            if (!mSuffixTrigger) {
                mExpanded = !mExpanded;
                applyState(mExpanded);
            }

            if (mCustomClickListener != null) {
                mCustomClickListener.onClick(v);
            }
        }
    };

    private ClickableSpan mClickSpanListener = new ClickableSpan() {
        @Override
        public void onClick(View widget) {
            if (mSuffixTrigger) {
                mExpanded = !mExpanded;
                applyState(mExpanded);
            }
        }

        @Override
        public void updateDrawState(TextPaint ds) {
            super.updateDrawState(ds);
            ds.setUnderlineText(false);
            ds.setColor(mSuffixColor);
        }
    };

    private void applyState(boolean expanded) {
        try {

            if (TextUtils.isEmpty(mText)) {
                return;
            }
            Layout layout = getLayout();
            if (layout == null) {
                return;
            }
            CharSequence note = mText, suffix;
            final SpannableStringBuilder spannerBuilder;
            if (expanded) {
                suffix = mExpandedText;
                spannerBuilder = new SpannableStringBuilder(note);
                spannerBuilder.append(suffix);
                spannerBuilder.setSpan(new ForegroundColorSpan(mSuffixColor),
                        note.length(),
                        note.length() + suffix.length(),
                        SpannableString.SPAN_EXCLUSIVE_EXCLUSIVE);
                if (mSuffixTrigger) {
                    spannerBuilder.setSpan(mClickSpanListener,
                            note.length(),
                            note.length() + suffix.length(),
                            SpannableString.SPAN_EXCLUSIVE_EXCLUSIVE);
                }
            } else {
                if (mCollapsedLines - 1 < 0) {
                    throw new RuntimeException("CollapsedLines must equal or greater than 1");
                }

                float rowWidth = getMeasuredWidth() - getPaddingLeft() - getPaddingRight();
                int lineEnd = getLayout().getLineEnd(mCollapsedLines - 1);
                float offset = 0;
                //计算前面每一行的误差
                SpannableStringBuilder tempSpannerBuilder = new SpannableStringBuilder(note, 0, lineEnd);
                for (int i = 0; i < mCollapsedLines - 1; i++) {
                    int indexLineEnd = getLayout().getLineEnd(i);
                    int indexLineStart = getLayout().getLineStart(i);
                    float realWidth = Layout.getDesiredWidth(tempSpannerBuilder, indexLineStart, indexLineEnd, getPaint());
                    offset += (rowWidth - realWidth);
                }

                //得出最后一行最后一个字符串的位置  然后往前减
                suffix = "..." + mCollapsedText;

                int maxWidth = mCollapsedLines * (getMeasuredWidth() - getPaddingLeft() - getPaddingRight());
                float realWidth = Layout.getDesiredWidth(note, 0, lineEnd, getPaint());
                spannerBuilder = new SpannableStringBuilder(note, 0, lineEnd);

                //去掉末尾换行符
                char lastChar = spannerBuilder.charAt(spannerBuilder.length() - 1);
                while ((lastChar == '\n' || lastChar == '\r') && spannerBuilder.length() > 0) {
                    removeOneChar(spannerBuilder);
                    if (spannerBuilder.length() == 0) {
                        break;
                    }
                    lastChar = spannerBuilder.charAt(spannerBuilder.length() - 1);
                }

                spannerBuilder.append(suffix);
                SpannableStringBuilder newspannerBuilder = removeWrap(spannerBuilder);
                realWidth = Layout.getDesiredWidth(newspannerBuilder, 0, newspannerBuilder.length(), getPaint());
                // todo
                // realWidth 是真实长度 ，spannerBuilder.length()是字符长度，有换行时差异较大
                // 先spannerBuilder.length() > suffix.length() 处理anr，再看怎么优化ui展示
                while (realWidth > (maxWidth - offset) && spannerBuilder.length() > suffix.length()) {
                    //首先去掉后面的后缀
                    spannerBuilder.delete(spannerBuilder.length() - suffix.length(), spannerBuilder.length());

                    //然后去掉一个字符或者表情
                    removeOneChar(spannerBuilder);
                    //然后再次加上后缀
                    spannerBuilder.append(suffix);

                    newspannerBuilder = removeWrap(spannerBuilder);
                    realWidth = Layout.getDesiredWidth(newspannerBuilder, 0, newspannerBuilder.length(), getPaint());
                }

                //找到合适的长度之后  然后我们开始添加变色的后缀
                //首先去掉后面的后缀
                spannerBuilder.delete(spannerBuilder.length() - suffix.length(), spannerBuilder.length());
                spannerBuilder.append(suffix);

                spannerBuilder.setSpan(new ForegroundColorSpan(mSuffixColor),
                        spannerBuilder.length() - mCollapsedText.length(),
                        spannerBuilder.length(),
                        SpannableString.SPAN_EXCLUSIVE_EXCLUSIVE);
                if (mSuffixTrigger) {
                    spannerBuilder.setSpan(mClickSpanListener,
                            spannerBuilder.length() - mCollapsedText.length(),
                            spannerBuilder.length(),
                            SpannableString.SPAN_EXCLUSIVE_EXCLUSIVE);
                }

            }
            setText(spannerBuilder);
            invalidate();
        } catch (Exception e) {
            Log.e("CollapsibleTextView", "applyState error", e);
            setText(mText);
        }
    }

    public SpannableStringBuilder removeWrap(SpannableStringBuilder input) {

        SpannableStringBuilder spannerBuilder = new SpannableStringBuilder(input, 0, input.length());

        int i = 0;
        while (spannerBuilder.length() > i) {
            char lastChar = spannerBuilder.charAt(spannerBuilder.length() - 1 - i);
            if (lastChar == '\n' || lastChar == '\r') {
                spannerBuilder.delete(spannerBuilder.length() - 1 - i, spannerBuilder.length() - i);
            } else {
                i++;
            }
        }

        return spannerBuilder;

    }

    private SpannableStringBuilder removeOneChar(SpannableStringBuilder ss) {
        if (ss.length() == 0) {
            return ss;
        }
        if (ss.length() < 6) {
            return ss.delete(ss.length() - 1, ss.length());
        }
        Pattern pattern = Pattern.compile("/:(\\d+?):");
        CharSequence subText = ss.subSequence(ss.length() - 6, ss.length());
        Matcher matcher = pattern.matcher(subText);
        if (matcher.find()) {
            //表示删除的是一个表情
            return ss.delete(ss.length() - 6, ss.length());
        }
        //否则直接处理掉
        return ss.delete(ss.length() - 1, ss.length());
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);

    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
/*    int width = getMeasuredHeight();
    int height = getMeasuredWidth();*/

        if (mShouldInitLayout && getLineCount() > mCollapsedLines) {
            mShouldInitLayout = false;
            applyState(mExpanded);
            //测量第二次
            super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        }


    }

    public void setFullString(CharSequence str) {
        this.mText = str;
        mShouldInitLayout = true;
        setText(mText);
    }

//    @Override
//    public void setOnClickListener(OnClickListener l) {
////        mCustomClickListener = l;// codecc
//    }

    public boolean isExpanded() {
        return mExpanded;
    }

    public void setExpanded(boolean mExpanded) {
        if (this.mExpanded != mExpanded) {
            this.mExpanded = mExpanded;
            applyState(mExpanded);
        }
    }

    public int getSuffixColor() {
        return mSuffixColor;
    }

    public void setSuffixColor(int mSuffixColor) {
        this.mSuffixColor = mSuffixColor;
        applyState(mExpanded);
    }

    public int getCollapsedLines() {
        return mCollapsedLines;
    }

    public void setCollapsedLines(int mCollapsedLines) {
        this.mCollapsedLines = mCollapsedLines;
        mShouldInitLayout = true;
        setText(mText);
    }

    public boolean isSuffixTrigger() {
        return mSuffixTrigger;
    }

    public void setSuffixTrigger(boolean mSuffixTrigger) {
        this.mSuffixTrigger = mSuffixTrigger;
        applyState(mExpanded);
    }

    public CharSequence getCollapsedText() {
        return mCollapsedText;
    }

    public void setCollapsedText(CharSequence mCollapsedText) {
        this.mCollapsedText = mCollapsedText;
        applyState(mExpanded);
    }

    public CharSequence getExpandedText() {
        return mExpandedText;
    }

    public void setExpandedText(CharSequence mExpandedText) {
        this.mExpandedText = mExpandedText;
        applyState(mExpanded);
    }


}
