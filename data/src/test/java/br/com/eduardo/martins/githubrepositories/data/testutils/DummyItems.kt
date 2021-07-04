package br.com.eduardo.martins.githubrepositories.data.testutils

import br.com.eduardo.martins.githubrepositories.domain.github.entity.Items
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow


val DUMMY_ITEMS = run {
    val list = mutableListOf<Items>()

    for (i in 1..5) {
        val item = Items(
            id = i.toLong(),
            name = "Eduardo",
            stars = 1000 + i,
            forksCount = 100 + i,
            owner = DUMMY_OWNER
        )
        list.add(item)
    }
    list
}