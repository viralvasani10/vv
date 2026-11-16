# Ochre Heart Keyboard

Android starter keyboard for Samsung and other Android devices.

- Custom keyboard service (Kotlin / InputMethodService)
- Champagne ochre key styling (#B49A70)
- Olive key styling (#87916A)
- Basic QWERTY rows and heart shortcuts
- No internet permission; no typed-text collection code

## Build
Open in Android Studio, sync Gradle, then Build > Build APK(s).
APK output: app/build/outputs/apk/debug/app-debug.apk

## Enable on Samsung
Settings > General management > Keyboard list and default > enable Ochre Heart Keyboard.
Then choose Default keyboard > Ochre Heart Keyboard.

## Honest limitations
The ochre and olive heart keys are coloured key labels, not custom Unicode emoji. When tapped, the keyboard commits a heart text glyph; the receiving app controls how that glyph is rendered. Native Instagram captions cannot accept an arbitrary custom-coloured inline image from a keyboard. A future sticker feature can share transparent PNG assets to Instagram Stories where Instagram's sticker workflow supports them.
