package me.saechimdaeki.playground.basic.backpressure

import me.saechimdaeki.playground.LoggerWithKotlinFile
import reactor.core.publisher.BufferOverflowStrategy
import reactor.core.publisher.Flux
import reactor.core.scheduler.Schedulers
import java.time.Duration


/**
 * Unbounded request 일 경우, Downstream 에 Backpressure Buffer DROP_LATEST 전략을 적용하는 예제
 *  - Downstream 으로 전달 할 데이터가 버퍼에 가득 찰 경우,
 *    버퍼 안에 있는 데이터 중에서 가장 최근에(나중에) 버퍼로 들어온 데이터부터 Drop 시키는 전략
 */
fun main() {
    val logger = LoggerWithKotlinFile.getLogger("BackPressureStrategyLatest")
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
            BufferOverflowStrategy.DROP_LATEST
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
        ) { error -> logger.error("에러 $error") }

    Thread.sleep(3000L)
}