package com.fortyseven

import arrow.Kind

inline fun <reified A> A.combine(b: A, FT: Monoid<A> = monoid()): A = FT.combine(this, b)
inline fun <reified A> A.empty(FT: Monoid<A> = monoid()): A = FT.empty()

inline fun <reified F, A> Kind<F, Kind<F, A>>.flatten(FT: Monad<F> = monad()): Kind<F, A> = FT.flatten(this)