package com.dzakyadlh.githubuser.data.response

import com.google.gson.annotations.SerializedName

data class GithubUserFollowingResponse(

	@field:SerializedName("GithubUserFollowingResponse")
	val githubUserFollowingResponse: List<GithubUserFollowingResponseItem>
)

data class GithubUserFollowingResponseItem(

	@field:SerializedName("avatar_url")
	val avatarUrl: String,

	@field:SerializedName("login")
	val login: String,

	@field:SerializedName("url")
	val url: String
)
