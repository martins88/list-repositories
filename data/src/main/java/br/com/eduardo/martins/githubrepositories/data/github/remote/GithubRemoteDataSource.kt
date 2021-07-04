package br.com.eduardo.martins.githubrepositories.data.github.remote

import br.com.eduardo.martins.githubrepositories.domain.github.entity.Items

interface GithubRemoteDataSource {

    suspend fun retireveRepositories(language: String, sort: String, page: Int) : List<Items>

}