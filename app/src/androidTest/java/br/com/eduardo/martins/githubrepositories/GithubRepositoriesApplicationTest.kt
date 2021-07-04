package br.com.eduardo.martins.githubrepositories

import android.app.Activity
import android.app.Application
import android.app.Service
import br.com.eduardo.martins.githubrepositories.di.core.DaggerAppComponentTest
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import dagger.android.HasServiceInjector
import timber.log.Timber
import javax.inject.Inject

class GithubRepositoriesApplicationTest : Application(), HasActivityInjector, HasServiceInjector {

    @Inject
    lateinit var dispatchingActivityInjector: DispatchingAndroidInjector<Activity>

    @Inject
    lateinit var dispatchingServiceInjector: DispatchingAndroidInjector<Service>

    override fun onCreate() {
        super.onCreate()
        setupInjector()
        Timber.plant(Timber.DebugTree())
        Timber.d("Test application initialized!")
    }

    override fun activityInjector(): AndroidInjector<Activity> = dispatchingActivityInjector

    override fun serviceInjector(): AndroidInjector<Service> = dispatchingServiceInjector

    private fun setupInjector() {
        DaggerAppComponentTest.builder()
            .application(this)
            .build()
            .inject(this)
    }

}