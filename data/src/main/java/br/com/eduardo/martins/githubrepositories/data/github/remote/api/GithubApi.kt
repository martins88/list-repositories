package br.com.eduardo.martins.githubrepositories.data.github.remote.api

import br.com.eduardo.martins.githubrepositories.data.github.remote.response.GithubResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import retrofit2.http.QueryMap


interface GithubApi {

    @GET("repositories")
    fun getRepositories(
        @QueryMap options: Map<String, String>
    ): Call<GithubResponse>

}