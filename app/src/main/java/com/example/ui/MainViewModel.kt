package com.example.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.data.MangaRepository
import com.example.data.model.AppSettings
import com.example.data.model.HistoryItem
import com.example.data.model.MangaItem
import com.example.data.model.MangaSource
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn

enum class MainTab {
    FEED, EXPLORE, FAVORITES, HISTORY
}

enum class SubScreen {
    NONE,
    SETTINGS_HUB,
    APPEARANCE_SETTINGS,
    READER_SETTINGS,
    STORAGE_NETWORK_SETTINGS,
    DOWNLOADS_SETTINGS,
    BACKUP_RESTORE_SETTINGS,
    MANGA_DETAILS,
    MANGA_READER,
    SOURCE_EXPLORE
}

class MainViewModel(private val repository: MangaRepository = MangaRepository()) : ViewModel() {

    private val _currentTab = MutableStateFlow(MainTab.FAVORITES)
    val currentTab: StateFlow<MainTab> = _currentTab.asStateFlow()

    private val _subScreen = MutableStateFlow(SubScreen.NONE)
    val subScreen: StateFlow<SubScreen> = _subScreen.asStateFlow()

    private val _searchQuery = MutableStateFlow("")
    val searchQuery: StateFlow<String> = _searchQuery.asStateFlow()

    private val _isSearchActive = MutableStateFlow(false)
    val isSearchActive: StateFlow<Boolean> = _isSearchActive.asStateFlow()

    private val _isOptionsSheetOpen = MutableStateFlow(false)
    val isOptionsSheetOpen: StateFlow<Boolean> = _isOptionsSheetOpen.asStateFlow()

    private val _isMenuOpen = MutableStateFlow(false)
    val isMenuOpen: StateFlow<Boolean> = _isMenuOpen.asStateFlow()

    private val _selectedManga = MutableStateFlow<MangaItem?>(null)
    val selectedManga: StateFlow<MangaItem?> = _selectedManga.asStateFlow()

    private val _selectedSource = MutableStateFlow<MangaSource?>(null)
    val selectedSource: StateFlow<MangaSource?> = _selectedSource.asStateFlow()

    private val _favoritesFilter = MutableStateFlow("كل") // "كل", "المفضلة", "اقرأ لاحقاً"
    val favoritesFilter: StateFlow<String> = _favoritesFilter.asStateFlow()

    val settings: StateFlow<AppSettings> = repository.settings
    val mangaList: StateFlow<List<MangaItem>> = repository.mangaList
    val history: StateFlow<List<HistoryItem>> = repository.history
    val sources: List<MangaSource> = repository.defaultSources

    val filteredMangaList = combine(mangaList, searchQuery, favoritesFilter) { list, query, filter ->
        var res = list
        if (query.isNotBlank()) {
            res = res.filter { it.title.contains(query, ignoreCase = true) || it.sourceName.contains(query, ignoreCase = true) }
        }
        when (filter) {
            "المفضلة" -> res.filter { it.isFavorite }
            "اقرأ لاحقاً" -> res.filter { it.isReadLater }
            else -> res
        }
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    fun selectTab(tab: MainTab) {
        _currentTab.value = tab
        _subScreen.value = SubScreen.NONE
        _isMenuOpen.value = false
    }

    fun navigateToSubScreen(screen: SubScreen) {
        _subScreen.value = screen
        _isMenuOpen.value = false
    }

    fun navigateBack(): Boolean {
        if (_subScreen.value != SubScreen.NONE) {
            if (_subScreen.value in listOf(
                    SubScreen.APPEARANCE_SETTINGS,
                    SubScreen.READER_SETTINGS,
                    SubScreen.STORAGE_NETWORK_SETTINGS,
                    SubScreen.DOWNLOADS_SETTINGS,
                    SubScreen.BACKUP_RESTORE_SETTINGS
                )) {
                _subScreen.value = SubScreen.SETTINGS_HUB
            } else {
                _subScreen.value = SubScreen.NONE
            }
            return true
        }
        return false
    }

    fun setSearchQuery(query: String) {
        _searchQuery.value = query
    }

    fun setSearchActive(active: Boolean) {
        _isSearchActive.value = active
    }

    fun setOptionsSheetOpen(open: Boolean) {
        _isOptionsSheetOpen.value = open
    }

    fun setMenuOpen(open: Boolean) {
        _isMenuOpen.value = open
    }

    fun setFavoritesFilter(filter: String) {
        _favoritesFilter.value = filter
    }

    fun openMangaDetails(manga: MangaItem) {
        _selectedManga.value = manga
        _subScreen.value = SubScreen.MANGA_DETAILS
    }

    fun openReader(manga: MangaItem) {
        _selectedManga.value = manga
        _subScreen.value = SubScreen.MANGA_READER
        repository.addToHistory(manga, "الفصل 1")
    }

    fun openSource(source: MangaSource) {
        _selectedSource.value = source
        _subScreen.value = SubScreen.SOURCE_EXPLORE
    }

    fun toggleFavorite(mangaId: String) {
        repository.toggleFavorite(mangaId)
    }

    fun toggleReadLater(mangaId: String) {
        repository.toggleReadLater(mangaId)
    }

    fun clearHistory() {
        repository.clearHistory()
        _isMenuOpen.value = false
    }

    fun toggleIncognito() {
        repository.updateSettings { it.copy(incognitoMode = !it.incognitoMode) }
        _isMenuOpen.value = false
    }

    fun updateSettings(transform: (AppSettings) -> AppSettings) {
        repository.updateSettings(transform)
    }
}
