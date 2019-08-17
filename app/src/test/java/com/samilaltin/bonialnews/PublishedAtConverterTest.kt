package com.samilaltin.bonialnews

import com.samilaltin.bonialnews.utility.CommonUtil
import org.junit.Assert.*
import org.junit.Test

class PublishedAtConverterTest {
    @Test
    @Throws(Exception::class)
    fun publishedAt_isCorrect() {
        val actual = CommonUtil.publishedAt("2019-08-16T06:23:00Z")
        val expected = CommonUtil.publishedTime
        assertEquals(expected, actual)
    }
}