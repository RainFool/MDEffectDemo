package com.rainfool.md.anotations;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.widget.TextView;

import com.rainfool.md.R;

import push.rainfool.com.anotation.BindAnnotation;

public class AnotationActivity extends AppCompatActivity {

    @BindAnnotation
    TextView mTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_anotation);
    }
}
