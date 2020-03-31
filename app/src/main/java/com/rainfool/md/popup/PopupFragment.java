package com.rainfool.md.popup;

import android.app.Fragment;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.rainfool.md.R;

public class PopupFragment extends Fragment {

    private Button mButton;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_popup_test, container, false);
        mButton = (Button) view.findViewById(R.id.btn_show_popup);
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showPopup(v);
            }
        });
        return view;
    }


    private void showPopup(View v) {
        int screenHeight = Resources.getSystem().getDisplayMetrics().heightPixels;


        LayoutInflater inflater = LayoutInflater.from(getActivity());
        View rootView = inflater.inflate(R.layout.layout_popup_text, null);
        TextView textView = rootView.findViewById(R.id.tv_in_popup);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(v.getContext(), "tv in popup click", Toast.LENGTH_LONG).show();
            }
        });
        PopupWindow popupWindow = new CustomPopupWindow(rootView, WindowManager.LayoutParams.MATCH_PARENT,
                400);
        popupWindow.setOutsideTouchable(true);
        popupWindow.setFocusable(false);
        popupWindow.setAnimationStyle(0);
        popupWindow.showAsDropDown(v, 0, 60);
    }

}
