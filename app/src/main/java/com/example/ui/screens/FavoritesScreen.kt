package com.example.ui.screens

import androidx.compose.foundation.Image
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
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.outlined.Bookmark
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.material.icons.outlined.GridView
import androidx.compose.material.icons.outlined.History
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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.R
import com.example.data.model.MangaItem
import com.example.ui.components.NeonButton
import com.example.ui.components.NeonCard
import com.example.ui.components.NeonFilterPill
import com.example.ui.theme.CairoFontFamily
import com.example.ui.theme.NeonAmber
import com.example.ui.theme.NeonCyan
import com.example.ui.theme.NeonPink
import com.example.ui.theme.NeonViolet
import com.example.ui.theme.TextMuted
import com.example.ui.theme.TextWhite

@Composable
fun FavoritesScreen(
    currentFilter: String,
    onFilterSelect: (String) -> Unit,
    mangaList: List<MangaItem>,
    onMangaClick: (MangaItem) -> Unit,
    onToggleFavorite: (String) -> Unit,
    onExploreClick: () -> Unit,
    listMode: String = "شبكة",
    modifier: Modifier = Modifier
) {
    CompositionLocalProvider(LocalLayoutDirection provides LayoutDirection.Rtl) {
        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp)
        ) {
            // Horizontal filter tabs matching screenshot 1
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 10.dp),
                horizontalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                NeonFilterPill(
                    text = "كل",
                    isSelected = currentFilter == "كل",
                    icon = Icons.Outlined.GridView,
                    onClick = { onFilterSelect("كل") },
                    modifier = Modifier.weight(1f)
                )
                NeonFilterPill(
                    text = "المفضلة",
                    isSelected = currentFilter == "المفضلة",
                    icon = Icons.Outlined.Favorite,
                    onClick = { onFilterSelect("المفضلة") },
                    modifier = Modifier.weight(1.1f)
                )
                NeonFilterPill(
                    text = "اقرأ لاحقاً",
                    isSelected = currentFilter == "اقرأ لاحقاً",
                    icon = Icons.Outlined.History,
                    onClick = { onFilterSelect("اقرأ لاحقاً") },
                    modifier = Modifier.weight(1.1f)
                )
            }

            Spacer(modifier = Modifier.height(8.dp))

            if (mangaList.isEmpty()) {
                // Empty state matching screenshot 1
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(bottom = 70.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        // Cute glowing mascot cat illustration
                        Image(
                            painter = painterResource(id = R.drawable.mascot_favorites),
                            contentDescription = "قناع المفضلة النيون",
                            modifier = Modifier
                                .size(240.dp)
                                .clip(RoundedCornerShape(24.dp)),
                            contentScale = ContentScale.Fit
                        )

                        Spacer(modifier = Modifier.height(16.dp))

                        Text(
                            text = "يبدو أنه فارغ...",
                            fontFamily = CairoFontFamily,
                            fontWeight = FontWeight.Bold,
                            fontSize = 26.sp,
                            color = TextWhite,
                            textAlign = TextAlign.Center
                        )

                        Spacer(modifier = Modifier.height(4.dp))

                        Text(
                            text = if (currentFilter == "المفضلة") "لا مفضلة بعد" else "لا توجد عناصر في هذا القسم",
                            fontFamily = CairoFontFamily,
                            fontWeight = FontWeight.Medium,
                            fontSize = 14.sp,
                            color = TextMuted,
                            textAlign = TextAlign.Center
                        )

                        Spacer(modifier = Modifier.height(24.dp))

                        NeonButton(
                            text = "اكتشف مانجا جديدة",
                            icon = Icons.Outlined.Favorite,
                            onClick = onExploreClick,
                            modifier = Modifier.testTag("explore_manga_button")
                        )
                    }
                }
            } else {
                // Display manga list
                if (listMode == "شبكة") {
                    LazyVerticalGrid(
                        columns = GridCells.Fixed(2),
                        contentPadding = PaddingValues(bottom = 80.dp),
                        horizontalArrangement = Arrangement.spacedBy(12.dp),
                        verticalArrangement = Arrangement.spacedBy(12.dp),
                        modifier = Modifier.fillMaxSize()
                    ) {
                        items(mangaList, key = { it.id }) { manga ->
                            MangaGridCard(
                                manga = manga,
                                onClick = { onMangaClick(manga) },
                                onToggleFavorite = { onToggleFavorite(manga.id) }
                            )
                        }
                    }
                } else {
                    LazyColumn(
                        contentPadding = PaddingValues(bottom = 80.dp),
                        verticalArrangement = Arrangement.spacedBy(10.dp),
                        modifier = Modifier.fillMaxSize()
                    ) {
                        items(mangaList, key = { it.id }) { manga ->
                            MangaListCard(
                                manga = manga,
                                onClick = { onMangaClick(manga) },
                                onToggleFavorite = { onToggleFavorite(manga.id) }
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun MangaGridCard(
    manga: MangaItem,
    onClick: () -> Unit,
    onToggleFavorite: () -> Unit,
    modifier: Modifier = Modifier
) {
    NeonCard(
        modifier = modifier
            .fillMaxWidth()
            .clickable(onClick = onClick),
        cornerRadius = 16.dp,
        glowColor = if (manga.isFavorite) NeonPink else NeonCyan.copy(alpha = 0.4f)
    ) {
        Column {
            // Stylized Cover
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(160.dp)
                    .clip(RoundedCornerShape(12.dp))
                    .background(
                        Brush.verticalGradient(
                            listOf(manga.coverGradientStart, manga.coverGradientEnd)
                        )
                    ),
                contentAlignment = Alignment.Center
            ) {
                // Source badge top right
                Box(
                    modifier = Modifier
                        .align(Alignment.TopEnd)
                        .padding(6.dp)
                        .clip(RoundedCornerShape(8.dp))
                        .background(Color(0xCC050E26))
                        .border(0.8.dp, NeonCyan, RoundedCornerShape(8.dp))
                        .padding(horizontal = 6.dp, vertical = 2.dp)
                ) {
                    Text(
                        text = manga.sourceName,
                        fontFamily = CairoFontFamily,
                        fontSize = 10.sp,
                        fontWeight = FontWeight.Bold,
                        color = NeonCyan
                    )
                }

                // Favorite button top left
                IconButton(
                    onClick = onToggleFavorite,
                    modifier = Modifier
                        .align(Alignment.TopStart)
                        .padding(2.dp)
                        .size(32.dp)
                ) {
                    Icon(
                        imageVector = if (manga.isFavorite) Icons.Filled.Favorite else Icons.Outlined.Favorite,
                        contentDescription = "مفضلة",
                        tint = if (manga.isFavorite) NeonPink else Color.White.copy(alpha = 0.8f),
                        modifier = Modifier.size(18.dp)
                    )
                }

                // Manga title monogram
                Text(
                    text = manga.title.take(2),
                    fontFamily = CairoFontFamily,
                    fontSize = 44.sp,
                    fontWeight = FontWeight.ExtraBold,
                    color = Color.White.copy(alpha = 0.35f)
                )

                // Chapter count badge at bottom
                Box(
                    modifier = Modifier
                        .align(Alignment.BottomStart)
                        .padding(6.dp)
                        .clip(RoundedCornerShape(6.dp))
                        .background(Color(0xD9050B1B))
                        .padding(horizontal = 6.dp, vertical = 2.dp)
                ) {
                    Text(
                        text = "فصل ${manga.totalChapters}",
                        fontFamily = CairoFontFamily,
                        fontSize = 10.sp,
                        color = TextWhite
                    )
                }
            }

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = manga.title,
                fontFamily = CairoFontFamily,
                fontWeight = FontWeight.Bold,
                fontSize = 14.sp,
                color = TextWhite,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        imageVector = Icons.Outlined.Star,
                        contentDescription = null,
                        tint = NeonAmber,
                        modifier = Modifier.size(14.dp)
                    )
                    Spacer(modifier = Modifier.width(3.dp))
                    Text(
                        text = "${manga.rating}",
                        fontFamily = CairoFontFamily,
                        fontSize = 11.sp,
                        color = NeonAmber,
                        fontWeight = FontWeight.Bold
                    )
                }

                if (manga.unreadCount > 0) {
                    Box(
                        modifier = Modifier
                            .clip(RoundedCornerShape(10.dp))
                            .background(NeonCyan)
                            .padding(horizontal = 6.dp, vertical = 1.dp)
                    ) {
                        Text(
                            text = "+${manga.unreadCount}",
                            fontFamily = CairoFontFamily,
                            fontSize = 10.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.Black
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun MangaListCard(
    manga: MangaItem,
    onClick: () -> Unit,
    onToggleFavorite: () -> Unit,
    modifier: Modifier = Modifier
) {
    NeonCard(
        modifier = modifier
            .fillMaxWidth()
            .clickable(onClick = onClick),
        cornerRadius = 14.dp
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            Box(
                modifier = Modifier
                    .size(width = 65.dp, height = 85.dp)
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
                    fontSize = 24.sp,
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
                    fontSize = 15.sp,
                    color = TextWhite,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
                Text(
                    text = "${manga.sourceName} • ${manga.totalChapters} فصول",
                    fontFamily = CairoFontFamily,
                    fontSize = 12.sp,
                    color = NeonCyan
                )
                Text(
                    text = manga.synopsis,
                    fontFamily = CairoFontFamily,
                    fontSize = 11.sp,
                    color = TextMuted,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
            }

            IconButton(onClick = onToggleFavorite) {
                Icon(
                    imageVector = if (manga.isFavorite) Icons.Filled.Favorite else Icons.Outlined.Favorite,
                    contentDescription = null,
                    tint = if (manga.isFavorite) NeonPink else TextMuted
                )
            }
        }
    }
}
