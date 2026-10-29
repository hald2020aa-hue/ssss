package com.example.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.outlined.Language
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.model.MangaItem
import com.example.data.model.MangaSource
import com.example.ui.components.NeonCard
import com.example.ui.theme.CairoFontFamily
import com.example.ui.theme.NeonCyan
import com.example.ui.theme.TextMuted
import com.example.ui.theme.TextWhite

@Composable
fun SourceExploreScreen(
    source: MangaSource,
    mangaList: List<MangaItem>,
    onBack: () -> Unit,
    onMangaClick: (MangaItem) -> Unit,
    onToggleFavorite: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    CompositionLocalProvider(LocalLayoutDirection provides LayoutDirection.Rtl) {
        Column(
            modifier = modifier
                .fillMaxSize()
        ) {
            // Header
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .statusBarsPadding()
                    .padding(horizontal = 16.dp, vertical = 10.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Box(
                    modifier = Modifier
                        .size(42.dp)
                        .clip(CircleShape)
                        .background(Color(0x800A1838))
                        .border(1.dp, NeonCyan.copy(alpha = 0.5f), CircleShape)
                        .clickable(onClick = onBack),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = "رجوع",
                        tint = TextWhite,
                        modifier = Modifier.size(20.dp)
                    )
                }

                Row(verticalAlignment = Alignment.CenterVertically) {
                    Box(
                        modifier = Modifier
                            .size(34.dp)
                            .clip(RoundedCornerShape(8.dp))
                            .background(
                                Brush.verticalGradient(
                                    listOf(source.bgGradientStart, source.bgGradientEnd)
                                )
                            )
                            .border(1.dp, source.accentColor, RoundedCornerShape(8.dp)),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = source.letterMark,
                            fontFamily = CairoFontFamily,
                            fontWeight = FontWeight.Bold,
                            fontSize = 16.sp,
                            color = source.accentColor
                        )
                    }
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = source.name,
                        fontFamily = CairoFontFamily,
                        fontWeight = FontWeight.Bold,
                        fontSize = 18.sp,
                        color = TextWhite
                    )
                }

                Box(
                    modifier = Modifier
                        .size(42.dp)
                        .clip(CircleShape)
                        .background(Color(0x800A1838)),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = Icons.Outlined.Language,
                        contentDescription = null,
                        tint = NeonCyan,
                        modifier = Modifier.size(20.dp)
                    )
                }
            }

            // Info bar
            NeonCard(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 6.dp),
                cornerRadius = 14.dp
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "مانجا متاحة: ${mangaList.size + 140} عنوان",
                        fontFamily = CairoFontFamily,
                        fontSize = 12.sp,
                        color = TextWhite
                    )
                    Text(
                        text = "اللغة: العربية • الحالة: متصل",
                        fontFamily = CairoFontFamily,
                        fontSize = 11.sp,
                        color = NeonCyan
                    )
                }
            }

            // Manga Grid
            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp),
                horizontalArrangement = Arrangement.spacedBy(12.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp),
                modifier = Modifier
                    .fillMaxSize()
                    .navigationBarsPadding()
            ) {
                items(mangaList, key = { it.id }) { manga ->
                    MangaGridCard(
                        manga = manga,
                        onClick = { onMangaClick(manga) },
                        onToggleFavorite = { onToggleFavorite(manga.id) }
                    )
                }
            }
        }
    }
}
