package br.com.eduardo.martins.githubrepositories.data.network.requests

import br.com.eduardo.martins.githubrepositories.domain.exception.HttpServerErrorException
import br.com.eduardo.martins.githubrepositories.domain.exception.MyIOException
import kotlinx.coroutines.delay
import retrofit2.Call
import java.lang.Exception

/**
 * Use this when you want that a retrofit call automatically retries if some 5xx or networking error
 * occurs
 * @throws IllegalArgumentException if [maxAttempts] < 1
 */

private const val DEFAULT_MAX_ATTEMPTS = 2

internal suspend fun <T> withRetry(
    call: Call<T>,
    delay: CallRetryDelays,
    maxAttempts: Int = DEFAULT_MAX_ATTEMPTS,
    makeCall: suspend (Call<T>) -> T
): T {

    require(maxAttempts >= 1) { "max retry attempts should be at least 1" }

    var currentAttempt = 0

    suspend fun <T> tryToMakeCall(call: Call<T>, makeCall: suspend (Call<T>) -> T): T {

        currentAttempt++

        try {
            return makeCall(call.clone())
        } catch (e: Exception) {

            if (e is HttpServerErrorException || e is MyIOException) {
                if (currentAttempt == 1) {
                    delay(delay.firstDelayInMills)
                    return tryToMakeCall(call, makeCall)
                } else if (currentAttempt <= maxAttempts) {
                    delay(delay.secondDelayInMills)
                    return tryToMakeCall(call, makeCall)
                }
            }

            throw e
        }
    }

    return tryToMakeCall(call, makeCall)

}