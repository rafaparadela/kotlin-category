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
    fun <A, B> map(fa: Kind<F, A>, f: (A) -> B): Kind<F, B>
}

@typeclass
interface Applicative<F> : Functor<F>, TC {
    fun <A> pure(a: A): Kind<F, A>
    fun <A, B> ap(fa: Kind<F, A>, ff: Kind<F, (A) -> B>): Kind<F, B>
    override fun <A, B> map(fa: Kind<F, A>, f: (A) -> B): Kind<F, B> = ap(fa, pure(f))
    fun <A, B> product(fa: Kind<F, A>, fb: Kind<F, B>): Kind<F, Tuple2<A, B>> = ap(fb, map(fa) { a: A -> { b: B -> Tuple2(a, b) } })
    fun <A, B, Z> map2(fa: Kind<F, A>, fb: Kind<F, B>, f: (Tuple2<A, B>) -> Z): Kind<F, Z> = map(product(fa, fb), f)
}

@typeclass
interface Monad<F> : Applicative<F>, TC {
    fun <A, B> flatMap(fa: Kind<F, A>, f: (A) -> Kind<F, B>): Kind<F, B>
    override fun <A, B> ap(fa: Kind<F, A>, ff: Kind<F, (A) -> B>): Kind<F, B> = flatMap(ff, { f -> map(fa, f) })
    fun <A> flatten(ffa: Kind<F, Kind<F, A>>): Kind<F, A> = flatMap(ffa, { it })
}
