package com.example.chucknorrisjokesapp.views

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.chucknorrisjokesapp.R
import com.example.chucknorrisjokesapp.databinding.FragmentNeverEndingListBinding

class NeverEndingListFragment : BaseFragment() {

    private val binding by lazy {
        FragmentNeverEndingListBinding.inflate(layoutInflater)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return binding.root
    }
}