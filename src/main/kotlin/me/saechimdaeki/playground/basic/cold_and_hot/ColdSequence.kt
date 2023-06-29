package me.saechimdaeki.playground.basic.cold_and_hot

import me.saechimdaeki.playground.LoggerWithKotlinFile
import reactor.core.publisher.Flux
import java.util.*

fun main() {

    val logger = LoggerWithKotlinFile.getLogger("ColdSequence")


    val coldFlux = Flux.fromIterable(mutableListOf("RED", "YELLOW", "PINK"))
        .map { obj ->
            obj.lowercase(
                Locale.getDefault()
            )
        }
    coldFlux.subscribe { country ->
        logger.info(
            "# Subscriber1: $country"
        )
    }
    logger.info("-------------------------")
    coldFlux.subscribe { country-> logger.info("# Subscriber2: $country") }
}