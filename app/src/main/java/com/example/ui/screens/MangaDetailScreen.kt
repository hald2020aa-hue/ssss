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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.outlined.Bookmark
import androidx.compose.material.icons.outlined.Download
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.material.icons.outlined.MenuBook
import androidx.compose.material.icons.outlined.Share
import androidx.compose.material.icons.outlined.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.model.MangaItem
import com.example.ui.components.NeonButton
import com.example.ui.components.NeonCard
import com.example.ui.theme.ActiveButtonGradient
import com.example.ui.theme.CairoFontFamily
import com.example.ui.theme.NeonAmber
import com.example.ui.theme.NeonCyan
import com.example.ui.theme.NeonPink
import com.example.ui.theme.TextMuted
import com.example.ui.theme.TextWhite

@Composable
fun MangaDetailScreen(
    manga: MangaItem,
    onBack: () -> Unit,
    onStartReading: () -> Unit,
    onToggleFavorite: () -> Unit,
    onToggleReadLater: () -> Unit,
    modifier: Modifier = Modifier
) {
    CompositionLocalProvider(LocalLayoutDirection provides LayoutDirection.Rtl) {
        Column(
            modifier = modifier
                .fillMaxSize()
        ) {
            // Header Bar
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

                Text(
                    text = "تفاصيل المانجا",
                    fontFamily = CairoFontFamily,
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp,
                    color = TextWhite
                )

                IconButton(onClick = onToggleFavorite) {
                    Icon(
                        imageVector = if (manga.isFavorite) Icons.Filled.Favorite else Icons.Outlined.Favorite,
                        contentDescription = "مفضلة",
                        tint = if (manga.isFavorite) NeonPink else TextWhite
                    )
                }
            }

            LazyColumn(
                contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp),
                verticalArrangement = Arrangement.spacedBy(14.dp),
                modifier = Modifier
                    .fillMaxSize()
                    .navigationBarsPadding()
            ) {
                // Manga Hero Card
                item {
                    NeonCard(
                        modifier = Modifier.fillMaxWidth(),
                        cornerRadius = 20.dp,
                        glowColor = NeonCyan.copy(alpha = 0.6f)
                    ) {
                        Row(modifier = Modifier.fillMaxWidth()) {
                            // Cover art
                            Box(
                                modifier = Modifier
                                    .size(width = 110.dp, height = 155.dp)
                                    .clip(RoundedCornerShape(14.dp))
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
                                    fontSize = 42.sp,
                                    fontWeight = FontWeight.Black,
                                    color = Color.White.copy(alpha = 0.4f)
                                )
                            }

                            Spacer(modifier = Modifier.width(16.dp))

                            Column(modifier = Modifier.weight(1f)) {
                                Text(
                                    text = manga.title,
                                    fontFamily = CairoFontFamily,
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 17.sp,
                                    color = TextWhite,
                                    maxLines = 2,
                                    overflow = TextOverflow.Ellipsis
                                )

                                Spacer(modifier = Modifier.height(4.dp))

                                Text(
                                    text = "المصدر: ${manga.sourceName}",
                                    fontFamily = CairoFontFamily,
                                    fontSize = 12.sp,
                                    color = NeonCyan
                                )

                                Spacer(modifier = Modifier.height(6.dp))

                                Row(verticalAlignment = Alignment.CenterVertically) {
                                    Icon(
                                        imageVector = Icons.Outlined.Star,
                                        contentDescription = null,
                                        tint = NeonAmber,
                                        modifier = Modifier.size(16.dp)
                                    )
                                    Spacer(modifier = Modifier.width(4.dp))
                                    Text(
                                        text = "${manga.rating} / 5.0",
                                        fontFamily = CairoFontFamily,
                                        fontSize = 12.sp,
                                        fontWeight = FontWeight.Bold,
                                        color = NeonAmber
                                    )
                                    Spacer(modifier = Modifier.width(10.dp))
                                    Text(
                                        text = "• ${manga.totalChapters} فصول",
                                        fontFamily = CairoFontFamily,
                                        fontSize = 11.sp,
                                        color = TextMuted
                                    )
                                }

                                Spacer(modifier = Modifier.height(10.dp))

                                // Genres pills
                                Row(horizontalArrangement = Arrangement.spacedBy(6.dp)) {
                                    manga.genres.take(3).forEach { tag ->
                                        Box(
                                            modifier = Modifier
                                                .clip(RoundedCornerShape(6.dp))
                                                .background(Color(0xFF132347))
                                                .border(0.8.dp, NeonCyan.copy(alpha = 0.5f), RoundedCornerShape(6.dp))
                                                .padding(horizontal = 6.dp, vertical = 2.dp)
                                        ) {
                                            Text(
                                                text = tag,
                                                fontFamily = CairoFontFamily,
                                                fontSize = 9.sp,
                                                color = TextWhite
                                            )
                                        }
                                    }
                                }
                            }
                        }
                    }
                }

                // Action Buttons
                item {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(10.dp)
                    ) {
                        NeonButton(
                            text = "ابدأ القراءة",
                            icon = Icons.Outlined.MenuBook,
                            onClick = onStartReading,
                            modifier = Modifier
                                .weight(1f)
                                .testTag("start_reading_button")
                        )

                        Box(
                            modifier = Modifier
                                .height(48.dp)
                                .clip(RoundedCornerShape(24.dp))
                                .background(Color(0xFF0F1E40))
                                .border(1.dp, NeonCyan.copy(alpha = 0.5f), RoundedCornerShape(24.dp))
                                .clickable(onClick = onToggleReadLater)
                                .padding(horizontal = 16.dp),
                            contentAlignment = Alignment.Center
                        ) {
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Icon(
                                    imageVector = Icons.Outlined.Bookmark,
                                    contentDescription = null,
                                    tint = if (manga.isReadLater) NeonCyan else TextWhite,
                                    modifier = Modifier.size(18.dp)
                                )
                                Spacer(modifier = Modifier.width(6.dp))
                                Text(
                                    text = if (manga.isReadLater) "تم الحفظ" else "لاحقاً",
                                    fontFamily = CairoFontFamily,
                                    fontSize = 12.sp,
                                    color = if (manga.isReadLater) NeonCyan else TextWhite
                                )
                            }
                        }
                    }
                }

                // Synopsis
                item {
                    NeonCard(modifier = Modifier.fillMaxWidth()) {
                        Column {
                            Text(
                                text = "القصة والوصف",
                                fontFamily = CairoFontFamily,
                                fontWeight = FontWeight.Bold,
                                fontSize = 15.sp,
                                color = TextWhite
                            )
                            Spacer(modifier = Modifier.height(6.dp))
                            Text(
                                text = manga.synopsis,
                                fontFamily = CairoFontFamily,
                                fontSize = 13.sp,
                                color = TextMuted,
                                lineHeight = 20.sp
                            )
                        }
                    }
                }

                // Chapters list Header
                item {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "الفصول المتاحة (${manga.totalChapters})",
                            fontFamily = CairoFontFamily,
                            fontWeight = FontWeight.Bold,
                            fontSize = 16.sp,
                            color = TextWhite
                        )
                        Text(
                            text = "ترتيب: الأحدث أولاً",
                            fontFamily = CairoFontFamily,
                            fontSize = 11.sp,
                            color = NeonCyan
                        )
                    }
                }

                // Chapter items
                val chapters = (manga.totalChapters downTo (manga.totalChapters - 12).coerceAtLeast(1)).toList()
                items(chapters) { ch ->
                    NeonCard(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable(onClick = onStartReading),
                        cornerRadius = 12.dp
                    ) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Column {
                                Text(
                                    text = "الفصل $ch",
                                    fontFamily = CairoFontFamily,
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 14.sp,
                                    color = TextWhite
                                )
                                Text(
                                    text = "منذ ${ch % 5 + 1} أيام",
                                    fontFamily = CairoFontFamily,
                                    fontSize = 11.sp,
                                    color = TextMuted
                                )
                            }

                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Box(
                                    modifier = Modifier
                                        .size(34.dp)
                                        .clip(CircleShape)
                                        .background(Color(0xFF132347))
                                        .clickable { },
                                    contentAlignment = Alignment.Center
                                ) {
                                    Icon(
                                        imageVector = Icons.Outlined.Download,
                                        contentDescription = "تحميل",
                                        tint = NeonCyan,
                                        modifier = Modifier.size(16.dp)
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}
