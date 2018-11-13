package com.rainfool.md.dialogfragment;

import android.app.Activity;
import android.app.Dialog;
import android.app.DialogFragment;
import android.app.FragmentManager;
import android.content.Context;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.IBinder;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import com.rainfool.md.BaseApp;

/**
 * @author rainfool
 * @date 2018/7/6
 */
public class BaseDialogFragment extends DialogFragment {

    public static boolean shouldExecuteFragmentActions(Activity activity) {
//        return activity != null && !(activity instanceof BaseActivity && ((BaseActivity) activity).hasStateSaved());
        return true;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    public Resources getResourceSafely() {
        if (getActivity() != null) {
            return getResources();
        }
        return BaseApp.gContext.getResources();
    }

    protected <T extends View> T findViewById(int id) {
        if (getView() != null) {
            return (T) getView().findViewById(id);
        }
        return null;
    }

    protected void hideSoftKeyboard() {
        InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
        if (imm != null) {
            IBinder windowToken = getActivity().getWindow().getDecorView().getWindowToken();
//            KLog.debug(BaseDialogFragment.class.getSimpleName(), "hideSoftKeyboard,window token is null?" + (windowToken == null));
            imm.hideSoftInputFromWindow(windowToken, 0);
        }
    }

    public FragmentManager getCompatFragmentManager() {
        if (false) {
            return getChildFragmentManager();
        } else {
            return getFragmentManager();
        }
    }

//    private boolean isSupportNestedFragment() {
//        return AndroidVersionUtils.isSupportNestedFragment();
//    }

    protected boolean useContextSystemVisibility() {
        return false;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Dialog dialog = super.onCreateDialog(savedInstanceState);
        if (useContextSystemVisibility()) {
//            DialogHelper.applySystemVisibility(dialog, getActivity());
        }
        return dialog;
    }
}