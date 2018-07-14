package com.example.app.password;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;

import com.example.app.R;

public class PasswordActivity extends AppCompatActivity {

    private EditText mEtNum1, mEtNum2, mEtNum3, mEtNum4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_password);
        mEtNum1 = (EditText) findViewById(R.id.et_fm_pwd_num_1);
        mEtNum2 = (EditText) findViewById(R.id.et_fm_pwd_num_2);
        mEtNum3 = (EditText) findViewById(R.id.et_fm_pwd_num_3);
        mEtNum4 = (EditText) findViewById(R.id.et_fm_pwd_num_4);

        View.OnFocusChangeListener l = new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
                }
            }
        };
        mEtNum1.setOnFocusChangeListener(l);
        mEtNum2.setOnFocusChangeListener(l);
        mEtNum3.setOnFocusChangeListener(l);
        mEtNum4.setOnFocusChangeListener(l);
    }
}
