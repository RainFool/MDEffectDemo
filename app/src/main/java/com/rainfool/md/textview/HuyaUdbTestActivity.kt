package com.rainfool.md.textview

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.rainfool.md.R
import kotlinx.android.synthetic.main.activity_huya_udb_test2.*

class HuyaUdbTestActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_huya_udb_test2)

        button.setOnClickListener {
            val deepLinkUrl = "kiwi_udb://auth?client_id=ns2t6uf3&package_name=com.tencent.qt.qtl"
            val intent = Intent("android.intent.action.VIEW", Uri.parse(deepLinkUrl))
            startActivity(intent)
        }
    }
}