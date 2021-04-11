package pl.kremblewski.android.simplesharedprefs

interface PreferenceMapper<Value, Stored> {
    fun fromStored(stored: Stored): Value
    fun toStored(value: Value): Stored
}