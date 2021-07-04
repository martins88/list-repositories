package br.com.eduardo.martins.githubrepositories.data.github.remote

import br.com.eduardo.martins.githubrepositories.data.github.remote.api.GithubApi
import br.com.eduardo.martins.githubrepositories.data.network.ApiClientBuilder
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Before


abstract class BaseRemoteTest {

    protected lateinit var server: MockWebServer
    protected lateinit var api: GithubApi

    @Before
    open fun setup() {
        server = MockWebServer()
        server.start()
        val url = server.url("/").toString()
        api = ApiClientBuilder.createService(GithubApi::class.java, url)
    }

    @After
    open fun tearDown() {
        server.shutdown()
    }

}