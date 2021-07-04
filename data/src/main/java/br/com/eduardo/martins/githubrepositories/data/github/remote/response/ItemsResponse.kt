package br.com.eduardo.martins.githubrepositories.data.github.remote.response

import br.com.eduardo.martins.githubrepositories.domain.github.entity.Items
import com.google.gson.annotations.SerializedName
import java.io.Serializable


data class ItemsResponse (
    @SerializedName("id")
    val id: Long,

    @SerializedName("name")
    val name: String,

    @SerializedName("stargazers_count")
    val stars: Int,

    @SerializedName("forks_count")
    val forksCount: Int,

    @SerializedName("owner")
    val owner: OwnerResponse
): Serializable

fun ItemsResponse.toItems(): Items {
    return Items(this.id, this.name, this.stars, this.forksCount, this.owner.toOwner())
}