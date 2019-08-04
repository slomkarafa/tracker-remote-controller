package org.kristi.trackerremote.steering


interface Steering {
    fun ride(angle:Int,power:Int)

    fun stop()

    fun setSaving(shouldSaving: Boolean)
}