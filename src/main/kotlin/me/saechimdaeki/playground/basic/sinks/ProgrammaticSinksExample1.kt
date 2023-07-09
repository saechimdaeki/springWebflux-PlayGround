package me.saechimdaeki.playground.basic.sinks

import me.saechimdaeki.playground.LoggerWithKotlinFile
import reactor.core.publisher.Sinks
import reactor.core.scheduler.Schedulers
import java.util.stream.IntStream

fun main() {
    val log = LoggerWithKotlinFile.getLogger("ProgrammaticCreateExample1")
    val tasks = 6

    val unicastSink = Sinks.many().unicast().onBackpressureBuffer<String>()
    val fluxView = unicastSink.asFlux()
    IntStream
        .range(1, tasks)
        .forEach { n ->
            try {
                Thread {
                    unicastSink.emitNext(doTask(n), Sinks.EmitFailureHandler.FAIL_FAST)
                    log.info("# emitted: {}", n)
                }.start()
                Thread.sleep(100L)
            } catch (e: InterruptedException) {
            }
        }

    fluxView
        .publishOn(Schedulers.parallel())
        .map { result -> "$result success!" }
        .doOnNext { n -> log.info("# map(): {}", n) }
        .publishOn(Schedulers.parallel())
        .subscribe { data: String? -> log.info("# onNext: {}", data) }

    Thread.sleep(200L)
}

private fun doTask(taskNumber: Int): String {
    // now tasking.
    // complete to task.
    return "task $taskNumber result"
}

