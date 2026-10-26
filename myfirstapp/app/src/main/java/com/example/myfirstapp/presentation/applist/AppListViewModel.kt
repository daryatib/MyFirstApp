package com.example.myfirstapp.presentation.applist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myfirstapp.domain.applist.AppListItem
import com.example.myfirstapp.domain.applist.GetAppListUseCase
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

class AppListViewModel(
    private val getAppListUseCase: GetAppListUseCase
) : ViewModel() {

    private val _apps = MutableStateFlow<List<AppListItem>>(emptyList())
    val apps: StateFlow<List<AppListItem>> = _apps.asStateFlow()

    private val _events = Channel<String>()
    val events = _events.receiveAsFlow()

    init {
        loadApps()
    }

    private fun loadApps() {
        viewModelScope.launch {
            getAppListUseCase().collect { list ->
                _apps.value = list
            }
        }
    }

    fun onToolbarClick() {
        viewModelScope.launch {
            _events.send("Вы нажали на тулбар!")
        }
    }
}