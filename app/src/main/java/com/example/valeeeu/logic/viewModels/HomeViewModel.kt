package com.example.valeeeu.logic.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.valeeeu.data.models.SummaryProfile
import com.example.valeeeu.data.repositories.IProfileRepository
import kotlinx.coroutines.launch

class HomeViewModel(
    private val profileRepository: IProfileRepository
) : ViewModel() {
    fun onFetchProfile(tag: String, index: Int, callback: (SummaryProfile) -> Unit) {
        viewModelScope.launch {
            val profile = profileRepository.getSummaryProfile()

            callback(profile)
        }
    }

    fun onCardClick(username: String) {

    }

    fun onFavoriteClick(username: String) {

    }

    fun onSeeMore(tag: String) {

    }
}