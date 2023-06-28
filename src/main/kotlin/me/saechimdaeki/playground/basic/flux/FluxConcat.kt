package me.saechimdaeki.playground.basic.flux

import me.saechimdaeki.playground.LoggerWithKotlinFile
import reactor.core.publisher.Flux

fun main() {
    val logger = LoggerWithKotlinFile.getLogger("FluxConcat")
    Flux.concat(
        Flux.just<String>("Venus"),
        Flux.just<String>("Earth"),
        Flux.just<String>("Mars")
    ).collectList().subscribe {
        logger.info("# Solar System: $it")
    }
}