package com.fortyseven

inline fun <reified A> A.combine(b: A, FT: Monoid<A>): A = FT.combine(this, b)
inline fun <reified A> A.empty(FT: Monoid<A>): A = FT.empty()