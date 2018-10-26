package com.example.app.recyclerview

import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.app.R

/**
 * @author rainfool
 * @date 2018/1/16
 */
class SimpleNumberAdapter : RecyclerView.Adapter<SimpleNumberAdapter.SimpleNumberViewHolder> {

    private val TAG = "SimpleNumberAdapter"

    private var parentPosition = 0
    private var count = 5

    constructor(count: Int) {
        this.count = count
    }

    constructor(parentPosition: Int, count: Int) {
        this.parentPosition = parentPosition
        this.count = count
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SimpleNumberViewHolder {
        Log.e(TAG, "onCreateViewHolder: sub[$parentPosition]")
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.item_recycler_demo_sub, parent, false)
        return SimpleNumberViewHolder(view)
    }

    override fun onBindViewHolder(holder: SimpleNumberViewHolder, position: Int) {
        Log.d(TAG, "onBindViewHolder: sub[$parentPosition,$position]")
        holder.setNum(parentPosition*10 + position)
    }

    override fun getItemCount(): Int {

        return count
    }

    override fun getItemViewType(position: Int): Int {
        return 1
    }


    inner class SimpleNumberViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private var textView: TextView = itemView.findViewById(R.id.tv_simple_demo)

        fun setNum(i: Int) {
            textView.text = i.toString() + ""
        }
    }
}
