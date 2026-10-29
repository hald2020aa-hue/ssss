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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.automirrored.outlined.ArrowBack
import androidx.compose.material.icons.automirrored.outlined.ArrowForward
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.outlined.ArrowBack
import androidx.compose.material.icons.outlined.ArrowForward
import androidx.compose.material.icons.outlined.Backup
import androidx.compose.material.icons.outlined.Block
import androidx.compose.material.icons.outlined.CloudDownload
import androidx.compose.material.icons.outlined.CloudUpload
import androidx.compose.material.icons.outlined.ColorLens
import androidx.compose.material.icons.outlined.DarkMode
import androidx.compose.material.icons.outlined.Delete
import androidx.compose.material.icons.outlined.Download
import androidx.compose.material.icons.outlined.Extension
import androidx.compose.material.icons.outlined.Folder
import androidx.compose.material.icons.outlined.GridView
import androidx.compose.material.icons.outlined.History
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material.icons.outlined.Language
import androidx.compose.material.icons.outlined.MenuBook
import androidx.compose.material.icons.outlined.Notifications
import androidx.compose.material.icons.outlined.Palette
import androidx.compose.material.icons.outlined.QuestionAnswer
import androidx.compose.material.icons.outlined.Refresh
import androidx.compose.material.icons.outlined.Security
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material.icons.outlined.Storage
import androidx.compose.material.icons.outlined.VpnKey
import androidx.compose.material.icons.outlined.Wifi
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.model.AppSettings
import com.example.ui.SubScreen
import com.example.ui.components.NeonCard
import com.example.ui.components.NeonSwitch
import com.example.ui.theme.CairoFontFamily
import com.example.ui.theme.NeonAmber
import com.example.ui.theme.NeonBlue
import com.example.ui.theme.NeonColorSchemeType
import com.example.ui.theme.NeonCyan
import com.example.ui.theme.NeonGreen
import com.example.ui.theme.NeonPink
import com.example.ui.theme.NeonRed
import com.example.ui.theme.NeonViolet
import com.example.ui.theme.TextMuted
import com.example.ui.theme.TextWhite

@Composable
fun SettingsTopBar(
    title: String,
    subtitle: String,
    onBackClick: () -> Unit,
    showGear: Boolean = true,
    modifier: Modifier = Modifier
) {
    CompositionLocalProvider(LocalLayoutDirection provides LayoutDirection.Rtl) {
        Row(
            modifier = modifier
                .fillMaxWidth()
                .statusBarsPadding()
                .padding(horizontal = 16.dp, vertical = 10.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            // Back Button
            Box(
                modifier = Modifier
                    .size(42.dp)
                    .clip(CircleShape)
                    .background(Color(0x800A1838))
                    .border(1.dp, NeonCyan.copy(alpha = 0.5f), CircleShape)
                    .clickable(onClick = onBackClick),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = "رجوع",
                    tint = TextWhite,
                    modifier = Modifier.size(20.dp)
                )
            }

            // Titles
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text(
                    text = title,
                    fontFamily = CairoFontFamily,
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp,
                    color = TextWhite
                )
                if (subtitle.isNotEmpty()) {
                    Text(
                        text = subtitle,
                        fontFamily = CairoFontFamily,
                        fontSize = 11.sp,
                        color = TextMuted
                    )
                }
            }

            // Gear icon indicator
            if (showGear) {
                Box(
                    modifier = Modifier
                        .size(42.dp)
                        .clip(CircleShape)
                        .background(
                            Brush.linearGradient(listOf(Color(0xFF1E3A8A), Color(0xFF2563EB)))
                        )
                        .border(1.2.dp, NeonCyan, CircleShape),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = Icons.Outlined.Settings,
                        contentDescription = null,
                        tint = TextWhite,
                        modifier = Modifier.size(22.dp)
                    )
                }
            } else {
                Spacer(modifier = Modifier.size(42.dp))
            }
        }
    }
}

// 1. Settings Main Hub Screen (Matching Screenshot 7)
@Composable
fun SettingsHomeScreen(
    onNavigate: (SubScreen) -> Unit,
    onBack: () -> Unit,
    modifier: Modifier = Modifier
) {
    CompositionLocalProvider(LocalLayoutDirection provides LayoutDirection.Rtl) {
        Column(
            modifier = modifier
                .fillMaxSize()
        ) {
            SettingsTopBar(
                title = "الإعدادات",
                subtitle = "خصص تجربتك كما تحب",
                onBackClick = onBack
            )

            LazyColumn(
                contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp),
                verticalArrangement = Arrangement.spacedBy(10.dp),
                modifier = Modifier
                    .fillMaxSize()
                    .navigationBarsPadding()
            ) {
                item {
                    SettingsHubItem(
                        title = "مظهر",
                        description = "مظهر التطبيق، وضع القائمة، اللغة",
                        icon = Icons.Outlined.Palette,
                        iconBg = Color(0xFF1E40AF),
                        onClick = { onNavigate(SubScreen.APPEARANCE_SETTINGS) }
                    )
                }
                item {
                    SettingsHubItem(
                        title = "مصادر المانجا",
                        description = "تمكين 444 من 882 مصدر",
                        icon = Icons.Outlined.MenuBook,
                        iconBg = Color(0xFF1E3A8A),
                        onClick = { onNavigate(SubScreen.SOURCE_EXPLORE) }
                    )
                }
                item {
                    SettingsHubItem(
                        title = "إعدادات القارئ",
                        description = "وضع القراءة، وضع القياس، تغيير صفحات",
                        icon = Icons.Outlined.MenuBook,
                        iconBg = Color(0xFF1E3A8A),
                        onClick = { onNavigate(SubScreen.READER_SETTINGS) }
                    )
                }
                item {
                    SettingsHubItem(
                        title = "Storage and network",
                        description = "استهلاك التخزين، الوكيل، اعادة تحميل المحتوى",
                        icon = Icons.Outlined.Storage,
                        iconBg = Color(0xFF1E3A8A),
                        onClick = { onNavigate(SubScreen.STORAGE_NETWORK_SETTINGS) }
                    )
                }
                item {
                    SettingsHubItem(
                        title = "التحميلات",
                        description = "مجلد التحميلات، التحميل عبر شبكة الواي فاي فقط",
                        icon = Icons.Outlined.Download,
                        iconBg = Color(0xFF1E3A8A),
                        onClick = { onNavigate(SubScreen.DOWNLOADS_SETTINGS) }
                    )
                }
                item {
                    SettingsHubItem(
                        title = "التحقق من الفصول الجديدة",
                        description = "البحث عن تحديثات، إعدادات الإشعارات",
                        icon = Icons.Outlined.Notifications,
                        iconBg = Color(0xFF1E3A8A),
                        onClick = { }
                    )
                }
                item {
                    SettingsHubItem(
                        title = "خدمات",
                        description = "الاقتراحات، التزامن، تتبع",
                        icon = Icons.Outlined.Extension,
                        iconBg = Color(0xFF1E3A8A),
                        onClick = { }
                    )
                }
                item {
                    SettingsHubItem(
                        title = "النسخ الاحتياطي و الاستعادة",
                        description = "النسخ الاحتياطي الدوري",
                        icon = Icons.Outlined.CloudUpload,
                        iconBg = Color(0xFF1E3A8A),
                        onClick = { onNavigate(SubScreen.BACKUP_RESTORE_SETTINGS) }
                    )
                }
            }
        }
    }
}

@Composable
fun SettingsHubItem(
    title: String,
    description: String,
    icon: ImageVector,
    iconBg: Color,
    onClick: () -> Unit
) {
    NeonCard(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick),
        cornerRadius = 16.dp,
        glowColor = NeonCyan.copy(alpha = 0.4f)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            // Icon on right in RTL
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.weight(1f)
            ) {
                Box(
                    modifier = Modifier
                        .size(46.dp)
                        .clip(RoundedCornerShape(14.dp))
                        .background(
                            Brush.linearGradient(listOf(iconBg, iconBg.copy(alpha = 0.7f)))
                        )
                        .border(1.2.dp, NeonCyan, RoundedCornerShape(14.dp)),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = icon,
                        contentDescription = null,
                        tint = TextWhite,
                        modifier = Modifier.size(24.dp)
                    )
                }

                Spacer(modifier = Modifier.width(14.dp))

                Column {
                    Text(
                        text = title,
                        fontFamily = CairoFontFamily,
                        fontWeight = FontWeight.Bold,
                        fontSize = 15.sp,
                        color = TextWhite
                    )
                    Text(
                        text = description,
                        fontFamily = CairoFontFamily,
                        fontSize = 12.sp,
                        color = TextMuted,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                }
            }

            // Arrow on left in RTL
            Box(
                modifier = Modifier
                    .size(32.dp)
                    .clip(CircleShape)
                    .background(Color(0x400A1838)),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = null,
                    tint = TextMuted,
                    modifier = Modifier.size(16.dp)
                )
            }
        }
    }
}

// 2. Appearance Settings Screen (Matching Screenshot 2)
@Composable
fun AppearanceSettingsScreen(
    settings: AppSettings,
    onUpdate: ((AppSettings) -> AppSettings) -> Unit,
    onBack: () -> Unit,
    modifier: Modifier = Modifier
) {
    CompositionLocalProvider(LocalLayoutDirection provides LayoutDirection.Rtl) {
        Column(modifier = modifier.fillMaxSize()) {
            SettingsTopBar(
                title = "الإعدادات",
                subtitle = "خصص مظهر التطبيق حسب ذوقك",
                onBackClick = onBack
            )

            LazyColumn(
                contentPadding = PaddingValues(horizontal = 16.dp, vertical = 6.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp),
                modifier = Modifier
                    .fillMaxSize()
                    .navigationBarsPadding()
            ) {
                // Color Scheme Selection Cards
                item {
                    NeonCard(modifier = Modifier.fillMaxWidth()) {
                        Column {
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Column {
                                    Text(
                                        text = "مخطط الألوان",
                                        fontFamily = CairoFontFamily,
                                        fontWeight = FontWeight.Bold,
                                        fontSize = 15.sp,
                                        color = TextWhite
                                    )
                                    Text(
                                        text = "اختر المظهر العام للتطبيق",
                                        fontFamily = CairoFontFamily,
                                        fontSize = 11.sp,
                                        color = TextMuted
                                    )
                                }
                                Icon(
                                    imageVector = Icons.Outlined.Palette,
                                    contentDescription = null,
                                    tint = NeonCyan,
                                    modifier = Modifier.size(20.dp)
                                )
                            }

                            Spacer(modifier = Modifier.height(14.dp))

                            // Palette Grid
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.spacedBy(8.dp)
                            ) {
                                NeonColorSchemeType.entries.forEach { scheme ->
                                    val isSelected = settings.colorScheme == scheme
                                    Column(
                                        horizontalAlignment = Alignment.CenterHorizontally,
                                        modifier = Modifier
                                            .weight(1f)
                                            .clickable {
                                                onUpdate { it.copy(colorScheme = scheme) }
                                            }
                                    ) {
                                        Box(
                                            modifier = Modifier
                                                .size(width = 46.dp, height = 64.dp)
                                                .clip(RoundedCornerShape(12.dp))
                                                .background(Color(0xFF0F1E40))
                                                .border(
                                                    width = if (isSelected) 1.5.dp else 0.8.dp,
                                                    color = if (isSelected) NeonCyan else Color(0x401E88E5),
                                                    shape = RoundedCornerShape(12.dp)
                                                )
                                                .padding(4.dp),
                                            contentAlignment = Alignment.TopCenter
                                        ) {
                                            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                                                if (isSelected) {
                                                    Icon(
                                                        imageVector = Icons.Default.Check,
                                                        contentDescription = null,
                                                        tint = NeonCyan,
                                                        modifier = Modifier.size(14.dp)
                                                    )
                                                } else {
                                                    Text(
                                                        "Aa",
                                                        fontFamily = CairoFontFamily,
                                                        fontSize = 10.sp,
                                                        color = TextMuted
                                                    )
                                                }
                                                Spacer(modifier = Modifier.height(6.dp))
                                                Box(
                                                    modifier = Modifier
                                                        .size(width = 24.dp, height = 4.dp)
                                                        .clip(RoundedCornerShape(2.dp))
                                                        .background(scheme.primaryColor)
                                                )
                                                Spacer(modifier = Modifier.height(4.dp))
                                                Box(
                                                    modifier = Modifier
                                                        .size(width = 16.dp, height = 3.dp)
                                                        .clip(RoundedCornerShape(2.dp))
                                                        .background(scheme.primaryColor.copy(alpha = 0.6f))
                                                )
                                            }
                                        }

                                        Spacer(modifier = Modifier.height(4.dp))

                                        Text(
                                            text = scheme.displayNameAr,
                                            fontFamily = CairoFontFamily,
                                            fontSize = 9.sp,
                                            color = if (isSelected) TextWhite else TextMuted,
                                            maxLines = 1
                                        )
                                    }
                                }
                            }
                        }
                    }
                }

                // Theme Mode Segment (حسب النظام, داكن, فاتح)
                item {
                    NeonCard(modifier = Modifier.fillMaxWidth()) {
                        Column {
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Column {
                                    Text(
                                        text = "مظهر",
                                        fontFamily = CairoFontFamily,
                                        fontWeight = FontWeight.Bold,
                                        fontSize = 15.sp,
                                        color = TextWhite
                                    )
                                    Text(
                                        text = "اختيار مظهر التطبيق العام",
                                        fontFamily = CairoFontFamily,
                                        fontSize = 11.sp,
                                        color = TextMuted
                                    )
                                }
                                Icon(
                                    imageVector = Icons.Outlined.DarkMode,
                                    contentDescription = null,
                                    tint = NeonCyan,
                                    modifier = Modifier.size(20.dp)
                                )
                            }

                            Spacer(modifier = Modifier.height(12.dp))

                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .clip(RoundedCornerShape(14.dp))
                                    .background(Color(0xFF0F1E40))
                                    .padding(4.dp),
                                horizontalArrangement = Arrangement.SpaceEvenly
                            ) {
                                listOf("فاتح", "داكن", "حسب النظام").forEach { mode ->
                                    val isSelected = settings.themeMode == mode
                                    Box(
                                        modifier = Modifier
                                            .weight(1f)
                                            .clip(RoundedCornerShape(10.dp))
                                            .background(
                                                if (isSelected) Brush.horizontalGradient(
                                                    listOf(Color(0xFF1E3A8A), Color(0xFF2563EB))
                                                ) else SolidColor(Color.Transparent)
                                            )
                                            .border(
                                                width = if (isSelected) 1.dp else 0.dp,
                                                color = if (isSelected) NeonCyan else Color.Transparent,
                                                shape = RoundedCornerShape(10.dp)
                                            )
                                            .clickable {
                                                onUpdate { it.copy(themeMode = mode) }
                                            }
                                            .padding(vertical = 8.dp),
                                        contentAlignment = Alignment.Center
                                    ) {
                                        Text(
                                            text = mode,
                                            fontFamily = CairoFontFamily,
                                            fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Medium,
                                            fontSize = 12.sp,
                                            color = if (isSelected) TextWhite else TextMuted
                                        )
                                    }
                                }
                            }
                        }
                    }
                }

                // Language
                item {
                    NeonCard(modifier = Modifier.fillMaxWidth()) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Icon(
                                    imageVector = Icons.Outlined.Language,
                                    contentDescription = null,
                                    tint = NeonCyan,
                                    modifier = Modifier.size(20.dp)
                                )
                                Spacer(modifier = Modifier.width(10.dp))
                                Column {
                                    Text(
                                        text = "اللغة",
                                        fontFamily = CairoFontFamily,
                                        fontWeight = FontWeight.Bold,
                                        fontSize = 14.sp,
                                        color = TextWhite
                                    )
                                    Text(
                                        text = "اختر لغة واجهة التطبيق",
                                        fontFamily = CairoFontFamily,
                                        fontSize = 11.sp,
                                        color = TextMuted
                                    )
                                }
                            }

                            Text(
                                text = settings.language,
                                fontFamily = CairoFontFamily,
                                fontWeight = FontWeight.Bold,
                                fontSize = 13.sp,
                                color = NeonCyan
                            )
                        }
                    }
                }

                // Grid Size
                item {
                    NeonCard(modifier = Modifier.fillMaxWidth()) {
                        Column {
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween
                            ) {
                                Column {
                                    Text(
                                        text = "حجم الشبكة",
                                        fontFamily = CairoFontFamily,
                                        fontWeight = FontWeight.Bold,
                                        fontSize = 14.sp,
                                        color = TextWhite
                                    )
                                    Text(
                                        text = "تعديل حجم العناصر في المكتبة",
                                        fontFamily = CairoFontFamily,
                                        fontSize = 11.sp,
                                        color = TextMuted
                                    )
                                }
                                Text(
                                    text = "${(settings.gridSize * 100).toInt()}%",
                                    fontFamily = CairoFontFamily,
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 13.sp,
                                    color = NeonCyan
                                )
                            }
                            Slider(
                                value = settings.gridSize,
                                onValueChange = { size -> onUpdate { it.copy(gridSize = size) } },
                                valueRange = 0.6f..1.4f,
                                colors = SliderDefaults.colors(
                                    thumbColor = NeonCyan,
                                    activeTrackColor = Color(0xFF2979FF),
                                    inactiveTrackColor = Color(0xFF132247)
                                )
                            )
                        }
                    }
                }

                // Reading Options Toggles
                item {
                    NeonCard(modifier = Modifier.fillMaxWidth()) {
                        Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
                            Text(
                                text = "خيارات القراءة",
                                fontFamily = CairoFontFamily,
                                fontWeight = FontWeight.Bold,
                                fontSize = 14.sp,
                                color = TextWhite
                            )
                            SettingToggleRow(
                                title = "إظهار عوامل التصفية السريعة",
                                subtitle = "إظهار عوامل التصفية السريعة فوق لوحة المحتوى",
                                checked = settings.showQuickFilters,
                                onCheckedChange = { v -> onUpdate { it.copy(showQuickFilters = v) } }
                            )
                            SettingToggleRow(
                                title = "إظهار مؤشرات التقدم في القراءة",
                                subtitle = "عرض نسبة القراءة على العناصر",
                                checked = settings.showReadingProgress,
                                onCheckedChange = { v -> onUpdate { it.copy(showReadingProgress = v) } }
                            )
                            SettingToggleRow(
                                title = "إظهار الإشارات في القوائم",
                                subtitle = "العلامات المحفوظة في الفصول والقوائم",
                                checked = settings.showBookmarksInLists,
                                onCheckedChange = { v -> onUpdate { it.copy(showBookmarksInLists = v) } }
                            )
                        }
                    }
                }

                // Main Screen Toggles
                item {
                    NeonCard(modifier = Modifier.fillMaxWidth()) {
                        Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
                            Text(
                                text = "الشاشة الرئيسية",
                                fontFamily = CairoFontFamily,
                                fontWeight = FontWeight.Bold,
                                fontSize = 14.sp,
                                color = TextWhite
                            )
                            SettingToggleRow(
                                title = "إظهار قسم المتابعة المستمرة",
                                subtitle = "عرض زر المتابعة العائم في الشاشة الرئيسية",
                                checked = settings.showContinueReading,
                                onCheckedChange = { v -> onUpdate { it.copy(showContinueReading = v) } }
                            )
                            SettingToggleRow(
                                title = "إظهار أحدث التحديثات",
                                subtitle = "عرض أحدث الإضافات والمصادر الجديدة",
                                checked = settings.showLatestUpdates,
                                onCheckedChange = { v -> onUpdate { it.copy(showLatestUpdates = v) } }
                            )
                        }
                    }
                }

                // Privacy Toggles
                item {
                    NeonCard(modifier = Modifier.fillMaxWidth()) {
                        Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
                            Text(
                                text = "الخصوصية",
                                fontFamily = CairoFontFamily,
                                fontWeight = FontWeight.Bold,
                                fontSize = 14.sp,
                                color = TextWhite
                            )
                            SettingToggleRow(
                                title = "تثبيت واجهة المستخدم",
                                subtitle = "منع تغييرات التصميم حسب تفضيلات النظام",
                                checked = settings.pinUI,
                                onCheckedChange = { v -> onUpdate { it.copy(pinUI = v) } }
                            )
                            SettingToggleRow(
                                title = "تأكيد الخروج",
                                subtitle = "إظهار تأكيد قبل إغلاق التطبيق",
                                checked = settings.confirmExit,
                                onCheckedChange = { v -> onUpdate { it.copy(confirmExit = v) } }
                            )
                        }
                    }
                }
            }
        }
    }
}

// 3. Reader Settings Screen (Matching Screenshot 4)
@Composable
fun ReaderSettingsScreen(
    settings: AppSettings,
    onUpdate: ((AppSettings) -> AppSettings) -> Unit,
    onBack: () -> Unit,
    modifier: Modifier = Modifier
) {
    CompositionLocalProvider(LocalLayoutDirection provides LayoutDirection.Rtl) {
        Column(modifier = modifier.fillMaxSize()) {
            SettingsTopBar(
                title = "إعدادات القارئ",
                subtitle = "خصص تجربة القراءة كما تحب",
                onBackClick = onBack
            )

            LazyColumn(
                contentPadding = PaddingValues(horizontal = 16.dp, vertical = 6.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp),
                modifier = Modifier
                    .fillMaxSize()
                    .navigationBarsPadding()
            ) {
                // Default Mode
                item {
                    NeonCard(modifier = Modifier.fillMaxWidth()) {
                        Column {
                            Text(
                                text = "الوضع الافتراضي",
                                fontFamily = CairoFontFamily,
                                fontWeight = FontWeight.Bold,
                                fontSize = 14.sp,
                                color = TextWhite
                            )
                            Text(
                                text = "اختر طريقة عرض الصفحات لقراءتك اليومية",
                                fontFamily = CairoFontFamily,
                                fontSize = 11.sp,
                                color = TextMuted
                            )

                            Spacer(modifier = Modifier.height(10.dp))

                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .clip(RoundedCornerShape(12.dp))
                                    .background(Color(0xFF0F1E40))
                                    .padding(4.dp),
                                horizontalArrangement = Arrangement.SpaceEvenly
                            ) {
                                listOf("صفحات مزدوجة", "مستمر", "ويبتون", "أساسي").forEach { mode ->
                                    val isSelected = settings.readerMode == mode
                                    Box(
                                        modifier = Modifier
                                            .weight(1f)
                                            .clip(RoundedCornerShape(8.dp))
                                            .background(
                                                if (isSelected) Brush.horizontalGradient(
                                                    listOf(Color(0xFF1E3A8A), Color(0xFF2563EB))
                                                ) else SolidColor(Color.Transparent)
                                            )
                                            .border(
                                                if (isSelected) 1.dp else 0.dp,
                                                if (isSelected) NeonCyan else Color.Transparent,
                                                RoundedCornerShape(8.dp)
                                            )
                                            .clickable { onUpdate { it.copy(readerMode = mode) } }
                                            .padding(vertical = 8.dp),
                                        contentAlignment = Alignment.Center
                                    ) {
                                        Text(
                                            text = mode,
                                            fontFamily = CairoFontFamily,
                                            fontSize = 11.sp,
                                            fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Medium,
                                            color = if (isSelected) TextWhite else TextMuted
                                        )
                                    }
                                }
                            }
                        }
                    }
                }

                // Scale Mode
                item {
                    NeonCard(modifier = Modifier.fillMaxWidth()) {
                        Column {
                            Text(
                                text = "وضع التكبير",
                                fontFamily = CairoFontFamily,
                                fontWeight = FontWeight.Bold,
                                fontSize = 14.sp,
                                color = TextWhite
                            )
                            Text(
                                text = "اختر طريقة التكبير الافتراضية للصور",
                                fontFamily = CairoFontFamily,
                                fontSize = 11.sp,
                                color = TextMuted
                            )

                            Spacer(modifier = Modifier.height(10.dp))

                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .clip(RoundedCornerShape(12.dp))
                                    .background(Color(0xFF0F1E40))
                                    .padding(4.dp),
                                horizontalArrangement = Arrangement.SpaceEvenly
                            ) {
                                listOf("مناسب للمركز", "ذكي", "مناسب للعرض").forEach { mode ->
                                    val isSelected = settings.scaleMode == mode
                                    Box(
                                        modifier = Modifier
                                            .weight(1f)
                                            .clip(RoundedCornerShape(8.dp))
                                            .background(
                                                if (isSelected) Brush.horizontalGradient(
                                                    listOf(Color(0xFF1E3A8A), Color(0xFF2563EB))
                                                ) else SolidColor(Color.Transparent)
                                            )
                                            .border(
                                                if (isSelected) 1.dp else 0.dp,
                                                if (isSelected) NeonCyan else Color.Transparent,
                                                RoundedCornerShape(8.dp)
                                            )
                                            .clickable { onUpdate { it.copy(scaleMode = mode) } }
                                            .padding(vertical = 8.dp),
                                        contentAlignment = Alignment.Center
                                    ) {
                                        Text(
                                            text = mode,
                                            fontFamily = CairoFontFamily,
                                            fontSize = 11.sp,
                                            fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Medium,
                                            color = if (isSelected) TextWhite else TextMuted
                                        )
                                    }
                                }
                            }

                            Spacer(modifier = Modifier.height(12.dp))

                            SettingToggleRow(
                                title = "التكبير بالضغط المزدوج",
                                subtitle = "السماح بالتكبير عند الضغط مرتين على الصفحة",
                                checked = settings.doubleTapZoom,
                                onCheckedChange = { v -> onUpdate { it.copy(doubleTapZoom = v) } }
                            )
                            SettingToggleRow(
                                title = "تكبير وضع الويبتون",
                                subtitle = "تحسين التكبير للصور الطويلة في وضع الويبتون",
                                checked = settings.webtoonZoom,
                                onCheckedChange = { v -> onUpdate { it.copy(webtoonZoom = v) } }
                            )
                        }
                    }
                }

                // Controls
                item {
                    NeonCard(modifier = Modifier.fillMaxWidth()) {
                        Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
                            Text(
                                text = "أدوات التحكم أثناء القراءة",
                                fontFamily = CairoFontFamily,
                                fontWeight = FontWeight.Bold,
                                fontSize = 14.sp,
                                color = TextWhite
                            )
                            SettingToggleRow(
                                title = "التحكم بالإيماءات في التبديل",
                                subtitle = "استخدم إيماءات السحب للتنقل بين الفصول",
                                checked = settings.gestureControls,
                                onCheckedChange = { v -> onUpdate { it.copy(gestureControls = v) } }
                            )
                            SettingToggleRow(
                                title = "أزرار الصوت للتنقل بين الصفحات",
                                subtitle = "استخدم أزرار رفع وخفض الصوت لتغيير الصفحة",
                                checked = settings.volumeKeysNav,
                                onCheckedChange = { v -> onUpdate { it.copy(volumeKeysNav = v) } }
                            )
                            SettingToggleRow(
                                title = "الاتجاه الافتراضي: من اليمين لليسار",
                                subtitle = "اختر اتجاه السحب للتنقل بين الفصول",
                                checked = settings.readingDirectionRtl,
                                onCheckedChange = { v -> onUpdate { it.copy(readingDirectionRtl = v) } }
                            )
                        }
                    }
                }

                // Page & Screen Appearance
                item {
                    NeonCard(modifier = Modifier.fillMaxWidth()) {
                        Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
                            Text(
                                text = "مظهر الصفحة والشاشة",
                                fontFamily = CairoFontFamily,
                                fontWeight = FontWeight.Bold,
                                fontSize = 14.sp,
                                color = TextWhite
                            )
                            SettingToggleRow(
                                title = "وضع الألوان 32-bit",
                                subtitle = "يعرض ملايين الألوان للأداء الأفضل",
                                checked = settings.color32Bit,
                                onCheckedChange = { v -> onUpdate { it.copy(color32Bit = v) } }
                            )
                            SettingToggleRow(
                                title = "وضع ملء الشاشة",
                                subtitle = "إخفاء شريط الحالة وأشرطة التنقل",
                                checked = settings.fullScreenMode,
                                onCheckedChange = { v -> onUpdate { it.copy(fullScreenMode = v) } }
                            )
                            SettingToggleRow(
                                title = "إبقاء الشاشة قيد التشغيل",
                                subtitle = "منع إيقاف الشاشة أثناء القراءة",
                                checked = settings.keepScreenOn,
                                onCheckedChange = { v -> onUpdate { it.copy(keepScreenOn = v) } }
                            )
                        }
                    }
                }
            }
        }
    }
}

// 4. Storage & Network Settings Screen (Matching Screenshot 6)
@Composable
fun StorageNetworkSettingsScreen(
    onBack: () -> Unit,
    modifier: Modifier = Modifier
) {
    CompositionLocalProvider(LocalLayoutDirection provides LayoutDirection.Rtl) {
        Column(modifier = modifier.fillMaxSize()) {
            SettingsTopBar(
                title = "التخزين والشبكة",
                subtitle = "Storage and network",
                onBackClick = onBack
            )

            LazyColumn(
                contentPadding = PaddingValues(horizontal = 16.dp, vertical = 6.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp),
                modifier = Modifier
                    .fillMaxSize()
                    .navigationBarsPadding()
            ) {
                // Storage Bar Card
                item {
                    NeonCard(modifier = Modifier.fillMaxWidth()) {
                        Column {
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Row(verticalAlignment = Alignment.CenterVertically) {
                                    Icon(
                                        imageVector = Icons.Outlined.Storage,
                                        contentDescription = null,
                                        tint = NeonCyan,
                                        modifier = Modifier.size(20.dp)
                                    )
                                    Spacer(modifier = Modifier.width(8.dp))
                                    Column {
                                        Text(
                                            text = "استهلاك التخزين",
                                            fontFamily = CairoFontFamily,
                                            fontWeight = FontWeight.Bold,
                                            fontSize = 14.sp,
                                            color = TextWhite
                                        )
                                        Text(
                                            text = "Storage usage",
                                            fontFamily = CairoFontFamily,
                                            fontSize = 10.sp,
                                            color = TextMuted
                                        )
                                    }
                                }

                                Text(
                                    text = "36.9 GB متوفر من 37.5 GB",
                                    fontFamily = CairoFontFamily,
                                    fontSize = 12.sp,
                                    color = NeonCyan,
                                    fontWeight = FontWeight.Bold
                                )
                            }

                            Spacer(modifier = Modifier.height(12.dp))

                            // Multi-segment Neon Progress Bar
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(10.dp)
                                    .clip(RoundedCornerShape(5.dp))
                                    .background(Color(0xFF1E293B))
                            ) {
                                Box(
                                    modifier = Modifier
                                        .weight(0.05f)
                                        .height(10.dp)
                                        .background(NeonGreen)
                                )
                                Box(
                                    modifier = Modifier
                                        .weight(0.95f)
                                        .height(10.dp)
                                        .background(Color(0xFF0F1E40))
                                )
                            }

                            Spacer(modifier = Modifier.height(12.dp))

                            // Storage breakdown chips
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween
                            ) {
                                StorageBreakdownChip(label = "المانجا المحفوظة", value = "0 B", dotColor = NeonBlue)
                                StorageBreakdownChip(label = "تخزين الصفحات", value = "0 B", dotColor = NeonPink)
                                StorageBreakdownChip(label = "بيانات أخرى", value = "5.9 MB", dotColor = NeonGreen)
                                StorageBreakdownChip(label = "متوفر", value = "36.9 GB", dotColor = Color.Gray)
                            }
                        }
                    }
                }

                // Data removal section
                item {
                    NeonCard(modifier = Modifier.fillMaxWidth()) {
                        Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
                            Text(
                                text = "إدارة البيانات",
                                fontFamily = CairoFontFamily,
                                fontWeight = FontWeight.Bold,
                                fontSize = 14.sp,
                                color = TextWhite
                            )
                            SettingNavRow(
                                title = "إعادة تحميل المحتوى",
                                subtitle = "أبداً",
                                icon = Icons.Outlined.Refresh,
                                onClick = { }
                            )
                            SettingNavRow(
                                title = "إعادة تحميل الصفحات",
                                subtitle = "فقط على Wi-Fi",
                                icon = Icons.Outlined.Wifi,
                                onClick = { }
                            )
                        }
                    }
                }

                // Network & Connection
                item {
                    NeonCard(modifier = Modifier.fillMaxWidth()) {
                        Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
                            Text(
                                text = "الشبكة والاتصال",
                                fontFamily = CairoFontFamily,
                                fontWeight = FontWeight.Bold,
                                fontSize = 14.sp,
                                color = TextWhite
                            )
                            SettingNavRow(
                                title = "الوكيل",
                                subtitle = "معطل",
                                icon = Icons.Outlined.Storage,
                                onClick = { }
                            )
                            SettingNavRow(
                                title = "DNS مع HTTPS",
                                subtitle = "معطل",
                                icon = Icons.Outlined.Security,
                                onClick = { }
                            )
                        }
                    }
                }

                // Advanced Settings
                item {
                    NeonCard(modifier = Modifier.fillMaxWidth()) {
                        Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
                            Text(
                                text = "إعدادات متقدمة",
                                fontFamily = CairoFontFamily,
                                fontWeight = FontWeight.Bold,
                                fontSize = 14.sp,
                                color = TextWhite
                            )
                            SettingToggleRow(
                                title = "وكيل تحسين الصور",
                                subtitle = "لا شيء",
                                checked = false,
                                onCheckedChange = { }
                            )
                            SettingToggleRow(
                                title = "تجاهل أخطاء SSL",
                                subtitle = "يمكنك تعطيل التحقق في حالة مواجهة مشاكل متعلقة بـ SSL",
                                checked = false,
                                onCheckedChange = { }
                            )
                            SettingToggleRow(
                                title = "Block ads in browser",
                                subtitle = "Block advertisement in the built-in browser",
                                checked = true,
                                onCheckedChange = { }
                            )
                        }
                    }
                }
            }
        }
    }
}

// 5. Downloads Settings Screen (Matching Screenshot 5)
@Composable
fun DownloadsSettingsScreen(
    onBack: () -> Unit,
    modifier: Modifier = Modifier
) {
    CompositionLocalProvider(LocalLayoutDirection provides LayoutDirection.Rtl) {
        Column(modifier = modifier.fillMaxSize()) {
            SettingsTopBar(
                title = "التحميلات",
                subtitle = "إدارة كل ما يخص تنزيل المانجا على جهازك",
                onBackClick = onBack
            )

            LazyColumn(
                contentPadding = PaddingValues(horizontal = 16.dp, vertical = 6.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp),
                modifier = Modifier
                    .fillMaxSize()
                    .navigationBarsPadding()
            ) {
                item {
                    SettingNavRow(
                        title = "مجلدات المانجا المحلية",
                        subtitle = "إدارة المجلدات التي تحتوي على مانجا في جهازك",
                        icon = Icons.Outlined.Folder,
                        onClick = { }
                    )
                }
                item {
                    SettingNavRow(
                        title = "مجلد التحميلات",
                        subtitle = "/Internal storage/Downloads",
                        icon = Icons.Outlined.Folder,
                        onClick = { }
                    )
                }
                item {
                    SettingNavRow(
                        title = "تنسيق التحميل المفضل",
                        subtitle = "اختيار صيغة حفظ الفصول بشكل افتراضي (CBZ/ZIP)",
                        icon = Icons.Outlined.Download,
                        onClick = { }
                    )
                }
                item {
                    NeonCard(modifier = Modifier.fillMaxWidth()) {
                        SettingToggleRow(
                            title = "التنزيل عبر شبكة الجوال",
                            subtitle = "السماح بالتنزيل عند استخدام بيانات الجوال",
                            checked = true,
                            onCheckedChange = { }
                        )
                    }
                }
                item {
                    NeonCard(modifier = Modifier.fillMaxWidth()) {
                        SettingToggleRow(
                            title = "تعطيل 'استهلاك أقل للبطارية'",
                            subtitle = "قد يساعد في بدء التنزيل إذا كان لديك مشاكل معه",
                            checked = false,
                            onCheckedChange = { }
                        )
                    }
                }
                item {
                    SettingNavRow(
                        title = "حفظ الصفحات",
                        subtitle = "مجلد حفظ الصفحة الافتراضية",
                        icon = Icons.Outlined.Folder,
                        onClick = { }
                    )
                }
                item {
                    NeonCard(modifier = Modifier.fillMaxWidth()) {
                        SettingToggleRow(
                            title = "اطلب وجهة المجلد في كل مرة",
                            subtitle = "السؤال عن مكان الحفظ في كل عملية تنزيل",
                            checked = false,
                            onCheckedChange = { }
                        )
                    }
                }
            }
        }
    }
}

// 6. Backup & Restore Screen (Matching Screenshot 8)
@Composable
fun BackupRestoreScreen(
    onBack: () -> Unit,
    modifier: Modifier = Modifier
) {
    CompositionLocalProvider(LocalLayoutDirection provides LayoutDirection.Rtl) {
        Column(modifier = modifier.fillMaxSize()) {
            SettingsTopBar(
                title = "النسخ الاحتياطي و الاستعادة",
                subtitle = "حماية ونقل بياناتك بأمان",
                onBackClick = onBack
            )

            LazyColumn(
                contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp),
                verticalArrangement = Arrangement.spacedBy(14.dp),
                modifier = Modifier
                    .fillMaxSize()
                    .navigationBarsPadding()
            ) {
                item {
                    SettingNavRow(
                        title = "إنشاء نسخة احتياطية",
                        subtitle = "يمكنك إنشاء نسخة احتياطية من السجل الخاص بك والمفضلة واستعادتها لاحقاً.",
                        icon = Icons.Outlined.CloudUpload,
                        onClick = { }
                    )
                }
                item {
                    SettingNavRow(
                        title = "استعادة من نسخة احتياطية",
                        subtitle = "استعادة النسخة الاحتياطية التي تم إنشاؤها مسبقاً إلى هذا الجهاز.",
                        icon = Icons.Outlined.CloudDownload,
                        onClick = { }
                    )
                }
                item {
                    SettingNavRow(
                        title = "النسخ الاحتياطي الدوري",
                        subtitle = "تفعيل النسخ الاحتياطي التلقائي لحماية بياناتك بشكل مستمر.",
                        icon = Icons.Outlined.History,
                        onClick = { }
                    )
                }
                item {
                    NeonCard(
                        modifier = Modifier.fillMaxWidth(),
                        glowColor = NeonCyan.copy(alpha = 0.5f)
                    ) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            verticalAlignment = Alignment.Top
                        ) {
                            Icon(
                                imageVector = Icons.Outlined.Info,
                                contentDescription = null,
                                tint = NeonCyan,
                                modifier = Modifier.size(24.dp)
                            )
                            Spacer(modifier = Modifier.width(12.dp))
                            Column {
                                Text(
                                    text = "معلومة",
                                    fontFamily = CairoFontFamily,
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 15.sp,
                                    color = TextWhite
                                )
                                Spacer(modifier = Modifier.height(4.dp))
                                Text(
                                    text = "تشمل النسخة الاحتياطية السجل، المفضلة، الإعدادات وجميع بياناتك الهامة في التطبيق بصيغة مشفرة وآمنة.",
                                    fontFamily = CairoFontFamily,
                                    fontSize = 12.sp,
                                    color = TextMuted,
                                    lineHeight = 18.sp
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun SettingToggleRow(
    title: String,
    subtitle: String,
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onCheckedChange(!checked) }
            .padding(vertical = 4.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(modifier = Modifier.weight(1f)) {
            Text(
                text = title,
                fontFamily = CairoFontFamily,
                fontWeight = FontWeight.Medium,
                fontSize = 13.sp,
                color = TextWhite
            )
            if (subtitle.isNotEmpty()) {
                Text(
                    text = subtitle,
                    fontFamily = CairoFontFamily,
                    fontSize = 11.sp,
                    color = TextMuted
                )
            }
        }

        Spacer(modifier = Modifier.width(12.dp))

        NeonSwitch(
            checked = checked,
            onCheckedChange = onCheckedChange
        )
    }
}

@Composable
fun SettingNavRow(
    title: String,
    subtitle: String,
    icon: ImageVector,
    onClick: () -> Unit
) {
    NeonCard(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick),
        cornerRadius = 16.dp,
        glowColor = NeonCyan.copy(alpha = 0.4f)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.weight(1f)
            ) {
                Box(
                    modifier = Modifier
                        .size(42.dp)
                        .clip(RoundedCornerShape(12.dp))
                        .background(Color(0xFF1E3A8A))
                        .border(1.dp, NeonCyan, RoundedCornerShape(12.dp)),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = icon,
                        contentDescription = null,
                        tint = TextWhite,
                        modifier = Modifier.size(22.dp)
                    )
                }

                Spacer(modifier = Modifier.width(12.dp))

                Column {
                    Text(
                        text = title,
                        fontFamily = CairoFontFamily,
                        fontWeight = FontWeight.Bold,
                        fontSize = 14.sp,
                        color = TextWhite
                    )
                    Text(
                        text = subtitle,
                        fontFamily = CairoFontFamily,
                        fontSize = 11.sp,
                        color = TextMuted,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                }
            }

            Box(
                modifier = Modifier
                    .size(28.dp)
                    .clip(CircleShape)
                    .background(Color(0x300A1838)),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = null,
                    tint = TextMuted,
                    modifier = Modifier.size(16.dp)
                )
            }
        }
    }
}

@Composable
fun StorageBreakdownChip(
    label: String,
    value: String,
    dotColor: Color
) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Box(
                modifier = Modifier
                    .size(8.dp)
                    .clip(CircleShape)
                    .background(dotColor)
            )
            Spacer(modifier = Modifier.width(4.dp))
            Text(
                text = value,
                fontFamily = CairoFontFamily,
                fontWeight = FontWeight.Bold,
                fontSize = 11.sp,
                color = TextWhite
            )
        }
        Text(
            text = label,
            fontFamily = CairoFontFamily,
            fontSize = 9.sp,
            color = TextMuted
        )
    }
}
