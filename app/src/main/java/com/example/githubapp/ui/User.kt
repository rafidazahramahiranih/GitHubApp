package com.example.githubapp.ui

data class User (
    val username: String,
    val avatarURL: String,
    val following: String = "0",
    val followers: String = "0"
)