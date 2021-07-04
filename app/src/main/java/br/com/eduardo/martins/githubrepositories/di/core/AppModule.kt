package br.com.eduardo.martins.githubrepositories.di.core

import android.content.Context
import br.com.eduardo.martins.githubrepositories.GithubRepositoriesApplication
import br.com.eduardo.martins.githubrepositories.data.github.remote.api.GithubApi
import br.com.eduardo.martins.githubrepositories.data.network.ApiClientBuilder
import br.com.eduardo.martins.githubrepositories.data.network.requests.CallRetryDelays
import br.com.eduardo.martins.githubrepositories.data.network.requests.RequestMaker
import br.com.eduardo.martins.githubrepositories.data.network.requests.RequestMakerImpl
import br.com.eduardo.martins.githubrepositories.di.data.RemoteModule
import dagger.Module
import dagger.Provides
import javax.inject.Named
import javax.inject.Singleton

private const val MOCK_GITHUB_API_BASE_URL = "https://api.github.com/search/"

@Module(
    includes = [
        RemoteModule::class
    ]
)
class AppModule {

    @Provides
    @Singleton
    @Named("application")
    fun providesApplicationContext(application: GithubRepositoriesApplication): Context {
        return application.applicationContext
    }

    @Provides
    @Singleton
    fun providesGithubApi(@Named("application") context: Context): GithubApi {
        return ApiClientBuilder.createService(serviceClass = GithubApi::class.java, baseUrl = MOCK_GITHUB_API_BASE_URL, context = context)
    }

    @Provides
    @Singleton
    fun providesRetryDelays() = CallRetryDelays()

    @Provides
    @Singleton
    fun providesRequestMaker(requestMaker: RequestMakerImpl): RequestMaker = requestMaker

}