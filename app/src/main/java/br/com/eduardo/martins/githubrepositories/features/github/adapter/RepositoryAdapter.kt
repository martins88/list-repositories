package br.com.eduardo.martins.githubrepositories.features.github.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.Adapter
import br.com.eduardo.martins.githubrepositories.databinding.ItemRepositoryBinding
import br.com.eduardo.martins.githubrepositories.domain.github.entity.Items

class RepositoryAdapter constructor(val items: ArrayList<Items>) : Adapter<RepositoryViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RepositoryViewHolder {
        LayoutInflater.from(parent.context).apply {
            return RepositoryViewHolder(ItemRepositoryBinding.inflate(this, parent, false))
        }
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: RepositoryViewHolder, position: Int) {
        holder.bind(items[position])
    }

    fun update(repositories: List<Items>) {
        items.addAll(repositories)
        notifyDataSetChanged()
    }

    fun isEmpty() = items.size == 0

}