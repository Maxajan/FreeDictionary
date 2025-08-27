package com.example.my_free_dictionary.ui.theme.presentations

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.my_free_dictionary.data.Repository
import com.example.my_free_dictionary.data.Resource
import com.example.my_free_dictionary.network.WordResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


data class DictionaryUiState(
    val isLoading: Boolean = false,
    val wordResponse: WordResponse? = null,
    val errorMessage: String? = null
)
@HiltViewModel
class ViewModelDictionary @Inject constructor(private val repository: Repository): ViewModel() {
private val _uiState = MutableStateFlow(DictionaryUiState())
    val uiState : StateFlow<DictionaryUiState> = _uiState

    private val _wordInput = MutableStateFlow("")
    val wordInput: StateFlow<String> = _wordInput

    fun updateWordInput (updatedWord: String){
        _wordInput.value = updatedWord
    }
    fun searchWord (){
       fetchWord (_wordInput.value)
        _wordInput.value = ""
    }

    fun fetchWord (word:String){
      viewModelScope.launch {
          repository.getWord(word).collect { result: Resource<WordResponse> ->
              when (result){
                  is Resource.Loading -> {
                      _uiState.value = DictionaryUiState(isLoading = true)
                  }
                  is Resource.Success -> {
                      _uiState.value = DictionaryUiState(
                          isLoading = false,
                          wordResponse = result.data)
                  }
                  is Resource.Error -> {
                      _uiState.value = DictionaryUiState(
                          isLoading = false,
                          errorMessage = result.message)
                  }
              }
          }
      }
    }

}
