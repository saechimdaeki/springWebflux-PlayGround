package me.saechimdaeki.playground.basic

import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import java.util.*

fun main() {
    Mono.just("Hello, Mono Reactor!")
        .subscribe(::println)

    val fluxSequence = Flux.just("Hello Flux", "Reactor!")
    fluxSequence.map {
        it.lowercase(Locale.getDefault())
    }.subscribe { println(it) }
}