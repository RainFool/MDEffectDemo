package com.rainfool.md.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentStatePagerAdapter
import androidx.viewpager.widget.ViewPager

import com.rainfool.md.R
import kotlinx.android.synthetic.main.fragment_blank.*

/**
 * A simple [Fragment] subclass.
 * Use the [ViewPagerFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ViewPagerFragment : Fragment() {

    init {
        Log.i(TAG, "constructor")
    }

    private lateinit var mFragmentStatePagerAdapter: FragmentStatePagerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.i(TAG, "onCreate")

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_blank, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val fragmentList = listOf<Fragment>(BlankFragment(), BlankFragment(), BlankFragment(), BlankFragment(), BlankFragment())
        childFragmentManager?.let { it ->
            mFragmentStatePagerAdapter = object : FragmentStatePagerAdapter(it) {
                //                override fun getItem(position: Int): Fragment = if (view?.parent is ViewPager) BlankFragment() else ViewPagerFragment()
                override fun getItem(position: Int): Fragment = fragmentList[position]

                override fun getCount(): Int = fragmentList.size

            }
            mViewPager.adapter = mFragmentStatePagerAdapter
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt("rainfool", 1)
    }

    companion object {

        private const val TAG = "BlankFragment"

        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment BlankFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance() =
                ViewPagerFragment()
    }
}
