package com.dzakyadlh.githubuser.data.response

import com.google.gson.annotations.SerializedName

data class GithubUserFollowersResponse(

	@field:SerializedName("GithubUserFollowersResponse")
	val githubUserFollowersResponse: List<GithubUserFollowersResponseItem>
)

data class GithubUserFollowersResponseItem(

	@field:SerializedName("avatar_url")
	val avatarUrl: String,

	@field:SerializedName("login")
	val login: String,

	@field:SerializedName("url")
	val url: String
)
