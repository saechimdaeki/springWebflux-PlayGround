package me.saechimdaeki.playground.basic.flux

import me.saechimdaeki.playground.LoggerWithKotlinFile
import reactor.core.publisher.Mono

fun main() {
    val logger = LoggerWithKotlinFile.getLogger("FluxByMono")
    val flux = Mono.justOrEmpty<Any>(null)
        .concatWith(Mono.justOrEmpty("Jobs"))
    flux.subscribe { logger.info("# result: $it") }

}