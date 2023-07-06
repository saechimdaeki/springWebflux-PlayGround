package me.saechimdaeki.playground.basic.backpressure

import me.saechimdaeki.playground.LoggerWithKotlinFile
import reactor.core.publisher.Flux
import reactor.core.scheduler.Schedulers
import java.time.Duration

/**
 * Unbounded request 일 경우, Downstream 에 Backpressure Drop 전략을 적용하는 예제
 *  - Downstream 으로 전달 할 데이터가 버퍼에 가득 찰 경우, 버퍼 밖에서 대기하는 먼저 emit 된 데이터를 Drop 시키는 전략
 */
fun main() {

    val logger = LoggerWithKotlinFile.getLogger("BackpressureStrategyDrop")

    Flux
        .interval(Duration.ofMillis(1L))
        .onBackpressureDrop { dropped: Long? ->
            logger.info(
                "# dropped: {}",
                dropped
            )
        }
        .publishOn(Schedulers.parallel())
        .subscribe(
            { data ->
                Thread.sleep(5L)
                logger.info("# onNext $data")
            }
        ) { error -> logger.error("에러 $error") }

    Thread.sleep(2000L)
}