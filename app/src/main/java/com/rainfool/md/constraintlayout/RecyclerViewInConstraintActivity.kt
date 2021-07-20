package com.rainfool.md.constraintlayout

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager.widget.PagerAdapter
import com.rainfool.md.BaseActivity
import com.rainfool.md.R
import com.rainfool.md.recyclerview.SimpleNumberAdapter
import kotlinx.android.synthetic.main.activity_recycler_view_in_constaint.*

/**
 * @author krystian
 */
class RecyclerViewInConstraintActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recycler_view_in_constaint)
        viewPager.adapter = object : PagerAdapter() {

            override fun instantiateItem(container: ViewGroup, position: Int): Any {
                val view = if (position == 0) {
                    TextView(container.context).apply {
                        text = "The first page"
                    }
                } else {
                    LayoutInflater.from(container.context)
                        .inflate(R.layout.pager_recyclerview_in_constaint, container, false)
                        .apply {
                            val rv = findViewById<RecyclerView>(R.id.recyclerView)
                            rv.adapter = SimpleNumberAdapter(1000)
                            rv.layoutManager = LinearLayoutManager(context, RecyclerView.HORIZONTAL, false)
                        }
                }
                container.addView(view)
                return view
            }

            override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
                super.destroyItem(container, position, `object`)
                container.removeView(`object` as View?)
            }

            override fun getCount(): Int {
                return 2
            }

            override fun isViewFromObject(view: View, `object`: Any): Boolean {
                return view == `object`
            }

        }
    }
}