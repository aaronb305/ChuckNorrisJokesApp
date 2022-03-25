package com.example.chucknorrisjokesapp.views

import android.app.AlertDialog
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.example.chucknorrisjokesapp.R
import com.example.chucknorrisjokesapp.databinding.FragmentTextInputBinding
import com.example.chucknorrisjokesapp.model.Jokes
import com.example.chucknorrisjokesapp.utils.JokeState

class TextInputFragment : BaseFragment() {

    private val binding by lazy {
        FragmentTextInputBinding.inflate(layoutInflater)
    }

    private var category : Array<String>? = arrayOf("explicit")

    private var lastName : String? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding.back.setOnClickListener {
            findNavController().navigateUp()
        }

        binding.checkboxNSFW.setOnCheckedChangeListener { compoundButton, b ->
            category = if (compoundButton.isChecked) {
                Log.d("main fragment", "checkbox checked")
                null
            } else {
                Log.d("main fragment", "checkbox unchecked")
                arrayOf("explicit")
            }
        }

        viewModel.joke.observe(viewLifecycleOwner) { state ->
            Log.d("text input fragment", "observe called")
            Log.d("text input fragment", viewModel.joke.value.toString())
            when(state) {
                is JokeState.LOADING -> {
                    Log.d("text input fragment", "observe loading called")
                    Toast.makeText(
                        requireContext(), "Loading ....", Toast.LENGTH_LONG
                    ).show()
                }
                is JokeState.SUCCESS<*> -> {
                    Log.d("text input fragment", "observe success called")
                    val jokes = state.jokes as Jokes
                    val joke = jokes.joke
                    AlertDialog.Builder(requireContext())
                        .setMessage(joke.joke)
                        .setPositiveButton("Dismiss") { dialog, i ->
                            dialog.dismiss()
                            dialog.cancel()
                        }
                        .show()
                    viewModel.resetState()
                }
                is JokeState.ERROR -> {
                    Log.d("text input fragment", "observe error called")
                    Toast.makeText(
                        requireContext(), state.throwable.localizedMessage, Toast.LENGTH_LONG
                    ).show()
                }
            }
        }

        binding.mainCharacterJoke.setOnClickListener {
            val name = binding.nameInput.text.toString()
            val parsedName = name.trim().split(" ")
            if (parsedName.size == 1) {
                val lastName = null
            }
            else {
                val lastName = parsedName[1].replaceFirstChar {
                    it.uppercase()
                }
            }
            val firstName = parsedName.first().replaceFirstChar {
                it.uppercase()
            }
            Log.d("text input first name", firstName)
            Log.d("text input last name", lastName.toString())
            viewModel.getRandomJoke(category, firstName, lastName)
        }
        // Inflate the layout for this fragment
        return binding.root
    }
}