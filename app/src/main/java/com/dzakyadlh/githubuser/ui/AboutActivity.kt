package com.dzakyadlh.githubuser.ui

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.dzakyadlh.githubuser.databinding.ActivityAboutBinding

class AboutActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAboutBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAboutBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
            elevation = 0f
        }

        binding.btnViewGithub.setOnClickListener {
            val myGithub: String = "https://github.com/dzakyadlh"
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(myGithub))
            startActivity(intent)
        }
    }
}