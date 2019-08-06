package com.rainfool.md.imagelibrary;

import android.os.Bundle;
import android.os.Environment;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;

import com.rainfool.md.R;

import java.io.File;

public class FrescoTestActivity extends AppCompatActivity {

    private static final String URL_FLUSH = "http://screenshot.msstatic.com/yysnapshot/a7f85c8994af983226611a1c41bba484a3211994";
    private static final String URL_A = "http://v-huya-img.huya.com/1814/28261075/4-460x268.jpg";


    Button mBtnRefresh;

    FrameLayout mContainer;
    private KiwiWebpView<File> kiwiWebpView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fresco_test);
        mContainer = findViewById(R.id.container);

        mBtnRefresh = findViewById(R.id.btn_fresco_refresh);
        kiwiWebpView = new KiwiWebpView<File>(this) {
            @Override
            protected String getFilePath(File item) {
                return item.getAbsolutePath();
            }
        };
        kiwiWebpView.setMaxLoopTimes(1);
        FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        mContainer.addView(kiwiWebpView, params);

        mBtnRefresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onRefreshClicked(v);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        kiwiWebpView.notifyVisibleToUser();
    }

    private void onRefreshClicked(View v) {
        File file = new File(Environment.getExternalStorageDirectory(), "/Pictures/test.webp");
        kiwiWebpView.start(file);
    }
}
