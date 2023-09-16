package com.dzakyadlh.githubuser.ui

import android.content.Intent
import android.content.Intent.EXTRA_USER
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.dzakyadlh.githubuser.data.response.GithubUserFollowsResponseItem
import com.dzakyadlh.githubuser.databinding.ListItemBinding

class FollowsAdapter :
    ListAdapter<GithubUserFollowsResponseItem, FollowsAdapter.FollowsViewHolder>(DIFF_CALLBACK) {

    companion object {
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<GithubUserFollowsResponseItem>() {
            override fun areContentsTheSame(
                oldItem: GithubUserFollowsResponseItem,
                newItem: GithubUserFollowsResponseItem
            ): Boolean {
                return oldItem == newItem
            }

            override fun areItemsTheSame(
                oldItem: GithubUserFollowsResponseItem,
                newItem: GithubUserFollowsResponseItem
            ): Boolean {
                return oldItem == newItem
            }
        }
    }

    class FollowsViewHolder(private val binding: ListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(result: GithubUserFollowsResponseItem) {
            Log.d("followsViewHolder", "$result")
            Glide.with(itemView.context).load(result.avatarUrl).into(binding.listImg)
            binding.listName.text = result.login
            itemView.setOnClickListener {
                val intent = Intent(itemView.context, DetailActivity::class.java)
                intent.putExtra(EXTRA_USER, result.login)
                itemView.context.startActivity(intent)
            }
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): FollowsViewHolder {
        val binding = ListItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return FollowsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: FollowsViewHolder, position: Int) {
        val follows = getItem(position)
        holder.bind(follows)
    }
}