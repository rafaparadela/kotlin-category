package com.fortyseven

import org.junit.Assert.assertEquals
import org.junit.Test

class AppTest {
    @Test fun testColorMonoid() {
        val c1 = Color(40, 47, 63)
        val c2 = Color(194, 82, 132)

        assertEquals(Color(117, 64, 97), c1.combine(c2))
    }
}