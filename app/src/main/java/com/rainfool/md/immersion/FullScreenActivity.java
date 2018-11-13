package com.rainfool.md.immersion;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;

import com.rainfool.md.R;

public class FullScreenActivity extends Activity {

    Button mButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_full_screen);

        mButton = findViewById(R.id.btn_skip_immersion);
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FullScreenActivity.this.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);

                Intent intent = new Intent(FullScreenActivity.this, ImmersionActivity.class);
                startActivity(intent);
//                overridePendingTransition(R.transition.slide, android.R.anim.fade_out);
            }
        });
    }
}
