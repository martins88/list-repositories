package br.com.eduardo.martins.githubrepositories.di.core

import br.com.eduardo.martins.githubrepositories.GithubRepositoriesApplicationTest
import dagger.BindsInstance
import dagger.Component
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AndroidSupportInjectionModule::class,
        AppModuleTest::class,
        BuilderModule::class
    ]
)
interface AppComponentTest {

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(application: GithubRepositoriesApplicationTest): Builder

        fun build(): AppComponentTest

    }

    fun inject(app: GithubRepositoriesApplicationTest)
}