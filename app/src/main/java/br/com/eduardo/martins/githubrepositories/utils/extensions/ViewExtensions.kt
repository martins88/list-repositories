package br.com.eduardo.martins.githubrepositories.utils.extensions

import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import br.com.eduardo.martins.githubrepositories.R
import com.google.android.material.snackbar.Snackbar

fun View.show() {
    this.visibility = VISIBLE
}

fun View.hide() {
    this.visibility = GONE
}

fun View.showSnackBar(message: Int?) {
    showSnackBar(message, this, Snackbar.LENGTH_LONG)
}

private fun showSnackBar(message: Int?, view: View?, duration: Int) {
    if (message != null && view != null) {
        Snackbar.make(view, message, duration)
            .setAction(R.string.common_ok) { v -> }
            .show()
    }
}