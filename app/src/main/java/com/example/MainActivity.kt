package com.example

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.BackHandler
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.ui.MainTab
import com.example.ui.MainViewModel
import com.example.ui.SubScreen
import com.example.ui.components.CosmicBackground
import com.example.ui.components.ListOptionsBottomSheet
import com.example.ui.components.NeonBottomNavigationBar
import com.example.ui.components.NeonTopSearchBar
import com.example.ui.screens.AppearanceSettingsScreen
import com.example.ui.screens.BackupRestoreScreen
import com.example.ui.screens.DownloadsSettingsScreen
import com.example.ui.screens.ExploreScreen
import com.example.ui.screens.FavoritesScreen
import com.example.ui.screens.FeedScreen
import com.example.ui.screens.HistoryScreen
import com.example.ui.screens.MangaDetailScreen
import com.example.ui.screens.ReaderScreen
import com.example.ui.screens.ReaderSettingsScreen
import com.example.ui.screens.SettingsHomeScreen
import com.example.ui.screens.SourceExploreScreen
import com.example.ui.screens.StorageNetworkSettingsScreen
import com.example.ui.theme.SSSMangaTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val viewModel: MainViewModel = viewModel()
            val settings by viewModel.settings.collectAsState()

            SSSMangaTheme(schemeType = settings.colorScheme) {
                CosmicBackground {
                    SSSAppContent(viewModel = viewModel)
                }
            }
        }
    }
}

@Composable
fun SSSAppContent(viewModel: MainViewModel) {
    val currentTab by viewModel.currentTab.collectAsState()
    val subScreen by viewModel.subScreen.collectAsState()
    val searchQuery by viewModel.searchQuery.collectAsState()
    val isMenuOpen by viewModel.isMenuOpen.collectAsState()
    val isOptionsSheetOpen by viewModel.isOptionsSheetOpen.collectAsState()
    val selectedManga by viewModel.selectedManga.collectAsState()
    val selectedSource by viewModel.selectedSource.collectAsState()
    val favoritesFilter by viewModel.favoritesFilter.collectAsState()
    val settings by viewModel.settings.collectAsState()
    val filteredManga by viewModel.filteredMangaList.collectAsState()
    val history by viewModel.history.collectAsState()
    val sources = viewModel.sources

    // Handle Hardware or Gesture Back
    BackHandler(enabled = subScreen != SubScreen.NONE) {
        viewModel.navigateBack()
    }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        containerColor = androidx.compose.ui.graphics.Color.Transparent,
        bottomBar = {
            if (subScreen == SubScreen.NONE) {
                NeonBottomNavigationBar(
                    currentTab = currentTab,
                    onTabSelected = { viewModel.selectTab(it) }
                )
            }
        }
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            when (subScreen) {
                SubScreen.NONE -> {
                    Column(modifier = Modifier.fillMaxSize()) {
                        // Top Search Bar with 3-dots Menu
                        NeonTopSearchBar(
                            query = searchQuery,
                            onQueryChange = { viewModel.setSearchQuery(it) },
                            onSearchClick = { },
                            onMenuClick = { viewModel.setMenuOpen(!isMenuOpen) },
                            isMenuOpen = isMenuOpen,
                            onDismissMenu = { viewModel.setMenuOpen(false) },
                            onClearHistory = { viewModel.clearHistory() },
                            onOptionsClick = { viewModel.setOptionsSheetOpen(true) },
                            onToggleIncognito = { viewModel.toggleIncognito() },
                            onSettingsClick = { viewModel.navigateToSubScreen(SubScreen.SETTINGS_HUB) },
                            incognitoActive = settings.incognitoMode,
                            modifier = Modifier.statusBarsPadding()
                        )

                        // Main Active Tab Content
                        Box(modifier = Modifier.weight(1f)) {
                            when (currentTab) {
                                MainTab.FAVORITES -> {
                                    FavoritesScreen(
                                        currentFilter = favoritesFilter,
                                        onFilterSelect = { viewModel.setFavoritesFilter(it) },
                                        mangaList = filteredManga,
                                        onMangaClick = { viewModel.openMangaDetails(it) },
                                        onToggleFavorite = { viewModel.toggleFavorite(it) },
                                        onExploreClick = { viewModel.selectTab(MainTab.EXPLORE) },
                                        listMode = settings.listMode
                                    )
                                }
                                MainTab.EXPLORE -> {
                                    ExploreScreen(
                                        sources = sources,
                                        onSourceClick = { viewModel.openSource(it) },
                                        onQuickActionClick = { action ->
                                            if (action == "المحفوظات") viewModel.selectTab(MainTab.FAVORITES)
                                            if (action == "عشوائي") {
                                                filteredManga.shuffled().firstOrNull()?.let {
                                                    viewModel.openMangaDetails(it)
                                                }
                                            }
                                        }
                                    )
                                }
                                MainTab.HISTORY -> {
                                    HistoryScreen(
                                        historyItems = history,
                                        onItemClick = { viewModel.openReader(it.manga) },
                                        onExploreClick = { viewModel.selectTab(MainTab.EXPLORE) }
                                    )
                                }
                                MainTab.FEED -> {
                                    FeedScreen(
                                        mangaList = filteredManga,
                                        onMangaClick = { viewModel.openMangaDetails(it) },
                                        onReadChapter = { viewModel.openReader(it) }
                                    )
                                }
                            }
                        }
                    }
                }

                // Sub-screens
                SubScreen.SETTINGS_HUB -> {
                    SettingsHomeScreen(
                        onNavigate = { viewModel.navigateToSubScreen(it) },
                        onBack = { viewModel.navigateBack() }
                    )
                }

                SubScreen.APPEARANCE_SETTINGS -> {
                    AppearanceSettingsScreen(
                        settings = settings,
                        onUpdate = { viewModel.updateSettings(it) },
                        onBack = { viewModel.navigateBack() }
                    )
                }

                SubScreen.READER_SETTINGS -> {
                    ReaderSettingsScreen(
                        settings = settings,
                        onUpdate = { viewModel.updateSettings(it) },
                        onBack = { viewModel.navigateBack() }
                    )
                }

                SubScreen.STORAGE_NETWORK_SETTINGS -> {
                    StorageNetworkSettingsScreen(
                        onBack = { viewModel.navigateBack() }
                    )
                }

                SubScreen.DOWNLOADS_SETTINGS -> {
                    DownloadsSettingsScreen(
                        onBack = { viewModel.navigateBack() }
                    )
                }

                SubScreen.BACKUP_RESTORE_SETTINGS -> {
                    BackupRestoreScreen(
                        onBack = { viewModel.navigateBack() }
                    )
                }

                SubScreen.MANGA_DETAILS -> {
                    selectedManga?.let { manga ->
                        MangaDetailScreen(
                            manga = manga,
                            onBack = { viewModel.navigateBack() },
                            onStartReading = { viewModel.openReader(manga) },
                            onToggleFavorite = { viewModel.toggleFavorite(manga.id) },
                            onToggleReadLater = { viewModel.toggleReadLater(manga.id) }
                        )
                    }
                }

                SubScreen.MANGA_READER -> {
                    selectedManga?.let { manga ->
                        ReaderScreen(
                            manga = manga,
                            onBack = { viewModel.navigateBack() }
                        )
                    }
                }

                SubScreen.SOURCE_EXPLORE -> {
                    selectedSource?.let { source ->
                        SourceExploreScreen(
                            source = source,
                            mangaList = filteredManga.filter { it.sourceName == source.name || true },
                            onBack = { viewModel.navigateBack() },
                            onMangaClick = { viewModel.openMangaDetails(it) },
                            onToggleFavorite = { viewModel.toggleFavorite(it) }
                        )
                    }
                }
            }
        }
    }

    // List Options Bottom Sheet matching screenshot 3
    ListOptionsBottomSheet(
        isOpen = isOptionsSheetOpen,
        onDismiss = { viewModel.setOptionsSheetOpen(false) },
        currentListMode = settings.listMode,
        onListModeChange = { mode ->
            viewModel.updateSettings { it.copy(listMode = mode) }
        },
        gridSize = settings.gridSize,
        onGridSizeChange = { size ->
            viewModel.updateSettings { it.copy(gridSize = size) }
        }
    )
}
