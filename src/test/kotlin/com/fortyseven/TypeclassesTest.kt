package com.fortyseven

import org.junit.Assert.assertEquals
import org.junit.Test

class TypeclassesTest {

    @Test
    fun testColorMonoid() {
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

    @Test
    fun testBoxFunctor() {

        val boxInt: Box<Int> = Full(Color(blue = 255)).map { it.blue }
        assertEquals("1", Full(255), boxInt)
    }

    @Test
    fun testBoxApplicative() {

        fun getPyramidFromDB(name: String): Box<Pyramid> = Full(Pyramid(Color()))
        fun getCylinderFromWS(name: String): Box<Cylinder> = Full(Cylinder(name))

        val maybePyramid: Box<Pyramid> = getPyramidFromDB("MyPyramid")
        val maybeCylinder: Box<Cylinder> = getCylinderFromWS("MyCylinder")


        val maybeCone: Box<Cone> = Box.applicative().map2(maybePyramid, maybeCylinder, { t -> Cone(t.b.name) })

        val boxInt: Box<Int> = Full("Rafa").ap(Box.pure { s -> s.length })

        assertEquals("ap works", Full(4), boxInt)
        assertEquals("map2 works", Full(Cone("MyCylinder")), maybeCone)
    }

    @Test
    fun testBoxMonad() {

        fun getPyramidFromDB(name: String): Box<Pyramid> = Full(Pyramid(Color()))
        fun getCylinderFromWS(pyramid: Pyramid): Box<Cylinder> = Full(Cylinder(pyramid.color.red.toString()))


        val cone: Box<Cone> = getPyramidFromDB("MyPyramid").flatMap { pyramid ->
            getCylinderFromWS(pyramid).map { cylinder ->
                Cone(cylinder.name)
            }
        }

        assertEquals("flatMap works", Full(Cone("0")), cone)
    }
}