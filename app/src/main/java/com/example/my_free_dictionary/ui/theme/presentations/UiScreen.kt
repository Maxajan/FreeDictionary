package com.example.my_free_dictionary.ui.theme.presentations

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


@Composable
fun DictionaryEntryScreen(viewModel: ViewModelDictionary= hiltViewModel()){
    val uiState by  viewModel.uiState.collectAsState()
    val wordInput by viewModel.wordInput.collectAsState()

    DictionaryScreen (
        uiState = uiState,
        wordInput = wordInput,
        onWordChange = {viewModel.updateWordInput(it)},
        onSearchClick = {viewModel.searchWord()}
    )

}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DictionaryScreen(
    uiState: DictionaryUiState,
    wordInput: String,
    onWordChange : (String) -> Unit,
    onSearchClick: () -> Unit
){
    Scaffold (
        topBar = {
        TopAppBar(
            title = { Text("My Smart Dictionary", fontSize = 20.sp, fontWeight = FontWeight.Bold) },
            colors = TopAppBarDefaults.topAppBarColors(containerColor = Color(0xFF1976D2))
        )
    },
        content = {padding ->
            Column (modifier = Modifier.padding(padding) .padding(16.dp) .fillMaxSize())
            {
                OutlinedTextField(
                value = wordInput,
                    onValueChange = onWordChange,
                  label = {Text("Enter a word")},
                    modifier = Modifier.fillMaxWidth(),
                    singleLine = true
                )
                Spacer(modifier = Modifier.height(8.dp))

                Button(
                    onClick = onSearchClick,
                    modifier = Modifier.align(Alignment.End) .padding(vertical = 4.dp)
                    ) {
                    Text("Search")
                }
                Spacer(modifier = Modifier.height(16.dp))
              when {
                 uiState.isLoading -> {
                     CircularProgressIndicator(modifier = Modifier.align(Alignment.CenterHorizontally))
                 }
                  uiState.errorMessage != null -> {
                 Text(
                     text = uiState.errorMessage,
                     color = Color.Red,
                     fontWeight = FontWeight.Bold,
                     modifier = Modifier.align (Alignment.CenterHorizontally)

                 )
                  }
                  uiState.wordResponse != null -> {
                      WordResult(wordResponse = uiState.wordResponse)
                  }

              }
            }
        }
    )
}