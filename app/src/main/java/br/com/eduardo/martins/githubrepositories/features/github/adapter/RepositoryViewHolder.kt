package br.com.eduardo.martins.githubrepositories.features.github.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import br.com.eduardo.martins.githubrepositories.databinding.ItemRepositoryBinding
import br.com.eduardo.martins.githubrepositories.domain.github.entity.Items
import br.com.eduardo.martins.githubrepositories.utils.extensions.load


class RepositoryViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private lateinit var binding: ItemRepositoryBinding

    constructor(binding: ItemRepositoryBinding) : this(binding.root) {
        this.binding = binding
    }

    fun bind(item: Items) {
        binding.appCompatImageViewItemRepositoryAvatar.load(item.owner.avatar)
        binding.textViewItemRepositoryNameRepository.text = item.name
        binding.textViewItemRepositoryNameUser.text = item.owner.author
        binding.textViewStars.text = item.stars.toString()
        binding.textViewItemRepositoryForks.text = item.forksCount.toString()
    }

}