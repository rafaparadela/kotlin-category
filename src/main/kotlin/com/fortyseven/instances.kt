package com.fortyseven

import arrow.instance

data class Color(val red: Int, val green: Int, val blue: Int)

object StringMonoidInstance : Monoid<String> {
    override fun empty(): String = ""
    override fun combine(a: String, b: String): String = "$a$b"
}

@instance(Color::class)
interface ColorMonoidInstance: Monoid<Color> {
    override fun empty(): Color = Color(0, 0, 0)
    override fun combine(a: Color, b: Color): Color = Color(
            red = a.red + b.red,
            green = a.green + b.green,
            blue = a.blue + b.blue)
}