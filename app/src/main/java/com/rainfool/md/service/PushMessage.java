package com.rainfool.md.service;

import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;

/**
 * @author gaotianyu on 15/3/24.
 */
public class PushMessage implements Parcelable {

    private static final String TAG = "PushMessage";

    private static final String KEY_PUSH_TYPE = "push_type";
    private static final String KEY_ACTION = "action";
    private static final String KEY_TITLE = "title";
    private static final String KEY_ALERT = "alert";
    private static final String KEY_CATALOG = "catalog";
    private static final String KEY_TRACEID = "traceid";
    private static final String KEY_IMAGE_URL = "image_url";

    public int pushType = 0;
    public String title = "";
    public String alert = "";
    public String action = "";
    public String catalog = "";
    public String traceid = "";
    public String imageurl = "";


    public static Uri toUri(PushMessage message) {
        return new Uri.Builder()
                .appendQueryParameter(KEY_PUSH_TYPE, String.valueOf(message.pushType))
                .appendQueryParameter(KEY_TITLE, message.title)
                .appendQueryParameter(KEY_ALERT, message.alert)
                .appendQueryParameter(KEY_ACTION, message.action)
                .appendQueryParameter(KEY_CATALOG, message.catalog)
                .appendQueryParameter(KEY_TRACEID, message.traceid)
                .appendQueryParameter(KEY_IMAGE_URL, message.imageurl)
                .build();
    }

    public static PushMessage fromUri(Uri uri) {
        String type = uri.getQueryParameter(KEY_PUSH_TYPE);
        String title = uri.getQueryParameter(KEY_TITLE);
        String alert = uri.getQueryParameter(KEY_ALERT);
        String action = uri.getQueryParameter(KEY_ACTION);
        String catalog = uri.getQueryParameter(KEY_CATALOG);
        String traceid = uri.getQueryParameter(KEY_TRACEID);
        String imageUrl = uri.getQueryParameter(KEY_IMAGE_URL);

        int typeInt = 0;
        try {
            typeInt = Integer.parseInt(type);
        } catch (Exception e) {
//            KLog.error(TAG, e);
        }
        return new PushMessage(typeInt, title, alert, action, catalog, traceid, imageUrl);
    }

    public PushMessage(int type, String title, String alert, String action, String catalog, String traceId, String imageUrl) {
        this.pushType = type;
        this.title = title;
        this.alert = alert;
        this.action = action;
        this.catalog = catalog;
        this.traceid = traceId;
        if (imageUrl == null) {
            this.imageurl = "";
        } else {
            this.imageurl = imageUrl;
        }
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.pushType);
        dest.writeString(this.title);
        dest.writeString(this.alert);
        dest.writeString(this.action);
        dest.writeString(this.catalog);
        dest.writeString(this.traceid);
        dest.writeString(this.imageurl);
    }

    protected PushMessage(Parcel in) {
        this.pushType = in.readInt();
        this.title = in.readString();
        this.alert = in.readString();
        this.action = in.readString();
        this.catalog = in.readString();
        this.traceid = in.readString();
        this.imageurl = in.readString();
    }

    public static final Creator<PushMessage> CREATOR = new Creator<PushMessage>() {
        @Override
        public PushMessage createFromParcel(Parcel source) {
            return new PushMessage(source);
        }

        @Override
        public PushMessage[] newArray(int size) {
            return new PushMessage[size];
        }
    };

    @Override
    public String toString() {
        return "PushMessage{" +
                "pushType=" + pushType +
                ", title='" + title + '\'' +
                ", alert='" + alert + '\'' +
                ", action='" + action + '\'' +
                ", catalog='" + catalog + '\'' +
                ", traceid='" + traceid + '\'' +
                ", imageurl='" + imageurl + '\'' +
                '}';
    }
}
