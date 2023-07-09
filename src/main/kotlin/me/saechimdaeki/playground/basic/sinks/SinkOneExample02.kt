package me.saechimdaeki.playground.basic.sinks

import me.saechimdaeki.playground.LoggerWithKotlinFile
import reactor.core.publisher.Sinks
import reactor.core.publisher.Sinks.EmitFailureHandler

/**
 * 두 건의 데이터만 emit 하는 예제
 */
fun main() {

    val log = LoggerWithKotlinFile.getLogger("ProgrammaticCreateExample2")


    // emit 된 데이터 중에서 단 하나의 데이터만 Subscriber에게 전달한다. 나머지 데이터는 Drop 됨.
    val sinkOne = Sinks.one<String>()
    val mono = sinkOne.asMono()

    sinkOne.emitValue("Hello Reactor", EmitFailureHandler.FAIL_FAST)

    // Sink.One 은 단 한개의 데이터를 emit 할 수 있기때문에 두번째 emit한 데이터는 drop 된다.

    // Sink.One 은 단 한개의 데이터를 emit 할 수 있기때문에 두번째 emit한 데이터는 drop 된다.
    sinkOne.emitValue("Hi Reactor", EmitFailureHandler.FAIL_FAST)


    mono.subscribe { data ->
        log.info(
            "# onNext: {}",
            data
        )
    }
    mono.subscribe { data ->
        log.info(
            "# onNext: {}",
            data
        )
    }
}