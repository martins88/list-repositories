package br.com.eduardo.martins.githubrepositories.features.github

import androidx.lifecycle.LiveData
import br.com.eduardo.martins.githubrepositories.domain.github.entity.Items
import br.com.eduardo.martins.githubrepositories.domain.github.interactor.RetrieveRepositoriesInteractor
import br.com.eduardo.martins.githubrepositories.features.BaseViewModel
import br.com.eduardo.martins.githubrepositories.features.RepositoryScreenState
import br.com.eduardo.martins.githubrepositories.utils.SingleLiveEvent
import kotlinx.coroutines.flow.collect
import javax.inject.Inject

class RepositoryViewModel @Inject constructor(
    private val retrieveRepositoriesInteractor: RetrieveRepositoriesInteractor
) : BaseViewModel() {

    private var page = 0
    private var items: List<Items>? = null

    suspend fun retrieveRepositories() = doWorkWithProgress {
        val params = RetrieveRepositoriesInteractor.Params("kotlin", "stars", incrementPage())
        val itemsFlow = retrieveRepositoriesInteractor.execute(params)
        itemsFlow.collect {
            items = it
            screenStateLiveEvent.value = RepositoryScreenState.Result(it)
        }
    }

    private fun incrementPage(): Int {
        return items?.let {
            page += 1
            page
        } ?: run {
            1
        }
    }

}