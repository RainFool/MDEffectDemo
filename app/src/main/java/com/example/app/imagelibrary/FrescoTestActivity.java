package com.example.app.imagelibrary;

import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.example.app.R;
import com.facebook.drawee.view.SimpleDraweeView;

public class FrescoTestActivity extends AppCompatActivity {

    private static final String URL_FLUSH = "http://screenshot.msstatic.com/yysnapshot/a7f85c8994af983226611a1c41bba484a3211994";
    private static final String URL_A = "http://v-huya-img.huya.com/1814/28261075/4-460x268.jpg";

    SimpleDraweeView mImageA, mImageB;
    Button mBtnRefresh;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fresco_test);

        mImageA = findViewById(R.id.sdv_fresco_a);
        mImageB = findViewById(R.id.sdv_fresco_b);
        mBtnRefresh = findViewById(R.id.btn_fresco_refresh);

        mBtnRefresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onRefreshClicked(v);
            }
        });
    }

    private void onRefreshClicked(View v) {
        Uri uriA = Uri.parse(URL_A);
        Uri uriB = Uri.parse(URL_FLUSH);

        mImageA.setImageURI(uriA);
        mImageB.setImageURI(uriB);
    }
}
