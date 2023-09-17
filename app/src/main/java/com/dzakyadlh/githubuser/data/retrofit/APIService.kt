package com.dzakyadlh.githubuser.data.retrofit

import com.dzakyadlh.githubuser.data.response.GithubUserDetailResponse
import com.dzakyadlh.githubuser.data.response.GithubUserFollowsResponseItem
import com.dzakyadlh.githubuser.data.response.GithubUserSearchResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface APIService {
    @GET("search/users")
    fun getSearchResults(
        @Query("q")
        query: String
    ): Call<GithubUserSearchResponse>

    @GET("users/{username}")
    fun getUserDetail(
        @Path("username") username: String
    ): Call<GithubUserDetailResponse>

    @GET("users/{username}/followers")
    fun getUserFollowers(
        @Path("username") username: String
    ): Call<List<GithubUserFollowsResponseItem>>

    @GET("users/{username}/following")
    fun getUserFollowing(
        @Path("username") username: String
    ): Call<List<GithubUserFollowsResponseItem>>
}