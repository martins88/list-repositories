package br.com.eduardo.martins.githubrepositories.utils.extensions

import android.widget.ImageView
import br.com.eduardo.martins.githubrepositories.R
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy

fun ImageView.load(url: String) {
    Glide.with(context).load(url)
        .placeholder(R.drawable.ic_default_image)
        .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
        .into(this)
}