package com.example.githubapp.retrofit

import com.example.githubapp.response.DetailUsersResponse
import com.example.githubapp.response.GitHubResponse
import com.example.githubapp.response.ItemsItem
import com.example.githubapp.ui.User
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    @Headers("Authorizations: Bearer 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")
    @GET("search/users")
    fun getsearchUser(@Query("q") query: String): Call<GitHubResponse>

    @Headers("Authorizations: Bearer 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")
    @GET("users/{username}")
    fun getDetailUser(@Path("username") username: String): Call<DetailUsersResponse>

    @Headers("Authorizations: Bearer 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")
    @GET("users/{username}/followers")
    fun getfollowers(@Path("username") username: String): Call<List<ItemsItem>>

    @Headers("Authorizations: Bearer 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")
    @GET("users/{username}/following")
    fun getfollowing(@Path("username") username: String): Call<List<ItemsItem>>
}
