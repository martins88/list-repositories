package br.com.eduardo.martins.githubrepositories.di.core

import android.app.Activity
import android.app.Service
import androidx.fragment.app.Fragment
import br.com.eduardo.martins.githubrepositories.di.domain.GithubModule
import br.com.eduardo.martins.githubrepositories.features.github.RepositoryActivity
import br.com.eduardo.martins.githubrepositories.features.github.RepositoryModule
import dagger.Module
import dagger.android.ContributesAndroidInjector

/**
 * Module that contains the [ContributesAndroidInjector] implementations for injecting the concrete
 * Android framework classes: [Activity] - [Fragment] - [Service]
 */

@Module
abstract class BuilderModule {

    @ContributesAndroidInjector(
        modules = [
            RepositoryModule::class,
            GithubModule::class
        ]
    )
    abstract fun contributesRepositoryActivity(): RepositoryActivity

}