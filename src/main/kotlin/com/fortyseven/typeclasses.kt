package com.fortyseven

import arrow.*
import arrow.core.Tuple2

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
    fun <A, B> product(fa: HK<F, A>, fb: HK<F, B>): HK<F, Tuple2<A, B>> = ap(fb, map(fa) { a: A -> { b: B -> Tuple2(a, b) } })
    fun <A, B, Z> map2(fa: HK<F, A>, fb: HK<F, B>, f: (Tuple2<A, B>) -> Z): HK<F, Z> = map(product(fa, fb), f)
}

@typeclass
interface Monad<F> : Applicative<F>, TC {
    fun <A, B> flatMap(fa: HK<F, A>, f: (A) -> HK<F, B>): HK<F, B>
    override fun <A, B> ap(fa: HK<F, A>, ff: HK<F, (A) -> B>): HK<F, B> = flatMap(ff, { f -> map(fa, f) })
    fun <A> flatten(ffa: HK<F, HK<F, A>>): HK<F, A> = flatMap(ffa, { it })
}
