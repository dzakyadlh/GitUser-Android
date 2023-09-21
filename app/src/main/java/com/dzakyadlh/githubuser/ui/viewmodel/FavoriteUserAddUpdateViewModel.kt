package com.dzakyadlh.githubuser.ui.viewmodel

import android.app.Application
import androidx.lifecycle.ViewModel
import com.dzakyadlh.githubuser.database.FavoriteUser
import com.dzakyadlh.githubuser.repository.FavoriteUserRepository

class FavoriteUserAddUpdateViewModel(application: Application) : ViewModel() {
    private val mFavoriteUserRepository: FavoriteUserRepository =
        FavoriteUserRepository(application)

    fun insert(favoriteUser: FavoriteUser) {
        mFavoriteUserRepository.insert(favoriteUser)
    }

    fun update(favoriteUser: FavoriteUser) {
        mFavoriteUserRepository.update(favoriteUser)
    }

    fun delete(favoriteUser: FavoriteUser) {
        mFavoriteUserRepository.delete(favoriteUser)
    }
}