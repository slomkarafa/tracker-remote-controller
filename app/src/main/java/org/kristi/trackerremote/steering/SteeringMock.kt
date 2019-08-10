package org.kristi.trackerremote.steering

import android.util.Log

class SteeringMock : Steering{
    override fun setSaving(shouldSaving: Boolean) {
        Log.d("STEERING","set saving to $shouldSaving")
    }

    override fun ride(angle: Int, power: Int) {
        Log.d("STEERING","RAAAAAAJD, angle:"+angle.toString()+", strength:"+power.toString())
    }

    override fun stop() {
        Log.d("STEERING","STAPhhh")
    }

}