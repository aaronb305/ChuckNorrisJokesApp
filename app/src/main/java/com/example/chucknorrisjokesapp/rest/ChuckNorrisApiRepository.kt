package com.example.chucknorrisjokesapp.rest

import com.example.chucknorrisjokesapp.model.Jokes
import retrofit2.Response

class ChuckNorrisApiRepositoryImpl(
    private val chuckNorrisApi: ChuckNorrisApi
) : ChuckNorrisApiRepository{
    override suspend fun getRandomJoke(
        category: String?,
        firstName: String?,
        lastName: String?
    ): Response<Jokes> =
        chuckNorrisApi.getRandomJoke()
}

interface ChuckNorrisApiRepository {
    suspend fun getRandomJoke(
        category: String? = null,
        firstName: String? = null,
        lastName: String? = null
    ) : Response<Jokes>
}