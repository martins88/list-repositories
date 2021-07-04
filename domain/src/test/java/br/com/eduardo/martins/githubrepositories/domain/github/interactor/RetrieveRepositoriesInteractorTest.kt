package br.com.eduardo.martins.githubrepositories.domain.github.interactor

import br.com.eduardo.martins.githubrepositories.domain.github.repository.GithubRepository
import br.com.eduardo.martins.githubrepositories.domain.testutils.DUMMY_ITEMS
import br.com.eduardo.martins.githubrepositories.domain.testutils.dummyRepositoriesFlow
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerifySequence
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.flow.single
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test


class RetrieveRepositoriesInteractorTest {

    private lateinit var retrieveRepositoriesInteractor: RetrieveRepositoriesInteractor

    @MockK
    private lateinit var repository: GithubRepository

    @Before
    fun setup() {
        MockKAnnotations.init(this)
        retrieveRepositoriesInteractor = RetrieveRepositoriesInteractor(repository)
    }

    @Test
    fun `should retrieve repositories`() = runBlocking {

        coEvery {
            repository.retrieveRepositories("kotlin", "stars", 1)
        } returns dummyRepositoriesFlow()

        val items = retrieveRepositoriesInteractor.execute(
            RetrieveRepositoriesInteractor.Params("kotlin", "stars", 1)
        ).single()

        assertEquals(DUMMY_ITEMS, items)

        coVerifySequence { repository.retrieveRepositories("kotlin", "stars", 1) }

    }

}