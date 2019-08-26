package com.rainfool.skinengine;

import android.content.Context;
import android.content.res.Resources;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.rainfool.skinengine.entity.SkinAttr;
import com.rainfool.skinengine.entity.SkinItem;

import java.util.ArrayList;
import java.util.List;

public class SkinInflateFactory implements LayoutInflater.Factory2 {

    private static final String TAG = "SkinInflateFactory";

    public static final String BACKGROUND = "background";
    public static final String STYLE = "style";


    private List<SkinItem> mSkinItems = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(@NonNull String name, @NonNull Context context, @NonNull AttributeSet attrs) {
        Log.d(TAG, "onCreateView: name %s" + name);
        int count = attrs.getAttributeCount();
        for (int i = 0; i < count; i++) {
            Log.d(TAG, "onCreateView: att name:" + attrs.getAttributeName(i));
        }
        return null;
    }

    @Override
    public View onCreateView(View parent, String name, Context context, AttributeSet attrs) {
        Log.d(TAG, "onCreateView: name %s" + name);

        View view = createView(context, name, attrs);
        if (view != null) {
            recordViewAndAttributes(context, view, attrs);
        }
        return view;
    }

    private View createView(Context context, String name, AttributeSet attrs) {
        View view = null;
        try {
            if (-1 == name.indexOf('.')) {
                if ("View".equals(name)) {
                    view = LayoutInflater.from(context).createView(name, "android.view.", attrs);
                }
                if (view == null) {
                    view = LayoutInflater.from(context).createView(name, "android.widget.", attrs);
                }
                if (view == null) {
                    view = LayoutInflater.from(context).createView(name, "android.webkit.", attrs);
                }
            } else {
                view = LayoutInflater.from(context).createView(name, null, attrs);
            }

            Log.i(TAG, "about to create " + name);

        } catch (Exception e) {
            Log.e(TAG, "error while create 【" + name + "】 : " + e.getMessage());
            view = null;
        }
        return view;
    }

    private void recordViewAndAttributes(Context context, View view, AttributeSet attrs) {
        List<SkinAttr> viewAttrs = new ArrayList<SkinAttr>();
        int count = attrs.getAttributeCount();

        for (int i = 0; i < count; i++) {
            Log.d(TAG, "recordViewAndAttributes: att name:" + attrs.getAttributeName(i));
            String attrName = attrs.getAttributeName(i);
            String attrValue = attrs.getAttributeValue(i);
            if (!STYLE.equals(attrName)) {
                continue;
            }
            if (attrValue.startsWith("@")) {
                try {
                    int id = Integer.parseInt(attrValue.substring(1));
                    String entryName = context.getResources().getResourceEntryName(id);
                    String typeName = context.getResources().getResourceTypeName(id);
                    SkinAttr skinAttr = new SkinAttr();
                    skinAttr.attrName = attrName;
                    skinAttr.attrValueRefId = id;
                    skinAttr.attrValueRefName = entryName;
                    skinAttr.attrValueTypeName = typeName;

                    viewAttrs.add(skinAttr);
                } catch (NumberFormatException e) {
                    e.printStackTrace();
                } catch (Resources.NotFoundException e) {
                    e.printStackTrace();
                }
            }
        }
        if (!viewAttrs.isEmpty()) {
            SkinItem skinItem = new SkinItem();
            skinItem.view = view;
            skinItem.attrs = viewAttrs;
            mSkinItems.add(skinItem);
        }
    }


    public List<SkinItem> getSkinItems() {
        return mSkinItems;
    }

    public void applySkin(Context context, Resources resources, String packageName) {
        for (SkinItem item : mSkinItems) {
            for (SkinAttr attr : item.attrs) {
                int originColor = context.getResources().getColor(attr.attrValueRefId);
                if (resources == null) {
                    item.view.setBackgroundColor(originColor);
                    return;
                }

                String resName = context.getResources().getResourceEntryName(attr.attrValueRefId);

                int trueResId = resources.getIdentifier(resName, "color", packageName);
                int trueColor = 0;

                try {
                    trueColor = resources.getColor(trueResId);
                } catch (Resources.NotFoundException e) {
                    e.printStackTrace();
                    trueColor = originColor;
                }
                item.view.setBackgroundColor(trueColor);
            }
        }
    }
}
