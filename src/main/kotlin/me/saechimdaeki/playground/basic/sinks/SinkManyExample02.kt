package me.saechimdaeki.playground.basic.sinks

import me.saechimdaeki.playground.LoggerWithKotlinFile
import reactor.core.publisher.Sinks
import reactor.core.publisher.Sinks.EmitFailureHandler

/**
 * multicast()를 사용해서 하나 이상의 Subscriber에게 데이터를 emit하는 예제
 */
fun main() {
    val log = LoggerWithKotlinFile.getLogger("ProgrammaticCreateExample2")


    // 하나 이상의 Subscriber에게 데이터를 emit할 수 있다.
    val multicastSink = Sinks.many().multicast().onBackpressureBuffer<Int>()
    val fluxView = multicastSink.asFlux()

    multicastSink.emitNext(1, EmitFailureHandler.FAIL_FAST)
    multicastSink.emitNext(2, EmitFailureHandler.FAIL_FAST)


    fluxView.subscribe { data ->
        log.info(
            "# onNext: {}",
            data
        )
    }

    fluxView.subscribe { data ->
        log.info(
            "# onNext: {}",
            data
        )
    }

    multicastSink.emitNext(3, EmitFailureHandler.FAIL_FAST)
}