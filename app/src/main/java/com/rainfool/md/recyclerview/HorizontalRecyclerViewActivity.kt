package com.rainfool.md.recyclerview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import android.util.Log
import com.rainfool.md.R
import kotlinx.android.synthetic.main.activity_horizontal_recycler_view.*

class HorizontalRecyclerViewActivity : AppCompatActivity() {

    companion object {
        private const val TAG = "HorizontalRecyclerView"
    }

    private val mDataList = listOf<Int>(10, 20, 33, 520, 999)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_horizontal_recycler_view)

        mRvHorizontal.layoutManager = HorizontalEqualLayoutManager(this, androidx.recyclerview.widget.LinearLayoutManager.HORIZONTAL, false)

        mRvHorizontal.adapter = SimplePriceAdapter(mDataList.map { it.toString() })

        mRvHorizontal.viewTreeObserver.addOnPreDrawListener {
            Log.d(TAG, "onlayout ${mRvHorizontal.width},${mRvHorizontal.measuredWidth}")
            if (mRvHorizontal.measuredWidth < 800) {

            }
            true
        }
    }
}
