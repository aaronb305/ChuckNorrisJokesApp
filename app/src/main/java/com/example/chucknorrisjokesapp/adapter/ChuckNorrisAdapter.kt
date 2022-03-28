package com.example.chucknorrisjokesapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.chucknorrisjokesapp.databinding.JokeItemBinding
import com.example.chucknorrisjokesapp.model.Joke

class ChuckNorrisAdapter(
    private val jokes : MutableList<Joke> = mutableListOf()
) : RecyclerView.Adapter<ChuckNorrisViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChuckNorrisViewHolder {
        val view = JokeItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ChuckNorrisViewHolder(view)
    }

    override fun onBindViewHolder(holder: ChuckNorrisViewHolder, position: Int) {
        holder.bind(jokes[position])
    }

    override fun getItemCount(): Int = jokes.size

    fun updateJokes(newJokes: MutableList<Joke>) {
        jokes.addAll(newJokes)
        notifyDataSetChanged()
    }
}

class ChuckNorrisViewHolder(
    private val binding : JokeItemBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(joke: Joke) {
        binding.joke.text = joke.joke
    }
}