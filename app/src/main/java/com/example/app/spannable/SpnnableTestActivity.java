package com.example.app.spannable;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.style.ImageSpan;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.app.R;

public class SpnnableTestActivity extends AppCompatActivity {

    private static final String TAG = "SpnnableTestActivity";

    Button mSpannableButton;
    EditText mEditText;
    TextView mTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spnnable_test);
        mEditText = (EditText) findViewById(R.id.et_input);
        mSpannableButton = (Button) findViewById(R.id.btn_spannable);
        mTextView = findViewById(R.id.tv_spannable_test);

        SpannableStringBuilder span = new SpannableStringBuilder(mTextView.getText().toString());
        span.setSpan(new CustomClickableSpan(ContextCompat.getColor(this, R.color.color_aaaaaa), getResources().getColor(R.color.cardview_dark_background), false) {
            @Override
            public void onClick(View widget) {
                Log.d(TAG, "onClick: ");
            }
        }, 0, span.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        mTextView.setText(span);

        mSpannableButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                spanText("test");
            }
        });
    }

    private void spanText(String s) {
        SpannableString spannableString = new SpannableString(s);
        Drawable drawable = ContextCompat.getDrawable(this, R.drawable.knife);
        drawable.setBounds(0, 0, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());
        spannableString.setSpan(new ImageSpan(drawable), 0, 1,
                Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        mEditText.setText(spannableString);
    }
}
