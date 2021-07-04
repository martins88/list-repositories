package br.com.eduardo.martins.githubrepositories.data.github.remote

import br.com.eduardo.martins.githubrepositories.data.github.remote.api.GithubApi
import br.com.eduardo.martins.githubrepositories.data.github.remote.response.toItems
import br.com.eduardo.martins.githubrepositories.data.network.requests.RequestMaker
import br.com.eduardo.martins.githubrepositories.domain.github.entity.Items
import javax.inject.Inject

class GithubRemoteDataSourceImpl @Inject constructor(
    private val api: GithubApi,
    private val requestMaker: RequestMaker
) : GithubRemoteDataSource {

    override suspend fun retireveRepositories(
        language: String,
        sort: String,
        page: Int
    ): List<Items> {
        HashMap<String, String>().apply {
            put("q", "language:$language")
            put("sort", sort)
            put("page", "$page")
        }.also {
            val call = api.getRepositories(it)
            val result = requestMaker.getResult(call)
            return result.items.map { it.toItems() }
        }
    }

}