package com.example.kotlin

/**
 *
 *
 * @author rainfool
 * @date 2018/8/22
 */
class KotlinDemo private constructor(int: Int) {
    companion object {
        fun getInstance(int: Int) = KotlinDemo(int)
    }
}