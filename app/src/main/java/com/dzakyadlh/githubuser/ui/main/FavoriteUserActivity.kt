package com.dzakyadlh.githubuser.ui.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.dzakyadlh.githubuser.databinding.ActivityFavoriteUserBinding
import com.dzakyadlh.githubuser.helper.ViewModelFactory
import com.dzakyadlh.githubuser.ui.adapter.FavoriteUserAdapter
import com.dzakyadlh.githubuser.ui.viewmodel.FavoriteUserViewModel

class FavoriteUserActivity : AppCompatActivity() {

    private var _activityFavoriteUserBinding: ActivityFavoriteUserBinding? = null
    private val binding get() = _activityFavoriteUserBinding

    private lateinit var adapter: FavoriteUserAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _activityFavoriteUserBinding = ActivityFavoriteUserBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        val favoriteUserViewModel = obtainViewModel(this@FavoriteUserActivity)
        favoriteUserViewModel.getAllFavoriteUsers().observe(this) { favoriteUserList ->
            if (favoriteUserList != null) {
                adapter.submitList(favoriteUserList)
                binding?.favoriteUsers?.adapter
            }
        }

        binding?.favoriteUsers?.layoutManager = LinearLayoutManager(this)
        binding?.favoriteUsers?.setHasFixedSize(true)
        binding?.favoriteUsers?.adapter = adapter
    }

    override fun onDestroy() {
        super.onDestroy()
        _activityFavoriteUserBinding = null
    }

//    private fun setFavoriteUserList(favoriteUser: FavoriteUser) {
//        adapter = FavoriteUserAdapter(favoriteUser)
//        adapter.submitList(favoriteUser)
//        binding?.favoriteUsers?.adapter
//    }

    private fun obtainViewModel(activity: AppCompatActivity): FavoriteUserViewModel {
        val factory = ViewModelFactory.getInstance(activity.application)
        return ViewModelProvider(activity, factory).get(FavoriteUserViewModel::class.java)
    }
}