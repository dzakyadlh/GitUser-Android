package com.dzakyadlh.githubuser.data.retrofit

import com.dzakyadlh.githubuser.data.response.GithubUserDetailResponse
import com.dzakyadlh.githubuser.data.response.GithubUserFollowersResponseItem
import com.dzakyadlh.githubuser.data.response.GithubUserFollowingResponseItem
import com.dzakyadlh.githubuser.data.response.GithubUserSearchResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

class APIService {
    interface APIService {
        @GET("search/users?q={username}")
        fun getSearchResults(
            @Path("username") username: String
        ): Call<GithubUserSearchResponse>

        @GET("users/{username}")
        fun getUserDetail(
            @Path("username") username: String
        ): Call<GithubUserDetailResponse>

        @GET("users/{username}/followers")
        fun getUserFollowers(
            @Path("username") username: String
        ): Call<GithubUserFollowersResponseItem>

        @GET("users/{username}/following")
        fun getUserFollowing(
            @Path("username") username: String
        ): Call<GithubUserFollowingResponseItem>
    }
}