package br.com.eduardo.martins.githubrepositories.utils.extensions

import android.content.Context
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

fun<T: RecyclerView.ViewHolder> RecyclerView.setup(
    context: Context,
    adapter: RecyclerView.Adapter<T>,
    layoutManager: RecyclerView.LayoutManager = LinearLayoutManager(context),
    itemAnimator: RecyclerView.ItemAnimator = DefaultItemAnimator(),
    isNestedScrollingEnabled: Boolean = true) : RecyclerView {

    this.layoutManager = layoutManager
    this.itemAnimator = itemAnimator
    this.adapter = adapter
    this.isNestedScrollingEnabled = isNestedScrollingEnabled

    return this
}