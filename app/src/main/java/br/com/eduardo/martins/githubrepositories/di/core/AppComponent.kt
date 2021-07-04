package br.com.eduardo.martins.githubrepositories.di.core

import br.com.eduardo.martins.githubrepositories.GithubRepositoriesApplication
import dagger.BindsInstance
import dagger.Component
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AndroidSupportInjectionModule::class,
        AppModule::class,
        BuilderModule::class
    ]
)
interface AppComponent {

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(application: GithubRepositoriesApplication): Builder

        fun build(): AppComponent

    }

    fun inject(app: GithubRepositoriesApplication)

}