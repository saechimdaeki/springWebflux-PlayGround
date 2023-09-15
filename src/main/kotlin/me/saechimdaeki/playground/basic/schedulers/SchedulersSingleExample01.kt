package me.saechimdaeki.playground.basic.schedulers

import me.saechimdaeki.playground.LoggerWithKotlinFile
import org.slf4j.Logger
import reactor.core.publisher.Flux
import reactor.core.scheduler.Schedulers


fun main() {
    val logger = LoggerWithKotlinFile.getLogger("SchedulerOperatorExample01")
    
    doTask("task1", logger)
        .subscribe{logger.info("onNext: {}", it)}
    doTask("task2",logger)
        .subscribe{logger.info("onNext: {}", it)}
}



private fun doTask(taskName: String, logger : Logger): Flux<Int> {
    return Flux.fromArray<Int>(arrayOf<Int>(1, 3, 5, 7))
        .publishOn(Schedulers.single())
        .filter { data: Int -> data > 3 }
        .doOnNext { data: Int? ->
            logger.info(
                taskName,
                "filter",
                data
            )
        }
        .map<Int> { data: Int -> data * 10 }
        .doOnNext { data: Int? -> logger.info(taskName, "map", data) }
}

