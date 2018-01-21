package com.fortyseven

inline fun <reified A> A.combine(b: A, FT: Monoid<A> = monoid()): A = FT.combine(this, b)
inline fun <reified A> A.empty(FT: Monoid<A> = monoid()): A = FT.empty()