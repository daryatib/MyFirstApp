package com.example.myfirstapp.presentation.applist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myfirstapp.domain.applist.AppListItem
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

class AppListViewModel : ViewModel() {

    // Состояние списка (данные)
    private val _apps = MutableStateFlow<List<AppListItem>>(emptyList())
    val apps: StateFlow<List<AppListItem>> = _apps.asStateFlow()

    // Канал для одноразовых событий (Snackbar)
    private val _events = Channel<String>()
    val events = _events.receiveAsFlow()

    init {
        loadApps()
    }

    private fun loadApps() {
        // Захардкоженные данные (в реальном приложении здесь был бы запрос к API)
        _apps.value = listOf(
            AppListItem("1", "ВКонтакте", "Социальная сеть", "Социальные", "https://placekitten.com/200/200"),
            AppListItem("2", "Telegram", "Мессенджер", "Общение", "https://placekitten.com/201/200"),
            AppListItem("3", "Яндекс Карты", "Навигация", "Карты", "https://placekitten.com/202/200"),
            AppListItem("4", "Сбербанк", "Банк", "Финансы", "https://placekitten.com/203/200"),
            AppListItem("5", "Кинопоиск", "Кино", "Развлечения", "https://placekitten.com/204/200")
        )
    }

    // Функция для клика по тулбару
    fun onToolbarClick() {
        viewModelScope.launch {
            _events.send("Вы нажали на тулбар!")
        }
    }

    // Функция для клика по приложению (если нужно будет навигировать)
    fun onAppClick(app: AppListItem) {
        // Пока ничего не делаем, навигация будет в AppNavigation
    }
}