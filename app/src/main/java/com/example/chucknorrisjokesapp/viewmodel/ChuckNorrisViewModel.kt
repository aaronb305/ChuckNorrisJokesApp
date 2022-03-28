package com.example.chucknorrisjokesapp.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.chucknorrisjokesapp.rest.ChuckNorrisApiRepository
import com.example.chucknorrisjokesapp.utils.JokeState
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.launch

class ChuckNorrisViewModel(
    private val chuckNorrisApiRepository: ChuckNorrisApiRepository,
    private val dispatcher: CoroutineDispatcher
) : ViewModel() {

    private val _joke : MutableLiveData<JokeState> = MutableLiveData(JokeState.LOADING)
    val joke : LiveData<JokeState> get() = _joke

    fun getRandomJoke(
        number : Int = 1,
        category: Array<String>? = null,
        firstName: String? = null,
        lastName: String? = null
    ) {
        _joke.postValue(JokeState.LOADING)
        Log.d("view model initial", joke.value.toString())
        viewModelScope.launch(dispatcher) {
            try {
                val response = chuckNorrisApiRepository.getRandomJoke(number, category, firstName, lastName)
                if (response.isSuccessful) {
                    response.body()?.let {
                        _joke.postValue(JokeState.SUCCESS(it))
                        Log.d("view model after", joke.value.toString())
                    }?: throw Exception("Response is Null")
                }
                else {
                    throw Exception("Unsuccessful Response")
                }
            }
            catch (e: Exception) {
                _joke.postValue(JokeState.ERROR(e))
            }
        }
    }

    fun resetState() {
        _joke.postValue(JokeState.DEFAULT)
    }
}