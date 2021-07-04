package br.com.eduardo.martins.githubrepositories.data.testutils

import br.com.eduardo.martins.githubrepositories.domain.github.entity.Owner


val DUMMY_OWNER = run {
    Owner(
        id = 1.toLong(),
        avatar = "https://avatars.githubusercontent.com/u/32689599?v=4",
        author = "Eduardo"
    )
}