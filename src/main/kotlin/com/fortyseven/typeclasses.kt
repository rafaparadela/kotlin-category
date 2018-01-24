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

@typeclass
interface Applicative<F> : Functor<F>, TC {
    fun <A> pure(a: A): HK<F, A>
    fun <A, B> ap(fa: HK<F, A>, ff: HK<F, (A) -> B>): HK<F, B>
    override fun <A, B> map(fa: HK<F, A>, f: (A) -> B): HK<F, B> = ap(fa, pure(f))
}
