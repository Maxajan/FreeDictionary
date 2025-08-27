package com.example.my_free_dictionary.network

import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiDictionary {
@GET ("api/v1/entries/{language}/{word}")
suspend fun getEntries (
    @Path ("word") wordApi: String,
    @Path ("language") languageApi: String,
    @Query ("translations") translations: Boolean
): WordResponse
}