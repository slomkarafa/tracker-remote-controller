package org.kristi.trackerremote.steering

import android.util.Log
import com.beust.klaxon.Klaxon
import okhttp3.WebSocket
import org.kristi.trackerremote.network.NetworkService
import kotlin.math.log


class ControllerService(
    private var ws: WebSocket
) : Steering {


    override fun ride(angle: Int, power: Int) {
        Log.d("WSS_angle", angle.toString())
        Log.d("WSS_power", power.toString())
        val request = """{
            "action": "manual",
            "data": ${Klaxon().toJsonString(circleToDrives(angle, power))}
        }"""
        Log.d("WSS_sending", request)

        ws.send(request)
    }

    override fun stop() {
        ws.send("""{"action":"manual","data":"stop"}""")

    }

}