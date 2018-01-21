package com.fortyseven

import arrow.instance

@instance(Color::class)
interface ColorMonoidInstance: Monoid<Color> {
    override fun empty(): Color = Color(0, 0, 0)
    override fun combine(a: Color, b: Color): Color = Color(
            red = (a.red + b.red)/2,
            green = (a.green + b.green)/2,
            blue = (a.blue + b.blue)/2)
}