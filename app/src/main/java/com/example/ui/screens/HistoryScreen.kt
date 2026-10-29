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
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Check
import androidx.compose.material.icons.outlined.Delete
import androidx.compose.material.icons.outlined.Explore
import androidx.compose.material.icons.outlined.PlayArrow
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LinearProgressIndicator
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
import com.example.data.model.HistoryItem
import com.example.ui.components.NeonButton
import com.example.ui.components.NeonCard
import com.example.ui.theme.CairoFontFamily
import com.example.ui.theme.NeonBlue
import com.example.ui.theme.NeonCyan
import com.example.ui.theme.TextMuted
import com.example.ui.theme.TextWhite

@Composable
fun HistoryScreen(
    historyItems: List<HistoryItem>,
    onItemClick: (HistoryItem) -> Unit,
    onExploreClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    CompositionLocalProvider(LocalLayoutDirection provides LayoutDirection.Rtl) {
        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp)
        ) {
            if (historyItems.isEmpty()) {
                // Empty state matching screenshot 9
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
                        Image(
                            painter = painterResource(id = R.drawable.mascot_history),
                            contentDescription = "قناع السجل النيون",
                            modifier = Modifier
                                .size(240.dp)
                                .clip(RoundedCornerShape(24.dp)),
                            contentScale = ContentScale.Fit
                        )

                        Spacer(modifier = Modifier.height(16.dp))

                        Text(
                            text = "كل ما تقرأه سيعرض هنا",
                            fontFamily = CairoFontFamily,
                            fontWeight = FontWeight.Bold,
                            fontSize = 24.sp,
                            color = TextWhite,
                            textAlign = TextAlign.Center
                        )

                        Spacer(modifier = Modifier.height(6.dp))

                        Text(
                            text = "تابع قراءة مانجاك المفضلة،\nوستجدها محفوظة في سجلك تلقائياً.",
                            fontFamily = CairoFontFamily,
                            fontWeight = FontWeight.Medium,
                            fontSize = 14.sp,
                            color = TextMuted,
                            textAlign = TextAlign.Center,
                            lineHeight = 22.sp
                        )

                        Spacer(modifier = Modifier.height(24.dp))

                        NeonButton(
                            text = "ابدأ الاستكشاف الآن",
                            icon = Icons.Outlined.Explore,
                            onClick = onExploreClick,
                            modifier = Modifier.testTag("start_exploring_button")
                        )
                    }
                }
            } else {
                LazyColumn(
                    contentPadding = PaddingValues(top = 10.dp, bottom = 90.dp),
                    verticalArrangement = Arrangement.spacedBy(12.dp),
                    modifier = Modifier.fillMaxSize()
                ) {
                    items(historyItems, key = { it.id }) { item ->
                        HistoryCard(
                            item = item,
                            onClick = { onItemClick(item) }
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun HistoryCard(
    item: HistoryItem,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    NeonCard(
        modifier = modifier
            .fillMaxWidth()
            .clickable(onClick = onClick),
        cornerRadius = 16.dp,
        glowColor = NeonCyan.copy(alpha = 0.4f)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Cover thumbnail
            Box(
                modifier = Modifier
                    .size(width = 65.dp, height = 85.dp)
                    .clip(RoundedCornerShape(12.dp))
                    .background(
                        Brush.verticalGradient(
                            listOf(item.manga.coverGradientStart, item.manga.coverGradientEnd)
                        )
                    ),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = item.manga.title.take(2),
                    fontFamily = CairoFontFamily,
                    fontSize = 26.sp,
                    fontWeight = FontWeight.Black,
                    color = Color.White.copy(alpha = 0.4f)
                )
            }

            Spacer(modifier = Modifier.width(14.dp))

            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = item.manga.title,
                    fontFamily = CairoFontFamily,
                    fontWeight = FontWeight.Bold,
                    fontSize = 15.sp,
                    color = TextWhite,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
                Text(
                    text = item.chapterTitle,
                    fontFamily = CairoFontFamily,
                    fontSize = 12.sp,
                    color = NeonCyan
                )
                Spacer(modifier = Modifier.height(4.dp))

                // Progress Bar
                LinearProgressIndicator(
                    progress = { item.progress },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(5.dp)
                        .clip(RoundedCornerShape(3.dp)),
                    color = NeonCyan,
                    trackColor = Color(0xFF132247),
                )

                Spacer(modifier = Modifier.height(4.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = item.timeAgo,
                        fontFamily = CairoFontFamily,
                        fontSize = 11.sp,
                        color = TextMuted
                    )
                    Text(
                        text = "${(item.progress * 100).toInt()}% مكتمل",
                        fontFamily = CairoFontFamily,
                        fontSize = 11.sp,
                        color = NeonCyan,
                        fontWeight = FontWeight.Bold
                    )
                }
            }

            Spacer(modifier = Modifier.width(8.dp))

            // Play button
            Box(
                modifier = Modifier
                    .size(40.dp)
                    .clip(CircleShape)
                    .background(Brush.linearGradient(listOf(Color(0xFF2979FF), Color(0xFF7C4DFF))))
                    .border(1.dp, NeonCyan, CircleShape),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.Outlined.PlayArrow,
                    contentDescription = "متابعة القراءة",
                    tint = Color.White,
                    modifier = Modifier.size(22.dp)
                )
            }
        }
    }
}
