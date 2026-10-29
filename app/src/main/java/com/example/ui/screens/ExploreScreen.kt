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
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Bookmark
import androidx.compose.material.icons.outlined.Casino
import androidx.compose.material.icons.outlined.Download
import androidx.compose.material.icons.outlined.Folder
import androidx.compose.material.icons.outlined.GridView
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.model.MangaSource
import com.example.ui.components.NeonCard
import com.example.ui.theme.CairoFontFamily
import com.example.ui.theme.NeonCyan
import com.example.ui.theme.TextMuted
import com.example.ui.theme.TextWhite

@Composable
fun ExploreScreen(
    sources: List<MangaSource>,
    onSourceClick: (MangaSource) -> Unit,
    onQuickActionClick: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    CompositionLocalProvider(LocalLayoutDirection provides LayoutDirection.Rtl) {
        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp)
        ) {
            // Quick action 2x2 grid matching screenshot 10
            Column(
                verticalArrangement = Arrangement.spacedBy(10.dp),
                modifier = Modifier.padding(vertical = 8.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    QuickActionPill(
                        title = "التخزين المحلي",
                        icon = Icons.Outlined.Folder,
                        gradient = Brush.horizontalGradient(
                            listOf(Color(0xFF2E1C6A), Color(0xFF1E3A8A))
                        ),
                        borderColor = NeonCyan,
                        onClick = { onQuickActionClick("التخزين المحلي") },
                        modifier = Modifier.weight(1f)
                    )
                    QuickActionPill(
                        title = "المحفوظات",
                        icon = Icons.Outlined.Bookmark,
                        gradient = Brush.horizontalGradient(
                            listOf(Color(0xFF0F2244), Color(0xFF153466))
                        ),
                        borderColor = Color(0xFF00B0FF),
                        onClick = { onQuickActionClick("المحفوظات") },
                        modifier = Modifier.weight(1f)
                    )
                }

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    QuickActionPill(
                        title = "عشوائي",
                        icon = Icons.Outlined.Casino,
                        gradient = Brush.horizontalGradient(
                            listOf(Color(0xFF0A2242), Color(0xFF133B6C))
                        ),
                        borderColor = Color(0xFF2979FF),
                        onClick = { onQuickActionClick("عشوائي") },
                        modifier = Modifier.weight(1f)
                    )
                    QuickActionPill(
                        title = "التحميلات",
                        icon = Icons.Outlined.Download,
                        gradient = Brush.horizontalGradient(
                            listOf(Color(0xFF0C2448), Color(0xFF163E78))
                        ),
                        borderColor = Color(0xFF00E5FF),
                        onClick = { onQuickActionClick("التحميلات") },
                        modifier = Modifier.weight(1f)
                    )
                }
            }

            Spacer(modifier = Modifier.height(12.dp))

            // Section Header matching screenshot 10
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Box(
                        modifier = Modifier
                            .width(3.dp)
                            .height(18.dp)
                            .background(NeonCyan)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = "مصادر المانجا",
                        fontFamily = CairoFontFamily,
                        fontWeight = FontWeight.Bold,
                        fontSize = 18.sp,
                        color = TextWhite
                    )
                }

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .clip(RoundedCornerShape(12.dp))
                        .background(Color(0x800A1838))
                        .border(1.dp, Color(0x401E88E5), RoundedCornerShape(12.dp))
                        .padding(horizontal = 8.dp, vertical = 4.dp)
                ) {
                    Text(
                        text = "القائمة",
                        fontFamily = CairoFontFamily,
                        fontSize = 12.sp,
                        color = TextMuted
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Icon(
                        imageVector = Icons.Outlined.GridView,
                        contentDescription = null,
                        tint = NeonCyan,
                        modifier = Modifier.size(16.dp)
                    )
                }
            }

            Spacer(modifier = Modifier.height(12.dp))

            // Manga Sources 4-column Grid matching screenshot 10
            LazyVerticalGrid(
                columns = GridCells.Fixed(4),
                contentPadding = PaddingValues(bottom = 90.dp),
                horizontalArrangement = Arrangement.spacedBy(10.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp),
                modifier = Modifier.fillMaxSize()
            ) {
                items(sources, key = { it.id }) { source ->
                    SourceTile(
                        source = source,
                        onClick = { onSourceClick(source) }
                    )
                }
            }
        }
    }
}

@Composable
fun QuickActionPill(
    title: String,
    icon: ImageVector,
    gradient: Brush,
    borderColor: Color,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .height(50.dp)
            .clip(RoundedCornerShape(16.dp))
            .border(1.2.dp, borderColor.copy(alpha = 0.8f), RoundedCornerShape(16.dp))
            .background(gradient)
            .clickable(onClick = onClick)
            .padding(horizontal = 12.dp),
        contentAlignment = Alignment.Center
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = title,
                fontFamily = CairoFontFamily,
                fontWeight = FontWeight.Bold,
                fontSize = 13.sp,
                color = TextWhite
            )
            Box(
                modifier = Modifier
                    .size(34.dp)
                    .clip(CircleShape)
                    .background(Color(0x33000000))
                    .border(0.8.dp, borderColor.copy(alpha = 0.5f), CircleShape),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = icon,
                    contentDescription = null,
                    tint = borderColor,
                    modifier = Modifier.size(18.dp)
                )
            }
        }
    }
}

@Composable
fun SourceTile(
    source: MangaSource,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .clip(RoundedCornerShape(14.dp))
            .clickable(onClick = onClick)
    ) {
        // Tile container with glowing neon border
        Box(
            modifier = Modifier
                .size(76.dp)
                .clip(RoundedCornerShape(18.dp))
                .border(
                    width = 1.3.dp,
                    color = source.accentColor.copy(alpha = 0.8f),
                    shape = RoundedCornerShape(18.dp)
                )
                .background(
                    Brush.verticalGradient(
                        listOf(source.bgGradientStart, source.bgGradientEnd)
                    )
                ),
            contentAlignment = Alignment.Center
        ) {
            // Neon letter mark or custom symbol
            Text(
                text = source.letterMark,
                fontFamily = CairoFontFamily,
                fontWeight = FontWeight.Black,
                fontSize = 32.sp,
                color = source.accentColor,
                textAlign = TextAlign.Center
            )
        }

        Spacer(modifier = Modifier.height(4.dp))

        Text(
            text = source.name,
            fontFamily = CairoFontFamily,
            fontWeight = FontWeight.Medium,
            fontSize = 11.sp,
            color = TextWhite,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            textAlign = TextAlign.Center
        )
    }
}
