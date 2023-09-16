package com.dzakyadlh.githubuser.data.retrofit

import com.dzakyadlh.githubuser.data.response.GithubUserDetailResponse
import com.dzakyadlh.githubuser.data.response.GithubUserFollowsResponseItem
import com.dzakyadlh.githubuser.data.response.GithubUserSearchResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query

interface APIService {
    @GET("search/users")
    @Headers("Authorization: token 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")
    fun getSearchResults(
        @Query("q")
        query: String
    ): Call<GithubUserSearchResponse>

    @GET("users/{username}")
    @Headers("Authorization: token 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")
    fun getUserDetail(
        @Path("username") username: String
    ): Call<GithubUserDetailResponse>

    @GET("users/{username}/followers")
    @Headers("Authorization: token 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")
    fun getUserFollowers(
        @Path("username") username: String
    ): Call<GithubUserFollowsResponseItem>

    @GET("users/{username}/following")
    @Headers("Authorization: token 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")
    fun getUserFollowing(
        @Path("username") username: String
    ): Call<GithubUserFollowsResponseItem>
}