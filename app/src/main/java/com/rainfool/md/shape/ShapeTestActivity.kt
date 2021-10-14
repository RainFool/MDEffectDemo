package com.rainfool.md.shape

import android.app.Activity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.rainfool.md.R
import kotlinx.android.synthetic.main.activity_shape_test.*

/**
 *
 * @author krystian
 */
class ShapeTestActivity : Activity() {

    private val factory = TeamCardBackgroundFactory(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_shape_test)
        rvShape.adapter = object : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
            override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
                val view = LayoutInflater.from(parent.context).inflate(R.layout.item_shape_test, parent, false)
                return object : RecyclerView.ViewHolder(view) {

                }
            }

            override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
                holder.itemView.background = factory.generateDrawableFromIndex(position)
            }

            override fun getItemCount() = factory.size()

        }
        rvShape.layoutManager = GridLayoutManager(this, 2)
        btnNext.setOnClickListener {
            shapeTestView.background = factory.next()
            tvInfo.text = "当前第${factory.colorIndex}个色值"
        }
    }
}