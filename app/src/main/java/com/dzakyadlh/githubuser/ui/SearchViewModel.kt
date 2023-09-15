package com.dzakyadlh.githubuser.ui

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dzakyadlh.githubuser.data.response.GithubUserSearchResponse
import com.dzakyadlh.githubuser.data.response.ItemsItem
import com.dzakyadlh.githubuser.data.retrofit.APIConfig
import com.dzakyadlh.githubuser.databinding.ActivitySearchBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SearchViewModel : ViewModel() {

    private var binding: ActivitySearchBinding? = null

    private val _listUser = MutableLiveData<List<ItemsItem>>()
    val listUser: LiveData<List<ItemsItem>> = _listUser

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    init {
        findUser()
    }

    private fun findUser() {
        _isLoading.value = true
        val client = APIConfig.getAPIService().getSearchResults(binding?.searchView?.text)
        client.enqueue(object : Callback<GithubUserSearchResponse> {
            override fun onResponse(
                call: Call<GithubUserSearchResponse>,
                response: Response<GithubUserSearchResponse>
            ) {
                _isLoading.value = false
                if (response.isSuccessful) {
                    val responseBody = response.body()
                    if (responseBody != null) {
                        _listUser.value = response.body()?.items
                    } else {
                        Log.e(TAG, "onFailure: ${response.message()}")
                    }
                }
            }

            override fun onFailure(call: Call<GithubUserSearchResponse>, t: Throwable) {
                _isLoading.value = false
                Log.e(TAG, "onFailure: ${t.message}")
            }
        })
    }

    companion object {
        private const val TAG = "SearchUserFragment"
    }
}