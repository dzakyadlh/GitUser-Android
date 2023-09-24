package com.dzakyadlh.githubuser.ui.main

import android.os.Build
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.dzakyadlh.githubuser.database.FavoriteUser
import com.dzakyadlh.githubuser.databinding.ActivityTestBinding
import com.dzakyadlh.githubuser.helper.ViewModelFactory
import com.dzakyadlh.githubuser.ui.viewmodel.TestViewModel

class TestActivity : AppCompatActivity() {

    private lateinit var binding: ActivityTestBinding

    private lateinit var testViewModel: TestViewModel

    private var favoriteUser: FavoriteUser? = null

    private var isFavorite = false

    companion object {
        const val EXTRA_USER = "extra_user"
        const val EXTRA_USERNAME = "extra_username"
        const val EXTRA_IMAGEURL = "extra_imageurl"
        const val TEST_USERNAME = "Test"
        const val TEST_AVATARURL = "https://avatars.githubusercontent.com/u/106021129?v=4"
        const val ALERT_DIALOG_ADD = "Added to favorite"
        const val ALERT_DIALOG_REMOVE = "Removed from favorite"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTestBinding.inflate(layoutInflater)
        setContentView(binding.root)

        testViewModel = obtainViewModel(this@TestActivity)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            favoriteUser = intent.getParcelableExtra(EXTRA_USER, FavoriteUser::class.java)
        }

        val username: String = TEST_USERNAME
        val avatarurl: String = TEST_AVATARURL

        if (favoriteUser != null) {
            binding.btnFav.setOnClickListener {
                favoriteUser.let { favoriteUser ->
                    favoriteUser?.username = username
                    favoriteUser?.avatarUrl = avatarurl
                }
                testViewModel.insert(favoriteUser as FavoriteUser)
                showToast(ALERT_DIALOG_ADD)
            }
        } else {
            favoriteUser = FavoriteUser()

        }
    }

    private fun obtainViewModel(activity: AppCompatActivity): TestViewModel {
        val factory = ViewModelFactory.getInstance(activity.application)
        return ViewModelProvider(activity, factory).get(TestViewModel::class.java)
    }

    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}