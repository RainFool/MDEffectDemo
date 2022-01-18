package com.rainfool.kotlin.init

/**
 *
 * @author krystian
 */
open class ParentClass {

    private val f1 = Field("ParentClass f1")

    init {
        println("ParentClass init")
        parentFun1()
    }

    constructor() {
        println("ParentClass constructor")
    }

    constructor(data: Int) {
        println("ParentClass constructor with data:$data")
    }

    private val f2 = Field("ParentClass f2")

    private fun parentFun1() {
        println(f2)
    }
}