package com.dzakyadlh.githubuser.data.retrofit

import android.text.Editable
import com.dzakyadlh.githubuser.data.response.GithubUserDetailResponse
import com.dzakyadlh.githubuser.data.response.GithubUserFollowsResponseItem
import com.dzakyadlh.githubuser.data.response.GithubUserSearchResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path

interface APIService {
    @GET("search/users/q={username}")
    @Headers("Authorization: token <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>")
    fun getSearchResults(
        @Path("username") username: Editable?
    ): Call<GithubUserSearchResponse>

    @GET("users/{username}")
    fun getUserDetail(
        @Path("username") username: String
    ): Call<GithubUserDetailResponse>

    @GET("users/{username}/followers")
    fun getUserFollowers(
        @Path("username") username: String
    ): Call<GithubUserFollowsResponseItem>

    @GET("users/{username}/following")
    fun getUserFollowing(
        @Path("username") username: String
    ): Call<GithubUserFollowsResponseItem>
}