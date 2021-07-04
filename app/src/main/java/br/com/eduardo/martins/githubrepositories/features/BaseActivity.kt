package br.com.eduardo.martins.githubrepositories.features

import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import br.com.eduardo.martins.githubrepositories.R
import br.com.eduardo.martins.githubrepositories.utils.lifecycle.job
import dagger.android.AndroidInjection
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlin.coroutines.CoroutineContext

abstract class BaseActivity : AppCompatActivity(), CoroutineScope {

    override val coroutineContext: CoroutineContext
        get() = lifecycle.job + Dispatchers.Main

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
    }

    protected open fun showMessage(message: String) {
        showAlertDialog(message)
    }

    protected open fun showAlertDialog(message: String) {
        val alertDialog = AlertDialog.Builder(this)
            .setTitle(R.string.common_title_error)
            .setMessage(message)
            .setPositiveButton(R.string.common_ok) { dialog, _ -> dialog.dismiss() }
        alertDialog.show()
    }

}