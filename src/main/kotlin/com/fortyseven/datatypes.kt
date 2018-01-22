package com.fortyseven

import arrow.higherkind

@higherkind
sealed class Box<out A> : BoxKind<A>{
    companion object {}
}
object Empty : Box<Nothing>()
data class Full<out T>(val t: T) : Box<T>()
