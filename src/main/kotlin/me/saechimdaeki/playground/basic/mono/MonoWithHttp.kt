package me.saechimdaeki.playground.basic.mono

import com.jayway.jsonpath.JsonPath
import me.saechimdaeki.playground.LoggerWithKotlinFile
import org.springframework.http.*
import org.springframework.web.client.RestTemplate
import org.springframework.web.util.UriComponentsBuilder
import reactor.core.publisher.Mono

fun main() {
    val logger = LoggerWithKotlinFile.getLogger("MonoWithHttp")


    val worldTimeUri = UriComponentsBuilder.newInstance().scheme("http")
        .host("worldtimeapi.org")
        .port(80)
        .path("/api/timezone/Asia/Seoul")
        .build()
        .encode()
        .toUri()

    val restTemplate = RestTemplate()
    val headers = HttpHeaders()
    headers.accept = listOf(MediaType.APPLICATION_JSON)


    Mono.just<ResponseEntity<String>>(
        restTemplate.exchange(
            worldTimeUri, HttpMethod.GET, HttpEntity<String>(headers),
            String::class.java
        )
    )
        .map { response: ResponseEntity<String> ->
            val jsonContext = JsonPath.parse(response.body)
            val dateTime: String = jsonContext.read("$.datetime")
            dateTime
        }
        .subscribe(
            { data-> logger.info("# emitted data: $data") },
            { error -> logger.info(error.toString()) }
        ) { logger.info("# emitted onComplete signal") }
}