package com.dzakyadlh.githubuser.ui

import android.content.Intent
import android.content.Intent.EXTRA_USER
import android.net.Uri
import android.os.Bundle
import android.view.View
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.viewpager2.widget.ViewPager2
import com.bumptech.glide.Glide
import com.dzakyadlh.githubuser.R
import com.dzakyadlh.githubuser.data.response.GithubUserDetailResponse
import com.dzakyadlh.githubuser.databinding.ActivityDetailBinding
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class DetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailBinding

    companion object {
        @StringRes
        private val TAB_TITLES = intArrayOf(
            R.string.followers,
            R.string.following
        )
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
        }

        val sectionsPagerAdapter = SectionsPagerAdapter(this)
        sectionsPagerAdapter.username = username
        val viewPager: ViewPager2 = findViewById(R.id.detail_view_pager)
        viewPager.adapter = sectionsPagerAdapter
        val tabs: TabLayout = findViewById(R.id.detail_tabs)
        TabLayoutMediator(tabs, viewPager) { tab, position ->
            tab.text = resources.getString(TAB_TITLES[position])
        }.attach()

        supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
            elevation = 0f
        }
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
    }

    private fun showLoading(isLoading: Boolean) {
        binding.progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
    }
}