package br.com.eduardo.martins.githubrepositories.domain.github.interactor

import br.com.eduardo.martins.githubrepositories.domain.Interactor
import br.com.eduardo.martins.githubrepositories.domain.github.entity.Items
import br.com.eduardo.martins.githubrepositories.domain.github.repository.GithubRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

/**
 * Used to retrieve a list of [Items]. It will return a [Flow] that may emit a list of [Items]
 */
class RetrieveRepositoriesInteractor @Inject constructor(
    private val repository: GithubRepository
): Interactor<Flow<List<Items>>, RetrieveRepositoriesInteractor.Params> {

    override suspend fun execute(params: Params): Flow<List<Items>> {
        return repository.retrieveRepositories(params.language, params.sort, params.page)
    }

    data class Params(val language: String, val sort: String, val page: Int)
}