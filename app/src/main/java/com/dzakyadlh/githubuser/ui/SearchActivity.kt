package com.dzakyadlh.githubuser.ui

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.dzakyadlh.githubuser.R
import com.dzakyadlh.githubuser.data.response.ItemsItem
import com.dzakyadlh.githubuser.databinding.ActivitySearchBinding

class SearchActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySearchBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySearchBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val searchViewModel =
            ViewModelProvider(
                this,
                ViewModelProvider.NewInstanceFactory()
            ).get(SearchViewModel::class.java)
        searchViewModel.listUser.observe(this) { listUser ->
            setListUserData(listUser)
        }

        with(binding) {
            searchView.setupWithSearchBar(searchBar)
            searchView.editText.setOnEditorActionListener { textView, actionId, event ->
                searchViewModel.findUser(binding.searchView.text.toString())
                searchBar.text = searchView.text
                searchView.hide()
                false
            }
        }

        supportActionBar?.hide()

        val layoutManager = LinearLayoutManager(this)
        binding.searchResults.layoutManager = layoutManager
        val itemDecoration = DividerItemDecoration(this, layoutManager.orientation)
        binding.searchResults.addItemDecoration(itemDecoration)

        searchViewModel.listUser.observe(this) { searchResults ->
            setListUserData(searchResults)
        }

        searchViewModel.isLoading.observe(this) {
            showLoading(it)
        }

        binding.appBarLayout.setOnMenuItemClickListener { menuItem ->

            when (menuItem.itemId) {
                R.id.detail -> {
                    val intent = Intent(this, DetailActivity::class.java)
                    startActivity(intent)
                    true
                }

                else -> false
            }

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