package br.com.eduardo.martins.githubrepositories.di.data

import br.com.eduardo.martins.githubrepositories.data.github.remote.GithubRemoteDataSource
import br.com.eduardo.martins.githubrepositories.data.github.remote.GithubRemoteDataSourceImpl
import dagger.Module
import dagger.Provides

@Module
class RemoteModule {

    @Provides
    fun providesGithubRemoteDataSource(dataSource: GithubRemoteDataSourceImpl):
            GithubRemoteDataSource = dataSource
}