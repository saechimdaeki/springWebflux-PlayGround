package me.saechimdaeki.playground.basic.sinks

import me.saechimdaeki.playground.LoggerWithKotlinFile
import reactor.core.publisher.Sinks
import reactor.core.publisher.Sinks.EmitFailureHandler

/**
 * replay()를 사용하여 이미 emit된 데이터 중에서 특정 개수의 최신 데이터만 전달하는 예제
 */
fun main() {
    val log = LoggerWithKotlinFile.getLogger("ProgrammaticCreateExample3")

    // 구독 이후, emit된 데이터 중에서 최신 데이터 2개만 replay 한다.
    val replaySink = Sinks.many().replay().limit<Int>(2)
    val fluxView = replaySink.asFlux()

    replaySink.emitNext(1, EmitFailureHandler.FAIL_FAST)
    replaySink.emitNext(2, EmitFailureHandler.FAIL_FAST)
    replaySink.emitNext(3, EmitFailureHandler.FAIL_FAST)

    fluxView.subscribe { data: Int? ->
        log.info(
            "# Subscriber1 onNext: {}",
            data
        )
    }

    replaySink.emitNext(4, EmitFailureHandler.FAIL_FAST)

    fluxView.subscribe { data: Int? ->
        log.info(
            "# Subscriber2 onNext: {}",
            data)
    }
}