# VersionPB Buzzer

A minimal, high-performance buzzer application for single-device interaction.

## Features
- **Instant Response**: Minimal latency between tap and audio feedback.
- **Natural Looping**: High-quality buzzer sound that loops seamlessly.
- **Orange Tap Animation**: Smooth color transition on the BUZZ button for tactile visual feedback.
- **Haptic Feedback**: Strong, continuous vibration pulse while the button is held.
- **Right-Aligned Versioning**: Official app version ("V 1.0") displayed elegantly in the top bar.
- **Premium Indigo Theme**: Professional dark aesthetic with Deep Indigo accents.
- **Debug Precision**: On-screen hold-timer for exact duration measurement.

## Version History
See [VERSION_HISTORY.md](file:///c:/Drive%20D/Paarth/Code/Buzzer/Buzzer/VERSION_HISTORY.md) for a full list of changes.

## Technical Details
- **Architecture**: Single Activity (MainActivity).
- **Core Engine**: `MediaPlayer` with `setLooping(true)`.
- **Animation**: `ValueAnimator` with `ArgbEvaluator`.
- **Styling**: `Material3` with custom `Toolbar`.
- **Minimum SDK**: 21 (Android 5.0).
- **Target SDK**: 34 (Android 14).
- **Language**: Java.

## Installation
Run `.\gradlew.bat assembleDebug` and install the generated APK from `app/build/outputs/apk/debug/app-debug.apk`.
