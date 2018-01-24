package com.fortyseven

import arrow.deriving
import arrow.higherkind

@higherkind
@deriving(Functor::class, Applicative::class)
sealed class Box<out A> : BoxKind<A> {

    fun <B> map(f: (A) -> B): Box<B> = when (this) {
        Empty -> Empty
        is Full -> Full(f(this.t))
    }

    fun <B> ap(ff: BoxKind<(A) -> B>): Box<B> {
        val box = ff.ev()
        return when (this) {
            is Full -> {
                when (box) {
                    is Full -> Full(box.t(this.t))
                    Empty -> Empty
                }
            }
            Empty -> Empty
        }
    }

    companion object {
        fun <A> pure(a: A): Box<A> = Full(a)
    }
}

object Empty : Box<Nothing>()
data class Full<out T>(val t: T) : Box<T>()
