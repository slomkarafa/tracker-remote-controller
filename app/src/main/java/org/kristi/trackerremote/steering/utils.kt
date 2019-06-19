package org.kristi.trackerremote.steering

import kotlin.math.PI
import kotlin.math.cos
import kotlin.math.sin

fun toRadians(degrees: Int) = degrees.toDouble()/180* PI


fun circleToDrives(angle:Int, power: Int): SteeringModel {
    val radAngle = toRadians(angle)
    val ratio = cos(radAngle)
    val _sin = sin(radAngle)


    val multiplier = (power * if (_sin<0) -1 else 1).toDouble()

    val drives = (if(ratio>0) arrayOf(1.0-ratio,1.0) else arrayOf(1.0,1.0+ratio)).map { (it*multiplier).toInt() }

    return SteeringModel(drives[0],drives[1])
}

