package com.dzakyadlh.githubuser.ui.adapter

import android.annotation.SuppressLint
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.dzakyadlh.githubuser.database.FavoriteUser
import com.dzakyadlh.githubuser.databinding.ListFavoriteBinding
import com.dzakyadlh.githubuser.helper.FavoriteUserDiffCallback
import com.dzakyadlh.githubuser.ui.main.DetailActivity

class FavoriteUserAdapter :
    ListAdapter<FavoriteUser, FavoriteUserAdapter.FavoriteUserViewHolder>(DIFF_CALLBACK) {
    private val listFavoriteUser = ArrayList<FavoriteUser>()

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

    fun setListFavoriteUser(listFavoriteUser: List<FavoriteUser>) {
        val diffCallback = FavoriteUserDiffCallback(this.listFavoriteUser, listFavoriteUser)
        val diffResult = DiffUtil.calculateDiff(diffCallback)
        this.listFavoriteUser.clear()
        this.listFavoriteUser.addAll(listFavoriteUser)
        diffResult.dispatchUpdatesTo(this)
    }

    inner class FavoriteUserViewHolder(private val binding: ListFavoriteBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(favoriteUser: FavoriteUser) {
            with(binding) {
                listFavName.text = favoriteUser.username
                Glide.with(itemView.context).load(favoriteUser.avatarUrl).into(binding.listFavImg)
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
            ListFavoriteBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return FavoriteUserViewHolder(binding)
    }

    override fun onBindViewHolder(holder: FavoriteUserViewHolder, position: Int) {
        val favoriteUser = getItem(position)
        holder.bind(favoriteUser)
    }

    override fun getItemCount(): Int {
        return listFavoriteUser.size
    }
}