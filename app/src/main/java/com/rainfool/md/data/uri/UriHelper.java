package com.rainfool.md.data.uri;

import android.net.Uri;
import android.util.Log;

/**
 * @author rainfool
 * @date 2018/5/9
 */

public class UriHelper {

    private static final String TAG = "UriHelper";

    public UriHelper() {
        Uri uri = Uri.parse("live://sid=97994589&subsid=97994589&full=0&type=6&liveid=0&uid=887764729&traceid=push_16_652999");
        Log.i(TAG, "" + uri.toString());
        Log.i(TAG, "" + uri.getScheme());
        Log.i(TAG, "" + uri.getSchemeSpecificPart());
        Log.i(TAG, "" + uri.getPath());
        Log.i(TAG, "" + uri.getAuthority());
        Log.i(TAG, "" + uri.getQuery());
    }
}
