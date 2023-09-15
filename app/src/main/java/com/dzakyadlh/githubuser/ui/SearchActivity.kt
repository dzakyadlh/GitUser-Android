package com.dzakyadlh.githubuser.ui

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.dzakyadlh.githubuser.data.response.ItemsItem
import com.dzakyadlh.githubuser.databinding.ActivitySearchBinding

class SearchActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySearchBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySearchBinding.inflate(layoutInflater)
        setContentView(binding.root)

        with(binding) {
            searchView.setupWithSearchBar(searchBar)
            searchView.editText.setOnEditorActionListener { textView, actionId, event ->
                searchBar.text = searchView.text
                searchView.hide()
                false
            }
        }

        supportActionBar?.hide()

        val searchViewModel =
            ViewModelProvider(
                this,
                ViewModelProvider.NewInstanceFactory()
            ).get(SearchViewModel::class.java)
        searchViewModel.listUser.observe(this) { users ->
            setListUserData(users)
        }

        val layoutManager = LinearLayoutManager(this)
        binding.searchResults.layoutManager = layoutManager
        val itemDecoration = DividerItemDecoration(this, layoutManager.orientation)
        binding.searchResults.addItemDecoration(itemDecoration)

        searchViewModel.isLoading.observe(this) {
            showLoading(it)
        }
    }

    private fun setListUserData(searchResponse: List<ItemsItem>) {
        val adapter = SearchAdapter()
        adapter.submitList(searchResponse)
        binding.searchResults.adapter = adapter
    }

    private fun showLoading(isLoading: Boolean) {
        binding.progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
    }
}