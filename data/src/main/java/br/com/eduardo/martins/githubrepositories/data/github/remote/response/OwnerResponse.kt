package br.com.eduardo.martins.githubrepositories.data.github.remote.response

import br.com.eduardo.martins.githubrepositories.domain.github.entity.Owner
import com.google.gson.annotations.SerializedName
import java.io.Serializable


data class OwnerResponse (

    @SerializedName("id")
    val id: Long,

    @SerializedName("avatar_url")
    val avatar: String,

    @SerializedName("login")
    val author: String

) : Serializable

fun OwnerResponse.toOwner(): Owner {

    return Owner(this.id, this.avatar, this.author)
}