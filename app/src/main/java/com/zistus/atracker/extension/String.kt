package com.zistus.atracker.extension

fun String.Companion.empty() = ""
fun String.testString() = "test string"
fun String.isCode() = this.isNotBlank() && this.isNotBlank() && this.length == 6

fun String.toPhoneNG() = "+234$this"
fun String.trimWhiteSpace() = this.replace("\\s".toRegex(), "")
