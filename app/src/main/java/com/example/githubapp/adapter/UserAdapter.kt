package com.example.githubapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.githubapp.databinding.ItemUserBinding
import com.example.githubapp.R
import com.example.githubapp.ui.User

class UserAdapter : ListAdapter<User, UserAdapter.UserViewHolder>(UserDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val binding = ItemUserBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return UserViewHolder(binding)
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        val user = getItem(position)
        holder.bind(user)
    }

    val placeholderUrl = "https://avatars.githubusercontent.com/u/0"
    val errorUrl = "https://avatars.githubusercontent.com/u/1"

    inner class UserViewHolder(private val binding: ItemUserBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(user: User) {
            binding.usernameTextView.text = user.username

            Glide.with(binding.root)
                .load(placeholderUrl)
                .placeholder(R.drawable.img)
                .circleCrop()
                .into(binding.userProfileImage)

            Glide.with(binding.root)
                .load(user.avatarURL)
                .error(R.drawable.img_1)
                .circleCrop()
                .into(binding.userProfileImage)
        }
    }

    private class UserDiffCallback : DiffUtil.ItemCallback<User>() {
        override fun areItemsTheSame(oldItem: User, newItem: User): Boolean {
            return oldItem.username == newItem.username
        }

        override fun areContentsTheSame(oldItem: User, newItem: User): Boolean {
            return oldItem == newItem
        }
    }
}
