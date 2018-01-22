package com.fortyseven

import arrow.instance
import kotlin.math.min

@instance(Color::class)
interface ColorMonoidInstance: Monoid<Color> {
    override fun empty(): Color = Color(0, 0, 0)
    override fun combine(a: Color, b: Color): Color = Color(
            red = min(a.red + b.red, 255),
            green = min(a.green + b.green, 255),
            blue = min(a.blue + b.blue, 255))
}

@instance(Box::class)
interface BoxFunctorInstance : Functor<BoxHK> {
    override fun <A, B> map(fa: BoxHK<A>, f: kotlin.Function1<A, B>): Box<B> = fa.ev().map(f)
             fun <A, B> map(fa: HK<F, A>, f: (A) -> B): HK<F, B>
}