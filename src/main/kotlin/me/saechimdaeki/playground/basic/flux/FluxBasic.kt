package me.saechimdaeki.playground.basic.flux

import org.slf4j.LoggerFactory
import reactor.core.publisher.Flux

fun main() {
    val logger = LoggerFactory.getLogger("FluxBasic")
    Flux.just(6,9,13)
        .map { it%2 }
        .subscribe { logger.info(" remainder $it") }
}