# Version History - VersionPB Buzzer

All notable changes to this project will be documented in this file.

## [1.0.0] - 2026-02-21
### Added
- **Natural Audio Looping**: Restored single `MediaPlayer` with `setLooping(true)` for seamless full-sound playback.
- **Orange Button Animation**: Added a smooth `ValueAnimator` that fades the BUZZ button from Red to Orange on touch.
- **Custom Toolbar**: Replaced standard ActionBar with a custom Toolbar.
- **Right-Aligned Versioning**: Version number ("V 1.0") is now elegantly displayed on the right side of the Toolbar.
- **Indigo Styling**: Restored the premium Indigo theme background to the title bar.
- **High-Precision Debug Timer**: Real-time hold duration display (0.00s) added to the main screen.
- **Config Management**: Created `Config.java` as a central location for the version number.

### Changed
- Improved touch responsiveness using `OnTouchListener`.
- Refined vibration patterns for better tactile feedback.

---

## [0.2.0] - Pre-Release Debugging
- Experimented with `SoundPool` overlap strategies.
- Implemented a temporary 15ms rapid restart cycle for audio testing.
- Integrated dual-player offset loops for gapless investigation.

## [0.1.0] - Initial MVP
- Basic buzzer functionality with Red button and simple vibration.
- Deprecated multi-player lobby and score tracking in favor of extreme simplicity.
