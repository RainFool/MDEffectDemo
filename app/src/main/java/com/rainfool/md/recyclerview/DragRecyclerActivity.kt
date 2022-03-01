package com.rainfool.md.recyclerview

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import com.rainfool.md.R
import kotlinx.android.synthetic.main.activity_recycler_drag_demo.rvContainer
import androidx.recyclerview.widget.RecyclerView
import java.util.Collections

/**
 *
 * @author krystian
 */
class DragRecyclerActivity : AppCompatActivity() {

    companion object {
        private const val TAG = "DragRecyclerActivity"
    }

    val list = (0..100).map {
        it.toString()
    }.toMutableList()
    val adapter = SimpleStringAdapter(list)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recycler_drag_demo)
        rvContainer.layoutManager = LinearLayoutManager(this)
        rvContainer.adapter = adapter
        val itemTouchHelper = ItemTouchHelper(callback)
        itemTouchHelper.attachToRecyclerView(rvContainer)
    }

    //itemHelper的回调
    val callback: ItemTouchHelper.Callback = object : ItemTouchHelper.Callback() {
        override fun getMovementFlags(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder): Int {
            //也就是说返回值是组合式的
            val swipFlag = 0
            val dragflag: Int = ItemTouchHelper.UP or ItemTouchHelper.DOWN
            Log.d(TAG, "getMovementFlags")
            return makeMovementFlags(dragflag, swipFlag)
        }

        override fun onMove(
            recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder, target: RecyclerView.ViewHolder
        ): Boolean {
            //直接按照文档来操作啊，这文档写得太给力了,简直完美！
            val adapterPosition = viewHolder.adapterPosition
            val targetPosition = target.adapterPosition
            adapter.notifyItemMoved(adapterPosition, targetPosition)
            //注意这里有个坑的，itemView 都移动了，对应的数据也要移动
            Collections.swap(list, adapterPosition, targetPosition)
            Log.d(TAG, "onMove,$adapterPosition to $targetPosition")
            return true
        }
        /**
         * 官方文档如下：返回true 当前tiem可以被拖动到目标位置后，直接”落“在target上，其他的上面的tiem跟着“落”，
         * 所以要重写这个方法，不然只是拖动的tiem在动，target tiem不动，静止的
         * Return true if the current ViewHolder can be dropped over the the target ViewHolder.
         * @param recyclerView
         * @param current
         * @param target
         * @return
         */
        override fun canDropOver(
            recyclerView: RecyclerView, current: RecyclerView.ViewHolder, target: RecyclerView.ViewHolder
        ): Boolean {
            return true
        }

        override fun isLongPressDragEnabled(): Boolean {
            return true
        }

        override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
            Log.d(TAG, "onSwiped,${viewHolder.adapterPosition},direction: $direction")
        }
    }
}