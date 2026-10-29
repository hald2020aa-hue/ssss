# SSS - قارئ المانجا النيون العصري (SSS Manga Reader)

![Android](https://img.shields.io/badge/Platform-Android-green.svg)
![Kotlin](https://img.shields.io/badge/Language-Kotlin-blue.svg)
![Jetpack Compose](https://img.shields.io/badge/UI-Jetpack%20Compose%20%7C%20Material%203-purple.svg)
![Style](https://img.shields.io/badge/Theme-Cyberpunk%20Neon%20Dark-cyan.svg)

تطبيق **SSS** هو نسخة احترافية فائقة التطوير ومخصصة بالكامل لقراءة المانجا والمانهوا والويبتون، مستوحى من بنية Kotatsu مع إعادة تصميم جذرية لجميع الواجهات بالكامل بنمط **النيون المتوهج العصري (Cyberpunk Neon Dark Mode)** وتجربة مستخدم عربية انسيابية.

---

## 🌟 الميزات الرئيسية

- **تصميم نيون مستقبلي متكامل (Neon Dark UI)**:
  - خلفيات فضائية داكنة كحلية مع حواف نيون متوهجة (`#00F0FF` و `#2979FF` و `#8B5CF6`).
  - حاويات زجاجية (`Glassmorphism Cards`) وتأثيرات إضاءة ديناميكية.
  - دعم 6 مخططات ألوان نيون قابلة للتبديل من الإعدادات (ديناميكية، داكن أزرق، أخضر، أمبري، وردي، بنفسجي).
  - خط عربي عصري أنيق ومثبت محلياً (**Cairo**).

- **الواجهات المتطابقة مع التصاميم**:
  - **المفضلة (Favorites)**: فلاتر نيون (كل، المفضلة، اقرأ لاحقاً)، رسمة القط النيون يقرأ كتاباً مضيئاً، وعرض شبكي وقائمي.
  - **السجل (History)**: شريط بحث، قائمة خيارات علوية (محو السجل، الوضع الخفي، خيارات العرض)، رسمة القط النيون، وأشرطة تقدم القراءة.
  - **استكشف (Explore)**: أزرار وصول سريعة (التخزين المحلي، عشوائي، المحفوظات، التحميلات)، وشبكة 16+ مصدراً للمانجا بأيقونات نيون مخصصة.
  - **الموجز (Feed)**: فصول وتحديثات جديدة لحظية.
  - **قائمة خيارات العرض السفلية (Bottom Sheet)**: تعديل وضع العرض (شبكة، تفاصيل، مدمج) والتحكم في حجم الشبكة.
  - **لوحة الإعدادات الشاملة**:
    - مظهر (Appearance).
    - مصادر المانجا (Manga Sources).
    - إعدادات القارئ (Reader Settings).
    - التخزين والشبكة (Storage and Network).
    - التحميلات (Downloads).
    - النسخ الاحتياطي والاستعادة (Backup and Restore).

- **قارئ المانجا التفاعلي وميزة الترجمة الفورية (AI OCR & Translation)**:
  - قارئ ملء الشاشة مع التحكم بالإيماءات، تكبير، سطوع، والتنقل بين الصفحات.
  - زر **ترجمة OCR الذكية**: يحلل فقاعات الحوار ويترجم النصوص الأجنبية (اليابانية/الإنجليزية/الكورية) إلى اللغة العربية مباشرة داخل فقاعات الحوار!

---

## 🚀 كيفية رفع المشروع إلى GitHub والبدء في البناء

### 1. عبر واجهة AI Studio
يمكنك مباشرة من خلال زر الإعدادات / التصدير في الشريط العلوي الضغط على:
- **Push to GitHub**: لربط حساب GitHub الخاص بك وإنشاء مستودع جديد ورفع الكود بضغطة زر واحدة.
- **Export as ZIP**: لتحميل المشروع كاملاً وفك ضغطه على جهازك.

### 2. عبر سطر الأوامر (Git Terminal)
إذا قمت بتنزيل المشروع أو فتح الطرفية:

```bash
# 1. تهيئة المستودع
git init

# 2. إضافة جميع الملفات
git add .

# 3. حفظ الالتزام الأول
git commit -m "Initial commit: SSS Manga Reader - Neon Edition"

# 4. ربط مستودع GitHub الخاص بك (استبدل الرابط برابط مستودعك)
git remote add origin https://github.com/YOUR_USERNAME/SSS-Manga-Reader.git

# 5. رفع الكود
git branch -M main
git push -u origin main
```

---

## ⚙️ البناء الآلي لملف APK عبر GitHub Actions

المشروع مزود بملف سير عمل جاهز تلقائياً داخل:
`.github/workflows/android-build.yml`

بمجرد رفع الكود إلى GitHub:
1. ستعمل أداة **GitHub Actions** تلقائياً في تبويب **Actions**.
2. ستقوم بتهيئة بيئة Java 17 و Gradle وبناء تطبيق الأندرويد.
3. ستجد ملف الـ APK الجاهز للتثبيت متاحاً للتحميل مباشرة تحت اسم **`SSS-Manga-Reader-APK`**.

---

## 💻 التشغيل على Android Studio محلياً

1. افتح **Android Studio**.
2. اختر **Open an Existing Project** وحدد مجلد المشروع.
3. انتظر حتى يكتمل مزامنة الـ Gradle (Gradle Sync).
4. اضغط على زر **Run ▶** لتشغيل التطبيق على محاكي أو جهازك الحقيقي.
5. لبناء ملف APK يدوياً:
   ```bash
   ./gradlew assembleDebug
   ```
   سيكون الملف الناتج في:
   `app/build/outputs/apk/debug/app-debug.apk`

---

## 🛠 البنية التقنية (Tech Stack)
- **Kotlin 2.2**
- **Jetpack Compose & Material 3**
- **Android Gradle Plugin 9.x**
- **Kotlin Coroutines & StateFlow**
- **Coil Compose** (تحميل الصور)
- **Navigation Compose**
- **Google Fonts Local Cairo Bundle**
