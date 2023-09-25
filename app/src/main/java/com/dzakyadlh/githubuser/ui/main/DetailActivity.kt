package com.dzakyadlh.githubuser.ui.main

import android.content.Intent
import android.content.Intent.EXTRA_USER
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.viewpager2.widget.ViewPager2
import com.bumptech.glide.Glide
import com.dzakyadlh.githubuser.R
import com.dzakyadlh.githubuser.data.remote.response.GithubUserDetailResponse
import com.dzakyadlh.githubuser.data.local.entity.FavoriteUser
import com.dzakyadlh.githubuser.databinding.ActivityDetailBinding
import com.dzakyadlh.githubuser.helper.ViewModelFactory
import com.dzakyadlh.githubuser.ui.adapter.SectionsPagerAdapter
import com.dzakyadlh.githubuser.ui.viewmodel.DetailViewModel
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class DetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailBinding

    private lateinit var detailViewModel: DetailViewModel

    private var favoriteUser: FavoriteUser? = null

    var isFavorite: Boolean = false

    companion object {
        @StringRes
        private val TAB_TITLES = intArrayOf(
            R.string.followers,
            R.string.following
        )
        const val EXTRA_FAVORITE = "extra_favorite"
        const val ALERT_DIALOG_ADD = "Added to favorite"
        const val ALERT_DIALOG_REMOVE = "Removed from favorite"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val detailViewModel = ViewModelProvider(
            this,
            ViewModelProvider.NewInstanceFactory()
        ).get(DetailViewModel::class.java)
        detailViewModel.detail.observe(this) { detail ->
            setUserDetail(detail)
        }
        detailViewModel.isLoading.observe(this) {
            showLoading(it)
        }

        val username = intent.getStringExtra(EXTRA_USER)

        if (username != null) {
            detailViewModel.getDetail(username)
//            if (favoriteUser != null) {
//                for (user in favoriteUser) {
//                    if (user == username) isFavorite = true
//                }
//            }
        }

        val sectionsPagerAdapter = SectionsPagerAdapter(this)
        sectionsPagerAdapter.username = username
        val viewPager: ViewPager2 = findViewById(R.id.detail_view_pager)
        viewPager.adapter = sectionsPagerAdapter
        val tabs: TabLayout = findViewById(R.id.detail_tabs)
        TabLayoutMediator(tabs, viewPager) { tab, position ->
            tab.text = resources.getString(TAB_TITLES[position])
        }.attach()
    }

    private fun setUserDetail(detail: GithubUserDetailResponse) {
        supportActionBar?.title = detail.login
        Glide.with(this@DetailActivity).load(detail.avatarUrl).into(binding.detailProfilePic)
        binding.detailProfileUsername.text = detail.login
        binding.detailProfileName.text = detail.name
        if (detail.bio != "null") binding.detailProfileBio.text =
            detail.bio else binding.detailProfileBio.text = "No bio"
        binding.reposNum.text = detail.publicRepos.toString()
        binding.followerNum.text = detail.followers.toString()
        binding.followingNum.text = detail.following.toString()
        binding.btnViewGithub.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(detail.htmlUrl))
            startActivity(intent)
        }
//        binding.btnFav.setOnClickListener {
//            favoriteUser.let { favoriteUser ->
//                favoriteUser?.username = detail.login
//                favoriteUser?.avatarUrl = detail.avatarUrl
//            }
//            detailViewModel.insert(favoriteUser as FavoriteUser)
//            showToast(ALERT_DIALOG_ADD)
//        }
        binding.btnShare.setOnClickListener {
            val intent = Intent(Intent.ACTION_SEND)
            intent.setType("text/plain")
            intent.putExtra(
                Intent.EXTRA_TEXT,
                "View and connect with this GitHub account! ${detail.htmlUrl}"
            )
            startActivity(Intent.createChooser(intent, "Share Account"))
        }
    }

    private fun obtainViewModel(activity: AppCompatActivity): DetailViewModel {
        val factory = ViewModelFactory.getInstance(activity.application)
        return ViewModelProvider(activity, factory).get(DetailViewModel::class.java)
    }

    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    private fun showLoading(isLoading: Boolean) {
        binding.progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
    }
}