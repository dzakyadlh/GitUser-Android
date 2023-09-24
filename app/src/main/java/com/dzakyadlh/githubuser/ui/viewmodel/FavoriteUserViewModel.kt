package com.dzakyadlh.githubuser.ui.viewmodel

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.dzakyadlh.githubuser.database.FavoriteUser
import com.dzakyadlh.githubuser.repository.FavoriteUserRepository

class FavoriteUserViewModel(application: Application) : ViewModel() {
    private val mFavoriteUserRepository: FavoriteUserRepository =
        FavoriteUserRepository(application)

    fun getAllFavoriteUsers(): LiveData<List<FavoriteUser>> =
        mFavoriteUserRepository.getAllFavoriteUsers()

//    fun saveFavorites(favoriteUser: FavoriteUser) {
//        mFavoriteUserRepository.setFavoriteUsers(favoriteUser, true)
//    }
//
//    fun deleteFavorite(favoriteUser: FavoriteUser) {
//        mFavoriteUserRepository.setFavoriteUsers(favoriteUser, false)
//    }
}