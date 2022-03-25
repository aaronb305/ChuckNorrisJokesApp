package com.example.chucknorrisjokesapp.views

import android.app.AlertDialog
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.example.chucknorrisjokesapp.R
import com.example.chucknorrisjokesapp.databinding.FragmentMainBinding
import com.example.chucknorrisjokesapp.model.Joke
import com.example.chucknorrisjokesapp.model.Jokes
import com.example.chucknorrisjokesapp.utils.JokeState

class MainFragment : BaseFragment() {

    private val binding by lazy {
        FragmentMainBinding.inflate(layoutInflater)
    }

    private var category: Array<String>? = arrayOf("explicit")

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding.checkboxNSFW.setOnCheckedChangeListener { compoundButton, b ->
            category = if (compoundButton.isChecked) {
                Log.d("main fragment", "checkbox checked")
                null
            } else {
                Log.d("main fragment", "checkbox unchecked")
                arrayOf("explicit")
            }
        }

        binding.neverEndingList.setOnClickListener {
            findNavController().navigate(R.id.action_mainFragment_to_neverEndingListFragment)
        }

        binding.textInput.setOnClickListener {
            findNavController().navigate(R.id.action_mainFragment_to_textInputFragment)
        }

        binding.randomJoke.setOnClickListener {
            viewModel.getRandomJoke(category)
        }

        viewModel.joke.observe(viewLifecycleOwner) { state ->
            when(state) {
                is JokeState.LOADING -> {
                    Toast.makeText(
                        requireContext(), "Loading ....", Toast.LENGTH_LONG
                    ).show()
                }
                is JokeState.SUCCESS<*> -> {
                    val jokes = state.jokes as Jokes
                    val joke = jokes.joke
                    AlertDialog.Builder(requireContext())
                        .setMessage(joke.joke)
                        .setPositiveButton("Dismiss") { dialog, i ->
                            dialog.dismiss()
                            dialog.cancel()
                        }
                        .show()
                }
                is JokeState.ERROR -> {
                    Toast.makeText(
                        requireContext(), state.throwable.localizedMessage, Toast.LENGTH_LONG
                    ).show()
                }
            }
        }


        // Inflate the layout for this fragment
        return binding.root
    }
}