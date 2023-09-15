package com.dzakyadlh.githubuser.data.response

import com.google.gson.annotations.SerializedName

data class GithubUserSearchResponse(

    @field:SerializedName("total_count")
    val totalCount: Int,

    @field:SerializedName("incomplete_results")
    val incompleteResults: Boolean,

    @field:SerializedName("items")
    val items: List<GithubUserSearchResponse>
)

data class ItemsItem(

    @field:SerializedName("avatar_url")
    val avatarUrl: String,

    @field:SerializedName("login")
    val login: String,

    @field:SerializedName("url")
    val url: String
)
