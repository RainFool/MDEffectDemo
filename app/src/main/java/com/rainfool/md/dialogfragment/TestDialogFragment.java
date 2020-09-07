package com.rainfool.md.dialogfragment;

import android.os.Bundle;
import androidx.annotation.Nullable;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.rainfool.md.R;

import java.util.logging.Logger;

/**
 * @author rainfool
 * @date 2018/7/9
 */

public class TestDialogFragment extends BaseDialogFragment {

    private static final String TAG = "TestDialogFragment";

    public TestDialogFragment() {
        Log.i(TAG, "TestDialogFragment: ");
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i(TAG,"onCreate");
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dialog_test, container, false);
        return view;
    }
}
