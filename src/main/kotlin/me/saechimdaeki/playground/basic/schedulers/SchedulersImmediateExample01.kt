package me.saechimdaeki.playground.basic.schedulers

import me.saechimdaeki.playground.LoggerWithKotlinFile
import reactor.core.publisher.Flux
import reactor.core.scheduler.Schedulers


fun main() {

    val logger = LoggerWithKotlinFile.getLogger("SchedulerOperatorExample04")
    Flux.fromArray(arrayOf(1, 3, 5, 7))
        .publishOn(Schedulers.parallel())
        .filter { data: Int -> data > 3 }
        .doOnNext { data: Int? -> logger.info("filter {}", data) }
        .publishOn(Schedulers.parallel())
        .map { data: Int -> data * 10 }
        .doOnNext { data: Int? -> logger.info("map {}", data) }
        .subscribe { data: Int? ->
            logger.info("onNext: {}", data)
        }
}