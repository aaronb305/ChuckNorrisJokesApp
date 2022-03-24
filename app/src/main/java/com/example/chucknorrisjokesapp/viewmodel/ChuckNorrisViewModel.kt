package com.example.chucknorrisjokesapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.chucknorrisjokesapp.rest.ChuckNorrisApiRepository
import com.example.chucknorrisjokesapp.utils.JokeState
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ChuckNorrisViewModel(
    private val chuckNorrisApiRepository: ChuckNorrisApiRepository,
    private val dispatcher: CoroutineDispatcher
) : ViewModel() {

    private val _joke : MutableLiveData<JokeState> = MutableLiveData(JokeState.LOADING)
    val joke : LiveData<JokeState> get() = _joke

    fun getRandomJoke() {
        viewModelScope.launch(dispatcher) {
            try {
                val response = chuckNorrisApiRepository.getRandomJoke()
                if (response.isSuccessful) {
                    response.body()?.let {
                        _joke.postValue(JokeState.SUCCESS(it))
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
}