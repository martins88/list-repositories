package br.com.eduardo.martins.githubrepositories.data.network

import br.com.eduardo.martins.githubrepositories.data.network.requests.CallRetryDelays
import br.com.eduardo.martins.githubrepositories.data.network.requests.RequestMakerImpl
import br.com.eduardo.martins.githubrepositories.data.testutils.enqueue200Response
import br.com.eduardo.martins.githubrepositories.data.testutils.enqueue400Response
import br.com.eduardo.martins.githubrepositories.data.testutils.enqueue500Response
import br.com.eduardo.martins.githubrepositories.data.testutils.enqueueIOError
import br.com.eduardo.martins.githubrepositories.domain.exception.HttpClientErrorException
import br.com.eduardo.martins.githubrepositories.domain.exception.HttpServerErrorException
import br.com.eduardo.martins.githubrepositories.domain.exception.MyIOException
import com.google.gson.JsonObject
import kotlinx.coroutines.runBlocking
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import retrofit2.Call
import retrofit2.http.GET
import kotlin.system.measureTimeMillis

/**
 * Used to simulate a retrofit API interface
 */
private interface TestApi {
    @GET("test")
    fun getTest(): Call<JsonObject>
}

class RequestMakerImplTest {

    private val response = "{\"name\": \"Eduardo\"}"
    private val callRetryDelay = CallRetryDelays(500L, 1000L)

    private lateinit var server: MockWebServer
    private lateinit var testApi: TestApi
    private lateinit var requestMakerImpl: RequestMakerImpl

    @Before
    fun setUp() {
        server = MockWebServer()
        server.start()
        val url = server.url("/").toString()
        testApi = ApiClientBuilder.createService(TestApi::class.java, url, 300L)
        requestMakerImpl = RequestMakerImpl(callRetryDelay)
    }

    @After
    fun tearDown() {
        server.shutdown()
    }

    @Test
    fun `should return api result`() = runBlocking {

        server.enqueue200Response(response)
        val call = testApi.getTest()
        val result = requestMakerImpl.getResult(call)

        Assert.assertEquals("Eduardo", result["name"].asString)

    }

    @Test
    fun `should return api result on first retry attempt`() = runBlocking {

        val time = measureTimeMillis {
            server.enqueue500Response(response)
            server.enqueue200Response(response)

            val call = testApi.getTest()
            val result = requestMakerImpl.getResult(call)

            Assert.assertEquals("Eduardo", result["name"].asString)

        }

        val mininumTime = callRetryDelay.firstDelayInMills
        Assert.assertTrue(
            "first attempt timeout should be at least ${mininumTime} mills",
            time >= mininumTime
        )
    }

    @Test
    fun `should return api result on second retry attempt`() = runBlocking {

        val time = measureTimeMillis {
            server.enqueue500Response(response)
            server.enqueueIOError(response)
            server.enqueue200Response(response)

            val call = testApi.getTest()
            val result = requestMakerImpl.getResult(call)

            Assert.assertEquals("Eduardo", result["name"].asString)

        }

        val mininumTime = callRetryDelay.getTotalDelay()
        Assert.assertTrue(
            "two attempts timeout should be at least ${mininumTime} mills",
            time >= mininumTime
        )

    }

    @Test(expected = MyIOException::class)
    fun `request should throw MyIOException`() = runBlocking {

        server.enqueueIOError(response)
        server.enqueue500Response(response)
        server.enqueueIOError(response)

        val call = testApi.getTest()
        val result = requestMakerImpl.getResult(call)
    }

    @Test(expected = HttpServerErrorException::class)
    fun `request should throw HttpServerErrorException`() = runBlocking {

        server.enqueueIOError(response)
        server.enqueueIOError(response)
        server.enqueue500Response(response)

        val call = testApi.getTest()
        val result = requestMakerImpl.getResult(call)
    }

    @Test(expected = HttpClientErrorException::class)
    fun `request should throw HttpClientErrorException`() = runBlocking {

        server.enqueue400Response(response)

        val call = testApi.getTest()
        val result = requestMakerImpl.getResult(call)
    }

}