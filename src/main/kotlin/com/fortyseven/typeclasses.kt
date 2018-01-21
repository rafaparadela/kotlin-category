package com.fortyseven

import arrow.*

@typeclass
interface Monoid<A> : TC {
    fun empty(): A
    fun combine(a: A, b: A): A
}