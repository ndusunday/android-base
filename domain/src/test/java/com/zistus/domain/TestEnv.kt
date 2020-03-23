package com.zistus.domain

import org.junit.Test

class TestEnv {

    @Test
    public fun test_test() {
        val naira: Double = 500.0
        val kobo: Long = 500
        val text = "5600"

        println("${text.toDouble()}")
        println("${naira.toLong()}")
        println("${kobo.toDouble()}")
        println("${naira.toLong()/kobo}")
    }
}