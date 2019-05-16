package com.rainfool.md.drawing

import android.graphics.Bitmap
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.rainfool.md.R
import kotlinx.android.synthetic.main.activity_drawing_cache_demo.*

class DrawingCacheDemoActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_drawing_cache_demo)
        mButton.setOnClickListener {
            mContainer.isDrawingCacheEnabled = true
            mContainer.buildDrawingCache()
            val bitmap = Bitmap.createBitmap(mContainer.drawingCache)
            mIvPreview.setImageBitmap(bitmap)
        }
    }
}
