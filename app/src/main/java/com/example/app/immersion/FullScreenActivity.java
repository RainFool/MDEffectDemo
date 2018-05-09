package com.example.app.immersion;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;

import com.example.app.R;

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
