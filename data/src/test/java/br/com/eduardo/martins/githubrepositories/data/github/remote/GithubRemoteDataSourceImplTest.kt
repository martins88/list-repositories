package br.com.eduardo.martins.githubrepositories.data.github.remote

import br.com.eduardo.martins.githubrepositories.data.network.requests.CallRetryDelays
import br.com.eduardo.martins.githubrepositories.data.network.requests.RequestMakerImpl
import br.com.eduardo.martins.githubrepositories.data.testutils.enqueue200Response
import br.com.eduardo.martins.githubrepositories.data.testutils.loadJsonFromResources
import io.mockk.MockKAnnotations
import kotlinx.coroutines.runBlocking
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

class GithubRemoteDataSourceImplTest : BaseRemoteTest() {

    private lateinit var githubRepositoryImpl: GithubRemoteDataSourceImpl
    private val requestMaker = RequestMakerImpl(CallRetryDelays(0, 0))

    @Before
    override fun setup() {
        super.setup()
        MockKAnnotations.init(this)
        githubRepositoryImpl = GithubRemoteDataSourceImpl(api, requestMaker)
    }

    @Test
    fun `should retrieve items`() = runBlocking {
        val response = loadJsonFromResources(javaClass.classLoader!!, "items_response_200.json")
        server.enqueue200Response(response)
        val items = githubRepositoryImpl.retireveRepositories("kotlin", "stars", 1)
        assertTrue(items.size == 2)

        val firstItem = items[0]
        assertEquals(firstItem.id, 51148780.toLong())
        assertEquals("architecture-samples", firstItem.name)
        assertEquals(38978, firstItem.stars)
        assertEquals(10725, firstItem.forksCount)
    }

}