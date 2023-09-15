package com.dzakyadlh.githubuser.data.response

import com.google.gson.annotations.SerializedName

data class GithubUserFollowsResponse(

	@field:SerializedName("GithubUserFollowsResponse")
	val githubUserFollowsResponse: List<GithubUserFollowsResponseItem>
)

data class GithubUserFollowsResponseItem(

	@field:SerializedName("avatar_url")
	val avatarUrl: String,

	@field:SerializedName("login")
	val login: String,

	@field:SerializedName("url")
	val url: String
)
