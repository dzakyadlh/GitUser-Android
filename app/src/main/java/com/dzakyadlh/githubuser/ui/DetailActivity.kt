package com.dzakyadlh.githubuser.ui

import android.os.Bundle
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import com.dzakyadlh.githubuser.R
import com.dzakyadlh.githubuser.databinding.ActivityDetailBinding
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
        setContentView(R.layout.activity_detail)

        val sectionsPagerAdapter = SectionsPagerAdapter(this)
        binding.detailViewPager.adapter = sectionsPagerAdapter
        TabLayoutMediator(binding.detailTabs, binding.detailViewPager) { tab, position ->
            tab.text = resources.getString(TAB_TITLES[position])
        }.attach()

        supportActionBar?.elevation = 0f
    }
}