package com.dzakyadlh.githubuser.ui.adapter

import android.content.Intent
import android.content.Intent.EXTRA_USER
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.dzakyadlh.githubuser.data.remote.response.ItemsItem
import com.dzakyadlh.githubuser.databinding.ListItemBinding
import com.dzakyadlh.githubuser.ui.main.DetailActivity

class SearchAdapter :
    ListAdapter<ItemsItem, SearchAdapter.SearchViewHolder>(DIFF_CALLBACK) {
    companion object {
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<ItemsItem>() {
            override fun areContentsTheSame(oldItem: ItemsItem, newItem: ItemsItem): Boolean {
                return oldItem == newItem
            }

            override fun areItemsTheSame(oldItem: ItemsItem, newItem: ItemsItem): Boolean {
                return oldItem == newItem
            }
        }
    }

    class SearchViewHolder(val binding: ListItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(result: ItemsItem) {
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
    ): SearchViewHolder {
        val binding =
            ListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return SearchViewHolder(binding)
    }

    override fun onBindViewHolder(holder: SearchViewHolder, position: Int) {
        val result = getItem(position)
        holder.bind(result)
    }
}