package com.example.my_free_dictionary.data

import com.example.my_free_dictionary.network.WordResponse
import kotlinx.coroutines.flow.Flow

interface Repository {
    fun getWord (wordRepo: String): Flow<Resource<WordResponse>>
}