package com.example.my_free_dictionary.ui.theme.presentations

import android.R.attr.fontWeight
import android.R.attr.text
import android.R.attr.thickness
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.my_free_dictionary.network.WordResponse


@Composable
fun WordResult (wordResponse: WordResponse){
    Column(
        modifier = Modifier.fillMaxWidth() .verticalScroll(rememberScrollState()) .padding(4.dp)
    ){
        Text(
            text = wordResponse.word,
            fontWeight = FontWeight.Bold,
            fontSize = 24.sp,
            color = Color (0xFFD32F2F)
        )
        Spacer(modifier = Modifier.height(8.dp))

        wordResponse.entries.forEach { entry->
            Card (
               modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp),

                elevation = CardDefaults.cardElevation(defaultElevation = 5.dp),
                shape = RoundedCornerShape(8.dp)
            ) {
                Column (modifier = Modifier.padding(16.dp)){
                    Text(
                        text = "Part of Speech: ${entry.partOfSpeech}",
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.primary
                        )
                    Spacer(modifier = Modifier.height(4.dp))

                    entry.senses.forEach { sense ->
                      Text(
                          text = "Definition: ",
                          fontWeight = FontWeight.Bold,
                          color = Color(0xFF1976D2),
                          fontSize = 16.sp,
                          modifier = Modifier.padding( 3.dp))
                       Text(
                          text = sense.definition,
                          fontWeight = FontWeight.Medium,
                          color = Color.Cyan,
                          fontSize = 15.sp,
                           )

                        if (sense.examples.isNotEmpty()){
                            Text(
                                text = "Example: ",
                                fontWeight = FontWeight.Bold,
                                color = Color(0xFF1976D2),
                                fontSize = 16.sp,
                            )
                            sense.examples.forEach { example ->
                              Text(
                                  text = example,
                                  style = MaterialTheme.typography.bodyLarge,
                                  fontStyle = FontStyle.Italic,
                                  color = Color.Green,
                                  modifier = Modifier.padding(start = 8.dp)
                              )
                            }
                        }
                       Spacer(modifier = Modifier.height(6.dp))

                         val filteredTranslations = sense.translations.filter {
                          it.language.name == "Arabic" || it.language.name == "Russian"}
                            .map { it.word }
                        if (filteredTranslations.isNotEmpty()){
                            Text(
                                text = "Translations: ${filteredTranslations.joinToString(", ")}",
                                style = MaterialTheme.typography.bodyLarge,
                                color = Color.White,
                                modifier = Modifier.padding(start = 5.dp)
                                )
                        }
                        HorizontalDivider(
                            modifier = Modifier .fillMaxWidth() .padding(vertical = 8.dp),
                            thickness = 2.dp,
                            color = MaterialTheme.colorScheme.outline
                        )
                      }
                }
            }
        }
    }
}