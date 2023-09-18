package com.example.githubapp.ui

import com.google.android.material.search.SearchBar
import androidx.appcompat.widget.SearchView

fun SearchView.setupWithSearchBar(searchBar: SearchBar) {
    this.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
        override fun onQueryTextSubmit(query: String?): Boolean {
            searchBar.text = query
            return true
        }

        override fun onQueryTextChange(newText: String?): Boolean {
            return true
        }
    })
}
