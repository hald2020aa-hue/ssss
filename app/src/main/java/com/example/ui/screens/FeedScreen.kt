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
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Bolt
import androidx.compose.material.icons.outlined.Check
import androidx.compose.material.icons.outlined.MenuBook
import androidx.compose.material.icons.outlined.Notifications
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
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.model.MangaItem
import com.example.ui.components.NeonCard
import com.example.ui.theme.CairoFontFamily
import com.example.ui.theme.NeonAmber
import com.example.ui.theme.NeonCyan
import com.example.ui.theme.NeonViolet
import com.example.ui.theme.TextMuted
import com.example.ui.theme.TextWhite

@Composable
fun FeedScreen(
    mangaList: List<MangaItem>,
    onMangaClick: (MangaItem) -> Unit,
    onReadChapter: (MangaItem) -> Unit,
    modifier: Modifier = Modifier
) {
    CompositionLocalProvider(LocalLayoutDirection provides LayoutDirection.Rtl) {
        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp)
        ) {
            // Hero banner: New updates
            NeonCard(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 10.dp),
                cornerRadius = 20.dp,
                glowColor = NeonCyan.copy(alpha = 0.6f)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Box(
                        modifier = Modifier
                            .size(48.dp)
                            .clip(CircleShape)
                            .background(
                                Brush.linearGradient(listOf(NeonCyan, Color(0xFF2979FF)))
                            ),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            imageVector = Icons.Outlined.Bolt,
                            contentDescription = null,
                            tint = Color.Black,
                            modifier = Modifier.size(28.dp)
                        )
                    }

                    Spacer(modifier = Modifier.width(14.dp))

                    Column(modifier = Modifier.weight(1f)) {
                        Text(
                            text = "آخر التحديثات والفصول الجديدة",
                            fontFamily = CairoFontFamily,
                            fontWeight = FontWeight.Bold,
                            fontSize = 15.sp,
                            color = TextWhite
                        )
                        Text(
                            text = "تم جلب أحدث 18 فصلاً من مصادرك المفضلة",
                            fontFamily = CairoFontFamily,
                            fontSize = 12.sp,
                            color = NeonCyan
                        )
                    }
                }
            }

            Text(
                text = "الفصول المضافة حديثاً",
                fontFamily = CairoFontFamily,
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp,
                color = TextWhite,
                modifier = Modifier.padding(vertical = 8.dp)
            )

            LazyColumn(
                contentPadding = PaddingValues(bottom = 90.dp),
                verticalArrangement = Arrangement.spacedBy(10.dp),
                modifier = Modifier.fillMaxSize()
            ) {
                items(mangaList, key = { it.id }) { manga ->
                    FeedChapterCard(
                        manga = manga,
                        onClick = { onMangaClick(manga) },
                        onRead = { onReadChapter(manga) }
                    )
                }
            }
        }
    }
}

@Composable
fun FeedChapterCard(
    manga: MangaItem,
    onClick: () -> Unit,
    onRead: () -> Unit,
    modifier: Modifier = Modifier
) {
    NeonCard(
        modifier = modifier
            .fillMaxWidth()
            .clickable(onClick = onClick),
        cornerRadius = 14.dp,
        glowColor = Color(0x501E88E5)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(width = 60.dp, height = 75.dp)
                    .clip(RoundedCornerShape(10.dp))
                    .background(
                        Brush.verticalGradient(
                            listOf(manga.coverGradientStart, manga.coverGradientEnd)
                        )
                    ),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = manga.title.take(2),
                    fontFamily = CairoFontFamily,
                    fontSize = 22.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White.copy(alpha = 0.5f)
                )
            }

            Spacer(modifier = Modifier.width(12.dp))

            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = manga.title,
                    fontFamily = CairoFontFamily,
                    fontWeight = FontWeight.Bold,
                    fontSize = 14.sp,
                    color = TextWhite,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
                Text(
                    text = "الفصل ${manga.totalChapters} • ${manga.sourceName}",
                    fontFamily = CairoFontFamily,
                    fontSize = 12.sp,
                    color = NeonCyan
                )
                Text(
                    text = manga.releaseDate,
                    fontFamily = CairoFontFamily,
                    fontSize = 11.sp,
                    color = TextMuted
                )
            }

            // Quick Read pill
            Box(
                modifier = Modifier
                    .clip(RoundedCornerShape(14.dp))
                    .background(
                        Brush.horizontalGradient(
                            listOf(Color(0xFF2979FF), Color(0xFF7C4DFF))
                        )
                    )
                    .border(1.dp, NeonCyan, RoundedCornerShape(14.dp))
                    .clickable(onClick = onRead)
                    .padding(horizontal = 14.dp, vertical = 6.dp),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "اقرأ",
                    fontFamily = CairoFontFamily,
                    fontWeight = FontWeight.Bold,
                    fontSize = 12.sp,
                    color = Color.White
                )
            }
        }
    }
}
