package me.saechimdaeki.playground.basic.schedulers

import me.saechimdaeki.playground.LoggerWithKotlinFile
import reactor.core.publisher.Flux
import reactor.core.scheduler.Schedulers


fun main() {
    val logger = LoggerWithKotlinFile.getLogger("SchedulerOperatorExample02")

    Flux.fromArray(arrayOf(1, 3, 5, 7))
        .doOnNext { logger.info("fromArray {}", it) }
        .publishOn(Schedulers.parallel())
        .filter {it > 3 }
        .doOnNext { logger.info("filter {}", it) }
        .map {  it * 10 }
        .doOnNext { logger.info("map {}", it) }
        .subscribe { logger.info("onNext: {}", it) }
}