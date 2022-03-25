package com.example.chucknorrisjokesapp.utils

sealed class JokeState {
    object LOADING : JokeState()
    class SUCCESS<T>(val jokes : T) : JokeState()
    class ERROR(val throwable: Throwable) : JokeState()
    object DEFAULT : JokeState()
}
