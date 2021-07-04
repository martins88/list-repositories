package br.com.eduardo.martins.githubrepositories.data.network.requests

import br.com.eduardo.martins.githubrepositories.domain.exception.MyException
import retrofit2.Call
import retrofit2.Retrofit

/**
 * Makes a HTTP request for some data using the [Retrofit]
 * */

interface RequestMaker {

    @Throws(MyException::class)
    suspend fun <T : Any> getResult(call: Call<T>): T

}