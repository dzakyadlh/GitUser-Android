package com.dzakyadlh.githubuser.ui.viewmodel

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.dzakyadlh.githubuser.data.FavoriteUserRepository
import com.dzakyadlh.githubuser.data.local.entity.FavoriteUser

class FavoriteUserViewModel(application: Application) : ViewModel() {
    private val mFavoriteUserRepository: FavoriteUserRepository =
        FavoriteUserRepository(application)

    fun getAllFavoriteUsers(): LiveData<List<FavoriteUser>> =
        mFavoriteUserRepository.getAllFavoriteUsers()
}