package me.saechimdaeki.playground.basic.cold_and_hot

import me.saechimdaeki.playground.LoggerWithKotlinFile
import reactor.core.publisher.Flux
import java.time.Duration
import java.util.stream.Stream

fun main() {

    val logger = LoggerWithKotlinFile.getLogger("HotSequence")

    val concertFlux = Flux.fromStream(Stream.of("Singer A", "Singer B", "Singer C", "Singer D", "Singer E"))
        .delayElements(Duration.ofSeconds(1)).share()
    concertFlux.subscribe { singer ->
        logger.info("# Subscriber1 is watching $singer 's song.")
    }
    Thread.sleep(2500)
    concertFlux.subscribe { singer ->
        logger.info("# Subscriber2 is watching $singer's song.")
    }
    Thread.sleep(3000)
}