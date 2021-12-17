package com.example.valeeeu.logic.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.valeeeu.data.models.SummarizedProfile
import com.example.valeeeu.data.repositories.IProfileRepository
import kotlinx.coroutines.launch

class HomeViewModel(
    private val profileRepository: IProfileRepository
) : ViewModel() {
    fun onFetchProfiles(
        limit: Int,
        offset: Int = 0,
        callback: (List<SummarizedProfile>) -> Unit
    ) {
        viewModelScope.launch {
            val profiles =
                profileRepository.getProfiles(
                    limit = limit,
                    offset = offset
                )

            callback(profiles)
        }
    }
}