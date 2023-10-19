package com.rainfool.md.constraintlayout

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.rainfool.md.R

class TestConstraintActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test_constraint)
    }

    fun onViewClick(view: View) {
        view.visibility = View.GONE
    }

}