package com.rainfool.json

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.google.gson.Gson
import com.rainfool.json.entity.TestJsonStr
import com.rainfool.json.entity.ZoneConfig
import com.rainfool.md.R
import kotlin.system.measureTimeMillis
import kotlinx.android.synthetic.main.activity_json_test.btnConfigParseJson
import kotlinx.android.synthetic.main.activity_json_test.btnConfigToJson

/**
 * python2 rheatrace.py -a com.rainfool.md -t 2 -o ./output/mynewtrace.html sched freq idle am wm gfx view binder_driver hal dalvik camera input res
 */
class JsonTestActivity : AppCompatActivity() {
    companion object {
        private const val TAG = "JsonTestActivity"
    }

    private var styleConfig = ""
    private var tabInfo = ZoneConfig.TabInfo()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_json_test)
        btnConfigParseJson.setOnClickListener {
            val times = measureTimeMillis {
                parseTabInfo()
            }
            Log.i(TAG, "btnConfigParseJson time:$times")
        }
        btnConfigToJson.setOnClickListener {

            val times = measureTimeMillis {
                strTabInfo()
            }
            Log.i(TAG, "btnConfigToJson time:$times")
        }
    }

    override fun onStart() {
        super.onStart()

//        parseTabInfo()
//        strTabInfo()
    }

    private fun parseTabInfo() {
        tabInfo = JsonUtil.fromJson(TestJsonStr.styleConfig, ZoneConfig.TabInfo::class.java)
    }

    private fun strTabInfo() {
        styleConfig = JsonUtil.toJson(tabInfo)
    }
}