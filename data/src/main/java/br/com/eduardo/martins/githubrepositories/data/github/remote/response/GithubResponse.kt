package br.com.eduardo.martins.githubrepositories.data.github.remote.response

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class GithubResponse (

    @SerializedName("total_count")
    val count: Int,

    @SerializedName("items")
    val items: List<ItemsResponse>

) : Serializable