package pl.kremblewski.android.simplesharedprefs

import android.content.SharedPreferences

sealed class Storage<T> {
    abstract fun read(sharedPreferences: SharedPreferences, key: String, defaultValue: T): T
    abstract fun write(editor: SharedPreferences.Editor, key: String, value: T)

    object BooleanStorage : Storage<Boolean>() {
        override fun read(
            sharedPreferences: SharedPreferences,
            key: String,
            defaultValue: Boolean
        ): Boolean {
            return sharedPreferences.getBoolean(key, defaultValue)
        }

        override fun write(editor: SharedPreferences.Editor, key: String, value: Boolean) {
            editor.putBoolean(key, value)
        }
    }

    object IntStorage : Storage<Int>() {
        override fun read(
            sharedPreferences: SharedPreferences,
            key: String,
            defaultValue: Int
        ): Int {
            return sharedPreferences.getInt(key, defaultValue)
        }

        override fun write(editor: SharedPreferences.Editor, key: String, value: Int) {
            editor.putInt(key, value)
        }
    }

    object LongStorage : Storage<Long>() {
        override fun read(
            sharedPreferences: SharedPreferences,
            key: String,
            defaultValue: Long
        ): Long {
            return sharedPreferences.getLong(key, defaultValue)
        }

        override fun write(editor: SharedPreferences.Editor, key: String, value: Long) {
            editor.putLong(key, value)
        }
    }

    object FloatStorage : Storage<Float>() {
        override fun read(
            sharedPreferences: SharedPreferences,
            key: String,
            defaultValue: Float
        ): Float {
            return sharedPreferences.getFloat(key, defaultValue)
        }

        override fun write(editor: SharedPreferences.Editor, key: String, value: Float) {
            editor.putFloat(key, value)
        }
    }

    object StringStorage : Storage<String?>() {
        override fun read(
            sharedPreferences: SharedPreferences,
            key: String,
            defaultValue: String?
        ): String? {
            return sharedPreferences.getString(key, defaultValue)
        }

        override fun write(editor: SharedPreferences.Editor, key: String, value: String?) {
            editor.putString(key, value)
        }
    }

    object StringSetStorage : Storage<Set<String>?>() {
        override fun read(
            sharedPreferences: SharedPreferences,
            key: String,
            defaultValue: Set<String>?
        ): Set<String>? {
            return sharedPreferences.getStringSet(key, defaultValue)
        }

        override fun write(editor: SharedPreferences.Editor, key: String, value: Set<String>?) {
            editor.putStringSet(key, value)
        }
    }
}
