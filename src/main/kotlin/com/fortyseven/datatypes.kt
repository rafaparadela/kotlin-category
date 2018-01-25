package com.fortyseven

import arrow.core.Tuple2
import arrow.deriving
import arrow.higherkind

@higherkind
@deriving(Functor::class, Applicative::class, Monad::class)
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

    fun <B> product(fb: BoxKind<B>): Box<Tuple2<A, B>> {
        val box = fb.ev()
        return when (this) {
            is Full -> {
                when (box) {
                    is Full -> Full(Tuple2(this.t, box.t))
                    Empty -> Empty
                }
            }
            Empty -> Empty
        }
    }

    fun <B, Z> map2(fb: BoxKind<B>, f: (Tuple2<A, B>) -> Z): Box<Z> {
        val box = fb.ev()
        return when (this) {
            is Full -> {
                when (box) {
                    is Full -> Full(f(Tuple2(this.t, box.t)))
                    Empty -> Empty
                }
            }
            Empty -> Empty
        }
    }

    inline fun <B> flatMap(crossinline f: (A) -> BoxKind<B>): Box<B> {
        return when (this) {
            is Full -> f(this.t).ev()
            Empty -> Empty
        }
    }

    companion object {
        fun <A> pure(a: A): Box<A> = Full(a)
    }
}

object Empty : Box<Nothing>()
data class Full<out T>(val t: T) : Box<T>()
