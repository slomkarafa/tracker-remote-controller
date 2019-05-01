package org.kristi.trackerremote.steering

import android.util.Log
import okhttp3.WebSocket
import org.kristi.trackerremote.network.NetworkService



class ControllerService(
    serverUrl: String
) : Steering {

    private var ws: WebSocket = NetworkService().create(3, serverUrl)

    override fun ride(angle: Int, power: Int) {
        Log.d("WSS", "sending msg")
        val request = """{
            "angle":$angle,
            "power":$power
        }"""
        ws.send(request)
    }

    override fun stop() {
        ws.send("stop")

    }

}