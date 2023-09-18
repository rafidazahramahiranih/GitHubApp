package com.example.githubapp.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.githubapp.R
import com.example.githubapp.adapter.SectionsPagerAdapter
import com.example.githubapp.databinding.ActivityDetailBinding
import com.google.android.material.tabs.TabLayoutMediator

class DetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)


        val username = intent.getStringExtra("username") ?: ""
        val fullName = intent.getStringExtra("fullName") ?: ""
        val followersCount = intent.getIntExtra("followersCount", 0)
        val followingCount = intent.getIntExtra("followingCount", 0)


        binding.usernameTextView.text = username
        binding.fullNameTextView.text = fullName
        binding.followersTextView.text = followersCount.toString()
        binding.followingTextView.text = followingCount.toString()


        val imageUrl = intent.getStringExtra("imageUrl")
        imageUrl?.let {
            Glide.with(this)
                .load(imageUrl)
                .placeholder(R.drawable.img)
                .error(R.drawable.img_1)
                .circleCrop()
                .into(binding.userProfileImage)
        }

        binding.viewPager2.adapter = SectionsPagerAdapter(this, username)
        TabLayoutMediator(binding.tabLayout, binding.viewPager2) { tab, position ->
            tab.text = if (position == 0) "Followers" else "Following"
        }.attach()
    }
}
