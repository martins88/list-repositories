package br.com.eduardo.martins.githubrepositories.domain.github.repository

import br.com.eduardo.martins.githubrepositories.domain.github.entity.Items
import kotlinx.coroutines.flow.Flow


interface GithubRepository {

    suspend fun retrieveRepositories(language: String, sort: String, page: Int): Flow<List<Items>>

}