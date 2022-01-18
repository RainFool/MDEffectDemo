package com.rainfool.kotlin.init

/**
 *
 * @author krystian
 */
class SubClass : ParentClass {

    constructor(data: Int) : super() {
        println("SubClass constructor")
    }

    private val f1: Field = Field("SubClass f1")

    init {
        println("SubClass init")
    }

    private val f2: Field = Field("SubClass f2")
}