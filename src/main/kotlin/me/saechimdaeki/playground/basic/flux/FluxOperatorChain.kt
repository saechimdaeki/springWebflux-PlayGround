package me.saechimdaeki.playground.basic.flux

import me.saechimdaeki.playground.LoggerWithKotlinFile
import reactor.core.publisher.Flux

fun main() {
    val logger = LoggerWithKotlinFile.getLogger("FluxOperatorChain")

    Flux.fromArray(arrayOf(3, 6, 7, 9))
        .filter { num -> num > 6 }
        .map { num -> num * 2 }
        .subscribe { multiply: Int? -> logger.info("# multiply: {}", multiply) }
}