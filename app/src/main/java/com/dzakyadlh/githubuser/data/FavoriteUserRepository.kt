package com.dzakyadlh.githubuser.data

import android.app.Application
import androidx.lifecycle.LiveData
import com.dzakyadlh.githubuser.data.local.entity.FavoriteUser
import com.dzakyadlh.githubuser.data.local.room.FavoriteUserDao
import com.dzakyadlh.githubuser.data.local.room.FavoriteUserRoomDatabase
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

class FavoriteUserRepository(application: Application) {
    private val mFavoriteUserDao: FavoriteUserDao
    private val executorService: ExecutorService = Executors.newSingleThreadExecutor()

    init {
        val db = FavoriteUserRoomDatabase.getDatabase(application)
        mFavoriteUserDao = db.favoriteUserDao()
    }

    fun getAllFavoriteUsers(): LiveData<List<FavoriteUser>> {
        return mFavoriteUserDao.getAllFavoriteUsers()
    }

    fun insert(favoriteUser: FavoriteUser) {
        executorService.execute { mFavoriteUserDao.insert(favoriteUser) }
    }

    fun delete(favoriteUser: FavoriteUser) {
        executorService.execute { mFavoriteUserDao.delete(favoriteUser) }
    }

    fun getFavoriteUser(username: String): LiveData<FavoriteUser?> {
        return mFavoriteUserDao.getFavoriteUser(username)
    }

}