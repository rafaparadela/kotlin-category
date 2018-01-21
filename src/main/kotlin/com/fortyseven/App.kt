package com.fortyseven


class App {

    fun sayHi() = println("Hi")

    val gg = "Hola".combine("Mundo", StringMonoidInstance)

    val c1 = Color(40, 47, 63)
    val c2 = Color(194, 82, 132)

//    val c3 = Monoid<Color>.combine(c1, c2)
    val g = monoid<Color>().empty()


}

