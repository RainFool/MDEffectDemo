package com.example.app.softinput;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.example.app.R;

import java.util.ArrayList;
import java.util.List;

public class SoftInputTestActivity extends AppCompatActivity {

    ListView listView;
    EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_soft_input_test);
        FrameLayout contentView = findViewById(android.R.id.content);
        contentView.setFitsSystemWindows(true);
        contentView.setClipToPadding(true);
        listView = findViewById(R.id.lv_content);
        editText = findViewById(R.id.et_input);

        listView.setAdapter(new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,getData()));
    }

    private List<String> getData(){

        List<String> data = new ArrayList<String>();
        data.add("测试数据1");
        data.add("测试数据2");
        data.add("测试数据3");
        data.add("测试数据4");

        return data;
    }
}
