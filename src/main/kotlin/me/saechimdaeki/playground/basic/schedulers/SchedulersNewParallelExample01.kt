package me.saechimdaeki.playground.basic.schedulers

import me.saechimdaeki.playground.LoggerWithKotlinFile
import reactor.core.publisher.Mono
import reactor.core.scheduler.Schedulers


fun main() {
    val logger = LoggerWithKotlinFile.getLogger("SchedulerOperatorExample01")
    
    val flux = Mono
        .just(1)
        .publishOn(Schedulers.newParallel("Parallel Thread", 4, true))
    flux.subscribe { data: Int? ->
        logger.info("subscribe 1 {}", data)
    }
    flux.subscribe { data: Int? ->
        logger.info("subscribe 2 {}", data)
    }
    flux.subscribe { data: Int? ->
        logger.info("subscribe 3 {}", data)
    }
    flux.subscribe { data: Int? ->
        logger.info("subscribe 4 {}", data)
    }
}