package com.example.chucknorrisjokesapp.views

import android.app.AlertDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.chucknorrisjokesapp.R
import com.example.chucknorrisjokesapp.databinding.FragmentMainBinding

class MainFragment : BaseFragment() {

    private val binding by lazy {
        FragmentMainBinding.inflate(layoutInflater)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {



        binding.neverEndingList.setOnClickListener {
            findNavController().navigate(R.id.action_mainFragment_to_neverEndingListFragment)
        }

        binding.textInput.setOnClickListener {
            findNavController().navigate(R.id.action_mainFragment_to_textInputFragment)
        }

        binding.randomJoke.setOnClickListener {

        }
        // Inflate the layout for this fragment
        return binding.root
    }
}