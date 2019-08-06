package com.rainfool.md.dialogfragment;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;

import com.rainfool.md.R;

public class DialogFragmentTestActivity extends AppCompatActivity {

    private static final String TAG = "DialogFragmentTestActiv";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dialog_fragment_test);
    }

    public void onShowDialogFragmentClick(View view) {
        new TestDialogFragment().show(getFragmentManager(), TAG);
    }
}
