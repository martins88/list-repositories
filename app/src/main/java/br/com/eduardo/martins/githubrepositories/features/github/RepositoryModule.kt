package br.com.eduardo.martins.githubrepositories.features.github

import androidx.lifecycle.ViewModel
import br.com.eduardo.martins.githubrepositories.di.core.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class RepositoryModule {

    @Binds
    @IntoMap
    @ViewModelKey(RepositoryViewModel::class)
    abstract fun bindsRepositoryViewModel(repositoryViewModel: RepositoryViewModel): ViewModel
}