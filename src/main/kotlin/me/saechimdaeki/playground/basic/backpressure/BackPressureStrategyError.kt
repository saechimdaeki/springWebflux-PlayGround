package me.saechimdaeki.playground.basic.backpressure

import me.saechimdaeki.playground.LoggerWithKotlinFile
import reactor.core.publisher.Flux
import reactor.core.scheduler.Schedulers
import java.time.Duration


/**
 * Unbounded request 일 경우, Downstream 에 Backpressure Error 전략을 적용하는 예제
 *  - Downstream 으로 전달 할 데이터가 버퍼에 가득 찰 경우, Exception을 발생 시키는 전략
 */
fun main() {
    val logger = LoggerWithKotlinFile.getLogger("BackPressureStrategyError")

    Flux.interval(Duration.ofMillis(1))
        .onBackpressureError()
        .doOnNext { logger.info("doOnNext") }
        .publishOn(Schedulers.parallel())
        .subscribe({ data ->
            Thread.sleep(5L)
            logger.info("onNext $data")
        }, { error ->
            logger.error("onError $error")
        })


    Thread.sleep(2000)
}


