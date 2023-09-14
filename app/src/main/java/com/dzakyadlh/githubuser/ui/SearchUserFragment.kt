package com.dzakyadlh.githubuser.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.dzakyadlh.githubuser.R
import com.dzakyadlh.githubuser.databinding.FragmentSearchUserBinding

class SearchUserFragment : Fragment() {

    private lateinit var binding: FragmentSearchUserBinding

//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentSearchUserBinding.inflate(inflater, container, false)
        with(binding) {
            searchView.setupWithSearchBar(searchBar)
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnTest.setOnClickListener { view ->
            view.findNavController().navigate(R.id.action_searchUserFragment_to_detailUserFragment)
        }
    }
}