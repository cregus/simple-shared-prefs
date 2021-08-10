# Simple SharedPrefs [![](https://jitpack.io/v/cregus/simple-shared-prefs.svg)](https://jitpack.io/#cregus/simple-shared-prefs)
Android library written in Kotlin to simplify use of SharedPreferences.

## Setup
### Add the JitPack repository to your build file
```gradle
allprojects {
  repositories {
    maven { url 'https://jitpack.io' }
  }
}
```

### Add library dependency to your project build file
```gradle
dependencies {
  implementation 'com.github.cregus:simple-shared-prefs:1.0.2'
}
```

## Sample usage
### Define prefs class and some properties using provided delegates
```kotlin
class AppPrefs(sharedPreferences: SharedPreferences) : SharedPrefs(sharedPreferences) {
    var isAuthorized by booleanPreference()
    var userName by stringPreference()
}
```

### Use it
```kotlin
val appPrefs = AppPrefs(PreferenceManager.getDefaultSharedPreferences(context))

if (appPrefs.isAuthorized) {
  appPrefs.userName = "John Doe"
}
```

### Config
You can use all data types that can be stored in shared preferences. Default values and delegates are:
Type | Value | Delegate
-----|-------|---------
`Boolean` | `false` | `booleanPreference()`
`Int` | `0` | `intPreference()`
`Long` | `0L` | `longPreference()`
`Float` | `0f` | `floatPreference()`
`String` | `null` | `stringPreference()`
`Set<String>` | `null` | `stringSetPreference()`

You can change default value by passing it to delegate method:
```kotlin
var isDarkTheme by booleanPreference(defaultValue = true)
```

`String` and `Set<String>` are nullable types until you provide default value, for example:
```kotlin
var userName: String? by stringPreference()
var themeColor: String by stringPreference(defaultValue = "#F44336")
```

You can also change preference key (it's property name by default) and switch from apply to commit save method:
```kotlin
var authToken by stringPreference(commit = true, key = "auth_token")
```

If you need to store other types of data you need to provide mapper:
```kotlin
object DateMapper : PreferenceMapper<Date, Long> {
    override fun fromStored(stored: Long): Date {
        return Date(stored)
    }

    override fun toStored(value: Date): Long {
        return value.time
    }
}
```

And now it can be stored using mapped version of delegate:
```kotlin
var lastUpdateCheck by mappedLongPreference(mapper = DateMapper, defaultValue = Date())
```