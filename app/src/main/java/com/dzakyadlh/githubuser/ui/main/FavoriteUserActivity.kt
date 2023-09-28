package com.dzakyadlh.githubuser.ui.main

import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.dzakyadlh.githubuser.databinding.ActivityFavoriteUserBinding
import com.dzakyadlh.githubuser.helper.ViewModelFactory
import com.dzakyadlh.githubuser.ui.adapter.FavoriteUserAdapter
import com.dzakyadlh.githubuser.ui.viewmodel.FavoriteUserViewModel

class FavoriteUserActivity : AppCompatActivity() {

    private var _activityFavoriteUserBinding: ActivityFavoriteUserBinding? = null
    private val binding get() = _activityFavoriteUserBinding

    private val favoriteUserViewModel: FavoriteUserViewModel by viewModels {
        ViewModelFactory.getInstance(application)
    }

    private lateinit var adapter: FavoriteUserAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _activityFavoriteUserBinding = ActivityFavoriteUserBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        adapter = FavoriteUserAdapter()

        supportActionBar?.hide()

        val layoutManager = LinearLayoutManager(this)
        binding?.favoriteUsers?.layoutManager = layoutManager
        val itemDecoration = DividerItemDecoration(this, layoutManager.orientation)
        binding?.favoriteUsers?.addItemDecoration(itemDecoration)

        favoriteUserViewModel.getAllFavoriteUsers().observe(this) { favoriteUserList ->
            Log.d("FavoriteUserActivity", "$favoriteUserList")
            if (favoriteUserList != null) {
                adapter.submitList(favoriteUserList.toMutableList())
                binding?.favoriteUsers?.adapter = adapter
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _activityFavoriteUserBinding = null
    }

}