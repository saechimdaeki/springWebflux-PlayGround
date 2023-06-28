package me.saechimdaeki.playground.basic.mono

import me.saechimdaeki.playground.LoggerWithKotlinFile
import reactor.core.publisher.Mono

fun main() {

    val logger = LoggerWithKotlinFile.getLogger("MonoBasic")
    /**
     * 원본 데이터의 emit 없이 onComplete signal만 emit함
     */
    Mono.empty<Any>()
        .subscribe(
            { data -> logger.info("# emitted data: {}", data) },
            { error -> }
        ) { logger.info("# emitted onComplete signal") }

}