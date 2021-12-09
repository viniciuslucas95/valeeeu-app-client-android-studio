package com.example.valeeeu.logic.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.valeeeu.data.models.SummaryProfile
import com.example.valeeeu.data.repositories.IProfileRepository
import com.example.valeeeu.data.repositories.ProfileFetchType
import kotlinx.coroutines.launch

class HomeViewModel(
    private val profileRepository: IProfileRepository
) : ViewModel() {
    fun onFetchProfile(
        limit: Int = 10,
        offset: Int = 0,
        fetchType: ProfileFetchType = ProfileFetchType.LIST,
        callback: (List<SummaryProfile>) -> Unit
    ) {
        viewModelScope.launch {
            val profiles =
                profileRepository.getProfiles(limit = limit, offset = offset, fetchType = fetchType)

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