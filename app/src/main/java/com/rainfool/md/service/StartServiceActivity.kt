package com.rainfool.md.service

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import com.rainfool.md.R

class StartServiceActivity : AppCompatActivity() {

    val NOTIFY_CLICK_EXTRA_DATA = "notify_data"


    private val KEY_PUSH_TYPE = "push_type"
    private val KEY_ACTION = "action"
    private val KEY_TITLE = "title"
    private val KEY_ALERT = "alert"
    private val KEY_CATALOG = "catalog"
    private val KEY_TRACEID = "traceid"
    private val KEY_IMAGE_URL = "image_url"

    val TEST_JSON =
        "{pushType=16, title='血色-孙耀威 发布了动态', alert='《爱的故事上集》终于等到孙耀威', action='live://uid=1018426358&catalog=mmt-post&traceid=push_mmt-post_6618883162017030606', catalog='mmt-post', traceid='push_mmt-post_6618883162017030606', imageurl=''}"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_start_service)
    }

    fun onStartServiceClick(v: View) {
//        val intent = Intent();
//        intent.component = ComponentName("com.duowan.kiwi", "com.duowan.kiwi.push.NotifyReceiver")
//        intent.action = "com.duowan.kiwi.notify_click"
//        val gson = Gson();
//        val pushMessage = gson.fromJson<PushMessage>(TEST_JSON, PushMessage::class.java)
//        intent.putExtra(NOTIFY_CLICK_EXTRA_DATA + "gegwe", toUri(pushMessage))
//        intent.setData(toUri(pushMessage))
//        sendBroadcast(intent)
        val intent = Intent()
        intent.setPackage("com.duowan.kiwi")
//        intent.component = ComponentName("com.duowan.kiwi","com.duowan.kiwi.services.pull.PullAliveService")
        intent.action = "com.duowan.kiwi.PullAlive"
        startService(intent)
    }

    fun toUri(message: PushMessage): Uri {
        return Uri.Builder()
            .appendQueryParameter(KEY_PUSH_TYPE, message.pushType.toString())
            .appendQueryParameter(KEY_TITLE, message.title)
            .appendQueryParameter(KEY_ALERT, message.alert)
            .appendQueryParameter(KEY_ACTION, message.action)
            .appendQueryParameter(KEY_CATALOG, message.catalog)
            .appendQueryParameter(KEY_TRACEID, message.traceid)
            .appendQueryParameter(KEY_IMAGE_URL, message.imageurl)
            .build()
    }
}
