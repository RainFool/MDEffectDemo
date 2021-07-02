package com.tencent.mobileqq.jmockit.mockuputils;

import android.text.TextUtils;
import mockit.Mock;
import mockit.MockUp;

/**
 * 用途：TextUtils的mock类
 *
 * @author kikipang
 * date     2021/1/7
 */
public final class TextUtilsMockUp extends MockUp<TextUtils> {
    public static boolean sNeedMock; //需指定其返回值
    public static boolean sMockEmptyResult;

    public TextUtilsMockUp() {
        sNeedMock = false;
    }

    @Mock
    public static boolean isEmpty(CharSequence str) {
        if (sNeedMock) {
            return sMockEmptyResult;
        }
        return str == null || str.length() == 0;
    }
}
