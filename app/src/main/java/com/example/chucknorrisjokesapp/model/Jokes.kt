package com.example.chucknorrisjokesapp.model


import com.google.gson.annotations.SerializedName

data class Jokes(
    @SerializedName("type")
    val type: String,
    @SerializedName("value")
    val joke: List<Joke>
)