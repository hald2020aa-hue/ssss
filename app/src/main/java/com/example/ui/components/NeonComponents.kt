package com.example.ui.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.outlined.Check
import androidx.compose.material.icons.outlined.Delete
import androidx.compose.material.icons.outlined.Explore
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.material.icons.outlined.FormatListBulleted
import androidx.compose.material.icons.outlined.GridView
import androidx.compose.material.icons.outlined.History
import androidx.compose.material.icons.outlined.RssFeed
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material.icons.outlined.VisibilityOff
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.material3.ripple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.R
import com.example.ui.MainTab
import com.example.ui.theme.ActiveButtonGradient
import com.example.ui.theme.CairoFontFamily
import com.example.ui.theme.CyberCardBg
import com.example.ui.theme.CyberDarkBg
import com.example.ui.theme.NeonBlue
import com.example.ui.theme.NeonBlueGlow
import com.example.ui.theme.NeonCyan
import com.example.ui.theme.NeonViolet
import com.example.ui.theme.TextMuted
import com.example.ui.theme.TextWhite

@Composable
fun CosmicBackground(
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit
) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(CyberDarkBg)
            .drawBehind {
                // Ambient radial glow top
                drawCircle(
                    brush = Brush.radialGradient(
                        colors = listOf(Color(0x330091EA), Color(0x150052D4), Color.Transparent),
                        center = Offset(size.width * 0.5f, size.height * 0.15f),
                        radius = size.width * 0.8f
                    )
                )
                // Ambient radial glow center bottom
                drawCircle(
                    brush = Brush.radialGradient(
                        colors = listOf(Color(0x287C4DFF), Color(0x102979FF), Color.Transparent),
                        center = Offset(size.width * 0.5f, size.height * 0.65f),
                        radius = size.width * 0.75f
                    )
                )
            }
    ) {
        content()
    }
}

@Composable
fun NeonCard(
    modifier: Modifier = Modifier,
    glowColor: Color = NeonCyan.copy(alpha = 0.5f),
    backgroundColor: Color = CyberCardBg,
    cornerRadius: Dp = 20.dp,
    onClick: (() -> Unit)? = null,
    content: @Composable () -> Unit
) {
    val clickableModifier = if (onClick != null) {
        Modifier.clickable(
            interactionSource = remember { MutableInteractionSource() },
            indication = ripple(color = NeonCyan),
            onClick = onClick
        )
    } else Modifier

    Box(
        modifier = modifier
            .clip(RoundedCornerShape(cornerRadius))
            .border(
                width = 1.2.dp,
                brush = Brush.linearGradient(
                    listOf(
                        glowColor,
                        glowColor.copy(alpha = 0.2f),
                        glowColor.copy(alpha = 0.6f)
                    )
                ),
                shape = RoundedCornerShape(cornerRadius)
            )
            .background(backgroundColor)
            .then(clickableModifier)
            .padding(16.dp)
    ) {
        content()
    }
}

@Composable
fun NeonButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    icon: ImageVector? = null,
    gradient: Brush = ActiveButtonGradient,
    glowColor: Color = NeonBlueGlow
) {
    Box(
        modifier = modifier
            .clip(RoundedCornerShape(32.dp))
            .border(
                width = 1.2.dp,
                color = NeonCyan.copy(alpha = 0.8f),
                shape = RoundedCornerShape(32.dp)
            )
            .background(gradient)
            .clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = ripple(color = Color.White),
                onClick = onClick
            )
            .padding(horizontal = 24.dp, vertical = 12.dp),
        contentAlignment = Alignment.Center
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            if (icon != null) {
                Icon(
                    imageVector = icon,
                    contentDescription = null,
                    tint = TextWhite,
                    modifier = Modifier.size(20.dp)
                )
                Spacer(modifier = Modifier.width(8.dp))
            }
            Text(
                text = text,
                fontFamily = CairoFontFamily,
                fontWeight = FontWeight.Bold,
                fontSize = 15.sp,
                color = TextWhite
            )
        }
    }
}

@Composable
fun NeonFilterPill(
    text: String,
    isSelected: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    icon: ImageVector? = null
) {
    val bgModifier = if (isSelected) {
        Modifier
            .background(
                brush = Brush.horizontalGradient(
                    listOf(Color(0xFF2979FF), Color(0xFF651FFF))
                )
            )
            .border(1.2.dp, NeonCyan, RoundedCornerShape(24.dp))
    } else {
        Modifier
            .background(Color(0x800B1736))
            .border(1.dp, Color(0x401E88E5), RoundedCornerShape(24.dp))
    }

    Box(
        modifier = modifier
            .clip(RoundedCornerShape(24.dp))
            .then(bgModifier)
            .clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = ripple(color = NeonCyan),
                onClick = onClick
            )
            .padding(horizontal = 16.dp, vertical = 8.dp),
        contentAlignment = Alignment.Center
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            if (icon != null) {
                Icon(
                    imageVector = icon,
                    contentDescription = null,
                    tint = if (isSelected) TextWhite else TextMuted,
                    modifier = Modifier.size(18.dp)
                )
                Spacer(modifier = Modifier.width(6.dp))
            }
            Text(
                text = text,
                fontFamily = CairoFontFamily,
                fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Medium,
                fontSize = 13.sp,
                color = if (isSelected) TextWhite else TextMuted
            )
        }
    }
}

@Composable
fun NeonSwitch(
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit,
    modifier: Modifier = Modifier
) {
    Switch(
        checked = checked,
        onCheckedChange = onCheckedChange,
        modifier = modifier,
        colors = SwitchDefaults.colors(
            checkedThumbColor = Color.White,
            checkedTrackColor = Color(0xFF2979FF),
            checkedBorderColor = NeonCyan,
            uncheckedThumbColor = Color(0xFF8E9EB8),
            uncheckedTrackColor = Color(0xFF0F1B38),
            uncheckedBorderColor = Color(0x601E88E5)
        )
    )
}

@Composable
fun NeonTopSearchBar(
    query: String,
    onQueryChange: (String) -> Unit,
    onSearchClick: () -> Unit,
    onMenuClick: () -> Unit,
    isMenuOpen: Boolean,
    onDismissMenu: () -> Unit,
    onClearHistory: () -> Unit,
    onOptionsClick: () -> Unit,
    onToggleIncognito: () -> Unit,
    onSettingsClick: () -> Unit,
    incognitoActive: Boolean = false,
    modifier: Modifier = Modifier
) {
    CompositionLocalProvider(LocalLayoutDirection provides LayoutDirection.Rtl) {
        Box(
            modifier = modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 8.dp)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(54.dp)
                    .clip(RoundedCornerShape(27.dp))
                    .border(
                        width = 1.3.dp,
                        brush = Brush.horizontalGradient(
                            listOf(Color(0xFF00E5FF), Color(0xFF2979FF))
                        ),
                        shape = RoundedCornerShape(27.dp)
                    )
                    .background(Color(0xCC09132C))
                    .padding(horizontal = 6.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                // Search action pill button
                Box(
                    modifier = Modifier
                        .size(42.dp)
                        .clip(CircleShape)
                        .background(
                            brush = Brush.linearGradient(
                                listOf(Color(0xFF0052D4), Color(0xFF2979FF))
                            )
                        )
                        .clickable(onClick = onSearchClick),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = Icons.Default.Search,
                        contentDescription = "بحث",
                        tint = TextWhite,
                        modifier = Modifier.size(20.dp)
                    )
                }

                Spacer(modifier = Modifier.width(10.dp))

                // Text field input
                BasicTextField(
                    value = query,
                    onValueChange = onQueryChange,
                    modifier = Modifier
                        .weight(1f)
                        .testTag("search_input"),
                    textStyle = TextStyle(
                        fontFamily = CairoFontFamily,
                        fontSize = 14.sp,
                        color = TextWhite,
                        textAlign = TextAlign.Start
                    ),
                    singleLine = true,
                    cursorBrush = SolidColor(NeonCyan),
                    decorationBox = { innerTextField ->
                        Box(contentAlignment = Alignment.CenterStart) {
                            if (query.isEmpty()) {
                                Text(
                                    text = if (incognitoActive) "البحث في المانجا (الوضع الخفي)..." else "البحث في المانجا...",
                                    fontFamily = CairoFontFamily,
                                    fontSize = 14.sp,
                                    color = if (incognitoActive) NeonViolet else TextMuted
                                )
                            }
                            innerTextField()
                        }
                    }
                )

                if (query.isNotEmpty()) {
                    IconButton(onClick = { onQueryChange("") }) {
                        Icon(
                            imageVector = Icons.Default.Close,
                            contentDescription = "مسح",
                            tint = TextMuted,
                            modifier = Modifier.size(18.dp)
                        )
                    }
                }

                // 3-dots Menu Button
                Box {
                    IconButton(
                        onClick = onMenuClick,
                        modifier = Modifier.testTag("search_menu_button")
                    ) {
                        Icon(
                            imageVector = Icons.Default.MoreVert,
                            contentDescription = "خيارات",
                            tint = TextWhite
                        )
                    }

                    // Dropdown popup matching screenshot 9
                    DropdownMenu(
                        expanded = isMenuOpen,
                        onDismissRequest = onDismissMenu,
                        modifier = Modifier
                            .background(Color(0xF00B1636))
                            .border(1.dp, NeonCyan.copy(alpha = 0.5f), RoundedCornerShape(12.dp))
                    ) {
                        DropdownMenuItem(
                            text = {
                                Text(
                                    "محو سجل",
                                    fontFamily = CairoFontFamily,
                                    color = TextWhite,
                                    fontSize = 14.sp
                                )
                            },
                            leadingIcon = {
                                Icon(Icons.Outlined.Delete, contentDescription = null, tint = Color(0xFFFF5252))
                            },
                            onClick = onClearHistory
                        )
                        DropdownMenuItem(
                            text = {
                                Text(
                                    "خيارات القائمة",
                                    fontFamily = CairoFontFamily,
                                    color = TextWhite,
                                    fontSize = 14.sp
                                )
                            },
                            leadingIcon = {
                                Icon(Icons.Outlined.FormatListBulleted, contentDescription = null, tint = NeonCyan)
                            },
                            onClick = {
                                onDismissMenu()
                                onOptionsClick()
                            }
                        )
                        DropdownMenuItem(
                            text = {
                                Text(
                                    if (incognitoActive) "تعطيل الوضع الخفي" else "الوضع الخفي",
                                    fontFamily = CairoFontFamily,
                                    color = if (incognitoActive) NeonViolet else TextWhite,
                                    fontSize = 14.sp
                                )
                            },
                            leadingIcon = {
                                Icon(
                                    Icons.Outlined.VisibilityOff,
                                    contentDescription = null,
                                    tint = if (incognitoActive) NeonViolet else TextMuted
                                )
                            },
                            onClick = onToggleIncognito
                        )
                        DropdownMenuItem(
                            text = {
                                Text(
                                    "الإعدادات",
                                    fontFamily = CairoFontFamily,
                                    color = TextWhite,
                                    fontSize = 14.sp
                                )
                            },
                            leadingIcon = {
                                Icon(Icons.Outlined.Settings, contentDescription = null, tint = NeonBlue)
                            },
                            onClick = {
                                onDismissMenu()
                                onSettingsClick()
                            }
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun NeonBottomNavigationBar(
    currentTab: MainTab,
    onTabSelected: (MainTab) -> Unit,
    modifier: Modifier = Modifier
) {
    CompositionLocalProvider(LocalLayoutDirection provides LayoutDirection.Rtl) {
        Surface(
            modifier = modifier
                .fillMaxWidth()
                .navigationBarsPadding(),
            color = Color(0xF2060E24),
            shadowElevation = 16.dp
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .border(
                        width = 1.dp,
                        brush = Brush.verticalGradient(
                            listOf(Color(0x6000E5FF), Color(0x1000E5FF))
                        ),
                        shape = RoundedCornerShape(topStart = 28.dp, topEnd = 28.dp)
                    )
                    .padding(horizontal = 12.dp, vertical = 8.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceAround,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    BottomNavItem(
                        title = "الموجز",
                        icon = Icons.Outlined.RssFeed,
                        isSelected = currentTab == MainTab.FEED,
                        onClick = { onTabSelected(MainTab.FEED) }
                    )
                    BottomNavItem(
                        title = "استكشف",
                        icon = Icons.Outlined.Explore,
                        isSelected = currentTab == MainTab.EXPLORE,
                        onClick = { onTabSelected(MainTab.EXPLORE) }
                    )
                    BottomNavItem(
                        title = "المفضلة",
                        icon = Icons.Outlined.Favorite,
                        isSelected = currentTab == MainTab.FAVORITES,
                        onClick = { onTabSelected(MainTab.FAVORITES) }
                    )
                    BottomNavItem(
                        title = "السجل",
                        icon = Icons.Outlined.History,
                        isSelected = currentTab == MainTab.HISTORY,
                        onClick = { onTabSelected(MainTab.HISTORY) }
                    )
                }
            }
        }
    }
}

@Composable
private fun BottomNavItem(
    title: String,
    icon: ImageVector,
    isSelected: Boolean,
    onClick: () -> Unit
) {
    val pillModifier = if (isSelected) {
        Modifier
            .clip(RoundedCornerShape(20.dp))
            .background(
                brush = Brush.verticalGradient(
                    listOf(Color(0xFF153375), Color(0xFF1E4AB8))
                )
            )
            .border(1.2.dp, Color(0xFF00E5FF), RoundedCornerShape(20.dp))
            .padding(horizontal = 18.dp, vertical = 6.dp)
    } else {
        Modifier
            .padding(horizontal = 14.dp, vertical = 6.dp)
    }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = null,
                onClick = onClick
            )
            .then(pillModifier)
    ) {
        Icon(
            imageVector = icon,
            contentDescription = title,
            tint = if (isSelected) Color.White else TextMuted,
            modifier = Modifier.size(24.dp)
        )
        Text(
            text = title,
            fontFamily = CairoFontFamily,
            fontSize = 11.sp,
            fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Medium,
            color = if (isSelected) Color.White else TextMuted
        )
        if (isSelected) {
            Box(
                modifier = Modifier
                    .padding(top = 2.dp)
                    .size(4.dp)
                    .clip(CircleShape)
                    .background(NeonCyan)
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ListOptionsBottomSheet(
    isOpen: Boolean,
    onDismiss: () -> Unit,
    currentListMode: String,
    onListModeChange: (String) -> Unit,
    gridSize: Float,
    onGridSizeChange: (Float) -> Unit,
    modifier: Modifier = Modifier
) {
    if (isOpen) {
        val sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)
        CompositionLocalProvider(LocalLayoutDirection provides LayoutDirection.Rtl) {
            ModalBottomSheet(
                onDismissRequest = onDismiss,
                sheetState = sheetState,
                containerColor = Color(0xF508122C),
                tonalElevation = 8.dp,
                dragHandle = {
                    Box(
                        modifier = Modifier
                            .padding(vertical = 12.dp)
                            .size(width = 44.dp, height = 4.dp)
                            .clip(RoundedCornerShape(2.dp))
                            .background(NeonCyan.copy(alpha = 0.7f))
                    )
                }
            ) {
                Column(
                    modifier = modifier
                        .fillMaxWidth()
                        .padding(horizontal = 20.dp, vertical = 8.dp)
                        .navigationBarsPadding()
                ) {
                    // Header
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "وضع القائمة",
                            fontFamily = CairoFontFamily,
                            fontWeight = FontWeight.Bold,
                            fontSize = 16.sp,
                            color = TextWhite
                        )
                        Icon(
                            imageVector = Icons.Outlined.GridView,
                            contentDescription = null,
                            tint = NeonCyan,
                            modifier = Modifier.size(20.dp)
                        )
                    }

                    Spacer(modifier = Modifier.height(14.dp))

                    // Modes: شبكة, تفاصيل, المدمج
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clip(RoundedCornerShape(16.dp))
                            .background(Color(0xFF0F1E40))
                            .border(1.dp, Color(0x401E88E5), RoundedCornerShape(16.dp))
                            .padding(4.dp),
                        horizontalArrangement = Arrangement.SpaceEvenly
                    ) {
                        val modes = listOf("شبكة", "تفاصيل", "المدمج")
                        modes.forEach { mode ->
                            val isSelected = currentListMode == mode
                            Box(
                                modifier = Modifier
                                    .weight(1f)
                                    .clip(RoundedCornerShape(12.dp))
                                    .background(
                                        if (isSelected) Brush.horizontalGradient(
                                            listOf(Color(0xFF2979FF), Color(0xFF651FFF))
                                        ) else SolidColor(Color.Transparent)
                                    )
                                    .clickable { onListModeChange(mode) }
                                    .padding(vertical = 10.dp),
                                contentAlignment = Alignment.Center
                            ) {
                                Text(
                                    text = mode,
                                    fontFamily = CairoFontFamily,
                                    fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Medium,
                                    fontSize = 13.sp,
                                    color = if (isSelected) TextWhite else TextMuted
                                )
                            }
                        }
                    }

                    Spacer(modifier = Modifier.height(20.dp))

                    // Grid Size Slider
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "حجم الشبكة",
                            fontFamily = CairoFontFamily,
                            fontWeight = FontWeight.Bold,
                            fontSize = 15.sp,
                            color = TextWhite
                        )
                        Text(
                            text = "${(gridSize * 100).toInt()}%",
                            fontFamily = CairoFontFamily,
                            fontWeight = FontWeight.Bold,
                            fontSize = 13.sp,
                            color = NeonCyan
                        )
                    }

                    Slider(
                        value = gridSize,
                        onValueChange = onGridSizeChange,
                        valueRange = 0.6f..1.4f,
                        steps = 4,
                        colors = SliderDefaults.colors(
                            thumbColor = NeonCyan,
                            activeTrackColor = Color(0xFF2979FF),
                            inactiveTrackColor = Color(0xFF132247)
                        )
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    // Sort Order selector
                    NeonCard(
                        modifier = Modifier.fillMaxWidth(),
                        cornerRadius = 14.dp,
                        backgroundColor = Color(0xFF0F1E40)
                    ) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = "ترتيب الفرز: آخر ما تم قراءته",
                                fontFamily = CairoFontFamily,
                                fontSize = 13.sp,
                                color = TextWhite
                            )
                            Icon(
                                imageVector = Icons.Outlined.History,
                                contentDescription = null,
                                tint = NeonCyan,
                                modifier = Modifier.size(18.dp)
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(12.dp))

                    // Group selector
                    NeonCard(
                        modifier = Modifier.fillMaxWidth(),
                        cornerRadius = 14.dp,
                        backgroundColor = Color(0xFF0F1E40)
                    ) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = "المجموعة: الكل",
                                fontFamily = CairoFontFamily,
                                fontSize = 13.sp,
                                color = TextWhite
                            )
                            Icon(
                                imageVector = Icons.Outlined.GridView,
                                contentDescription = null,
                                tint = NeonCyan,
                                modifier = Modifier.size(18.dp)
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(24.dp))
                }
            }
        }
    }
}
