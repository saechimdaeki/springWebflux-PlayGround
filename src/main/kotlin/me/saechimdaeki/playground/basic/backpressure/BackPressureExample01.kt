package me.saechimdaeki.playground.basic.backpressure

import me.saechimdaeki.playground.LoggerWithKotlinFile
import org.reactivestreams.Subscription
import reactor.core.publisher.BaseSubscriber
import reactor.core.publisher.Flux


/**
 * Subscriber가 처리 가능한 만큼의 request 개수를 조절하는 Backpressure 예제
 */
fun main() {
    val logger = LoggerWithKotlinFile.getLogger("BackPressureExample")

    Flux.range(1, 5)
        .doOnNext { logger.info("doOnNext") }
        .doOnRequest { logger.info("doOnRequest") }
        .subscribe(object : BaseSubscriber<Int>() {
            override fun hookOnSubscribe(subscription: Subscription) {
                request(1)
            }

            override fun hookOnNext(value: Int) {
                Thread.sleep(2000)
                logger.info("onNext $value")
                request(1)
            }
        })
}