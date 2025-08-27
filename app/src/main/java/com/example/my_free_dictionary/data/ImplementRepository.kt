package com.example.my_free_dictionary.data

import com.example.my_free_dictionary.network.ApiDictionary
import com.example.my_free_dictionary.network.WordResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class ImplementRepository @Inject constructor(private val api: ApiDictionary): Repository {
    override fun getWord(wordRepo: String): Flow<Resource<WordResponse>> = flow {
       emit(Resource.Loading())
        try {
            val response = api.getEntries(
                wordApi = wordRepo,
                languageApi = "en",
                translations = true
            )
            emit(Resource.Success(response))
        } catch (e: HttpException){
         emit(Resource.Error("Something went wrong: ${e.response()}"))
        } catch (e: IOException){
            emit(Resource.Error("Check your Internet connection"))
        }
    }.flowOn(Dispatchers.IO)
}