package br.com.eduardo.martins.githubrepositories.data.network

import android.app.Application
import android.content.Context
import br.com.eduardo.martins.githubrepositories.data.network.interceptor.getSimpleLogging
import com.google.gson.Gson
import okhttp3.Cache
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import timber.log.BuildConfig
import java.io.File
import java.util.concurrent.TimeUnit


class ApiClientBuilder {

    companion object {

        fun <S> createService(
            serviceClass: Class<S>,
            baseUrl: String,
            context: Context,
            readTimeoutInMills: Long = 15000L,
            gson: Gson = Gson(),
            vararg interceptors: Interceptor
        ): S {

            val file = File(context.cacheDir, "offlineCache")
            val cacheSize = 10 * 1024 * 1024

            val httpClientBuilder = OkHttpClient.Builder().cache(Cache(file, cacheSize.toLong()))

            interceptors.forEach { interceptor -> httpClientBuilder.addInterceptor(interceptor) }

            if (BuildConfig.DEBUG) {

                // Critical part, LogClient must be last one if you have more interceptors
                httpClientBuilder.addInterceptor(HttpLoggingInterceptor().getSimpleLogging())

            }

            val client = httpClientBuilder
                .readTimeout(readTimeoutInMills, TimeUnit.MILLISECONDS)
                .connectTimeout(5, TimeUnit.SECONDS)
                .build()
            val retrofit = getClientBuilder(baseUrl, gson)
                .client(client)
                .build()
            return retrofit.create(serviceClass)
        }

        private fun getClientBuilder(baseUrl: String, gson: Gson): Retrofit.Builder {
            return Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create(gson))
        }
    }

}