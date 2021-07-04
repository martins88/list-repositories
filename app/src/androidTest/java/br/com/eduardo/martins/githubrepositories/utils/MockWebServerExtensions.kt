package br.com.eduardo.martins.githubrepositories.utils

import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import java.util.concurrent.TimeUnit

private const val BODY_DELAY = 0L

fun MockWebServer.enqueue200Response(body: String) {

    enqueue(
        MockResponse()
            .setResponseCode(200)
            .setBodyDelay(BODY_DELAY, TimeUnit.SECONDS)
            .setBody(body))

}