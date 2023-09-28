package com.dzakyadlh.githubuser.ui.adapter

import android.annotation.SuppressLint
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.dzakyadlh.githubuser.data.local.entity.FavoriteUser
import com.dzakyadlh.githubuser.databinding.ListItemBinding
import com.dzakyadlh.githubuser.ui.main.DetailActivity

class FavoriteUserAdapter :
    ListAdapter<FavoriteUser, FavoriteUserAdapter.FavoriteUserViewHolder>(DIFF_CALLBACK) {

    companion object {
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<FavoriteUser>() {
            @SuppressLint("DiffUtilEquals")
            override fun areContentsTheSame(
                oldItem: FavoriteUser,
                newItem: FavoriteUser
            ): Boolean {
                return oldItem.username == newItem.username
            }

            override fun areItemsTheSame(
                oldItem: FavoriteUser,
                newItem: FavoriteUser
            ): Boolean {
                return oldItem.username == newItem.username
            }
        }
    }

    class FavoriteUserViewHolder(val binding: ListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(favoriteUser: FavoriteUser) {
            with(binding) {
                listName.text = favoriteUser.username
                Glide.with(itemView.context).load(favoriteUser.avatarUrl).into(binding.listImg)
                itemView.setOnClickListener {
                    val intent = Intent(itemView.context, DetailActivity::class.java)
                    intent.putExtra(Intent.EXTRA_USER, favoriteUser.username)
                    itemView.context.startActivity(intent)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteUserViewHolder {
        val binding =
            ListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return FavoriteUserViewHolder(binding)
    }

    override fun onBindViewHolder(holder: FavoriteUserViewHolder, position: Int) {
        val favoriteUser = getItem(position)
        holder.bind(favoriteUser)
    }

}