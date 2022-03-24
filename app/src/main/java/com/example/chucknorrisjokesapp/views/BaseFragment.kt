package com.example.chucknorrisjokesapp.views

import androidx.fragment.app.Fragment
import com.example.chucknorrisjokesapp.viewmodel.ChuckNorrisViewModel
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

open class BaseFragment : Fragment() {

    protected val viewModel : ChuckNorrisViewModel by sharedViewModel()
}