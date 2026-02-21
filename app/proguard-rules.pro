# Add project specific ProGuard rules here.
# You can control the set of applied configuration files using the
# proguardFiles setting in build.gradle.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# ── Keep Model Classes ──────────────────────────────────────────────────────
# Prevent obfuscation of POJO/model classes used for data binding or Gson
-keep class com.versionpb.buzzer.model.** { *; }

# ── Keep ViewModels ─────────────────────────────────────────────────────────
-keep class com.versionpb.buzzer.viewmodel.** { *; }

# ── Lifecycle ───────────────────────────────────────────────────────────────
-keep class * extends androidx.lifecycle.ViewModel { *; }
-keep class * extends androidx.lifecycle.AndroidViewModel { *; }

# ── AndroidX ────────────────────────────────────────────────────────────────
-dontwarn androidx.**
-keep class androidx.** { *; }
-keep interface androidx.** { *; }

# ── Material Components ──────────────────────────────────────────────────────
-keep class com.google.android.material.** { *; }
-dontwarn com.google.android.material.**

# ── Suppress warnings for reflection-based classes ──────────────────────────
-dontwarn java.lang.invoke.**
-dontwarn **$$Lambda$*

# ── Remove logging in release builds ────────────────────────────────────────
-assumenosideeffects class android.util.Log {
    public static boolean isLoggable(java.lang.String, int);
    public static int v(...);
    public static int i(...);
    public static int w(...);
    public static int d(...);
    public static int e(...);
}
