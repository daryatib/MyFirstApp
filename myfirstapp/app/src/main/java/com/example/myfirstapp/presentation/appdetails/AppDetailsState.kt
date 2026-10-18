package com.example.myfirstapp.presentation.appdetails

import com.example.myfirstapp.domain.appdetails.AppDetails
import com.example.myfirstapp.domain.appdetails.SimilarApp

sealed interface AppDetailsState {
    object Loading : AppDetailsState
    object Error : AppDetailsState
    data class Content(
        val appDetails: AppDetails,
        val similarApps: List<SimilarApp>,
        val descriptionCollapsed: Boolean = false
    ) : AppDetailsState
}