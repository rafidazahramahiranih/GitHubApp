package com.example.githubapp.retrofit

import com.example.githubapp.response.GitHubResponse
import com.example.githubapp.ui.User
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    @Headers("Authorization: token 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")
    @GET("search/users")
    fun getGithubSearch(@Query("q") query: String): Call<GitHubResponse>

    @GET("users/{username}")
    fun getDetailUser(@Path("username") username: String): Call<GitHubResponse>

    @GET("users/{username}/followers")
    fun getFollowers(@Path("username") username: String): Call<List<User>>

    @GET("users/{username}/following")
    fun getFollowing(@Path("username") username: String): Call<List<User>>
}
