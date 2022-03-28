package com.example.chucknorrisjokesapp.views

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.size
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.chucknorrisjokesapp.adapter.ChuckNorrisAdapter
import com.example.chucknorrisjokesapp.databinding.FragmentNeverEndingListBinding
import com.example.chucknorrisjokesapp.model.Joke
import com.example.chucknorrisjokesapp.model.Jokes
import com.example.chucknorrisjokesapp.utils.JokeState

class NeverEndingListFragment : BaseFragment() {

    private val binding by lazy {
        FragmentNeverEndingListBinding.inflate(layoutInflater)
    }

    private val chuckNorrisAdapter by lazy {
        ChuckNorrisAdapter()
    }

    private var jokeList = mutableListOf<Joke>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val mLayoutManager = LinearLayoutManager(
            requireContext(), LinearLayoutManager.VERTICAL, false
        )

        binding.recycler.apply {
            layoutManager = mLayoutManager
            adapter = chuckNorrisAdapter
        }

        viewModel.joke.observe(viewLifecycleOwner) { state ->
            Log.d("endless list fragment", "observe called")
            Log.d("endless list fragment", viewModel.joke.value.toString())
            when(state) {
                is JokeState.LOADING -> {
                    Toast.makeText(
                        requireContext(), "Loading ....", Toast.LENGTH_LONG
                    ).show()
                }
                is JokeState.SUCCESS<*> -> {
                    val jokes = state.jokes as Jokes
                    jokeList = jokes.joke as MutableList<Joke>
                    chuckNorrisAdapter.updateJokes(jokeList)
                    viewModel.resetState()
                }
                is JokeState.ERROR -> {
                    Toast.makeText(
                        requireContext(), state.throwable.localizedMessage, Toast.LENGTH_LONG
                    ).show()
                }
            }
        }

        viewModel.getRandomJoke(30)

        binding.recycler.addOnScrollListener(object: RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)

                if (mLayoutManager.findLastVisibleItemPosition() == chuckNorrisAdapter.itemCount - 1) {
                    viewModel.getRandomJoke(30)
                }
            }
        })

        binding.backButton.setOnClickListener {
            findNavController().navigateUp()
        }
        // Inflate the layout for this fragment
        return binding.root
    }
}