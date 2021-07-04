package br.com.eduardo.martins.githubrepositories.features.github

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import br.com.eduardo.martins.githubrepositories.domain.github.entity.Items
import br.com.eduardo.martins.githubrepositories.domain.github.interactor.RetrieveRepositoriesInteractor
import br.com.eduardo.martins.githubrepositories.features.RepositoryScreenState
import br.com.eduardo.martins.githubrepositories.features.ScreenState
import br.com.eduardo.martins.githubrepositories.utils.DUMMY_ITEMS
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import io.mockk.impl.annotations.RelaxedMockK
import io.mockk.verifySequence
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule


class RepositoryViewModelTest {

    @Rule
    @JvmField
    var rule: TestRule = InstantTaskExecutorRule()

    private lateinit var viewmodel: RepositoryViewModel

    @MockK
    private lateinit var retrieveRepositoriesInteractor: RetrieveRepositoriesInteractor

    @RelaxedMockK
    private lateinit var observerShowProgress: Observer<Void>

    @RelaxedMockK
    private lateinit var observerHideProgress: Observer<Void>

    @RelaxedMockK
    private lateinit var observerShowMessage: Observer<String>

    @RelaxedMockK
    private lateinit var observerScreenState: Observer<ScreenState>

    @Before
    fun setup() {
        MockKAnnotations.init(this)
        viewmodel = RepositoryViewModel(retrieveRepositoriesInteractor)

        with(viewmodel) {
            showProgressEvent.observeForever(observerShowProgress)
            hideProgressEvent.observeForever(observerHideProgress)
            showMessageEvent.observeForever(observerShowMessage)
            screenStateEvent.observeForever(observerScreenState)
        }
    }

    @After
    fun `tear down`() {
        with(viewmodel) {
            showProgressEvent.removeObserver(observerShowProgress)
            hideProgressEvent.removeObserver(observerHideProgress)
            showMessageEvent.removeObserver(observerShowMessage)
            screenStateEvent.removeObserver(observerScreenState)
        }
    }

    @Test
    fun `should collect all emited repositories`() = runBlocking {

        val flow: Flow<List<Items>> = flow {
            emit(DUMMY_ITEMS)
        }

        val params = RetrieveRepositoriesInteractor.Params("kotlin", "stars", 1)
        coEvery { retrieveRepositoriesInteractor.execute(params) } returns flow

        viewmodel.retrieveRepositories()

        verifySequence {
            observerShowProgress.onChanged(null)
            observerScreenState.onChanged(RepositoryScreenState.Result(DUMMY_ITEMS))
            observerHideProgress.onChanged(null)
        }
    }
}