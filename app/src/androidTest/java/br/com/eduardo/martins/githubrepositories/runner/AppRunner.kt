package br.com.eduardo.martins.githubrepositories.runner

import android.app.Application
import android.content.Context
import androidx.test.runner.AndroidJUnitRunner
import br.com.eduardo.martins.githubrepositories.GithubRepositoriesApplicationTest

class AppRunner : AndroidJUnitRunner() {

    override fun newApplication(cl: ClassLoader?, className: String?, context: Context?): Application {
        return super.newApplication(cl, GithubRepositoriesApplicationTest::class.java.name, context)
    }

}