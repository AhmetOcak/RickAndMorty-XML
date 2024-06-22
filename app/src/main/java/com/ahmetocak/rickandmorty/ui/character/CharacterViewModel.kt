package com.ahmetocak.rickandmorty.ui.character

import android.net.Uri
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.ahmetocak.rickandmorty.domain.model.character.Character
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import java.time.OffsetDateTime
import java.time.format.DateTimeFormatter
import java.util.Locale
import javax.inject.Inject

@HiltViewModel
class CharacterViewModel @Inject constructor(savedStateHandle: SavedStateHandle): ViewModel() {

    private val _uiState = MutableStateFlow(CharacterUiState())
    val uiState = _uiState.asStateFlow()

    init {
        savedStateHandle.get<Character>("character_data")?.let { character ->
            _uiState.update {
                it.copy(
                    character = character.copy(
                        created = formatCreatedDate(character.created),
                        episode = extractEpisodeNumbers(character.episode)
                    )
                )
            }
        }
    }

    /**
     * This function edits the creation date of the character in the format May 5, 2017 09:48:44(Example).
     * @param createdDate character's created date.
     */
    private fun formatCreatedDate(createdDate: String): String {
        val date = OffsetDateTime.parse(createdDate)
        val formatter = DateTimeFormatter.ofPattern("dd MMM uuuu, HH:mm:ss", Locale.ENGLISH)
        return formatter.format(date)
    }

    private fun extractEpisodeNumbers(episodes: List<String>): List<String> {
        val episodeNumbers: ArrayList<String> = arrayListOf()
        episodes.forEach {
            val uri = Uri.parse(Uri.decode(it))
            episodeNumbers.add(
                uri.lastPathSegment ?: ""
            )
        }

        return episodeNumbers
    }
}

data class CharacterUiState(
    val character: Character? = null
)