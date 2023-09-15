package me.saechimdaeki.playground.basic.schedulers

import me.saechimdaeki.playground.LoggerWithKotlinFile
import reactor.core.publisher.Mono
import reactor.core.scheduler.Schedulers


fun main() {

    val logger = LoggerWithKotlinFile.getLogger("SchedulerOperatorExample01")

    val scheduler = Schedulers.newBoundedElastic(2, 2, "I/O-Thread")
    val mono = Mono
        .just(1)
        .subscribeOn(scheduler)
    logger.info("# Start")
    mono.subscribe { data: Int? ->
        logger.info("subscribe 1 doing {}", data)
        logger.info("subscribe 1 done {}", data)
    }
    mono.subscribe { data: Int? ->
        logger.info("subscribe 2 doing {}", data)
        logger.info("subscribe 2 done {}", data)
    }
    mono.subscribe { data: Int? ->
        logger.info(
            "subscribe 3 doing {}",
            data
        )
    }
    mono.subscribe { data: Int? ->
        logger.info(
            "subscribe 4 doing {}",
            data
        )
    }
    mono.subscribe { data: Int? ->
        logger.info(
            "subscribe 5 doing {}",
            data
        )
    }
    mono.subscribe { data: Int? ->
        logger.info(
            "subscribe 6 doing {}",
            data
        )
    }

}