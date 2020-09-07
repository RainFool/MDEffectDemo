package com.rainfool.md.fragment

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.rainfool.md.R
import kotlinx.android.synthetic.main.activity_fragment_test.*

class FragmentTestActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fragment_test)
        mBtnTest.setOnClickListener {
            supportFragmentManager.beginTransaction()
                    .replace(R.id.mFragmentContainer, ViewPagerFragment())
                    .commit()
        }
    }
}
