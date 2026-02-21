# VersionPB Buzzer

A minimal, high-performance buzzer application for single-device interaction.

## Features
- **Instant Response**: Minimal latency between tap and feedback.
- **Haptic Feedback**: Strong vibration pulse on every buzz.
- **Visual Confirmation**: Clear "BUZZED!" status indicator.
- **Dark Mode Aesthetic**: Eye-friendly dark background for focus.

## Version History

### v1.1.0 (2026-02-21)
- **Extreme Simplification**: Scrapped MVVM and multi-screen logic for a streamlined single-activity experience.
- **Performance Optimization**: Removed all unnecessary background management and lifecycle observers.
- **Compatibility Fix**: Switched to system fonts (sans-serif-black) to ensure 100% reliable layout inflation across all Android devices.
- **Critical Fix**: Resolved SplashActivity theme conflict and resource naming issues.

### v1.0.0 (Initial Implementation)
- Original MVP with multi-player lobby, score tracking, and custom managers. (Now deprecated in favor of v1.1.0 simplicity).

## Technical Details
- **Architecture**: Single Activity (MainActivity).
- **Minimum SDK**: 21 (Android 5.0).
- **Target SDK**: 34 (Android 14).
- **Language**: Java.
- **UI Framework**: XML Layouts with Material Design components.

## Installation
Run `.\gradlew.bat assembleDebug` and install the generated APK from `app/build/outputs/apk/debug/app-debug.apk`.
