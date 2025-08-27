package com.example.my_free_dictionary.network



data class WordResponse(
    val word: String,
    val entries: List<Entry>,
    val source: Source? = null
)
data class Entry (
val language: Language,
    val partOfSpeech: String,
    val pronunciations: List<Pronunciation> = emptyList(),
    val forms: List<Form> = emptyList(),
    val senses: List<Sense> = emptyList(),
    val synonyms: List<String> = emptyList(),
    val antonyms: List<String> = emptyList()
)
data class Language (
    val code: String,
    val name: String
)
data class Pronunciation (
    val type: String,
    val text: String,
    val tags: List<String> = emptyList()

)
data class Form (
    val word: String,
    val tags: List<String> = emptyList()
)
data class Sense (
    val definition: String,
    val tags: List<String> = emptyList(),
    val examples: List<String> = emptyList(),
    val synonyms: List<String> = emptyList(),
    val antonyms: List<String> = emptyList(),
    val translations: List<Translation> = emptyList()
)
data class Translation (
    val language: Language,
    val word: String
)
data class Source (
    val url: String,
    val licence: License
)
data class License (
    val name: String,
    val url: String
)