package com.fortyseven

import org.junit.Assert.assertEquals
import org.junit.Test

class AppTest {

    @Test fun testColorMonoid() {
        val green = Color(0, 255, 0)
        val red = Color(255, 0, 0)
        val c3 = Color(11, 201,94)

        val id1 = green.combine(Color.monoid().empty())
        val yellow = green.combine(red)
        val pathOne = (green.combine(red)).combine(c3)
        val pathTwo = green.combine(red.combine(c3))

        assertEquals("Composition", Color(255, 255, 0), yellow)
        assertEquals("Identity", green, id1)
        assertEquals("Associativity", pathTwo, pathOne)
    }

    @Test fun testBoxFunctor() {

val box1: Box<Int> = Full(1)
val boxHi: Box<String> = Full("Hi")
val boxBlue: Box<Color> = Full(Color(0, 0, 255))


        assertEquals("1", 1, 1)
    }
}