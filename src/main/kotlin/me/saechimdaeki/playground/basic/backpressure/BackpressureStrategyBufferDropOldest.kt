package me.saechimdaeki.playground.basic.backpressure

import me.saechimdaeki.playground.LoggerWithKotlinFile
import reactor.core.publisher.BufferOverflowStrategy
import reactor.core.publisher.Flux
import reactor.core.scheduler.Schedulers
import java.time.Duration

fun main() {
    val logger = LoggerWithKotlinFile.getLogger("BackpressureStrategyBufferDropOldest")

    Flux
        .interval(Duration.ofMillis(300L))
        .doOnNext { data ->
            logger.info(
                "# emitted by original Flux: {}",
                data
            )
        }
        .onBackpressureBuffer(
            2,
            { dropped ->
                logger.info(
                    "# Overflow & dropped: {}",
                    dropped
                )
            },
            BufferOverflowStrategy.DROP_OLDEST
        )
        .doOnNext { data ->
            logger.info(
                "# emitted by Buffer: {}",
                data
            )
        }
        .publishOn(Schedulers.parallel(), false, 1)
        .subscribe(
            { data ->
                Thread.sleep(1000L)
                logger.info("# onNext: {}", data)
            }
        ) { error -> logger.error("error $error") }

    Thread.sleep(3000L)
}