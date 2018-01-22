package com.fortyseven

import arrow.deriving
import arrow.higherkind

@higherkind
@deriving(Functor::class)
sealed class Box<out A> : BoxKind<A>{

    fun <B> map(f: (A) -> B): Box<B> =
            when (this) {
                Empty -> Empty
                is Full -> Full(f(this.t))
            }

    companion object
}
object Empty : Box<Nothing>()
data class Full<out T>(val t: T) : Box<T>()
