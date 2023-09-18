package com.example.githubapp.ui

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.githubapp.response.GitHubResponse
import com.example.githubapp.response.ItemsItem
import com.example.githubapp.retrofit.ApiConfig
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UserViewModel : ViewModel() {

    companion object{
        private const val TAG = "UserVIewModel"
    }

    private val apiService = ApiConfig.getApiService()
    private val userListLiveData = MutableLiveData<List<ItemsItem>>()
    private val _users = MutableLiveData<List<User>>()
    private val _error = MutableLiveData<String>()
    val users: LiveData<List<User>> = MutableLiveData()

    fun searchUsers(query: String) {
        apiService.getGithubSearch(query).enqueue(object : Callback<GitHubResponse> {
            override fun onResponse(call: Call<GitHubResponse>, response: Response<GitHubResponse>) {
                if (response.isSuccessful) {
                    userListLiveData.value = response.body()?.items as List<ItemsItem>
                }
                else {
                    Log.e(TAG, "Error response body: ${response.errorBody()?.string()}")
                }
            }

            override fun onFailure(call: Call<GitHubResponse>, t: Throwable) {
                Log.e(TAG,"Error: ${t.message}", t)
            }
        })
    }

    fun getUsersLiveData(): LiveData<List<ItemsItem>> {
        return userListLiveData
    }
    fun getFollowers(username: String) {
        apiService.getFollowers(username).enqueue(object : Callback<List<User>> {
            override fun onResponse(call: Call<List<User>>, response: Response<List<User>>) {
                if (response.isSuccessful) { response.body()?.let {
                        _users.postValue(it)
                    }
                } else {
                    _error.postValue("Failed to fetch followers. Error code: ${response.code()}")
                }
            }

            override fun onFailure(call: Call<List<User>>, t: Throwable) {
                _error.postValue("Network error: ${t.message}")
            }
        })
    }

    fun getFollowing(username: String) {
        apiService.getFollowing(username).enqueue(object : Callback<List<User>> {
            override fun onResponse(call: Call<List<User>>, response: Response<List<User>>) {
                if (response.isSuccessful) { response.body()?.let {
                        _users.postValue(it)
                    }
                } else {
                    _error.postValue("Failed to fetch following. Error code: ${response.code()}")
                }
            }

            override fun onFailure(call: Call<List<User>>, t: Throwable) {
                _error.postValue("Network error: ${t.message}")
            }
        })
    }
}
