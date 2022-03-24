package com.example.chucknorrisjokesapp.rest

import com.example.chucknorrisjokesapp.model.Jokes
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ChuckNorrisApi {

//    @GET(RANDOM_PATH)
//    fun getRandomJoke()

    @GET(RANDOM_PATH)
    suspend fun getRandomJoke(
        @Query("limitTo") category: List<String>? = null,
        @Query("firstName") firstName : String? = null,
        @Query("lastName") lastName : String? = null
    ) : Response<Jokes>


    companion object {
        const val BASE_URL = "http://api.icndb.com/"
        private const val RANDOM_PATH = "jokes/random/"
        private const val CATEGORIES_PATH = "categories/"
    }
}