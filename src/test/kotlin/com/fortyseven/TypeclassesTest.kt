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

        fun getPotatoFromDB(name: String): Box<Potato> = Full(Potato(Color()))
        fun getPizzaFromWS(name: String): Box<PizzaSlide> = Full(PizzaSlide(name))

        val maybePotato: Box<Potato> = getPotatoFromDB("MyPotato")
        val maybePizza: Box<PizzaSlide> = getPizzaFromWS("MyPizza")


        val maybeBeer: Box<Beer> = Box.applicative().map2(maybePotato, maybePizza, { t -> Beer(t.b.name) })

        val boxInt: Box<Int> = Full("Rafa").ap(Box.pure { s -> s.length })

        assertEquals("ap works", Full(4), boxInt)
        assertEquals("map2 works", Full(Beer("MyPizza")), maybeBeer)
    }

    @Test
    fun testBoxMonad() {

        fun getPotatoFromDB(name: String): Box<Potato> = Full(Potato(Color()))
        fun getPizzaFromWS(potato: Potato): Box<PizzaSlide> = Full(PizzaSlide(potato.color.red.toString()))


        val beer: Box<Beer> = getPotatoFromDB("MyPotato").flatMap { po ->
            getPizzaFromWS(po).map { pi ->
                Beer(pi.name)
            }
        }

        assertEquals("flatMap works", Full(Beer("0")), beer)
    }
}