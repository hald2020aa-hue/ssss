package com.example.data.model

import androidx.compose.ui.graphics.Color
import com.example.ui.theme.NeonColorSchemeType

data class MangaSource(
    val id: String,
    val name: String,
    val letterMark: String,
    val bgGradientStart: Color,
    val bgGradientEnd: Color,
    val accentColor: Color,
    val iconSymbol: String = "",
    val totalManga: Int = 120,
    val isEnabled: Boolean = true
)

data class MangaItem(
    val id: String,
    val title: String,
    val sourceName: String,
    val coverGradientStart: Color,
    val coverGradientEnd: Color,
    val rating: Float = 4.8f,
    val totalChapters: Int = 100,
    val lastReadChapter: Int? = null,
    val unreadCount: Int = 0,
    val isFavorite: Boolean = false,
    val isReadLater: Boolean = false,
    val genres: List<String> = listOf("أكشن", "مغامرات", "خيال"),
    val synopsis: String = "قصة مشوقة مليئة بالأحداث المثيرة والمغامرات الخيالية في عالم مليء بالأسرار والتحديات الكبرى.",
    val releaseDate: String = "اليوم، 14:30"
)

data class HistoryItem(
    val id: String,
    val manga: MangaItem,
    val chapterTitle: String,
    val timeAgo: String,
    val progress: Float = 0.85f
)

data class AppSettings(
    val colorScheme: NeonColorSchemeType = NeonColorSchemeType.DYNAMIC,
    val themeMode: String = "داكن",
    val language: String = "العربية",
    val listMode: String = "شبكة", // "شبكة", "تفاصيل", "المدمج"
    val gridSize: Float = 1.0f,
    val showQuickFilters: Boolean = true,
    val showReadingProgress: Boolean = true,
    val showBookmarksInLists: Boolean = true,
    val showContinueReading: Boolean = true,
    val showLatestUpdates: Boolean = true,
    val pinUI: Boolean = false,
    val confirmExit: Boolean = true,
    // Reader Settings
    val readerMode: String = "أساسي", // "أساسي", "ويبتون", "مستمر", "صفحات مزدوجة"
    val scaleMode: String = "مناسب للعرض", // "مناسب للعرض", "ذكي", "مناسب للمركز"
    val doubleTapZoom: Boolean = true,
    val webtoonZoom: Boolean = true,
    val webtoonZoomLevel: Float = 0.5f,
    val showGapsInWebtoon: Boolean = false,
    val gestureControls: Boolean = true,
    val volumeKeysNav: Boolean = false,
    val swipeChapterChange: Boolean = false,
    val readingDirectionRtl: Boolean = true,
    val color32Bit: Boolean = false,
    val fullScreenMode: Boolean = true,
    val keepScreenOn: Boolean = true,
    val transparentInfoBar: Boolean = false,
    val showChapterChangeDialog: Boolean = true,
    // State toggles
    val incognitoMode: Boolean = false
)
