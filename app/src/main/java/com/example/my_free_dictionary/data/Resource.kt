package com.example.my_free_dictionary.data

sealed class Resource <out T> {
    class Loading: Resource<Nothing>()
    data class Success <out T> (val data: T): Resource<T>()
    data class Error (val message: String): Resource<Nothing>()
}