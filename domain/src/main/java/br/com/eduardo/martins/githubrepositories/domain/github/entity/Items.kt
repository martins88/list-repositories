package br.com.eduardo.martins.githubrepositories.domain.github.entity


data class Items (
    val id: Long,
    val name: String,
    val stars: Int,
    val forksCount: Int,
    val owner: Owner
)