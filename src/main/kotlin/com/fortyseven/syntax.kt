package com.fortyseven

import arrow.HK

inline fun <reified A> A.combine(b: A, FT: Monoid<A> = monoid()): A = FT.combine(this, b)
inline fun <reified A> A.empty(FT: Monoid<A> = monoid()): A = FT.empty()

inline fun <reified F, A> HK<F, HK<F, A>>.flatten(FT: Monad<F> = monad()): HK<F, A> = FT.flatten(this)