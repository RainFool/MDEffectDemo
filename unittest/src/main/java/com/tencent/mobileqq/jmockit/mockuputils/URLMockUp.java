package com.tencent.mobileqq.jmockit.mockuputils;

import java.net.MalformedURLException;
import java.net.URL;
import mockit.Mock;
import mockit.MockUp;

public final class URLMockUp extends MockUp<URL> {


    @Mock
    public void $init(String spec) throws MalformedURLException {

    }
    @Mock
    public void $clinit() {

    }



}
