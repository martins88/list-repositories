package br.com.eduardo.martins.githubrepositories.data.github.repository

import br.com.eduardo.martins.githubrepositories.data.github.remote.GithubRemoteDataSource
import br.com.eduardo.martins.githubrepositories.data.testutils.DUMMY_ITEMS
import br.com.eduardo.martins.githubrepositories.domain.exception.MyIOException
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerifySequence
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.flow.single
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import java.net.SocketTimeoutException

class GithubRepositoryImplTest {

    private lateinit var githubRepositoryImpl: GithubRepositoryImpl

    @MockK
    private lateinit var githubRemoteDataSource: GithubRemoteDataSource

    @Before
    fun setup() {
        MockKAnnotations.init(this)
        githubRepositoryImpl = GithubRepositoryImpl(githubRemoteDataSource)
    }

    @Test
    fun `should emit from remote data source`() = runBlocking {

        coEvery { githubRemoteDataSource.retireveRepositories("kotlin", "stars", 1) } returns DUMMY_ITEMS

        val itemsFlow = githubRepositoryImpl.retrieveRepositories("kotlin", "stars", 1)

        val items = itemsFlow.single()

        assertEquals(5, items.size)
        assertEquals(DUMMY_ITEMS, items)

        coVerifySequence { githubRemoteDataSource.retireveRepositories("kotlin", "stars", 1) }
    }

    @Test(expected = MyIOException::class)
    fun `should throw exception remote source`() = runBlocking {

        coEvery { githubRemoteDataSource.retireveRepositories("kotlin", "stars", 1) } throws MyIOException("io error", SocketTimeoutException())

        val items = githubRepositoryImpl.retrieveRepositories("kotlin", "stars", 1).single()

    }

}