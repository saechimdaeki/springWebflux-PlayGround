package me.saechimdaeki.playground.basic.schedulers

import me.saechimdaeki.playground.LoggerWithKotlinFile
import reactor.core.publisher.Flux


fun main() {
    val logger = LoggerWithKotlinFile.getLogger("SchedulerOperatorExample01")
    Flux.fromArray(arrayOf(1, 3, 5, 7))
        .filter { data: Int -> data > 3 }
        .map { data: Int -> data * 10 }
        .subscribe {logger.info("onNext: {}", it)}
}
