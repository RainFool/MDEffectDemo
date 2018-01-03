package com.example.app.spannable;

import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ImageSpan;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.app.R;

public class SpnnableTestActivity extends AppCompatActivity {

    Button mSpannableButton;
    EditText mEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spnnable_test);
        mEditText = (EditText) findViewById(R.id.et_input);
        mSpannableButton = (Button) findViewById(R.id.btn_spannable);

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
