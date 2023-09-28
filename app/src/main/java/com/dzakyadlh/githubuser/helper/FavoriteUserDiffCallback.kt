package com.dzakyadlh.githubuser.helper

import androidx.recyclerview.widget.DiffUtil
import com.dzakyadlh.githubuser.data.local.entity.FavoriteUser

class FavoriteUserDiffCallback(
    private val oldFavoriteUserList: List<FavoriteUser>,
    private val newFavoriteUserList: List<FavoriteUser>
) : DiffUtil.Callback() {
    override fun getOldListSize(): Int = oldFavoriteUserList.size

    override fun getNewListSize(): Int = newFavoriteUserList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldFavoriteUserList[oldItemPosition].username == newFavoriteUserList[newItemPosition].username
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldFavoriteUser = oldFavoriteUserList[oldItemPosition]
        val newFavoriteUser = newFavoriteUserList[newItemPosition]
        return oldFavoriteUser.username == newFavoriteUser.username && oldFavoriteUser.avatarUrl == newFavoriteUser.avatarUrl
    }

}