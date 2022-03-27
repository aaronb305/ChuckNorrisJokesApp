package com.example.chucknorrisjokesapp.adapter

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.chucknorrisjokesapp.model.Joke

class OnScrollListener(
    private val layoutManager: LinearLayoutManager,
    private val adapter: ChuckNorrisAdapter,
    private val jokes : MutableList<Joke>
) : RecyclerView.OnScrollListener() {

    private var previousTotal = 0
    var loading = true
    private val visibleThreshold = 10
    var firstVisibleItem = 0
    var visibleItemCount = 0
    var totalItemCount = 0

    override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
        super.onScrolled(recyclerView, dx, dy)

        visibleItemCount = recyclerView.childCount
        totalItemCount = layoutManager.itemCount
        firstVisibleItem = layoutManager.findFirstVisibleItemPosition()

        if (loading) {
            if (totalItemCount > previousTotal) {
                loading = false
                previousTotal = totalItemCount
            }
        }

        if (!loading && (
                    totalItemCount - visibleItemCount) <= (firstVisibleItem + visibleThreshold)
        ) {
            val initialSize = jokes.size
            adapter.updateJokes(jokes)
            val updatedSize = jokes.size
//            recyclerView.post { adapter.notifyItemRangeInserted(initialSize, updatedSize) }
            loading = true
        }
    }
}