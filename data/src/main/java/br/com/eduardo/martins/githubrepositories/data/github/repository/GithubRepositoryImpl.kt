package br.com.eduardo.martins.githubrepositories.data.github.repository

import br.com.eduardo.martins.githubrepositories.data.github.remote.GithubRemoteDataSource
import br.com.eduardo.martins.githubrepositories.domain.github.entity.Items
import br.com.eduardo.martins.githubrepositories.domain.github.repository.GithubRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.lang.Exception
import javax.inject.Inject

class GithubRepositoryImpl @Inject constructor(
    private val dataSource: GithubRemoteDataSource
): GithubRepository {

    override suspend fun retrieveRepositories(language: String, sort: String, page: Int) : Flow<List<Items>> = flow {
        try {
            val remote = dataSource.retireveRepositories(language, sort, page)
            emit(remote)
        } catch (e: Exception) {
            throw e
        }
    }

}