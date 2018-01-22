package com.fortyseven

import arrow.*

@typeclass
interface Monoid<A> : TC {
    fun empty(): A
    fun combine(a: A, b: A): A
}


@typeclass
interface Functor<F> : TC {
    fun <A, B> map(fa: HK<F, A>, f: (A) -> B): HK<F, B>
}
