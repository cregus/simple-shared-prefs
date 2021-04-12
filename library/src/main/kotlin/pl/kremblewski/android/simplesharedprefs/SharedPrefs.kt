package pl.kremblewski.android.simplesharedprefs

import android.content.SharedPreferences
import kotlin.properties.ReadWriteProperty

abstract class SharedPrefs(internal val sharedPreferences: SharedPreferences) {
    fun booleanPreference(
        commit: Boolean = false,
        key: String? = null,
        defaultValue: Boolean = false
    ): ReadWriteProperty<SharedPrefs, Boolean> {
        return object : PrefsDelegate<Boolean>(commit, key, defaultValue) {
            override fun SharedPreferences.getValue(key: String, defaultValue: Boolean): Boolean {
                return getBoolean(key, defaultValue)
            }

            override fun SharedPreferences.Editor.setValue(key: String, value: Boolean) {
                putBoolean(key, value)
            }
        }
    }

    fun <T> mappedBooleanPreference(
        commit: Boolean = false,
        key: String? = null,
        defaultValue: T,
        mapper: PreferenceMapper<T, Boolean>
    ): ReadWriteProperty<SharedPrefs, T> {
        return object : PrefsDelegate<T>(commit, key, defaultValue) {
            override fun SharedPreferences.getValue(key: String, defaultValue: T): T {
                return mapper.fromStored(getBoolean(key, mapper.toStored(defaultValue)))
            }

            override fun SharedPreferences.Editor.setValue(key: String, value: T) {
                putBoolean(key, mapper.toStored(value))
            }
        }
    }

    fun intPreference(
        commit: Boolean = false,
        key: String? = null,
        defaultValue: Int = 0
    ): ReadWriteProperty<SharedPrefs, Int> {
        return object : PrefsDelegate<Int>(commit, key, defaultValue) {
            override fun SharedPreferences.getValue(key: String, defaultValue: Int): Int {
                return getInt(key, defaultValue)
            }

            override fun SharedPreferences.Editor.setValue(key: String, value: Int) {
                putInt(key, value)
            }
        }
    }

    fun <T> mappedIntPreference(
        commit: Boolean = false,
        key: String? = null,
        defaultValue: T,
        mapper: PreferenceMapper<T, Int>
    ): ReadWriteProperty<SharedPrefs, T> {
        return object : PrefsDelegate<T>(commit, key, defaultValue) {
            override fun SharedPreferences.getValue(key: String, defaultValue: T): T {
                return mapper.fromStored(getInt(key, mapper.toStored(defaultValue)))
            }

            override fun SharedPreferences.Editor.setValue(key: String, value: T) {
                putInt(key, mapper.toStored(value))
            }
        }
    }

    fun longPreference(
        commit: Boolean = false,
        key: String? = null,
        defaultValue: Long = 0L
    ): ReadWriteProperty<SharedPrefs, Long> {
        return object : PrefsDelegate<Long>(commit, key, defaultValue) {
            override fun SharedPreferences.getValue(key: String, defaultValue: Long): Long {
                return getLong(key, defaultValue)
            }

            override fun SharedPreferences.Editor.setValue(key: String, value: Long) {
                putLong(key, value)
            }
        }
    }

    fun <T> mappedLongPreference(
        commit: Boolean = false,
        key: String? = null,
        defaultValue: T,
        mapper: PreferenceMapper<T, Long>
    ): ReadWriteProperty<SharedPrefs, T> {
        return object : PrefsDelegate<T>(commit, key, defaultValue) {
            override fun SharedPreferences.getValue(key: String, defaultValue: T): T {
                return mapper.fromStored(getLong(key, mapper.toStored(defaultValue)))
            }

            override fun SharedPreferences.Editor.setValue(key: String, value: T) {
                putLong(key, mapper.toStored(value))
            }
        }
    }

    fun floatPreference(
        commit: Boolean = false,
        key: String? = null,
        defaultValue: Float = 0f
    ): ReadWriteProperty<SharedPrefs, Float> {
        return object : PrefsDelegate<Float>(commit, key, defaultValue) {
            override fun SharedPreferences.getValue(key: String, defaultValue: Float): Float {
                return getFloat(key, defaultValue)
            }

            override fun SharedPreferences.Editor.setValue(key: String, value: Float) {
                putFloat(key, value)
            }
        }
    }

    fun <T> mappedFloatPreference(
        commit: Boolean = false,
        key: String? = null,
        defaultValue: T,
        mapper: PreferenceMapper<T, Float>
    ): ReadWriteProperty<SharedPrefs, T> {
        return object : PrefsDelegate<T>(commit, key, defaultValue) {
            override fun SharedPreferences.getValue(key: String, defaultValue: T): T {
                return mapper.fromStored(getFloat(key, mapper.toStored(defaultValue)))
            }

            override fun SharedPreferences.Editor.setValue(key: String, value: T) {
                putFloat(key, mapper.toStored(value))
            }
        }
    }

    fun stringPreference(
        commit: Boolean = false,
        key: String? = null
    ): ReadWriteProperty<SharedPrefs, String?> {
        return object : PrefsDelegate<String?>(commit, key, null) {
            override fun SharedPreferences.getValue(key: String, defaultValue: String?): String? {
                return getString(key, defaultValue)
            }

            override fun SharedPreferences.Editor.setValue(key: String, value: String?) {
                putString(key, value)
            }
        }
    }

    fun <T> mappedStringPreference(
        commit: Boolean = false,
        key: String? = null,
        mapper: PreferenceMapper<T?, String?>
    ): ReadWriteProperty<SharedPrefs, T?> {
        return object : PrefsDelegate<T?>(commit, key, null) {
            override fun SharedPreferences.getValue(key: String, defaultValue: T?): T? {
                val mappedDefaultValue = defaultValue?.let(mapper::toStored)
                return getString(key, mappedDefaultValue)?.let(mapper::fromStored)
            }

            override fun SharedPreferences.Editor.setValue(key: String, value: T?) {
                putString(key, value?.let(mapper::toStored))
            }
        }
    }

    fun stringPreference(
        commit: Boolean = false,
        key: String? = null,
        defaultValue: String
    ): ReadWriteProperty<SharedPrefs, String> {
        return object : PrefsDelegate<String>(commit, key, defaultValue) {
            override fun SharedPreferences.getValue(key: String, defaultValue: String): String {
                return getString(key, null) ?: defaultValue
            }

            override fun SharedPreferences.Editor.setValue(key: String, value: String) {
                putString(key, value)
            }
        }
    }

    fun <T> mappedStringPreference(
        commit: Boolean = false,
        key: String? = null,
        defaultValue: T,
        mapper: PreferenceMapper<T, String>
    ): ReadWriteProperty<SharedPrefs, T> {
        return object : PrefsDelegate<T>(commit, key, defaultValue) {
            override fun SharedPreferences.getValue(key: String, defaultValue: T): T {
                val mappedDefaultValue = defaultValue?.let(mapper::toStored)
                val storedValue = getString(key, mappedDefaultValue)
                return storedValue?.let(mapper::fromStored) ?: defaultValue
            }

            override fun SharedPreferences.Editor.setValue(key: String, value: T) {
                putString(key, mapper.toStored(value))
            }
        }
    }

    fun stringSetPreference(
        commit: Boolean = false,
        key: String? = null
    ): ReadWriteProperty<SharedPrefs, Set<String>?> {
        return object : PrefsDelegate<Set<String>?>(commit, key, null) {
            override fun SharedPreferences.getValue(
                key: String,
                defaultValue: Set<String>?
            ): Set<String>? {
                return getStringSet(key, defaultValue)
            }

            override fun SharedPreferences.Editor.setValue(key: String, value: Set<String>?) {
                putStringSet(key, value)
            }
        }
    }

    fun <T> mappedStringSetPreference(
        commit: Boolean = false,
        key: String? = null,
        mapper: PreferenceMapper<T?, Set<String>?>
    ): ReadWriteProperty<SharedPrefs, T?> {
        return object : PrefsDelegate<T?>(commit, key, null) {
            override fun SharedPreferences.getValue(key: String, defaultValue: T?): T? {
                val mappedDefaultValue = defaultValue?.let(mapper::toStored)
                return getStringSet(key, mappedDefaultValue)?.let(mapper::fromStored)
            }

            override fun SharedPreferences.Editor.setValue(key: String, value: T?) {
                putStringSet(key, value?.let(mapper::toStored))
            }
        }
    }

    fun stringSetPreference(
        commit: Boolean = false,
        key: String? = null,
        defaultValue: Set<String>
    ): ReadWriteProperty<SharedPrefs, Set<String>> {
        return object : PrefsDelegate<Set<String>>(commit, key, defaultValue) {
            override fun SharedPreferences.getValue(
                key: String,
                defaultValue: Set<String>
            ): Set<String> {
                return getStringSet(key, null) ?: defaultValue
            }

            override fun SharedPreferences.Editor.setValue(key: String, value: Set<String>) {
                putStringSet(key, value)
            }
        }
    }

    fun <T> mappedStringSetPreference(
        commit: Boolean = false,
        key: String? = null,
        defaultValue: T,
        mapper: PreferenceMapper<T, Set<String>>
    ): ReadWriteProperty<SharedPrefs, T> {
        return object : PrefsDelegate<T>(commit, key, defaultValue) {
            override fun SharedPreferences.getValue(key: String, defaultValue: T): T {
                val mappedDefaultValue = defaultValue?.let(mapper::toStored)
                val storedValue = getStringSet(key, mappedDefaultValue)
                return storedValue?.let(mapper::fromStored) ?: defaultValue
            }

            override fun SharedPreferences.Editor.setValue(key: String, value: T) {
                putStringSet(key, mapper.toStored(value))
            }
        }
    }
}