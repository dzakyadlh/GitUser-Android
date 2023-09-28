package com.dzakyadlh.githubuser.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.dzakyadlh.githubuser.data.remote.response.GithubUserFollowsResponseItem
import com.dzakyadlh.githubuser.databinding.FragmentFollowsBinding
import com.dzakyadlh.githubuser.ui.adapter.FollowsAdapter
import com.dzakyadlh.githubuser.ui.viewmodel.DetailViewModel

class FollowsFragment : Fragment() {

    private lateinit var binding: FragmentFollowsBinding

    companion object {
        const val ARG_USERNAME = "username"
        const val ARG_POSITION = "position"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentFollowsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val detailViewModel = ViewModelProvider(
            this,
            ViewModelProvider.NewInstanceFactory()
        ).get(DetailViewModel::class.java)

        arguments?.let { args ->
            val position = args.getInt(ARG_POSITION)
            val username = args.getString(ARG_USERNAME).toString()
            if (position == 1) {
                detailViewModel.getFollowers(username)
                detailViewModel.listFollower.observe(viewLifecycleOwner) { listFollower ->
                    setListFollowsData(listFollower)
                }
                detailViewModel.isLoading.observe(viewLifecycleOwner) {
                    showLoading(it)
                }
            } else {
                detailViewModel.getFollowing(username)
                detailViewModel.listFollowing.observe(viewLifecycleOwner) { listFollowing ->
                    setListFollowsData(listFollowing)
                }
                detailViewModel.isLoading.observe(viewLifecycleOwner) {
                    showLoading(it)
                }
            }
        }

        val layoutManager = LinearLayoutManager(requireActivity())
        binding.followsList.layoutManager = layoutManager
        val itemDecoration = DividerItemDecoration(requireActivity(), layoutManager.orientation)
        binding.followsList.addItemDecoration(itemDecoration)
    }

    private fun setListFollowsData(followsResponse: List<GithubUserFollowsResponseItem>) {
        val adapter = FollowsAdapter()
        adapter.submitList(followsResponse)
        binding.followsList.adapter = adapter
    }

    private fun showLoading(isLoading: Boolean) {
        binding.progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
    }
}