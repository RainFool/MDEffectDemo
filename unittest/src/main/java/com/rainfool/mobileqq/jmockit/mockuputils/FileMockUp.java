package com.rainfool.mobileqq.jmockit.mockuputils;

import java.io.File;
import mockit.Invocation;
import mockit.Mock;
import mockit.MockUp;

/**
 * @author: jensenweng
 * @data: 2020/12/22
 * @Desp: File类的mockup对象
 */
public final class FileMockUp extends MockUp<File> {
    public boolean needMockExist = false;
    public boolean mockExist = false;
    public boolean needMockCanRead = false;
    public boolean mockCanRead = false;
    public long mockFileLength = 0L;

    @Mock
    public boolean exists(Invocation invocation) {
        if (needMockExist) {
            return mockExist;
        } else {
            return invocation.proceed();
        }
    }

    @Mock
    public boolean canRead(Invocation invocation) {
        if (needMockCanRead) {
            return mockCanRead;
        } else {
            return invocation.proceed();
        }
    }

    @Mock
    public long length() {
        return mockFileLength;
    }
}
