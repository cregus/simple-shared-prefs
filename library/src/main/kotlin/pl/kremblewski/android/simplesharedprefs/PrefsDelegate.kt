package pl.kremblewski.android.simplesharedprefs

import android.content.SharedPreferences
import androidx.core.content.edit
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

abstract class PrefsDelegate<T>(
    private val commit: Boolean,
    private val key: String?,
    private val defaultValue: T
) : ReadWriteProperty<SharedPrefs, T> {
    abstract fun SharedPreferences.getValue(key: String, defaultValue: T): T
    abstract fun SharedPreferences.Editor.setValue(key: String, value: T)

    final override fun getValue(thisRef: SharedPrefs, property: KProperty<*>): T {
        return thisRef.sharedPreferences.getValue(key ?: property.name, defaultValue)
    }

    final override fun setValue(thisRef: SharedPrefs, property: KProperty<*>, value: T) {
        thisRef.sharedPreferences.edit(commit) { setValue(key ?: property.name, value) }
    }
}