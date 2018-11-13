package com.rainfool.md.inflater

import android.util.Log

/**
 *
 *
 * @author rainfool
 * @date 2018/10/25
 */
object TimeWatcher {

    private val TAG = "TimeWatcher"

    private val mSparseLongArray = HashMap<String, Long>()

    fun onMethodEntry(tag: String = "Default") {
        mSparseLongArray[tag] = System.currentTimeMillis()
        Log.d(TAG, "⇢ $tag entry")
    }

    fun onMethodExit(tag: String = "Default") {
        val startTime = mSparseLongArray[tag] ?: return
        val cost = System.currentTimeMillis() - startTime
        Log.d(TAG, "⇠ $tag exit [$cost ms]")
    }
}