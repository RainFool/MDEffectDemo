package com.rainfool.md.chat;

import android.content.Context;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageButton;

import com.rainfool.md.R;

import cn.dreamtobe.kpswitch.util.KPSwitchConflictUtil;
import cn.dreamtobe.kpswitch.util.KeyboardUtil;
import cn.dreamtobe.kpswitch.widget.KPSwitchPanelLinearLayout;

public class ChatDemoActivity extends AppCompatActivity {

    ImageButton mSwitchClick;
    EditText mEditText;
    KPSwitchPanelLinearLayout mPanel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_demo);

        mEditText = findViewById(R.id.edt_input);
        mPanel = findViewById(R.id.panel);
        mSwitchClick = findViewById(R.id.iv_switch);

//        mSwitchClick.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                onSwitchClick(v);
//            }
//        });

        KeyboardUtil.attach(this, mPanel);

        KPSwitchConflictUtil.attach(mPanel, mSwitchClick, mEditText);
    }

    private void onSwitchClick(View v) {
        if (mPanel.getVisibility() == View.VISIBLE) {
            mPanel.setVisibility(View.GONE);
            showKeyboard(v);
        } else {
            mPanel.setVisibility(View.VISIBLE);
            hideKeyboard(v);
        }
    }

    public static void showKeyboard(final View view) {
        view.requestFocus();
        InputMethodManager inputManager =
                (InputMethodManager) view.getContext().getSystemService(
                        Context.INPUT_METHOD_SERVICE);
        inputManager.showSoftInput(view, 0);
    }

    public static void hideKeyboard(final View view) {
        InputMethodManager imm =
                (InputMethodManager) view.getContext()
                        .getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }
}
