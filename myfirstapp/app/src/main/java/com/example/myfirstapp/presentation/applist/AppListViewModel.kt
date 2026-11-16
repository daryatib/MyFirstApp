package com.example.myfirstapp.presentation.applist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myfirstapp.domain.applist.AppListItem
import com.example.myfirstapp.domain.applist.GetAppListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlin.system.measureTimeMillis
import javax.inject.Inject

@HiltViewModel
class AppListViewModel @Inject constructor(
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
            _events.send("Кнопка меню нажата")
        }
    }

    // Эмуляция задержки сети
    private suspend fun getRating(id: String): Float {
        kotlinx.coroutines.delay(1500)
        return (30..50).random() / 10f
    }

    fun testSequentialLoading() {
        viewModelScope.launch {
            val ids = _apps.value.map { it.id }
            if (ids.isEmpty()) return@launch

            val time = measureTimeMillis {
                val ratings = mutableMapOf<String, Float>()
                for (id in ids) {
                    ratings[id] = getRating(id)
                }
                updateRatings(ratings)
            }
            _events.send("Последовательно: $time мс")
        }
    }

    fun testParallelLoading() {
        viewModelScope.launch {
            val ids = _apps.value.map { it.id }
            if (ids.isEmpty()) return@launch

            val time = measureTimeMillis {
                val deferredList = ids.map { id ->
                    async { id to getRating(id) }
                }
                val ratings = deferredList.awaitAll().toMap()
                updateRatings(ratings)
            }
            _events.send("Параллельно: $time мс")
        }
    }

    private fun updateRatings(ratings: Map<String, Float>) {
        _apps.update { currentList ->
            currentList.map { app ->
                app.copy(rating = ratings[app.id] ?: app.rating)
            }
        }
    }
}