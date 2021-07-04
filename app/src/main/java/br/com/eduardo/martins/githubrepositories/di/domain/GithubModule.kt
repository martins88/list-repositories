package br.com.eduardo.martins.githubrepositories.di.domain

import br.com.eduardo.martins.githubrepositories.data.github.repository.GithubRepositoryImpl
import br.com.eduardo.martins.githubrepositories.domain.github.repository.GithubRepository
import dagger.Module
import dagger.Provides

@Module
class GithubModule {

    @Provides
    fun providesGithubRepository(githubRepositoryImpl: GithubRepositoryImpl):
            GithubRepository = githubRepositoryImpl
}