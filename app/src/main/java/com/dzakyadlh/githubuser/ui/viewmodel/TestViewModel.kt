package com.dzakyadlh.githubuser.ui.viewmodel

import android.app.Application
import androidx.lifecycle.ViewModel
import com.dzakyadlh.githubuser.data.FavoriteUserRepository
import com.dzakyadlh.githubuser.data.local.entity.FavoriteUser

class TestViewModel(application: Application) : ViewModel() {
    private val mFavoriteUserRepository: FavoriteUserRepository =
        FavoriteUserRepository(application)

    fun insert(favoriteUser: FavoriteUser) {
        mFavoriteUserRepository.insert(favoriteUser)
    }

    fun delete(favoriteUser: FavoriteUser) {
        mFavoriteUserRepository.delete(favoriteUser)
    }
}