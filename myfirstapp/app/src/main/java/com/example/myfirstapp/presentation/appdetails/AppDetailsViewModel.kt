package com.example.myfirstapp.presentation.appdetails

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myfirstapp.domain.appdetails.AppDetails
import com.example.myfirstapp.domain.appdetails.Category
import com.example.myfirstapp.domain.appdetails.SimilarApp
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AppDetailsViewModel @Inject constructor() : ViewModel() {

    private val _state = MutableStateFlow<AppDetailsState>(AppDetailsState.Loading)
    val state: StateFlow<AppDetailsState> = _state.asStateFlow()

    private var installJob: Job? = null

    fun loadAppDetails(appId: String) {
        _state.value = AppDetailsState.Loading
        viewModelScope.launch {
            delay(500) // Имитация загрузки

            val details = AppDetails(
                id = appId,
                name = "Приложение $appId",
                developer = "Разработчик",
                category = Category.APP,
                ageRating = 12,
                size = 150.5f,
                iconUrl = "https://picsum.photos/200",
                screenshotUrlList = listOf("https://picsum.photos/800/450"),
                description = "Описание для приложения с ID $appId. Здесь мог быть ваш текст."
            )

            val mockSimilar = List(5) { index ->
                SimilarApp(
                    id = "similar-$index",
                    name = "Похожее ${index + 1}",
                    iconUrl = "https://picsum.photos/200"
                )
            }

            _state.value = AppDetailsState.Content(
                appDetails = details,
                similarApps = mockSimilar,
                descriptionCollapsed = false,
                installState = InstallState.NotInstalled
            )
        }
    }

    fun onInstallClick() {
        val current = _state.value
        if (current !is AppDetailsState.Content) return

        if (current.installState is InstallState.Installing) {
            installJob?.cancel()
            installJob = null
            _state.update {
                (it as AppDetailsState.Content).copy(installState = InstallState.NotInstalled)
            }
        } else {
            installJob = viewModelScope.launch {
                emulateInstall()
            }
        }
    }

    private suspend fun emulateInstall() {
        _state.update {
            (it as AppDetailsState.Content).copy(installState = InstallState.Installing(0))
        }

        for (progress in 0..100 step 5) {
            delay(150)
            _state.update {
                (it as AppDetailsState.Content).copy(installState = InstallState.Installing(progress))
            }
        }

        _state.update {
            (it as AppDetailsState.Content).copy(installState = InstallState.Installed)
        }
    }
}