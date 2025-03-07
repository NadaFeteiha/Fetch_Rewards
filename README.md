# Fetch Rewards

## Description
Fetch Rewards is an Android application that allows users to manage and view rewards.

## Features
- View reward groups
- Expand and collapse reward groups
- Sort rewards

## Structure

```
fetchrewards/
├── data/
│   └── model/
│       └── Reward.kt
├── ui/
│   ├── component/
│   │   ├── AppBar.kt
│   │   ├── ErrorView.kt
│   │   └── Loading.kt
│   ├── home/
│   │   ├── HomeScreen.kt
│   │   ├── HomeUiState.kt
│   │   └── HomeViewModel.kt
│   ├── main/
│   │   └── MainActivity.kt
│   └── theme/
│       ├── Color.kt
│       ├── Theme.kt
│       └── Type.kt
```

## Dependencies
- Kotlin
- Jetpack Compose
- Navigation Component
- Koin
- Kotlinx Serialization
