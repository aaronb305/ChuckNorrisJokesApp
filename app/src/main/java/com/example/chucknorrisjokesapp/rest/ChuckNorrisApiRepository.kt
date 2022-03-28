package com.example.chucknorrisjokesapp.rest

import com.example.chucknorrisjokesapp.model.Jokes
import retrofit2.Response

class ChuckNorrisApiRepositoryImpl(
    private val chuckNorrisApi: ChuckNorrisApi
) : ChuckNorrisApiRepository{
    override suspend fun getRandomJoke(
        number: Int,
        category: Array<String>?,
        firstName: String?,
        lastName: String?
    ): Response<Jokes> =
        chuckNorrisApi.getRandomJoke(number, category, firstName, lastName)
}

interface ChuckNorrisApiRepository {
    suspend fun getRandomJoke(
        number: Int = 1,
        category: Array<String>? = null,
        firstName: String? = null,
        lastName: String? = null
    ) : Response<Jokes>
}