package com.example.valeeeu.logic.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.valeeeu.data.models.SummarizedProfile
import com.example.valeeeu.data.repositories.IProfileRepository
import kotlinx.coroutines.launch

class HomeViewModel(
    private val profileRepository: IProfileRepository
) : ViewModel() {
    fun onFetchProfile(
        limit: Int = 10,
        offset: Int = 0,
        includeDescription: Boolean = true,
        callback: (List<SummarizedProfile>) -> Unit
    ) {
        viewModelScope.launch {
            val profiles =
                profileRepository.getSummarizedProfiles(
                    limit = limit,
                    offset = offset,
                    includeDescription = includeDescription
                )

            callback(profiles)
        }
    }

    fun onCardClick(username: String) {

    }

    fun onFavoriteClick(username: String) {

    }

    fun onSeeMore(tag: String) {

    }
}