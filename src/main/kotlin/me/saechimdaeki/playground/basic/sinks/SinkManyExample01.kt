package me.saechimdaeki.playground.basic.sinks

import me.saechimdaeki.playground.LoggerWithKotlinFile
import reactor.core.publisher.Sinks
import reactor.core.publisher.Sinks.EmitFailureHandler

/**
 * unicast()를 사용해서 단 하나의 Subscriber에게만 데이터를 emit하는 예제
 */
fun main() {
    val log = LoggerWithKotlinFile.getLogger("ProgrammaticCreateExample1")

    val unicastSink = Sinks.many().unicast().onBackpressureBuffer<Int>()
    val fluxView = unicastSink.asFlux()

    unicastSink.emitNext(1, EmitFailureHandler.FAIL_FAST)
    unicastSink.emitNext(2, EmitFailureHandler.FAIL_FAST)

    fluxView.subscribe { data: Int? ->
        log.info("# onNext: {}", data)
    }

    unicastSink.emitNext(3, EmitFailureHandler.FAIL_FAST)


    fluxView.subscribe { log.info("$it") }
}