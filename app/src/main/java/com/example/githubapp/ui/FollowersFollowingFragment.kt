package com.example.githubapp.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.githubapp.adapter.UserAdapter
import com.example.githubapp.databinding.FragmentFollowersFollowingBinding

class FollowersFollowingFragment : Fragment() {
    private lateinit var username: String
    private var position: Int = 0
    private lateinit var viewModel: UserViewModel
    private lateinit var adapter: UserAdapter
    private lateinit var binding: FragmentFollowersFollowingBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentFollowersFollowingBinding.inflate(inflater, container, false)

        arguments?.let {
            position = it.getInt("position", 1)
            username = it.getString("username", "")

            viewModel = ViewModelProvider(requireActivity()).get(UserViewModel::class.java)
            adapter = UserAdapter()

            binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
            binding.recyclerView.adapter = adapter

            if (position == 1) {
                viewModel.getFollowers(username)
            } else {
                viewModel.getFollowing(username)
            }

            viewModel.users.observe(viewLifecycleOwner, Observer {users ->
                adapter.submitList(users)
            })
        }

        return binding.root
    }
}
