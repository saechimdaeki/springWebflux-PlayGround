package me.saechimdaeki.playground

import reactor.core.publisher.Mono

fun main() {
    Mono.just("Hello, Reactor!")
        .subscribe(::println)
}