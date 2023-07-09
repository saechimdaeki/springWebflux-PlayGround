package me.saechimdaeki.playground.basic.sinks

import me.saechimdaeki.playground.LoggerWithKotlinFile
import reactor.core.publisher.Flux
import reactor.core.publisher.FluxSink
import reactor.core.scheduler.Schedulers
import java.util.stream.IntStream

fun main() {
    val log = LoggerWithKotlinFile.getLogger("ProgrammaticCreateExample1")


    val tasks = 6
    Flux
        .create<String?> { sink: FluxSink<String?> ->
            IntStream
                .range(1, tasks)
                .forEach { n -> sink.next(doTask(n)) }
        }
        .subscribeOn(Schedulers.boundedElastic())
        .doOnNext { n -> log.info("# create(): {}", n) }
        .publishOn(Schedulers.parallel())
        .map { result -> "$result success!" }
        .doOnNext { n -> log.info("# map(): {}", n) }
        .publishOn(Schedulers.parallel())
        .subscribe { data -> log.info("# onNext: {}", data) }
    Thread.sleep(500L)

}

private fun doTask(taskNumber: Int): String {
    return "task $taskNumber result"
}