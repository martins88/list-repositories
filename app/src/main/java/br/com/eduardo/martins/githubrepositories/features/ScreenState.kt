package br.com.eduardo.martins.githubrepositories.features

import br.com.eduardo.martins.githubrepositories.domain.github.entity.Items

sealed class ScreenState
object NetworkError : ScreenState()
object HttpError : ScreenState()

sealed class RepositoryScreenState : ScreenState() {
    data class Result(val result: List<Items>) : RepositoryScreenState()
}