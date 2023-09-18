package com.example.githubapp.ui

import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.githubapp.R
import com.example.githubapp.adapter.UserAdapter
import com.example.githubapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val viewModel: UserViewModel by lazy {
        ViewModelProvider(this).get(UserViewModel::class.java)
    }
    private lateinit var userAdapter: UserAdapter

    private fun showLoading(isLoading: Boolean) {
        binding.progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        userAdapter = UserAdapter()
        binding.recyclerView.adapter = userAdapter


        binding.searchView.setupWithSearchBar(binding.searchBar)
        binding.searchView.editText.setOnEditorActionListener { textView, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                val query = textView.text.toString()
                viewModel.searchUsers(query)
                showLoading(true)
                binding.searchView.hide()
                return@setOnEditorActionListener true
            }
            false
        }

        viewModel.getUsersLiveData().observe(this, Observer { githubResponse ->
            showLoading(false)
            if (githubResponse != null) {
                val userList: List<User> = githubResponse.map { response ->
                    User(response.login ?: "", response.avatarUrl?: "")
                }
                Log.d("TAG", "response: $githubResponse")
            }
        })
    }
}

