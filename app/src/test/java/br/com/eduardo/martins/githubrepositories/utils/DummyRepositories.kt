package br.com.eduardo.martins.githubrepositories.utils

import br.com.eduardo.martins.githubrepositories.domain.github.entity.Items
import br.com.eduardo.martins.githubrepositories.domain.github.entity.Owner

val DUMMY_ITEMS = run {
    val list = mutableListOf<Items>().apply {
        add(
            Items(
                id = 1,
                name = "Android",
                stars = 1000,
                forksCount = 100,
                owner = Owner(
                    id = 1.toLong(),
                    avatar = "https://avatars.githubusercontent.com/u/32689599?v=4",
                    author = "Android"
                )
            )
        )
        add(
            Items(
                id = 2,
                name = "MyProject",
                stars = 5,
                forksCount = 2,
                owner = Owner(
                    id = 2.toLong(),
                    avatar = "https://avatars.githubusercontent.com/u/32689599?v=4",
                    author = "Eduardo"
                )
            )
        )
    }
    list
}