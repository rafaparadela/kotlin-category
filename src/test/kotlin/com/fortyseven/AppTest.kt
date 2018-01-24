package com.fortyseven

import org.junit.Assert.assertEquals
import org.junit.Test

class AppTest {

    @Test fun testColorMonoid() {
        val green = Color(green = 255)
        val red = Color(red = 255)
        val c3 = Color(11, 201, 94)

        val id1 = green.combine(Color.monoid().empty())
        val yellow = green.combine(red)
        val pathOne = (green.combine(red)).combine(c3)
        val pathTwo = green.combine(red.combine(c3))

        assertEquals("Composition", Color(255, 255), yellow)
        assertEquals("Identity", green, id1)
        assertEquals("Associativity", pathTwo, pathOne)
    }

    @Test fun testBoxFunctor() {

        val box1: Box<Int> = Full(10).map { value -> value * 2 }
        val boxHi: Box<String> = Full("Hi")

        val myColor = Color(134, 83, 299)
        val boxColor = Full(myColor)
        val isGray = boxColor
        val boxInt: Box<Int> = Full(Color(blue = 255)).map{ it.blue }


        assertEquals("1", Full(255), boxInt)
    }

    @Test fun testApplicativeFunctor() {

        val getSize: (String) -> Int = { s -> s.length }
        val boxGetSize: Box<(String) -> Int> = Box.pure(getSize)
        val boxInt: Box<Int> = Full("Rafa").ap(boxGetSize)

        assertEquals("1", Full(4), boxInt)
    }
}