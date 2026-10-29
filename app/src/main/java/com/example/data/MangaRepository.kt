package com.example.data

import androidx.compose.ui.graphics.Color
import com.example.data.model.AppSettings
import com.example.data.model.HistoryItem
import com.example.data.model.MangaItem
import com.example.data.model.MangaSource
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class MangaRepository {

    val defaultSources = listOf(
        MangaSource("1", "MangaPro", "M", Color(0xFF0F1E3D), Color(0xFF162E60), Color(0xFF00E5FF)),
        MangaSource("2", "Manjanoon", "M", Color(0xFF0D1C38), Color(0xFF142B55), Color(0xFF2979FF)),
        MangaSource("3", "AreaScans", "A", Color(0xFF0C2424), Color(0xFF133B36), Color(0xFF00E676)),
        MangaSource("4", "ArAreaScans", "A", Color(0xFF0B2433), Color(0xFF12374F), Color(0xFF00F0FF)),
        MangaSource("5", "ThunderScans", "⚡", Color(0xFF121B35), Color(0xFF1B2850), Color(0xFFFFFFFF), "THUNDER"),
        MangaSource("6", "Hijalacom", "H", Color(0xFF1C1338), Color(0xFF2C1E55), Color(0xFFB388FF)),
        MangaSource("7", "MangaAtrium", "◎", Color(0xFF0D253E), Color(0xFF153860), Color(0xFF00E5FF), "IRIS"),
        MangaSource("8", "Fl-Ares", "⚔", Color(0xFF1E1325), Color(0xFF2E1C38), Color(0xFFFF5252), "ARES"),
        MangaSource("9", "MangaSwat", "N", Color(0xFF1E1335), Color(0xFF2D1D50), Color(0xFFE040FB)),
        MangaSource("10", "UmiManga", "U", Color(0xFF0F223D), Color(0xFF173560), Color(0xFF40C4FF)),
        MangaSource("11", "MangaFlame", "M", Color(0xFF25132B), Color(0xFF381C42), Color(0xFFFF4081)),
        MangaSource("12", "StellarSaber", "S", Color(0xFF0E2238), Color(0xFF163456), Color(0xFF00E5FF)),
        MangaSource("13", "3Asq", "3", Color(0xFF281414), Color(0xFF3F1F1F), Color(0xFFFF5252)),
        MangaSource("14", "LekManga", "L", Color(0xFF2B141E), Color(0xFF441F30), Color(0xFFFF1744)),
        MangaSource("15", "Link-Manga", "M", Color(0xFF282214), Color(0xFF40361F), Color(0xFFFFD700)),
        MangaSource("16", "Like-Manga", "M", Color(0xFF0E282E), Color(0xFF163E48), Color(0xFF1DE9B6))
    )

    val sampleMangaList = listOf(
        MangaItem(
            id = "m1",
            title = "ون بيس (One Piece)",
            sourceName = "3Asq",
            coverGradientStart = Color(0xFF1E3A8A),
            coverGradientEnd = Color(0xFF3B82F6),
            rating = 4.9f,
            totalChapters = 1115,
            lastReadChapter = 1110,
            unreadCount = 5,
            isFavorite = true,
            isReadLater = false,
            genres = listOf("أكشن", "مغامرات", "كوميديا", "شونين"),
            synopsis = "مونكي دي لوفي شاب يبحر في أعالي البحار بحثاً عن الكنز الأسطوري 'ون بيس' ليصبح ملك القراصنة القادم.",
            releaseDate = "اليوم، 18:00"
        ),
        MangaItem(
            id = "m2",
            title = "سولو ليفلينج (Solo Leveling)",
            sourceName = "MangaSwat",
            coverGradientStart = Color(0xFF311042),
            coverGradientEnd = Color(0xFF7C3AED),
            rating = 4.95f,
            totalChapters = 200,
            lastReadChapter = 179,
            unreadCount = 0,
            isFavorite = true,
            isReadLater = false,
            genres = listOf("أكشن", "فانتازيا", "بوابات", "سحر"),
            synopsis = "سونغ جين وو أضعف صياد في البشرية يكتسب قدرة فريدة للترقية المستمرة ليصبح أقوى صياد في العالم.",
            releaseDate = "أمس، 22:15"
        ),
        MangaItem(
            id = "m3",
            title = "جوجيتسو كايسن (Jujutsu Kaisen)",
            sourceName = "ThunderScans",
            coverGradientStart = Color(0xFF1E1B4B),
            coverGradientEnd = Color(0xFF4338CA),
            rating = 4.85f,
            totalChapters = 271,
            lastReadChapter = null,
            unreadCount = 12,
            isFavorite = false,
            isReadLater = true,
            genres = listOf("خوارق", "أكشن", "شياطين", "شونين"),
            synopsis = "يوجي إيتادوري يبتلع إصبعاً ملعوناً لإنقاذ أصدقائه، مما يجعله مضيفاً لملك اللعنات ريومن سوكونا.",
            releaseDate = "منذ يومين"
        ),
        MangaItem(
            id = "m4",
            title = "برج الإله (Tower of God)",
            sourceName = "MangaFlame",
            coverGradientStart = Color(0xFF4C0519),
            coverGradientEnd = Color(0xFFBE123C),
            rating = 4.7f,
            totalChapters = 620,
            lastReadChapter = 350,
            unreadCount = 14,
            isFavorite = true,
            isReadLater = false,
            genres = listOf("مغامرات", "فانتازيا", "غموض", "قوى خارقة"),
            synopsis = "ما الذي ترغب به؟ المال؟ المجد؟ القوة؟ كل ذلك ينتظرك في قمة برج الإله العظيم.",
            releaseDate = "منذ 3 أيام"
        ),
        MangaItem(
            id = "m5",
            title = "بيرسيرك (Berserk)",
            sourceName = "Fl-Ares",
            coverGradientStart = Color(0xFF18181B),
            coverGradientEnd = Color(0xFF3F3F46),
            rating = 4.98f,
            totalChapters = 376,
            lastReadChapter = null,
            unreadCount = 0,
            isFavorite = false,
            isReadLater = true,
            genres = listOf("دارك فانتازيا", "رعب", "مأساة", "سينين"),
            synopsis = "غاتس المبارز الأسود يسعى للانتقام ومحاربة قوى الشياطين المظلمة عبر رحلة دموية شاقة.",
            releaseDate = "الأسبوع الماضي"
        ),
        MangaItem(
            id = "m6",
            title = "كينغدوم (Kingdom)",
            sourceName = "MangaPro",
            coverGradientStart = Color(0xFF0F172A),
            coverGradientEnd = Color(0xFF0284C7),
            rating = 4.92f,
            totalChapters = 800,
            lastReadChapter = null,
            unreadCount = 8,
            isFavorite = false,
            isReadLater = false,
            genres = listOf("حروب", "تاريخي", "عسكري", "سينين"),
            synopsis = "في حقبة الممالك المتحاربة في الصين القديمة، يسعى الشاب شين لأن يصبح أعظم جنرال تحت السماء.",
            releaseDate = "اليوم، 12:00"
        )
    )

    private val _settings = MutableStateFlow(AppSettings())
    val settings: StateFlow<AppSettings> = _settings.asStateFlow()

    private val _mangaList = MutableStateFlow(sampleMangaList)
    val mangaList: StateFlow<List<MangaItem>> = _mangaList.asStateFlow()

    private val _history = MutableStateFlow(
        listOf(
            HistoryItem("h1", sampleMangaList[0], "الفصل 1110: أسرار البحر الأخير", "منذ ساعتين", 0.95f),
            HistoryItem("h2", sampleMangaList[1], "الفصل 179: معركة الملوك العظمى", "منذ يوم", 1.0f)
        )
    )
    val history: StateFlow<List<HistoryItem>> = _history.asStateFlow()

    fun updateSettings(transform: (AppSettings) -> AppSettings) {
        _settings.update(transform)
    }

    fun toggleFavorite(mangaId: String) {
        _mangaList.update { list ->
            list.map { item ->
                if (item.id == mangaId) item.copy(isFavorite = !item.isFavorite) else item
            }
        }
    }

    fun toggleReadLater(mangaId: String) {
        _mangaList.update { list ->
            list.map { item ->
                if (item.id == mangaId) item.copy(isReadLater = !item.isReadLater) else item
            }
        }
    }

    fun clearHistory() {
        _history.value = emptyList()
    }

    fun addToHistory(manga: MangaItem, chapterTitle: String) {
        val newItem = HistoryItem(
            id = "h_${System.currentTimeMillis()}",
            manga = manga,
            chapterTitle = chapterTitle,
            timeAgo = "الآن",
            progress = 0.1f
        )
        _history.update { listOf(newItem) + it.filter { h -> h.manga.id != manga.id } }
    }
}
