package com.dzakyadlh.githubuser.ui

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dzakyadlh.githubuser.data.response.GithubUserDetailResponse
import com.dzakyadlh.githubuser.data.response.GithubUserFollowsResponse
import com.dzakyadlh.githubuser.data.response.GithubUserFollowsResponseItem
import com.dzakyadlh.githubuser.data.retrofit.APIConfig
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailViewModel : ViewModel() {
    private val _detail = MutableLiveData<GithubUserDetailResponse>()
    val detail: LiveData<GithubUserDetailResponse> = _detail

    private val _listFollower = MutableLiveData<List<GithubUserFollowsResponseItem>>()
    val listFollower: LiveData<List<GithubUserFollowsResponseItem>> = _listFollower

    private val _listFollowing = MutableLiveData<List<GithubUserFollowsResponseItem>>()
    val listFollowing: LiveData<List<GithubUserFollowsResponseItem>> = _listFollowing

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    companion object {
        private const val TAG = "DetailViewModel"
    }

    fun getDetail(username: String) {
        _isLoading.value = true
        val client = APIConfig.getAPIService().getUserDetail(username)
        client.enqueue(object : Callback<GithubUserDetailResponse> {
            override fun onResponse(
                call: Call<GithubUserDetailResponse>,
                response: Response<GithubUserDetailResponse>
            ) {
                _isLoading.value = false
                if (response.isSuccessful) {
                    _detail.value = response.body()
                } else {
                    Log.e(TAG, "onFailure: ${response.message()}")
                }
            }

            override fun onFailure(call: Call<GithubUserDetailResponse>, t: Throwable) {
                _isLoading.value = false
                Log.e(TAG, "onFailure: ${t.message.toString()}")
            }
        })
    }

    fun getFollowers(username: String) {
        _isLoading.value = true
        val client = APIConfig.getAPIService().getUserFollowers(username)
        client.enqueue(object : Callback<GithubUserFollowsResponse> {
            override fun onResponse(
                call: Call<GithubUserFollowsResponse>,
                response: Response<GithubUserFollowsResponse>
            ) {
                _isLoading.value = false
                if (response.isSuccessful) {
                    _listFollower.value = response.body()?.githubUserFollowsResponse
                } else {
                    Log.e(TAG, "onFailure: ${response.message()}")
                }
            }

            override fun onFailure(call: Call<GithubUserFollowsResponse>, t: Throwable) {
                _isLoading.value = false
                Log.e(TAG, "onFailure: ${t.message.toString()}")
            }
        })
    }

    fun getFollowing(username: String) {
        _isLoading.value = true
        val client = APIConfig.getAPIService().getUserFollowing(username)
        client.enqueue(object : Callback<GithubUserFollowsResponse> {
            override fun onResponse(
                call: Call<GithubUserFollowsResponse>,
                response: Response<GithubUserFollowsResponse>
            ) {
                _isLoading.value = false
                if (response.isSuccessful) {
                    _listFollowing.value = response.body()?.githubUserFollowsResponse
                } else {
                    Log.e(TAG, "onFailure: ${response.message()}")
                }
            }

            override fun onFailure(call: Call<GithubUserFollowsResponse>, t: Throwable) {
                _isLoading.value = false
                Log.e(TAG, "onFailure: ${t.message.toString()}")
            }
        })
    }
}