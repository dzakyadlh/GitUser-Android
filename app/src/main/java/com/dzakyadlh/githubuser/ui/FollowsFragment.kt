package com.dzakyadlh.githubuser.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.dzakyadlh.githubuser.data.response.GithubUserFollowsResponseItem
import com.dzakyadlh.githubuser.databinding.FragmentFollowsBinding

class FollowsFragment : Fragment() {

    private lateinit var binding: FragmentFollowsBinding

    companion object {
        const val ARG_USERNAME = "username"
        const val ARG_POSITION = "position"
        fun getInstance(username: String): FollowsFragment {
            val fragment = FollowsFragment()
            val bundle = Bundle()
            bundle.putString(ARG_USERNAME, username)
            fragment.arguments = bundle
            return fragment
        }
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
        detailViewModel.listFollower.observe(viewLifecycleOwner) { listFollower ->
            setListFollowsData(listFollower)
        }
        detailViewModel.listFollowing.observe(viewLifecycleOwner) { listFollowing ->
            setListFollowsData(listFollowing)
        }
        detailViewModel.isLoading.observe(viewLifecycleOwner) {
            showLoading(it)
        }

        arguments?.let { args ->
            val position = args.getInt(ARG_POSITION)
            val username = args.getString(ARG_USERNAME)
            if (position == 1 && username != null) {
                detailViewModel.getFollowers(username)
            } else if (username != null) {
                detailViewModel.getFollowing(username)
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